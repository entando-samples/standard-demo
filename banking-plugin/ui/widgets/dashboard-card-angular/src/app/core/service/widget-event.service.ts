import { Injectable } from '@angular/core';

export const EVENT_KEY = {
  KEYCLOAK_EVENT_TYPE: 'keycloak',
  OUTPUT_EVENT_TYPES: {
    transactionsDetail: 'seedscard.transactionsDetail',
  },
};

@Injectable({
  providedIn: 'root',
})
export class WidgetEventService {
  constructor() {}

  publishWidgetEvent(eventId, payload) {
    const widgetEvent = new CustomEvent(eventId, {
      detail: {
        payload,
      },
    });
    window.dispatchEvent(widgetEvent);
  }

  createWidgetEventPublisher(eventId, payload) {
    this.publishWidgetEvent(eventId, payload);
  }

  subscribeToWidgetEvent(eventId, eventHandler) {
    window.addEventListener(eventId, eventHandler);
    return () => {
      window.removeEventListener(eventId, eventHandler);
    };
  }

  subscribeToWidgetEvents(widgetEvents, eventHandler) {
    widgetEvents.forEach((eventType) =>
      window.addEventListener(eventType, eventHandler)
    );

    return () => {
      widgetEvents.forEach((eventType) =>
        window.removeEventListener(eventType, eventHandler)
      );
    };
  }

  widgetEventToFSA = (widgetEvent) => {
    // for info about Flux Standard Action (FSA) see https://github.com/redux-utilities/flux-standard-action
    const {
      type,
      detail: { payload, error, meta },
    } = widgetEvent;
    return { type, payload, error, meta };
  };
}
