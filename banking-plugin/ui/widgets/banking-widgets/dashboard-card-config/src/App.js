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
        <input
          id="cardname"
          onChange={e => this.handleCardChange(e.target.value)}
          value={cardname}
        />
      </div>
    );
  }
}

export default App;
