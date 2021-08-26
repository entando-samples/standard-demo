import React, {Component} from 'react';
import {svgTypes} from '../../assets/svg/svg'

class AlertBarIconConfig extends Component {
  constructor(props) {
    super(props);
    this.state = {
      icon: props.icon || '',
      title: props.title || '',
    };
  }

  handleChange = e => {
    const input = e.target;
    this.setState({
      [input.name]: input.value,
    });
  };

  render() {
    const { icon, title } = this.state;
    return (
      <div>
        <h1>Alert Bar Icon Configuration</h1>
        <div>
          <label htmlFor="icon">Icon</label>
          <select name="icon" value={icon} onChange={this.handleChange}>
            <option value="">Select a value</option>
            {Object.entries(svgTypes).map( item => (
              <option key={item[0]}value={item[0]}>{item[[0]]}</option>
            ))}
          </select>
        </div>
        <div>
          <label htmlFor="title">Title (String)</label>
          <input name="title" value={title} type="text" onChange={this.handleChange}  />
        </div>
      </div>
    );
  }
}

export default AlertBarIconConfig;
