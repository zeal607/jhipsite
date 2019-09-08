import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ISysUserRole, defaultValue } from 'app/shared/model/sys-user-role.model';

export const ACTION_TYPES = {
  FETCH_SYSUSERROLE_LIST: 'sysUserRole/FETCH_SYSUSERROLE_LIST',
  FETCH_SYSUSERROLE: 'sysUserRole/FETCH_SYSUSERROLE',
  CREATE_SYSUSERROLE: 'sysUserRole/CREATE_SYSUSERROLE',
  UPDATE_SYSUSERROLE: 'sysUserRole/UPDATE_SYSUSERROLE',
  DELETE_SYSUSERROLE: 'sysUserRole/DELETE_SYSUSERROLE',
  RESET: 'sysUserRole/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ISysUserRole>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type SysUserRoleState = Readonly<typeof initialState>;

// Reducer

export default (state: SysUserRoleState = initialState, action): SysUserRoleState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_SYSUSERROLE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_SYSUSERROLE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_SYSUSERROLE):
    case REQUEST(ACTION_TYPES.UPDATE_SYSUSERROLE):
    case REQUEST(ACTION_TYPES.DELETE_SYSUSERROLE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_SYSUSERROLE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_SYSUSERROLE):
    case FAILURE(ACTION_TYPES.CREATE_SYSUSERROLE):
    case FAILURE(ACTION_TYPES.UPDATE_SYSUSERROLE):
    case FAILURE(ACTION_TYPES.DELETE_SYSUSERROLE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_SYSUSERROLE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_SYSUSERROLE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_SYSUSERROLE):
    case SUCCESS(ACTION_TYPES.UPDATE_SYSUSERROLE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_SYSUSERROLE):
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

const apiUrl = 'api/sys-user-roles';

// Actions

export const getEntities: ICrudGetAllAction<ISysUserRole> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_SYSUSERROLE_LIST,
    payload: axios.get<ISysUserRole>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ISysUserRole> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_SYSUSERROLE,
    payload: axios.get<ISysUserRole>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ISysUserRole> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_SYSUSERROLE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ISysUserRole> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_SYSUSERROLE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ISysUserRole> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_SYSUSERROLE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
