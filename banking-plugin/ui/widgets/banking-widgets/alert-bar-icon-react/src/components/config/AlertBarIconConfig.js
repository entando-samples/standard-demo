import React, {Component} from 'react';

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

        <label htmlFor="icon">Icon</label>
        {/*TODO: add the select options from the icon list*/}
        <input id="icon" onChange={e => this.handleChange(e)} value={icon} />

        <label htmlFor="title">Title (String)</label>
        <input id="title" onChange={e => this.handleChange(e)} value={title} />
      </div>
    );
  }
}

export default AlertBarIconConfig;
