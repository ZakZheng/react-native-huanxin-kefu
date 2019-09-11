// 参考 http://www.easemob.com/apidoc/android/chat3.0/classcom_1_1hyphenate_1_1_e_m_error.html
export enum errorCodeEnum {
  GENERAL_ERROR = 1,
  NETWORK_ERROR = 2,
}

export enum registerErrorCode {
  // 网络错误
  NETWORK_ERROR = 2,
  // 无开放注册权限（后台管理界面设置[开放|授权]）
  USER_AUTHENTICATION_FAILED = 202,
  /** 用户名已存在 */
  USER_ALREADY_EXIST = 203,
  // 用户名非法
  USER_ILLEGAL_ARGUMENT = 205
}

export interface Error {
  error: string
  errorCode: errorCodeEnum
}

declare namespace HuanxinKefu {

  function register(username: string, password: string): Promise<string | {
    error: string
    errorCode: registerErrorCode
  }>;

  function login(username: string, password: string): Promise<string | Error>;

  function openChat(): Promise<string | Error>;

  function loginout(): Promise<string | Error>;
}

export = HuanxinKefu;