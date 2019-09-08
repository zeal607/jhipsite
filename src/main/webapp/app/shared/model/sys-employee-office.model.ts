export interface ISysEmployeeOffice {
  id?: number;
  sysEmployeeId?: string;
  sysOfficeId?: string;
  sysPostId?: string;
}

export const defaultValue: Readonly<ISysEmployeeOffice> = {};
