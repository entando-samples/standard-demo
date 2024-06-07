import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';

class SeedsCardConfigElement extends HTMLElement {
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

if (!customElements.get('sd-seeds-card-config')) {
  customElements.define('sd-seeds-card-config', SeedsCardConfigElement);
}
