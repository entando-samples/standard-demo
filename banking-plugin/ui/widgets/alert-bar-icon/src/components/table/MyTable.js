import React from 'react';
import PropTypes from 'prop-types';
import { Table } from 'reactstrap';
import Pagination from 'components/table/Pagination';
import Pdf from 'assets/pdf/Entando_Development_Methodology.pdf';

class MyTable extends React.PureComponent {
  constructor(props) {
    super(props);
    this.state = {
      itemsPerPage: 5,
      currentPage: 1,
    };
  }

  // componentDidMount() {
  //   const {data} = this.props;
  //   this.setState({data: data})
  // }

  componentDidUpdate(prevProps) {
    const { data } = this.props;
    if (prevProps.data !== data) {
      this.handleTable();
    }
  }

  setCurrentPage(pageNumber) {
    this.setState({ currentPage: pageNumber });
  }

  paginate = pageNumber => this.setCurrentPage(pageNumber);

  handleFileRead = doc => {
    const newDoc = { ...doc };
    if (doc.read) return;
    const { putDocumentRead } = this.props;
    newDoc.read = true;
    putDocumentRead(newDoc);
  };

  generateTable = data => {
    return (
      <Table className="MyTable" responsive size="sm" hover>
        <thead>
          <tr>
            <th>DOCUMENT</th>
            <th>DATE</th>
          </tr>
        </thead>
        <tbody>
          {data &&
            data.map(d => {
              const {
                // userId,
                createdAt,
                description,
                read,
              } = d;
              return (
                <tr
                  key={d.id}
                  onClick={() => {
                    this.handleFileRead(d);
                    window.open(Pdf, '_blank');
                    // toggleModal()
                  }}
                >
                  <td style={read ? { fontWeight: 'normal' } : { fontWeight: 'bold' }}>
                    {description}
                  </td>
                  <td style={read ? { fontWeight: 'normal' } : { fontWeight: 'bold' }}>
                    {createdAt}
                  </td>
                </tr>
              );
            })}
        </tbody>
      </Table>
    );
  };

  handleTable() {
    const { data } = this.props;
    const { currentPage, itemsPerPage } = this.state;
    const indexOfLastStatement = currentPage * itemsPerPage;
    const indexOfFirstStatement = indexOfLastStatement - itemsPerPage;
    const currentStatements = data.slice(indexOfFirstStatement, indexOfLastStatement);
    return this.generateTable(currentStatements);
  }

  render() {
    const { itemsPerPage, currentPage } = this.state;
    const { data } = this.props;

    return (
      <div>
        {this.handleTable()}
        <Pagination
          className="paginator"
          itemsPerPage={itemsPerPage}
          totalItems={data.length}
          currentPage={currentPage}
          paginate={this.paginate}
        />
      </div>
    );
  }
}

MyTable.propTypes = {
  data: PropTypes.arrayOf(Object).isRequired,
  putDocumentRead: PropTypes.func.isRequired,
};

export default MyTable;
