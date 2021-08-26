import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import MyTable from 'components/table/MyTable';
import {
  getDateFromRange,
  getStartMonthFromDate,
  parseDateToString,
} from 'components/filters/utils';

class MyModal extends Component {
  constructor(props) {
    super(props);
    this.state = {
      descriptionFilter: '',
    };
    this.onChangeDescription = this.onChangeDescription.bind(this);
    this.filterByDescription = this.filterByDescription.bind(this);
    this.onChangeRangeFilter = this.onChangeRangeFilter.bind(this);
  }

  onChangeDescription(evt) {
    let newDescription;
    if (evt && evt.target) newDescription = evt.target.value;
    this.setState({
      descriptionFilter: newDescription,
    });
  }

  onChangeRangeFilter(evt) {
    const { changeRangeFilter } = this.props;

    let newRangeFilter;
    if (evt && evt.target) newRangeFilter = evt.target.value;

    const today = new Date();

    let dateA = parseDateToString({ date: today });
    const dateB = parseDateToString({ date: today });

    let range = {
      dateA,
      dateB,
    };
    switch (newRangeFilter) {
      case '90': {
        dateA = getDateFromRange({ d: today, range: 90 });
        range.dateA = dateA;
        break;
      }
      case 'month': {
        dateA = getStartMonthFromDate({ d: new Date() });
        range.dateA = dateA;
        break;
      }
      default: {
        range = null;
      }
    }
    changeRangeFilter(range);
  }

  filterByDescription(descriptionOptional) {
    const { descriptionFilter } = this.state;
    const { changeDescription } = this.props;
    changeDescription(descriptionOptional === '' ? descriptionOptional : descriptionFilter);
  }

  render() {
    const { isOpen, toggle, title, className, data, putDocumentRead } = this.props;

    const { descriptionFilter } = this.state;
    return (
      <Modal isOpen={isOpen} className={className}>
        <ModalHeader style={{ color: '#0E6837' }}>
          {title}
          <select
            name="transactionRange"
            id="transactionRange"
            className="select-range"
            onChange={this.onChangeRangeFilter}
          >
            <option value="all">All transactions</option>
            <option value="month">Last month</option>
            <option value="90">Last 90 days</option>
          </select>
          <input
            className="search-input"
            type="text"
            onChange={this.onChangeDescription}
            value={descriptionFilter}
          />
          <button className="search-button" type="button" onClick={this.filterByDescription}>
            Filter
          </button>
        </ModalHeader>
        <ModalBody style={{ padding: 0 }}>
          <div className="my-table">
            {data && <MyTable toggleModal={toggle} data={data} putDocumentRead={putDocumentRead} />}
          </div>
        </ModalBody>
        <ModalFooter>
          <Button
            onClick={() => {
              this.onChangeRangeFilter();
              this.onChangeDescription();
              this.filterByDescription('');
              toggle();
            }}
          >
            Close
          </Button>
        </ModalFooter>
      </Modal>
    );
  }
}

MyModal.propTypes = {
  isOpen: PropTypes.bool.isRequired,
  toggle: PropTypes.func.isRequired,
  putDocumentRead: PropTypes.func.isRequired,
  title: PropTypes.string,
  className: PropTypes.string,
  data: PropTypes.arrayOf(Object),
  changeDescription: PropTypes.func.isRequired,
  changeRangeFilter: PropTypes.func.isRequired,
};

MyModal.defaultProps = {
  title: '',
  className: '',
  data: [],
};

export default MyModal;
