export const enum OfficeType {
  NATIONAL = 'NATIONAL',
  PROVINCIAL = 'PROVINCIAL',
  CITY = 'CITY'
}

export const enum OfficeStatusType {
  NORMAL = 'NORMAL',
  DELETE = 'DELETE',
  DISABLE = 'DISABLE'
}

export interface ISysOffice {
  id?: number;
  officeCode?: string;
  parentCode?: string;
  parentCodes?: string;
  treeSort?: number;
  treeSorts?: number;
  treeLeaf?: boolean;
  treeLevel?: number;
  treeNames?: string;
  viewCode?: string;
  officeName?: string;
  fullName?: string;
  officeType?: OfficeType;
  leader?: string;
  phone?: string;
  address?: string;
  zipCode?: string;
  email?: string;
  remarks?: string;
  status?: OfficeStatusType;
}

export const defaultValue: Readonly<ISysOffice> = {
  treeLeaf: false
};
