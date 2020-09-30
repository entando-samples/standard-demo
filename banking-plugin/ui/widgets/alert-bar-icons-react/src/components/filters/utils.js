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
