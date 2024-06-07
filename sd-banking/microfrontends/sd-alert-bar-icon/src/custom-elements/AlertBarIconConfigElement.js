import React from 'react';
import ReactDOM from 'react-dom';
import AlertBarIconConfig from '../components/config/AlertBarIconConfig';

const ATTRIBUTES = {
  icon: 'icon',
  title: 'title',
};

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

  static get observedAttributes() {
    return Object.values(ATTRIBUTES);
  }

  attributeChangedCallback(name, oldValue, newValue) {
    if (!Object.values(ATTRIBUTES).includes(name)) {
      throw new Error(`Untracked changed attribute: ${name}`);
    }
    if (this.mountPoint && newValue !== oldValue) {
      this.render();
    }
  }

  connectedCallback() {
    this.mountPoint = document.createElement('div');
    this.appendChild(this.mountPoint);

    const icon = this.getAttribute(ATTRIBUTES.icon);
    const title = this.getAttribute(ATTRIBUTES.title);
    ReactDOM.render(<AlertBarIconConfig ref={this.reactRootRef} icon={icon} title={title}/>, this.mountPoint);
  }
}

if (!customElements.get('sd-alert-bar-icon-config')) {
  customElements.define('sd-alert-bar-icon-config', AlertBarIconConfigElement);
}
