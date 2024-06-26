import React from 'react';
import ReactDOM from 'react-dom';
import './public-path.js';

import { KeycloakContext } from 'auth/KeycloakContext';
import AppContainer from 'AppContainer';
import { subscribeToWidgetEvent } from 'helpers/widgetEvents';
import { KEYCLOAK_EVENT_TYPE } from 'custom-elements/widgetEventTypes';
import i18next from 'i18next';

const getKeycloakInstance = () =>
  (window &&
    window.entando &&
    window.entando.keycloak && { ...window.entando.keycloak, initialized: true }) || {
    initialized: false,
  };

const ATTRIBUTES = {
  config: 'config'
};

class AlertBarIconElement extends HTMLElement {
  constructor(...args) {
    super(...args);

    this.mountPoint = null;
    this.unsubscribeFromKeycloakEvent = null;
    this.keycloak = getKeycloakInstance();
  }

  static get observedAttributes() {
    return Object.values(ATTRIBUTES);
  }

  attributeChangedCallback(attribute, oldValue, newValue) {
    if (!Object.values(ATTRIBUTES).includes(attribute)) {
      throw new Error(`Untracked changed attribute: ${attribute}`);
    }
    if (this.mountPoint && newValue !== oldValue) {
      this.render();
    }
  }

  connectedCallback() {
    this.mountPoint = document.createElement('div');
    this.appendChild(this.mountPoint);

    const locale = this.getAttribute('locale') || 'en';
    i18next.changeLanguage(locale);

    this.keycloak = { ...getKeycloakInstance(), initialized: true };

    this.unsubscribeFromKeycloakEvent = subscribeToWidgetEvent(KEYCLOAK_EVENT_TYPE, () => {
      this.keycloak = { ...getKeycloakInstance(), initialized: true };
      this.render();
    });

    this.render();
  }

  render() {
    const attributeConfig = this.getAttribute(ATTRIBUTES.config);
    const config = attributeConfig && JSON.parse(attributeConfig);
    
    ReactDOM.render(
      <KeycloakContext.Provider value={this.keycloak}>
        <AppContainer config={config}/>
      </KeycloakContext.Provider>,
      this.mountPoint
    );
  }

  disconnectedCallback() {
    if (this.unsubscribeFromKeycloakEvent) {
      this.unsubscribeFromKeycloakEvent();
    }
  }
}

if (!customElements.get('sd-alert-bar-icon')) {
  customElements.define('sd-alert-bar-icon', AlertBarIconElement);
}
