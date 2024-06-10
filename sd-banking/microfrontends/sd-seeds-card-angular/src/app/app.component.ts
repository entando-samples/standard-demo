import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { EVENT_KEY, WidgetEventService } from './core/service/widget-event.service';

const getKeycloakInstance = () =>
  (window &&
    window['entando'] &&
    window['entando'].keycloak && {
      ...window['entando'].keycloak,
      initialized: true,
    }) || {
    initialized: false,
  };

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit, OnDestroy {
  keycloak = getKeycloakInstance();
  unsubscribeFromKeycloakEvent = null;
  config: any;

  @Input('config') configJson: string;

  constructor(private widgetEventService: WidgetEventService) {}

  ngOnInit() {
    this.keycloak = { ...getKeycloakInstance(), initialized: true };
    this.config = this.configJson ? JSON.parse(this.configJson) : {};

    this.unsubscribeFromKeycloakEvent = this.widgetEventService.subscribeToWidgetEvent(
      EVENT_KEY.KEYCLOAK_EVENT_TYPE,
      () => {
        this.keycloak = { ...getKeycloakInstance(), initialized: true };
      }
    );
  }

  ngOnDestroy() {
    if (this.unsubscribeFromKeycloakEvent) {
      this.unsubscribeFromKeycloakEvent();
    }
  }
}
