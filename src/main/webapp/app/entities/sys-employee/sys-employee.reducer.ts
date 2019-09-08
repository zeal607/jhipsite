import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ISysEmployee, defaultValue } from 'app/shared/model/sys-employee.model';

export const ACTION_TYPES = {
  FETCH_SYSEMPLOYEE_LIST: 'sysEmployee/FETCH_SYSEMPLOYEE_LIST',
  FETCH_SYSEMPLOYEE: 'sysEmployee/FETCH_SYSEMPLOYEE',
  CREATE_SYSEMPLOYEE: 'sysEmployee/CREATE_SYSEMPLOYEE',
  UPDATE_SYSEMPLOYEE: 'sysEmployee/UPDATE_SYSEMPLOYEE',
  DELETE_SYSEMPLOYEE: 'sysEmployee/DELETE_SYSEMPLOYEE',
  RESET: 'sysEmployee/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ISysEmployee>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type SysEmployeeState = Readonly<typeof initialState>;

// Reducer

export default (state: SysEmployeeState = initialState, action): SysEmployeeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_SYSEMPLOYEE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_SYSEMPLOYEE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_SYSEMPLOYEE):
    case REQUEST(ACTION_TYPES.UPDATE_SYSEMPLOYEE):
    case REQUEST(ACTION_TYPES.DELETE_SYSEMPLOYEE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_SYSEMPLOYEE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_SYSEMPLOYEE):
    case FAILURE(ACTION_TYPES.CREATE_SYSEMPLOYEE):
    case FAILURE(ACTION_TYPES.UPDATE_SYSEMPLOYEE):
    case FAILURE(ACTION_TYPES.DELETE_SYSEMPLOYEE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_SYSEMPLOYEE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_SYSEMPLOYEE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_SYSEMPLOYEE):
    case SUCCESS(ACTION_TYPES.UPDATE_SYSEMPLOYEE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_SYSEMPLOYEE):
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

const apiUrl = 'api/sys-employees';

// Actions

export const getEntities: ICrudGetAllAction<ISysEmployee> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_SYSEMPLOYEE_LIST,
    payload: axios.get<ISysEmployee>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ISysEmployee> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_SYSEMPLOYEE,
    payload: axios.get<ISysEmployee>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ISysEmployee> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_SYSEMPLOYEE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ISysEmployee> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_SYSEMPLOYEE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ISysEmployee> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_SYSEMPLOYEE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
