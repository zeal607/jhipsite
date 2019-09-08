import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ISysRoleDataScope, defaultValue } from 'app/shared/model/sys-role-data-scope.model';

export const ACTION_TYPES = {
  FETCH_SYSROLEDATASCOPE_LIST: 'sysRoleDataScope/FETCH_SYSROLEDATASCOPE_LIST',
  FETCH_SYSROLEDATASCOPE: 'sysRoleDataScope/FETCH_SYSROLEDATASCOPE',
  CREATE_SYSROLEDATASCOPE: 'sysRoleDataScope/CREATE_SYSROLEDATASCOPE',
  UPDATE_SYSROLEDATASCOPE: 'sysRoleDataScope/UPDATE_SYSROLEDATASCOPE',
  DELETE_SYSROLEDATASCOPE: 'sysRoleDataScope/DELETE_SYSROLEDATASCOPE',
  RESET: 'sysRoleDataScope/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ISysRoleDataScope>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type SysRoleDataScopeState = Readonly<typeof initialState>;

// Reducer

export default (state: SysRoleDataScopeState = initialState, action): SysRoleDataScopeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_SYSROLEDATASCOPE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_SYSROLEDATASCOPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_SYSROLEDATASCOPE):
    case REQUEST(ACTION_TYPES.UPDATE_SYSROLEDATASCOPE):
    case REQUEST(ACTION_TYPES.DELETE_SYSROLEDATASCOPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_SYSROLEDATASCOPE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_SYSROLEDATASCOPE):
    case FAILURE(ACTION_TYPES.CREATE_SYSROLEDATASCOPE):
    case FAILURE(ACTION_TYPES.UPDATE_SYSROLEDATASCOPE):
    case FAILURE(ACTION_TYPES.DELETE_SYSROLEDATASCOPE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_SYSROLEDATASCOPE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_SYSROLEDATASCOPE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_SYSROLEDATASCOPE):
    case SUCCESS(ACTION_TYPES.UPDATE_SYSROLEDATASCOPE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_SYSROLEDATASCOPE):
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

const apiUrl = 'api/sys-role-data-scopes';

// Actions

export const getEntities: ICrudGetAllAction<ISysRoleDataScope> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_SYSROLEDATASCOPE_LIST,
    payload: axios.get<ISysRoleDataScope>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ISysRoleDataScope> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_SYSROLEDATASCOPE,
    payload: axios.get<ISysRoleDataScope>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ISysRoleDataScope> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_SYSROLEDATASCOPE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ISysRoleDataScope> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_SYSROLEDATASCOPE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ISysRoleDataScope> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_SYSROLEDATASCOPE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
