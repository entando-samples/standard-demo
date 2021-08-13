import React from 'react';
import ReactDOM from 'react-dom';
import AlertBarIconConfig from '../components/config/AlertBarIconConfig';

class AlertBarIconConfigElement extends HTMLElement {
  constructor() {
    super();
    this.reactRootRef = React.createRef();
    this.mountPoint = null;
  }

  get config() {
    return this.reactRootRef.current ? this.reactRootRef.current.state : {};
  }

  set config(value) {
    return this.reactRootRef.current.setState(value);
  }

  connectedCallback() {
    this.mountPoint = document.createElement('div');
    this.appendChild(this.mountPoint);
    ReactDOM.render(<AlertBarIconConfig/>, this.mountPoint);
  }
}

if (!customElements.get('sd-alert-bar-icon-config')) {
  customElements.define('sd-alert-bar-icon-config', AlertBarIconConfigElement);
}
