import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ISysPost, defaultValue } from 'app/shared/model/sys-post.model';

export const ACTION_TYPES = {
  FETCH_SYSPOST_LIST: 'sysPost/FETCH_SYSPOST_LIST',
  FETCH_SYSPOST: 'sysPost/FETCH_SYSPOST',
  CREATE_SYSPOST: 'sysPost/CREATE_SYSPOST',
  UPDATE_SYSPOST: 'sysPost/UPDATE_SYSPOST',
  DELETE_SYSPOST: 'sysPost/DELETE_SYSPOST',
  RESET: 'sysPost/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ISysPost>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type SysPostState = Readonly<typeof initialState>;

// Reducer

export default (state: SysPostState = initialState, action): SysPostState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_SYSPOST_LIST):
    case REQUEST(ACTION_TYPES.FETCH_SYSPOST):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_SYSPOST):
    case REQUEST(ACTION_TYPES.UPDATE_SYSPOST):
    case REQUEST(ACTION_TYPES.DELETE_SYSPOST):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_SYSPOST_LIST):
    case FAILURE(ACTION_TYPES.FETCH_SYSPOST):
    case FAILURE(ACTION_TYPES.CREATE_SYSPOST):
    case FAILURE(ACTION_TYPES.UPDATE_SYSPOST):
    case FAILURE(ACTION_TYPES.DELETE_SYSPOST):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_SYSPOST_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_SYSPOST):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_SYSPOST):
    case SUCCESS(ACTION_TYPES.UPDATE_SYSPOST):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_SYSPOST):
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

const apiUrl = 'api/sys-posts';

// Actions

export const getEntities: ICrudGetAllAction<ISysPost> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_SYSPOST_LIST,
    payload: axios.get<ISysPost>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ISysPost> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_SYSPOST,
    payload: axios.get<ISysPost>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ISysPost> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_SYSPOST,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ISysPost> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_SYSPOST,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ISysPost> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_SYSPOST,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
