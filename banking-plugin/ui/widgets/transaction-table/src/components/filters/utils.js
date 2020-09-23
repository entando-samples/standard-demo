const DEFAULT_FILTER_TYPES = ['equals', 'in', 'specified', 'unspecified'];
const STRING_FILTER_TYPES = ['contains'];
const DATE_NUMBER_FILTER_TYPES = [
  { value: 'greaterThan', title: '>' },
  { value: 'lessThan', title: '<' },
  { value: 'greaterOrEqualThan', title: '>=' },
  { value: 'lessOrEqualThan', title: '<=' },
];

const STRING_FIELDS = ['description'];
const DATE_FIELDS = ['date'];
const NUMBER_FIELDS = ['amount', 'balance', 'accountID'];

export const getFieldFilterTypes = field => {
  return [
    ...DEFAULT_FILTER_TYPES,
    ...(STRING_FIELDS.includes(field) ? STRING_FILTER_TYPES : []),
    ...(DATE_FIELDS.includes(field) ? DATE_NUMBER_FILTER_TYPES : []),
    ...(NUMBER_FIELDS.includes(field) ? DATE_NUMBER_FILTER_TYPES : []),
  ];
};

export const getFilterQuery = (filters = []) => {
  if (filters.length) {
    return filters
      .filter(f => f.field && f.operator)
      .reduce((acc, f) => {
        switch (f.operator) {
          case 'specified':
            return [...acc, `${encodeURIComponent(`${f.field}.specified`)}=true`];
          case 'unspecified':
            return [...acc, `${encodeURIComponent(`${f.field}.specified`)}=false`];
          default:
        }
        return [
          ...acc,
          `${encodeURIComponent(`${f.field}.${f.operator}`)}=${encodeURIComponent(f.value)}`,
        ];
      }, [])
      .join('&');
  }
  return '';
};

/**
 * from Date format to yyyy-MM-dd format
 */
export const parseDateToString = ({ date }) => {
  return date.toISOString().substring(0, 10);
};

/**
 * return the first day of month from a date (ISO FORMAT without time)
 */
export const getStartMonthFromDate = ({ d }) => {
  const date = new Date(d.getFullYear(), d.getMonth(), 1, d.getHours());
  return parseDateToString({ date });
};

/**
 * return the date starting from a date d and back to 'range' days (ISO FORMAT without time)
 */
export const getDateFromRange = ({ d, range }) => {
  const date = new Date();
  date.setDate(d.getDate() - range);
  return parseDateToString({ date });
};
