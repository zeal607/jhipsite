import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ISysRole, defaultValue } from 'app/shared/model/sys-role.model';

export const ACTION_TYPES = {
  FETCH_SYSROLE_LIST: 'sysRole/FETCH_SYSROLE_LIST',
  FETCH_SYSROLE: 'sysRole/FETCH_SYSROLE',
  CREATE_SYSROLE: 'sysRole/CREATE_SYSROLE',
  UPDATE_SYSROLE: 'sysRole/UPDATE_SYSROLE',
  DELETE_SYSROLE: 'sysRole/DELETE_SYSROLE',
  RESET: 'sysRole/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ISysRole>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type SysRoleState = Readonly<typeof initialState>;

// Reducer

export default (state: SysRoleState = initialState, action): SysRoleState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_SYSROLE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_SYSROLE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_SYSROLE):
    case REQUEST(ACTION_TYPES.UPDATE_SYSROLE):
    case REQUEST(ACTION_TYPES.DELETE_SYSROLE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_SYSROLE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_SYSROLE):
    case FAILURE(ACTION_TYPES.CREATE_SYSROLE):
    case FAILURE(ACTION_TYPES.UPDATE_SYSROLE):
    case FAILURE(ACTION_TYPES.DELETE_SYSROLE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_SYSROLE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_SYSROLE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_SYSROLE):
    case SUCCESS(ACTION_TYPES.UPDATE_SYSROLE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_SYSROLE):
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

const apiUrl = 'api/sys-roles';

// Actions

export const getEntities: ICrudGetAllAction<ISysRole> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_SYSROLE_LIST,
    payload: axios.get<ISysRole>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ISysRole> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_SYSROLE,
    payload: axios.get<ISysRole>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ISysRole> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_SYSROLE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ISysRole> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_SYSROLE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ISysRole> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_SYSROLE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
