export const enum MenuType {
  MENU = 'MENU',
  PERMISSION = 'PERMISSION',
  DEV = 'DEV'
}

export const enum SysType {
  WEB = 'WEB',
  MOBILE = 'MOBILE'
}

export const enum MenuStatusType {
  NORMAL = 'NORMAL',
  DELETE = 'DELETE',
  DISABLE = 'DISABLE'
}

export interface ISysMenu {
  id?: number;
  menuCode?: string;
  parentCode?: string;
  parentCodes?: string;
  treeSort?: number;
  treeSorts?: number;
  treeLeaf?: boolean;
  treeLevel?: number;
  treeNames?: string;
  menuName?: string;
  menuType?: MenuType;
  menuHref?: string;
  menuIcon?: string;
  menuTitle?: string;
  permission?: string;
  menuSort?: number;
  isShow?: boolean;
  sysCode?: SysType;
  status?: MenuStatusType;
  remarks?: string;
}

export const defaultValue: Readonly<ISysMenu> = {
  treeLeaf: false,
  isShow: false
};
