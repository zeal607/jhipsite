export const enum RoleType {
  ORGANIZARION = 'ORGANIZARION',
  USER = 'USER'
}

export const enum DataScopeType {
  NONE = 'NONE',
  ALL = 'ALL',
  CUSTOM = 'CUSTOM'
}

export const enum RoleStatusType {
  NORMAl = 'NORMAl',
  DELETE = 'DELETE',
  DISABLE = 'DISABLE'
}

export interface ISysRole {
  id?: number;
  roleCode?: string;
  roleName?: string;
  roleType?: RoleType;
  roleSort?: number;
  isSys?: boolean;
  dataScope?: DataScopeType;
  bizScope?: string;
  status?: RoleStatusType;
  remarks?: string;
}

export const defaultValue: Readonly<ISysRole> = {
  isSys: false
};
