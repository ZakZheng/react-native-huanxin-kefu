
package com.zakzheng.huanxin.kefu;


import android.app.Activity;
import android.content.Intent;

// React Native
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;

// 环信SDK
import com.hyphenate.chat.ChatClient;
import com.hyphenate.chat.Conversation;
import com.hyphenate.helpdesk.Error;
import com.hyphenate.helpdesk.callback.Callback;
import com.hyphenate.helpdesk.easeui.util.IntentBuilder;

public class RNHuanxinKefuModule extends ReactContextBaseJavaModule {
    private final ReactApplicationContext reactContext;

    private boolean _checkLoginStatus() {
        return ChatClient.getInstance().isLoggedInBefore();
    }

    private void toChatActivity() {
        Activity currentActivity = getCurrentActivity();
        Intent intent = new IntentBuilder(currentActivity).setTitleName("客服").setServiceIMNumber("kefuchannelimid_000502").build();
        currentActivity.startActivity(intent);
    }
    public RNHuanxinKefuModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }


    @Override
    public String getName() {
        return "RNHuanxinKefu";
    }

    /**
     * 检查登录状态
     */
    @ReactMethod
    public void checkLoginStatus(Promise promise) {
        if (this._checkLoginStatus()) {
            promise.resolve("已登录");
        } else {
            WritableMap map = Arguments.createMap();
            map.putString("error", "未登录");
            promise.resolve(map);
        }
    }

    @ReactMethod
    public void login(final String username, final String password, final Promise promise) {
        if (this._checkLoginStatus()) {
            //已经登录
            WritableMap map = Arguments.createMap();
            map.putString("error", "账号已登录");
            promise.resolve(map);
        } else {
            ChatClient.getInstance().login(username, password, new Callback() {
                @Override
                public void onSuccess() {
                    promise.resolve("登录成功");
                }

                @Override
                public void onError(final int errorCode, String error) {
                    WritableMap map = Arguments.createMap();
                    map.putInt("errorCode", errorCode);
                    map.putString("error", error);
                    promise.resolve(map);
                }

                @Override
                public void onProgress(int progress, String status) {

                }
            });
        }
    }

    @ReactMethod
    public void loginout(final Promise promise) {
        if (this._checkLoginStatus() == true) {
            ChatClient.getInstance().logout(true, new Callback() {
                @Override
                public void onSuccess() {
                    promise.resolve("退出成功");
                }

                @Override
                public void onError(int errorCode, String error) {
                    WritableMap map = Arguments.createMap();
                    map.putInt("errorCode", errorCode);
                    map.putString("error", error);
                    promise.resolve(map);
                }

                @Override
                public void onProgress(int progress, String status) {

                }
            });
        } else {
            WritableMap map = Arguments.createMap();
            map.putString("error", "未登录账号");
            promise.resolve(map);
        }
    }

    @ReactMethod
    public void register(String username, String password, final Promise promise) {
        ChatClient.getInstance().register(username, password, new Callback() {
            @Override
            public void onSuccess() {
                promise.resolve("注册成功");
            }

            @Override
            public void onError(final int errorCode, String error) {
                WritableMap map = Arguments.createMap();
                map.putInt("errorCode", errorCode);
                map.putString("error", error);
                promise.resolve(map);
            }

            @Override
            public void onProgress(int progress, String status) {
            }
        });
    }

    // 打开聊天页面
    @ReactMethod
    public void openChat(Promise promise) {
        if (ChatClient.getInstance().isLoggedInBefore()) {
            try {
                toChatActivity();
                promise.resolve("打开成功");
            } catch (Exception e) {
                WritableMap map = Arguments.createMap();
                map.putString("error", e.getMessage());
                promise.resolve(map);
            }
        } else {
            WritableMap map = Arguments.createMap();
            map.putString("error", "未登录账号");
            promise.resolve(map);
        }
    }

}