import React, {Component} from 'react';
import {svgTypes} from '../../assets/svg/svg'

class AlertBarIconConfig extends Component {
  constructor(props) {
    super(props);
    this.state = {
      icon: '',
      title: '',
    };
  }

  handleChange = e => {
    const input = e.target;
    const name = input.name;
    const value = input.value;
    this.setState({ [name]: value });
  };

  render() {
    const { icon, title } = this.state;
    return (
      <div>
        <h1>Alert Bar Icon Configuration</h1>
        <div>
          <label htmlFor="icon">Icon</label>
          <select name="icon" value={icon} onChange={e => this.handleChange(e)}>
            <option value="">Select a value</option>
            {Object.entries(svgTypes).map( item => (
              <option key={item[0]}value={item[0]}>{item[[0]]}</option>
            ))}
          </select>
        </div>
        <div>
          <label htmlFor="title">Title (String)</label>
          <input id="title" value={title} type="text" onChange={e => this.handleChange(e)}  />
        </div>
      </div>
    );
  }
}

export default AlertBarIconConfig;
