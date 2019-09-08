import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ISysCompany, defaultValue } from 'app/shared/model/sys-company.model';

export const ACTION_TYPES = {
  FETCH_SYSCOMPANY_LIST: 'sysCompany/FETCH_SYSCOMPANY_LIST',
  FETCH_SYSCOMPANY: 'sysCompany/FETCH_SYSCOMPANY',
  CREATE_SYSCOMPANY: 'sysCompany/CREATE_SYSCOMPANY',
  UPDATE_SYSCOMPANY: 'sysCompany/UPDATE_SYSCOMPANY',
  DELETE_SYSCOMPANY: 'sysCompany/DELETE_SYSCOMPANY',
  RESET: 'sysCompany/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ISysCompany>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type SysCompanyState = Readonly<typeof initialState>;

// Reducer

export default (state: SysCompanyState = initialState, action): SysCompanyState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_SYSCOMPANY_LIST):
    case REQUEST(ACTION_TYPES.FETCH_SYSCOMPANY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_SYSCOMPANY):
    case REQUEST(ACTION_TYPES.UPDATE_SYSCOMPANY):
    case REQUEST(ACTION_TYPES.DELETE_SYSCOMPANY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_SYSCOMPANY_LIST):
    case FAILURE(ACTION_TYPES.FETCH_SYSCOMPANY):
    case FAILURE(ACTION_TYPES.CREATE_SYSCOMPANY):
    case FAILURE(ACTION_TYPES.UPDATE_SYSCOMPANY):
    case FAILURE(ACTION_TYPES.DELETE_SYSCOMPANY):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_SYSCOMPANY_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_SYSCOMPANY):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_SYSCOMPANY):
    case SUCCESS(ACTION_TYPES.UPDATE_SYSCOMPANY):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_SYSCOMPANY):
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

const apiUrl = 'api/sys-companies';

// Actions

export const getEntities: ICrudGetAllAction<ISysCompany> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_SYSCOMPANY_LIST,
    payload: axios.get<ISysCompany>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ISysCompany> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_SYSCOMPANY,
    payload: axios.get<ISysCompany>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ISysCompany> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_SYSCOMPANY,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ISysCompany> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_SYSCOMPANY,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ISysCompany> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_SYSCOMPANY,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
