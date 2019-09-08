import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ISysOffice, defaultValue } from 'app/shared/model/sys-office.model';

export const ACTION_TYPES = {
  FETCH_SYSOFFICE_LIST: 'sysOffice/FETCH_SYSOFFICE_LIST',
  FETCH_SYSOFFICE: 'sysOffice/FETCH_SYSOFFICE',
  CREATE_SYSOFFICE: 'sysOffice/CREATE_SYSOFFICE',
  UPDATE_SYSOFFICE: 'sysOffice/UPDATE_SYSOFFICE',
  DELETE_SYSOFFICE: 'sysOffice/DELETE_SYSOFFICE',
  RESET: 'sysOffice/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ISysOffice>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type SysOfficeState = Readonly<typeof initialState>;

// Reducer

export default (state: SysOfficeState = initialState, action): SysOfficeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_SYSOFFICE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_SYSOFFICE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_SYSOFFICE):
    case REQUEST(ACTION_TYPES.UPDATE_SYSOFFICE):
    case REQUEST(ACTION_TYPES.DELETE_SYSOFFICE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_SYSOFFICE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_SYSOFFICE):
    case FAILURE(ACTION_TYPES.CREATE_SYSOFFICE):
    case FAILURE(ACTION_TYPES.UPDATE_SYSOFFICE):
    case FAILURE(ACTION_TYPES.DELETE_SYSOFFICE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_SYSOFFICE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_SYSOFFICE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_SYSOFFICE):
    case SUCCESS(ACTION_TYPES.UPDATE_SYSOFFICE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_SYSOFFICE):
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

const apiUrl = 'api/sys-offices';

// Actions

export const getEntities: ICrudGetAllAction<ISysOffice> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_SYSOFFICE_LIST,
    payload: axios.get<ISysOffice>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ISysOffice> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_SYSOFFICE,
    payload: axios.get<ISysOffice>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ISysOffice> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_SYSOFFICE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ISysOffice> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_SYSOFFICE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ISysOffice> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_SYSOFFICE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
