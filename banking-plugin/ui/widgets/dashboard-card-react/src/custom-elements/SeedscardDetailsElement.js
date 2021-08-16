import React from 'react';
import ReactDOM from 'react-dom';
import i18next from 'i18next';

import { KeycloakContext } from 'auth/KeycloakContext';
import SeedscardDetailsContainer from 'components/SeedscardDetailsContainer';
import { createWidgetEventPublisher, subscribeToWidgetEvent } from 'helpers/widgetEvents';
import { OUTPUT_EVENT_TYPES, KEYCLOAK_EVENT_TYPE } from 'custom-elements/widgetEventTypes';

const getKeycloakInstance = () =>
  (window &&
    window.entando &&
    window.entando.keycloak && { ...window.entando.keycloak, initialized: true }) || {
    initialized: false,
  };

const ATTRIBUTES = {
  cardname: 'cardname',
};

class SeedscardDetailsElement extends HTMLElement {
  onDetail = createWidgetEventPublisher(OUTPUT_EVENT_TYPES.transactionsDetail);

  constructor(...args) {
    super(...args);

    this.mountPoint = null;
    this.unsubscribeFromKeycloakEvent = null;
    this.keycloak = getKeycloakInstance();
  }

  static get observedAttributes() {
    return Object.values(ATTRIBUTES);
  }

  attributeChangedCallback(cardname, oldValue, newValue) {
    if (!Object.values(ATTRIBUTES).includes(cardname)) {
      throw new Error(`Untracked changed attribute: ${cardname}`);
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
    const customEventPrefix = 'seedscard.details.';
    const cardname = this.getAttribute(ATTRIBUTES.cardname);

    const onError = error => {
      const customEvent = new CustomEvent(`${customEventPrefix}error`, {
        details: {
          error,
        },
      });
      this.dispatchEvent(customEvent);
    };

    ReactDOM.render(
      <KeycloakContext.Provider value={this.keycloak}>
        <SeedscardDetailsContainer cardname={cardname} onError={onError} onDetail={this.onDetail} />
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

customElements.define('seedscard-details', SeedscardDetailsElement);
