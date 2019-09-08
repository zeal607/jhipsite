import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ISysEmployeeOffice, defaultValue } from 'app/shared/model/sys-employee-office.model';

export const ACTION_TYPES = {
  FETCH_SYSEMPLOYEEOFFICE_LIST: 'sysEmployeeOffice/FETCH_SYSEMPLOYEEOFFICE_LIST',
  FETCH_SYSEMPLOYEEOFFICE: 'sysEmployeeOffice/FETCH_SYSEMPLOYEEOFFICE',
  CREATE_SYSEMPLOYEEOFFICE: 'sysEmployeeOffice/CREATE_SYSEMPLOYEEOFFICE',
  UPDATE_SYSEMPLOYEEOFFICE: 'sysEmployeeOffice/UPDATE_SYSEMPLOYEEOFFICE',
  DELETE_SYSEMPLOYEEOFFICE: 'sysEmployeeOffice/DELETE_SYSEMPLOYEEOFFICE',
  RESET: 'sysEmployeeOffice/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ISysEmployeeOffice>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type SysEmployeeOfficeState = Readonly<typeof initialState>;

// Reducer

export default (state: SysEmployeeOfficeState = initialState, action): SysEmployeeOfficeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_SYSEMPLOYEEOFFICE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_SYSEMPLOYEEOFFICE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_SYSEMPLOYEEOFFICE):
    case REQUEST(ACTION_TYPES.UPDATE_SYSEMPLOYEEOFFICE):
    case REQUEST(ACTION_TYPES.DELETE_SYSEMPLOYEEOFFICE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_SYSEMPLOYEEOFFICE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_SYSEMPLOYEEOFFICE):
    case FAILURE(ACTION_TYPES.CREATE_SYSEMPLOYEEOFFICE):
    case FAILURE(ACTION_TYPES.UPDATE_SYSEMPLOYEEOFFICE):
    case FAILURE(ACTION_TYPES.DELETE_SYSEMPLOYEEOFFICE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_SYSEMPLOYEEOFFICE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_SYSEMPLOYEEOFFICE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_SYSEMPLOYEEOFFICE):
    case SUCCESS(ACTION_TYPES.UPDATE_SYSEMPLOYEEOFFICE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_SYSEMPLOYEEOFFICE):
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

const apiUrl = 'api/sys-employee-offices';

// Actions

export const getEntities: ICrudGetAllAction<ISysEmployeeOffice> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_SYSEMPLOYEEOFFICE_LIST,
    payload: axios.get<ISysEmployeeOffice>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ISysEmployeeOffice> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_SYSEMPLOYEEOFFICE,
    payload: axios.get<ISysEmployeeOffice>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ISysEmployeeOffice> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_SYSEMPLOYEEOFFICE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ISysEmployeeOffice> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_SYSEMPLOYEEOFFICE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ISysEmployeeOffice> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_SYSEMPLOYEEOFFICE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
