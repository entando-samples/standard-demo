import React, { Component } from 'react';
import './App.css';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = { cardname: 'checking' };
  }

  handleCardChange(value) {
    this.setState(prevState => ({
      ...prevState,
      cardname: value,
    }));
  }

  render() {
    const { cardname } = this.state;
    return (
      <div>
        <h1>
          Insert the microservice SERVER_SERVLET_CONTEXT_PATH (ex: &quot;checking&quot; or
          &quot;saving&quot;)
        </h1>
        {/* eslint-disable-next-line jsx-a11y/label-has-associated-control */}
        <label htmlFor="cardname">Card name</label>
        <select
          name="cardname"
          value={cardname}
          onChange={e => this.handleCardChange(e.target.value)}
        >
          <option value="">Select a value</option>
          <option value="checking">Checking</option>
          <option value="creditcard">Credit Card</option>
          <option value="saving">Savings</option>
        </select>
      </div>
    );
  }
}

export default App;
