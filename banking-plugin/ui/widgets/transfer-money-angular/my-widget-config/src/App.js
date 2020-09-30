import React from 'react';

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      serviceurl: 'http://quickstart-flex.apps.rd.entando.org',
      title: 'Transfer Money'
    };
    this.handleChange = this.handleChange.bind(this);
  }

  handleChange(e) {
    const id = e.target.id;
    const value = e.target.value;
    this.setState(prevState => ({
      ...prevState,
      [id]: value
    }));
  }

  render() {
    const { serviceurl, title } = this.state;
    return (
      <div>
        <h1>Sample Entando 6 Widget Configuration</h1>
        <div>
          <label htmlFor="serviceurl">Service url</label>
          <input id="serviceurl" onChange={this.handleChange} value={serviceurl} />
        </div>
        <div>
          <label htmlFor="title">Title</label>
          <input id="title" onChange={this.handleChange} value={title} />
        </div>
      </div>
    );
  }
}

export default App;
