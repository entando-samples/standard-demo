import { INPUT_EVENT_TYPES } from 'custom-elements/widgetEventTypes';
import {
  ADD_FILTER,
  UPDATE_FILTER,
  DELETE_FILTER,
  CLEAR_FILTERS,
  SET_DESCRIPTION_FILTER,
  SET_RANGE_FILTER,
} from 'state/filter.types';
import {
  READ_ALL,
  ERROR_FETCH,
  CLEAR_ERRORS,
  CLEAR_ITEMS,
  CREATE,
  UPDATE,
  DELETE,
} from 'state/seedscardtransaction.types';

export const initialState = {
  filters: [],
  items: [],
  itemCount: 0,
  errorMessage: null,
  errorStatus: null,
  loading: false,
  descriptionFilter: '',
  rangeFilter: null,
};

export const reducer = (state = initialState, action) => {
  switch (action.type) {
    case ADD_FILTER:
      return {
        ...state,
        filters: [...state.filters, { field: '', operator: '', value: '' }],
      };
    case UPDATE_FILTER:
      return {
        ...state,
        filters: state.filters.map((filter, index) =>
          index === action.payload.filterId ? { ...filter, ...action.payload.values } : filter
        ),
      };
    case DELETE_FILTER:
      return {
        ...state,
        items: initialState.items,
        itemCount: initialState.itemCount,
        filters: state.filters.filter((f, index) => index !== action.payload.filterId),
      };
    case CLEAR_FILTERS:
      return {
        ...state,
        items: initialState.items,
        itemCount: initialState.itemCount,
        filters: initialState.filters,
      };
    case CLEAR_ITEMS:
      return {
        ...state,
        items: initialState.items,
        itemCount: initialState.itemCount,
      };
    case READ_ALL:
      return {
        ...state,
        items: action.payload.items,
        itemCount: action.payload.count,
      };
    case ERROR_FETCH:
      return { ...state, errorMessage: action.payload.message, errorStatus: action.payload.status };
    case CLEAR_ERRORS:
      return { ...state, errorMessage: null, errorStatus: null };
    case CREATE:
    case INPUT_EVENT_TYPES.transactionDetails:
      return { ...state, items: [...state.items, action.payload] };
    case UPDATE:
    case DELETE:
    case SET_DESCRIPTION_FILTER:
      return { ...state, descriptionFilter: action.payload };
    case SET_RANGE_FILTER:
      return { ...state, rangeFilter: action.payload };
    default:
      return state;
  }
};
