import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ISysRoleMenu, defaultValue } from 'app/shared/model/sys-role-menu.model';

export const ACTION_TYPES = {
  FETCH_SYSROLEMENU_LIST: 'sysRoleMenu/FETCH_SYSROLEMENU_LIST',
  FETCH_SYSROLEMENU: 'sysRoleMenu/FETCH_SYSROLEMENU',
  CREATE_SYSROLEMENU: 'sysRoleMenu/CREATE_SYSROLEMENU',
  UPDATE_SYSROLEMENU: 'sysRoleMenu/UPDATE_SYSROLEMENU',
  DELETE_SYSROLEMENU: 'sysRoleMenu/DELETE_SYSROLEMENU',
  RESET: 'sysRoleMenu/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ISysRoleMenu>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type SysRoleMenuState = Readonly<typeof initialState>;

// Reducer

export default (state: SysRoleMenuState = initialState, action): SysRoleMenuState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_SYSROLEMENU_LIST):
    case REQUEST(ACTION_TYPES.FETCH_SYSROLEMENU):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_SYSROLEMENU):
    case REQUEST(ACTION_TYPES.UPDATE_SYSROLEMENU):
    case REQUEST(ACTION_TYPES.DELETE_SYSROLEMENU):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_SYSROLEMENU_LIST):
    case FAILURE(ACTION_TYPES.FETCH_SYSROLEMENU):
    case FAILURE(ACTION_TYPES.CREATE_SYSROLEMENU):
    case FAILURE(ACTION_TYPES.UPDATE_SYSROLEMENU):
    case FAILURE(ACTION_TYPES.DELETE_SYSROLEMENU):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_SYSROLEMENU_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_SYSROLEMENU):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_SYSROLEMENU):
    case SUCCESS(ACTION_TYPES.UPDATE_SYSROLEMENU):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_SYSROLEMENU):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/sys-role-menus';

// Actions

export const getEntities: ICrudGetAllAction<ISysRoleMenu> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_SYSROLEMENU_LIST,
    payload: axios.get<ISysRoleMenu>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ISysRoleMenu> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_SYSROLEMENU,
    payload: axios.get<ISysRoleMenu>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ISysRoleMenu> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_SYSROLEMENU,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ISysRoleMenu> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_SYSROLEMENU,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ISysRoleMenu> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_SYSROLEMENU,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
