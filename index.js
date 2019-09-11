import { NativeModules } from 'react-native';

const { RNHuanxinKefu } = NativeModules;

export default class HuanxinKefu {
  static register(username, password) {
    return RNHuanxinKefu.register(username, password).then(res => {
      if (res.error) {
        return Promise.reject(res);
      }
      return res;
    });
  }
  static login(username, password) {
    return RNHuanxinKefu.login(username, password).then(res => {
      if (res.error) {
        return Promise.reject(res);
      }
      return res;
    });
  }
  static openChat() {
    return RNHuanxinKefu.openChat().then(res => {
      if (res.error) {
        return Promise.reject(res);
      }
      return res;
    });
  }
  static loginout() {
    return RNHuanxinKefu.loginout().then(res => {
      if (res.error) {
        return Promise.reject(res);
      }
      return res;
    });
  }
}
