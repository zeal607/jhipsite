import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ISysEmployeePost, defaultValue } from 'app/shared/model/sys-employee-post.model';

export const ACTION_TYPES = {
  FETCH_SYSEMPLOYEEPOST_LIST: 'sysEmployeePost/FETCH_SYSEMPLOYEEPOST_LIST',
  FETCH_SYSEMPLOYEEPOST: 'sysEmployeePost/FETCH_SYSEMPLOYEEPOST',
  CREATE_SYSEMPLOYEEPOST: 'sysEmployeePost/CREATE_SYSEMPLOYEEPOST',
  UPDATE_SYSEMPLOYEEPOST: 'sysEmployeePost/UPDATE_SYSEMPLOYEEPOST',
  DELETE_SYSEMPLOYEEPOST: 'sysEmployeePost/DELETE_SYSEMPLOYEEPOST',
  RESET: 'sysEmployeePost/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ISysEmployeePost>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type SysEmployeePostState = Readonly<typeof initialState>;

// Reducer

export default (state: SysEmployeePostState = initialState, action): SysEmployeePostState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_SYSEMPLOYEEPOST_LIST):
    case REQUEST(ACTION_TYPES.FETCH_SYSEMPLOYEEPOST):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_SYSEMPLOYEEPOST):
    case REQUEST(ACTION_TYPES.UPDATE_SYSEMPLOYEEPOST):
    case REQUEST(ACTION_TYPES.DELETE_SYSEMPLOYEEPOST):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_SYSEMPLOYEEPOST_LIST):
    case FAILURE(ACTION_TYPES.FETCH_SYSEMPLOYEEPOST):
    case FAILURE(ACTION_TYPES.CREATE_SYSEMPLOYEEPOST):
    case FAILURE(ACTION_TYPES.UPDATE_SYSEMPLOYEEPOST):
    case FAILURE(ACTION_TYPES.DELETE_SYSEMPLOYEEPOST):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_SYSEMPLOYEEPOST_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_SYSEMPLOYEEPOST):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_SYSEMPLOYEEPOST):
    case SUCCESS(ACTION_TYPES.UPDATE_SYSEMPLOYEEPOST):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_SYSEMPLOYEEPOST):
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

const apiUrl = 'api/sys-employee-posts';

// Actions

export const getEntities: ICrudGetAllAction<ISysEmployeePost> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_SYSEMPLOYEEPOST_LIST,
    payload: axios.get<ISysEmployeePost>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ISysEmployeePost> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_SYSEMPLOYEEPOST,
    payload: axios.get<ISysEmployeePost>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ISysEmployeePost> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_SYSEMPLOYEEPOST,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ISysEmployeePost> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_SYSEMPLOYEEPOST,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ISysEmployeePost> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_SYSEMPLOYEEPOST,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
