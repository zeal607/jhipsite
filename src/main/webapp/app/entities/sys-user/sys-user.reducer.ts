import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ISysUser, defaultValue } from 'app/shared/model/sys-user.model';

export const ACTION_TYPES = {
  FETCH_SYSUSER_LIST: 'sysUser/FETCH_SYSUSER_LIST',
  FETCH_SYSUSER: 'sysUser/FETCH_SYSUSER',
  CREATE_SYSUSER: 'sysUser/CREATE_SYSUSER',
  UPDATE_SYSUSER: 'sysUser/UPDATE_SYSUSER',
  DELETE_SYSUSER: 'sysUser/DELETE_SYSUSER',
  RESET: 'sysUser/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ISysUser>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type SysUserState = Readonly<typeof initialState>;

// Reducer

export default (state: SysUserState = initialState, action): SysUserState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_SYSUSER_LIST):
    case REQUEST(ACTION_TYPES.FETCH_SYSUSER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_SYSUSER):
    case REQUEST(ACTION_TYPES.UPDATE_SYSUSER):
    case REQUEST(ACTION_TYPES.DELETE_SYSUSER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_SYSUSER_LIST):
    case FAILURE(ACTION_TYPES.FETCH_SYSUSER):
    case FAILURE(ACTION_TYPES.CREATE_SYSUSER):
    case FAILURE(ACTION_TYPES.UPDATE_SYSUSER):
    case FAILURE(ACTION_TYPES.DELETE_SYSUSER):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_SYSUSER_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_SYSUSER):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_SYSUSER):
    case SUCCESS(ACTION_TYPES.UPDATE_SYSUSER):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_SYSUSER):
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

const apiUrl = 'api/sys-users';

// Actions

export const getEntities: ICrudGetAllAction<ISysUser> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_SYSUSER_LIST,
    payload: axios.get<ISysUser>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ISysUser> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_SYSUSER,
    payload: axios.get<ISysUser>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ISysUser> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_SYSUSER,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ISysUser> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_SYSUSER,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ISysUser> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_SYSUSER,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
