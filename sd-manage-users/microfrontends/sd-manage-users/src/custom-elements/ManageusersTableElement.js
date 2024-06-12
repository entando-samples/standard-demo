import React from 'react';
import ReactDOM from 'react-dom';
import retargetEvents from 'react-shadow-dom-retarget-events';
import './public-path';

import { StylesProvider, jssPreset, createMuiTheme, ThemeProvider } from '@material-ui/core/styles';
import { create } from 'jss';

import KeycloakContext from 'auth/KeycloakContext';
import { PaginationProvider } from 'components/pagination/PaginationContext';
import ManageusersTableContainer from 'components/ManageusersTableContainer';
import setLocale from 'i18n/setLocale';
import {
  createWidgetEventPublisher,
  subscribeToWidgetEvents,
  subscribeToWidgetEvent,
  widgetEventToFSA,
} from 'helpers/widgetEvents';
import {
  INPUT_EVENT_TYPES,
  OUTPUT_EVENT_TYPES,
  KEYCLOAK_EVENT_TYPE,
} from 'custom-elements/widgetEventTypes';

const getKeycloakInstance = () =>
  (window &&
    window.entando &&
    window.entando.keycloak && { ...window.entando.keycloak, initialized: true }) || {
    initialized: false,
  };

const ATTRIBUTES = {
  hidden: 'hidden',
  locale: 'locale',
  disableDefaultEventHandler: 'disable-default-event-handler', // custom element attribute names MUST be written in kebab-case
  config: 'config',
};

class ManageusersTableElement extends HTMLElement {
  muiTheme;

  jss;

  container;

  mountPoint;

  unsubscribeFromWidgetEvents;

  unsubscribeFromKeycloakEvent;

  keycloak = getKeycloakInstance();

  onAdd = createWidgetEventPublisher(OUTPUT_EVENT_TYPES.add);

  onError = createWidgetEventPublisher(OUTPUT_EVENT_TYPES.error);

  onSelect = createWidgetEventPublisher(OUTPUT_EVENT_TYPES.select);

  // onDelete = createWidgetEventPublisher(OUTPUT_EVENT_TYPES.delete);

  onReset = createWidgetEventPublisher(OUTPUT_EVENT_TYPES.reset);

  reactRootRef = React.createRef();

  static get observedAttributes() {
    return Object.values(ATTRIBUTES);
  }

  attributeChangedCallback(name, oldValue, newValue) {
    if (!this.container || oldValue === newValue) {
      return;
    }
    if (!Object.values(ATTRIBUTES).includes(name)) {
      throw new Error(`Untracked changed attribute: ${name}`);
    }
    this.render();
  }

  connectedCallback() {
    this.container = document.createElement('div');
    this.mountPoint = document.createElement('div');
    this.container.appendChild(this.mountPoint);

    const shadowRoot = this.attachShadow({ mode: 'open' });
    shadowRoot.appendChild(this.container);

    this.jss = create({
      ...jssPreset(),
      insertionPoint: this.container,
    });

    this.muiTheme = createMuiTheme({
      props: {
        MuiDialog: {
          container: this.mountPoint,
        },
      },
    });

    this.keycloak = { ...getKeycloakInstance(), initialized: true };

    this.unsubscribeFromKeycloakEvent = subscribeToWidgetEvent(KEYCLOAK_EVENT_TYPE, () => {
      this.keycloak = { ...getKeycloakInstance(), initialized: true };
      this.render();
    });

    this.render();

    retargetEvents(shadowRoot);
  }

  disconnectedCallback() {
    if (this.unsubscribeFromWidgetEvents) {
      this.unsubscribeFromWidgetEvents();
    }
    if (this.unsubscribeFromKeycloakEvent) {
      this.unsubscribeFromKeycloakEvent();
    }
  }

  defaultWidgetEventHandler() {
    return evt => {
      const action = widgetEventToFSA(evt);
      this.reactRootRef.current.dispatch(action);
    };
  }

  render() {
    const hidden = this.getAttribute(ATTRIBUTES.hidden) === 'true';
    if (hidden) {
      return;
    }

    const locale = this.getAttribute(ATTRIBUTES.locale);
    setLocale(locale);

    const paginationMode = 'pagination';

    const attributeConfig = this.getAttribute(ATTRIBUTES.config);
    const config = attributeConfig && JSON.parse(attributeConfig);
    const { systemParams } = config || {};
    const { api } = systemParams || {};
    const serviceUrl = api && api['sd-banking-api'].url;

    const disableEventHandler = this.getAttribute(ATTRIBUTES.disableDefaultEventHandler) === 'true';
    if (!disableEventHandler) {
      const defaultWidgetEventHandler = this.defaultWidgetEventHandler();

      this.unsubscribeFromWidgetEvents = subscribeToWidgetEvents(
        Object.values(INPUT_EVENT_TYPES),
        defaultWidgetEventHandler
      );
    } else {
      if (this.unsubscribeFromWidgetEvents) {
        this.unsubscribeFromWidgetEvents();
      }
      if (this.unsubscribeFromKeycloakEvent) {
        this.unsubscribeFromKeycloakEvent();
      }
    }

    ReactDOM.render(
      <KeycloakContext.Provider value={this.keycloak}>
        <StylesProvider jss={this.jss}>
          <ThemeProvider theme={this.muiTheme}>
            <PaginationProvider paginationMode={paginationMode}>
              <ManageusersTableContainer
                ref={this.reactRootRef}
                onAdd={this.onAdd}
                onReset={this.onReset}
                onSelect={this.onSelect}
                onError={this.onError}
                paginationMode={paginationMode}
                serviceUrl={serviceUrl}
              />
            </PaginationProvider>
          </ThemeProvider>
        </StylesProvider>
      </KeycloakContext.Provider>,
      this.mountPoint
    );
  }
}

if (!customElements.get('sd-manage-users')) {
  customElements.define('sd-manage-users', ManageusersTableElement);
}
