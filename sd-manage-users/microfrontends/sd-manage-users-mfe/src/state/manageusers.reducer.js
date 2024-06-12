import { INPUT_EVENT_TYPES } from 'custom-elements/widgetEventTypes';
import { ADD_FILTER, UPDATE_FILTER, DELETE_FILTER, CLEAR_FILTERS } from 'state/filter.types';
import {
  READ_ALL,
  ERROR_FETCH,
  CLEAR_ERRORS,
  CLEAR_ITEMS,
  CREATE,
  UPDATE,
  DELETE,
  RESET,
} from 'state/manageusers.types';

export const initialState = {
  filters: [],
  items: [],
  count: 0,
  notificationMessage: null,
  notificationStatus: null,
  loading: false,
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
        count: initialState.count,
        filters: state.filters.filter((f, index) => index !== action.payload.filterId),
      };
    case CLEAR_FILTERS:
      return {
        ...state,
        items: initialState.items,
        count: initialState.count,
        filters: initialState.filters,
      };
    case CLEAR_ITEMS:
      return {
        ...state,
        items: initialState.items,
        count: initialState.count,
      };
    case READ_ALL:
      return {
        ...state,
        items: action.payload.items,
        count: action.payload.count,
      };
    case ERROR_FETCH:
      return {
        ...state,
        notificationMessage: action.payload.message,
        notificationStatus: action.payload.status,
      };
    case CLEAR_ERRORS:
      return {
        ...state,
        notificationMessage: null,
      };
    case CREATE:
    case INPUT_EVENT_TYPES.formCreate:
      return { ...state, items: [...state.items, action.payload] };
    case UPDATE:
    case INPUT_EVENT_TYPES.formUpdate: {
      const i = state.items.findIndex(item => {
        return item.id === action.payload.id;
      });
      const items = [...state.items];
      items[i] = action.payload;
      return { ...state, items };
    }
    case DELETE:
    case INPUT_EVENT_TYPES.formDelete:
      return { ...state, items: state.items.filter(item => item.id !== action.payload.id) };
    case RESET:
    default:
      return state;
  }
};
