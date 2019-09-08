export const enum ControlType {
  COMPANY = 'COMPANY',
  OFFICE = 'OFFICE'
}

export interface ISysUserDataScope {
  id?: number;
  sysUserId?: string;
  ctrlType?: ControlType;
  ctrlData?: string;
  ctrlPermi?: string;
}

export const defaultValue: Readonly<ISysUserDataScope> = {};
