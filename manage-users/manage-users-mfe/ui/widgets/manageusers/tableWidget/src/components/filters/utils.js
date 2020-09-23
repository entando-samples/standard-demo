const DEFAULT_FILTER_TYPES = ['equals', 'in', 'specified', 'unspecified'];
const STRING_FILTER_TYPES = ['contains'];
const DATE_NUMBER_FILTER_TYPES = [
  { value: 'greaterThan', title: '>' },
  { value: 'lessThan', title: '<' },
  { value: 'greaterOrEqualThan', title: '>=' },
  { value: 'lessOrEqualThan', title: '<=' },
];

const STRING_FIELDS = ['username'];
const DATE_FIELDS = [];
const NUMBER_FIELDS = [];

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
