import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ISysCompanyOffice, defaultValue } from 'app/shared/model/sys-company-office.model';

export const ACTION_TYPES = {
  FETCH_SYSCOMPANYOFFICE_LIST: 'sysCompanyOffice/FETCH_SYSCOMPANYOFFICE_LIST',
  FETCH_SYSCOMPANYOFFICE: 'sysCompanyOffice/FETCH_SYSCOMPANYOFFICE',
  CREATE_SYSCOMPANYOFFICE: 'sysCompanyOffice/CREATE_SYSCOMPANYOFFICE',
  UPDATE_SYSCOMPANYOFFICE: 'sysCompanyOffice/UPDATE_SYSCOMPANYOFFICE',
  DELETE_SYSCOMPANYOFFICE: 'sysCompanyOffice/DELETE_SYSCOMPANYOFFICE',
  RESET: 'sysCompanyOffice/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ISysCompanyOffice>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type SysCompanyOfficeState = Readonly<typeof initialState>;

// Reducer

export default (state: SysCompanyOfficeState = initialState, action): SysCompanyOfficeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_SYSCOMPANYOFFICE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_SYSCOMPANYOFFICE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_SYSCOMPANYOFFICE):
    case REQUEST(ACTION_TYPES.UPDATE_SYSCOMPANYOFFICE):
    case REQUEST(ACTION_TYPES.DELETE_SYSCOMPANYOFFICE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_SYSCOMPANYOFFICE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_SYSCOMPANYOFFICE):
    case FAILURE(ACTION_TYPES.CREATE_SYSCOMPANYOFFICE):
    case FAILURE(ACTION_TYPES.UPDATE_SYSCOMPANYOFFICE):
    case FAILURE(ACTION_TYPES.DELETE_SYSCOMPANYOFFICE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_SYSCOMPANYOFFICE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_SYSCOMPANYOFFICE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_SYSCOMPANYOFFICE):
    case SUCCESS(ACTION_TYPES.UPDATE_SYSCOMPANYOFFICE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_SYSCOMPANYOFFICE):
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

const apiUrl = 'api/sys-company-offices';

// Actions

export const getEntities: ICrudGetAllAction<ISysCompanyOffice> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_SYSCOMPANYOFFICE_LIST,
    payload: axios.get<ISysCompanyOffice>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ISysCompanyOffice> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_SYSCOMPANYOFFICE,
    payload: axios.get<ISysCompanyOffice>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ISysCompanyOffice> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_SYSCOMPANYOFFICE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ISysCompanyOffice> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_SYSCOMPANYOFFICE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ISysCompanyOffice> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_SYSCOMPANYOFFICE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
