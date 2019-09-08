export const enum EmployeeStatusType {
  NORMAL = 'NORMAL',
  DELETE = 'DELETE',
  DEPARTUTE = 'DEPARTUTE'
}

export interface ISysEmployee {
  id?: number;
  empCode?: string;
  empName?: string;
  empNameEn?: string;
  sysOfficeId?: string;
  officeName?: string;
  sysCompanyId?: string;
  companyName?: string;
  status?: EmployeeStatusType;
  remarks?: string;
}

export const defaultValue: Readonly<ISysEmployee> = {};
