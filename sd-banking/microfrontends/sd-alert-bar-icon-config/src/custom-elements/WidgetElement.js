import React from 'react';
import ReactDOM from 'react-dom';
import App from '../App';

class WidgetElement extends HTMLElement {
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
      this.render();
   }

   render() {
      ReactDOM.render(<App ref={this.reactRootRef} />, this.mountPoint);
   }
}

if (!customElements.get('sd-alert-bar-icon-config')) {
    customElements.define('sd-alert-bar-icon-config', WidgetElement);
}
  