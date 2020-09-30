import { Component, Input, OnDestroy, OnInit } from '@angular/core';

const getKeycloakInstance = () => {
  return (
    (window &&
      window['entando'] &&
      window['entando'].keycloak && {
        ...window['entando'].keycloak,
        initialized: true
      }) || {
      initialized: false
    }
  );
};
const KEYCLOAK_EVENT_TYPE = 'keycloak';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {
  @Input() serviceurl: string;
  @Input() title: string;
  keycloak = getKeycloakInstance();
  unsubscribeFromKeycloakEvent = null;

  constructor() {}

  ngOnInit(): void {
    this.keycloak = { ...getKeycloakInstance(), initialized: true };

    this.unsubscribeFromKeycloakEvent = this.subscribeToWidgetEvent(KEYCLOAK_EVENT_TYPE, () => {
      this.keycloak = { ...getKeycloakInstance(), initialized: true };
    });
  }

  subscribeToWidgetEvent(eventType, eventHandler) {
    window.addEventListener(eventType, eventHandler);
    return () => {
      window.removeEventListener(eventType, eventHandler);
    };
  }

  ngOnDestroy() {
    if (this.unsubscribeFromKeycloakEvent) {
      this.unsubscribeFromKeycloakEvent();
    }
  }
}
