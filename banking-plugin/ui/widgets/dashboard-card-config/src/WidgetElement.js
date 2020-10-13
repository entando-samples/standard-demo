import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';

class WidgetElement extends HTMLElement {
  constructor() {
    super();
    this.reactRoofRef = React.createRef();
    this.mountPoint = null;
  }

  get config() {
    return this.reactRoofRef.current ? this.reactRoofRef.current.state : {};
  }

  set config(value) {
    return this.reactRoofRef.current.setState(value);
  }

  connectedCallback() {
    this.mountPoint = document.createElement('div');
    this.appendChild(this.mountPoint);
    ReactDOM.render(<App ref={this.reactRoofRef} />, this.mountPoint);
  }
}

customElements.define('seeds-card-config', WidgetElement);

export default WidgetElement;
