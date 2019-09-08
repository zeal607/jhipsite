export const enum ControlType {
  COMPANY = 'COMPANY',
  OFFICE = 'OFFICE'
}

export interface ISysRoleDataScope {
  id?: number;
  sysRoleId?: string;
  ctrlType?: ControlType;
  ctrlData?: string;
  ctrlPermi?: string;
}

export const defaultValue: Readonly<ISysRoleDataScope> = {};
