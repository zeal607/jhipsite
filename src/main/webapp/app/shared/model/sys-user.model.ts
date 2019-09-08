import { Moment } from 'moment';

export const enum GenderType {
  MALE = 'MALE',
  FEMALE = 'FEMALE'
}

export const enum UserType {
  EMPLOYEE = 'EMPLOYEE',
  MEMBER = 'MEMBER'
}

export const enum UserStatusType {
  NORMAL = 'NORMAL',
  DELETE = 'DELETE',
  DISABLE = 'DISABLE',
  FREEZE = 'FREEZE'
}

export interface ISysUser {
  id?: number;
  userCode?: string;
  loginCode?: string;
  userName?: string;
  password?: string;
  email?: string;
  mobile?: string;
  phone?: string;
  sex?: GenderType;
  avatar?: string;
  sign?: string;
  wxOpenid?: string;
  mobileImei?: string;
  userType?: UserType;
  refCode?: string;
  refName?: string;
  lastLoginIp?: string;
  lastLoginDate?: Moment;
  freezeDate?: Moment;
  freezeCause?: string;
  userSort?: number;
  status?: UserStatusType;
  remarks?: string;
}

export const defaultValue: Readonly<ISysUser> = {};
