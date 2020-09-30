import React from 'react';
import PropTypes from 'prop-types';

import Icon from 'components/Icon';
import Title from 'components/Title';
import Counter from 'components/Counter';
import MyModal from 'components/modal/MyModal';

class App extends React.PureComponent {
  render() {
    const {
      icon,
      title,
      modalOpen,
      toggleModal,
      data,
      putDocumentRead,
      onChangeDescription,
      onChangeRangeFilter,
    } = this.props;

    const getUnreadData = () => {
      let i = 0;
      data.forEach(d => {
        if (!d.read) {
          // eslint-disable-next-line no-plusplus
          i++;
        }
      });
      return i;
    };

    const changeDescription = description => {
      onChangeDescription(description);
    };

    const changeRangeFilter = range => {
      onChangeRangeFilter(range);
    };

    return (
      <>
        {/* eslint-disable-next-line jsx-a11y/click-events-have-key-events,jsx-a11y/no-static-element-interactions */}
        <div
          className="list-item"
          onClick={Array.isArray(data) && data.length ? () => toggleModal() : null}
        >
          {icon && <Icon icon={icon} />} {title && <Title title={title} />}{' '}
          {data && data.length > 0 && getUnreadData() !== 0 ? (
            <Counter count={getUnreadData()} />
          ) : null}
        </div>
        {data ? (
          <MyModal
            className="my-modal"
            isOpen={modalOpen}
            toggle={toggleModal}
            title={title}
            data={data}
            putDocumentRead={putDocumentRead}
            changeDescription={changeDescription}
            changeRangeFilter={changeRangeFilter}
          />
        ) : null}
      </>
    );
  }
}

App.propTypes = {
  icon: PropTypes.string,
  iconStyle: PropTypes.shape({}),
  countStyle: PropTypes.shape({}),
  title: PropTypes.string,
  titleStyle: PropTypes.shape({}),
  modalOpen: PropTypes.bool.isRequired,
  toggleModal: PropTypes.func.isRequired,
  putDocumentRead: PropTypes.func.isRequired,
  data: PropTypes.arrayOf(Object),
  onChangeDescription: PropTypes.func.isRequired,
  onChangeRangeFilter: PropTypes.func.isRequired,
};

App.defaultProps = {
  icon: '',
  iconStyle: {},
  countStyle: {},
  title: '',
  titleStyle: {},
  data: [],
};

export default App;
