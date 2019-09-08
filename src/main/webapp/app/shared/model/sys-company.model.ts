export const enum CompanyStatusType {
  NORMAL = 'NORMAL',
  DELETE = 'DELETE',
  DISABLE = 'DISABLE'
}

export interface ISysCompany {
  id?: number;
  companyCode?: string;
  parentCode?: string;
  parentCodes?: string;
  treeSort?: number;
  treeSorts?: number;
  treeLeaf?: boolean;
  treeLevel?: number;
  treeNames?: string;
  viewCode?: string;
  companyName?: string;
  fullName?: string;
  areaCode?: string;
  status?: CompanyStatusType;
  remarks?: string;
}

export const defaultValue: Readonly<ISysCompany> = {
  treeLeaf: false
};
