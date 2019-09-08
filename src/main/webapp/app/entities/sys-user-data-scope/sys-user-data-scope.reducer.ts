import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ISysUserDataScope, defaultValue } from 'app/shared/model/sys-user-data-scope.model';

export const ACTION_TYPES = {
  FETCH_SYSUSERDATASCOPE_LIST: 'sysUserDataScope/FETCH_SYSUSERDATASCOPE_LIST',
  FETCH_SYSUSERDATASCOPE: 'sysUserDataScope/FETCH_SYSUSERDATASCOPE',
  CREATE_SYSUSERDATASCOPE: 'sysUserDataScope/CREATE_SYSUSERDATASCOPE',
  UPDATE_SYSUSERDATASCOPE: 'sysUserDataScope/UPDATE_SYSUSERDATASCOPE',
  DELETE_SYSUSERDATASCOPE: 'sysUserDataScope/DELETE_SYSUSERDATASCOPE',
  RESET: 'sysUserDataScope/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ISysUserDataScope>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type SysUserDataScopeState = Readonly<typeof initialState>;

// Reducer

export default (state: SysUserDataScopeState = initialState, action): SysUserDataScopeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_SYSUSERDATASCOPE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_SYSUSERDATASCOPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_SYSUSERDATASCOPE):
    case REQUEST(ACTION_TYPES.UPDATE_SYSUSERDATASCOPE):
    case REQUEST(ACTION_TYPES.DELETE_SYSUSERDATASCOPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_SYSUSERDATASCOPE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_SYSUSERDATASCOPE):
    case FAILURE(ACTION_TYPES.CREATE_SYSUSERDATASCOPE):
    case FAILURE(ACTION_TYPES.UPDATE_SYSUSERDATASCOPE):
    case FAILURE(ACTION_TYPES.DELETE_SYSUSERDATASCOPE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_SYSUSERDATASCOPE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_SYSUSERDATASCOPE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_SYSUSERDATASCOPE):
    case SUCCESS(ACTION_TYPES.UPDATE_SYSUSERDATASCOPE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_SYSUSERDATASCOPE):
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

const apiUrl = 'api/sys-user-data-scopes';

// Actions

export const getEntities: ICrudGetAllAction<ISysUserDataScope> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_SYSUSERDATASCOPE_LIST,
    payload: axios.get<ISysUserDataScope>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ISysUserDataScope> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_SYSUSERDATASCOPE,
    payload: axios.get<ISysUserDataScope>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ISysUserDataScope> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_SYSUSERDATASCOPE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ISysUserDataScope> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_SYSUSERDATASCOPE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ISysUserDataScope> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_SYSUSERDATASCOPE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
