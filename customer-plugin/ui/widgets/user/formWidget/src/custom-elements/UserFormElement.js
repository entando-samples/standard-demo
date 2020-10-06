import React from 'react';
import ReactDOM from 'react-dom';
import retargetEvents from 'react-shadow-dom-retarget-events';

import { StylesProvider, ThemeProvider, jssPreset } from '@material-ui/core/styles';
import { createMuiTheme } from '@material-ui/core';
import { create } from 'jss';

import { KeycloakContext } from 'auth/KeycloakContext';
import setLocale from 'i18n/setLocale';
import {
  createWidgetEventPublisher,
  subscribeToWidgetEvent,
  subscribeToWidgetEvents,
} from 'helpers/widgetEvents';
import {
  INPUT_EVENT_TYPES,
  OUTPUT_EVENT_TYPES,
  KEYCLOAK_EVENT_TYPE,
} from 'custom-elements/widgetEventTypes';
import UserAddFormContainer from 'components/UserAddFormContainer';
import AddUserSuccess from 'components/AddUserSuccess';
import 'fontawesome';

const getKeycloakInstance = () =>
  (window &&
    window.entando &&
    window.entando.keycloak && { ...window.entando.keycloak, initialized: true }) || {
    initialized: false,
  };

const ATTRIBUTES = {
  id: 'id',
  hidden: 'hidden',
  locale: 'locale',
  disableDefaultEventHandler: 'disable-default-event-handler', // custom element attribute names MUST be written in kebab-case
};

class UserFormElement extends HTMLElement {
  jss;

  mountPoint;

  keycloak = getKeycloakInstance();

  unsubscribeFromWidgetEvents;

  unsubscribeFromKeycloakEvent;

  onCreate = createWidgetEventPublisher(OUTPUT_EVENT_TYPES.create);

  onLogin = createWidgetEventPublisher(OUTPUT_EVENT_TYPES.login);

  onUpdate = createWidgetEventPublisher(OUTPUT_EVENT_TYPES.update);

  onErrorCreate = createWidgetEventPublisher(OUTPUT_EVENT_TYPES.errorCreate);

  onErrorUpdate = createWidgetEventPublisher(OUTPUT_EVENT_TYPES.errorUpdate);

  muiTheme;

  static get observedAttributes() {
    return Object.values(ATTRIBUTES);
  }

  attributeChangedCallback(name, oldValue, newValue) {
    if (!this.mountPoint || oldValue === newValue) {
      return;
    }
    if (!Object.values(ATTRIBUTES).includes(name)) {
      throw new Error(`Untracked changed attribute: ${name}`);
    }
    this.render();
  }

  connectedCallback() {
    this.mountPoint = document.createElement('div');

    const shadowRoot = this.attachShadow({ mode: 'open' });
    shadowRoot.appendChild(this.mountPoint);

    this.jss = create({
      ...jssPreset(),
      insertionPoint: this.mountPoint,
    });

    this.muiTheme = createMuiTheme({
      props: {
        MuiDialog: {
          container: this.mountPoint,
        },
        MuiPopover: {
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
      const { tableAdd, tableSelect } = INPUT_EVENT_TYPES;
      const { id } = ATTRIBUTES;
      switch (evt.type) {
        case tableAdd: {
          this.setAttribute(id, '');
          break;
        }
        case tableSelect: {
          this.setAttribute(id, evt.detail.payload.id);
          break;
        }
        default:
          throw new Error(`Unsupported event: ${evt.type}`);
      }
    };
  }

  render() {
    const hidden = this.getAttribute(ATTRIBUTES.hidden) === 'true';
    if (hidden) {
      ReactDOM.render(<></>);
      return;
    }

    const locale = this.getAttribute(ATTRIBUTES.locale);
    setLocale(locale);

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

    const id = this.getAttribute(ATTRIBUTES.id);

    const FormContainer = id
      ? React.createElement(
          AddUserSuccess,
          {
            onLogin: this.onLogin,
            onError: this.onErrorUpdate,
          },
          null
        )
      : React.createElement(
          UserAddFormContainer,
          {
            onCreate: this.onCreate,
            onError: this.onErrorCreate,
            onLogin: this.onLogin,
          },
          null
        );

    ReactDOM.render(
      <KeycloakContext.Provider value={this.keycloak}>
        <StylesProvider jss={this.jss}>
          <ThemeProvider theme={this.muiTheme}>{FormContainer}</ThemeProvider>
        </StylesProvider>
      </KeycloakContext.Provider>,
      this.mountPoint
    );
  }
}

customElements.define('user-form', UserFormElement);
