# ~~react-native-huanxin-kefu(弃用)~~

~~~将环信客服集成到 React Native 项目中。目前只实现了 Android 端

## Getting started

`$ npm install react-native-huanxin-kefu --save`

### Mostly automatic installation

`$ react-native link react-native-huanxin-kefu`

#### Android

1.打开 React Native 项目 `android/app/build.gradle`，在 dependencies 内添加依赖

```
dependencies {
    // 环信客服
    implementation 'com.hyphenate:kefu-easeui-android:latest.release'
    implementation 'com.github.bumptech.glide:glide:4.7.1'
    implementation 'com.easemob:kefu-sdk:1.1.9r2'


    implementation fileTree(dir: "libs", include: ["*.jar"])
    implementation "com.facebook.react:react-native:+"  // From node_modules

    if (enableHermes) {
      def hermesPath = "../../node_modules/hermesvm/android/";
      debugImplementation files(hermesPath + "hermes-debug.aar")
      releaseImplementation files(hermesPath + "hermes-release.aar")
    } else {
      implementation jscFlavor
    }
}
```

2. 打开 `android/app/src/main/java/com/your project/MainActivity.java` 在 onCreate 方法下添加初始化代码

```
package com.your project;

import com.facebook.react.ReactActivity;

import android.os.Bundle;
import com.hyphenate.chat.ChatClient;
import com.hyphenate.helpdesk.easeui.UIProvider;

public class MainActivity extends ReactActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ChatClient.Options options = new ChatClient.Options();
        options.setAppkey("环信客服的Appkey");
        options.setTenantId("环信客服的tenantid");
        ChatClient.getInstance().init(this, new ChatClient.Options().setConsoleLog(true));
        if (!ChatClient.getInstance().init(this, options)){
            return;
        }
        UIProvider.getInstance().init(this);
    }
		...
}
```

3. 运行 `react-native run-android`, 打印 `NaviteModules`，发现有`NaviteModules` 则引入成功，否则检查步骤，并进入 `android` 目录，使用 `./gradlew clean` 清理缓存，重新运行 `react-native run-android` 即可

## Usage

```javascript
import RNHuanxinKefu from 'react-native-huanxin-kefu';

const username = 'zakzheng';
const password = '123456';

HuanxinKefu.register(username, password).then(() => {
  HuanxinKefu.login(username, password)
    .then(() => {
      HuanxinKefu.openChat();
    })
    .catch(e => {
      if (e.error === '账号已登录') {
        // 打开聊天页面
        return HuanxinKefu.openChat();
      }
    });
});

// 退出登录
HuanxinKefu.loginout();
```
~~~
