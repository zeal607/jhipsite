import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ISysMenu, defaultValue } from 'app/shared/model/sys-menu.model';

export const ACTION_TYPES = {
  FETCH_SYSMENU_LIST: 'sysMenu/FETCH_SYSMENU_LIST',
  FETCH_SYSMENU: 'sysMenu/FETCH_SYSMENU',
  CREATE_SYSMENU: 'sysMenu/CREATE_SYSMENU',
  UPDATE_SYSMENU: 'sysMenu/UPDATE_SYSMENU',
  DELETE_SYSMENU: 'sysMenu/DELETE_SYSMENU',
  RESET: 'sysMenu/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ISysMenu>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type SysMenuState = Readonly<typeof initialState>;

// Reducer

export default (state: SysMenuState = initialState, action): SysMenuState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_SYSMENU_LIST):
    case REQUEST(ACTION_TYPES.FETCH_SYSMENU):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_SYSMENU):
    case REQUEST(ACTION_TYPES.UPDATE_SYSMENU):
    case REQUEST(ACTION_TYPES.DELETE_SYSMENU):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_SYSMENU_LIST):
    case FAILURE(ACTION_TYPES.FETCH_SYSMENU):
    case FAILURE(ACTION_TYPES.CREATE_SYSMENU):
    case FAILURE(ACTION_TYPES.UPDATE_SYSMENU):
    case FAILURE(ACTION_TYPES.DELETE_SYSMENU):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_SYSMENU_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_SYSMENU):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_SYSMENU):
    case SUCCESS(ACTION_TYPES.UPDATE_SYSMENU):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_SYSMENU):
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

const apiUrl = 'api/sys-menus';

// Actions

export const getEntities: ICrudGetAllAction<ISysMenu> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_SYSMENU_LIST,
    payload: axios.get<ISysMenu>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ISysMenu> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_SYSMENU,
    payload: axios.get<ISysMenu>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ISysMenu> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_SYSMENU,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ISysMenu> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_SYSMENU,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ISysMenu> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_SYSMENU,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
