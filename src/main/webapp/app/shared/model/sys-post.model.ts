export const enum PostType {
  SENIOR = 'SENIOR',
  MIDDLE = 'MIDDLE',
  BASIC = 'BASIC'
}

export const enum PostStatusType {
  NORMAL = 'NORMAL',
  DELETE = 'DELETE',
  DISABLE = 'DISABLE'
}

export interface ISysPost {
  id?: number;
  postCode?: string;
  postName?: string;
  postType?: PostType;
  status?: PostStatusType;
  remarks?: string;
}

export const defaultValue: Readonly<ISysPost> = {};
