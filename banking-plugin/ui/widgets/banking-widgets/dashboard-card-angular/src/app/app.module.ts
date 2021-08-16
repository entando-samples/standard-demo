import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Injector, NgModule, APP_INITIALIZER } from '@angular/core';
import { createCustomElement } from '@angular/elements';
import { BrowserModule } from '@angular/platform-browser';
import { TranslateModule } from '@ngx-translate/core';
import { AppComponent } from './app.component';
import { CardComponent } from './card/card/card.component';
import { SecurityInterceptor } from './core/interceptor/security.interceptor';
import { PipesModule } from './core/pipes/pipes.module';
import { AppInitializerService } from './core/service/app-initializer.service';

export function init(appInitializerService: AppInitializerService) {
  return () => appInitializerService.initialize();
}

@NgModule({
  declarations: [AppComponent, CardComponent],
  imports: [
    BrowserModule,
    HttpClientModule,
    PipesModule,
    TranslateModule.forRoot({
      defaultLanguage: 'en',
    }),
  ],
  providers: [
    {
      provide: APP_INITIALIZER,
      useFactory: init,
      deps: [AppInitializerService],
      multi: true,
    },
    { provide: HTTP_INTERCEPTORS, useClass: SecurityInterceptor, multi: true },
  ],
  entryComponents: [AppComponent],
})
export class AppModule {
  constructor(private injector: Injector) {}

  ngDoBootstrap() {
    const el = createCustomElement(AppComponent, { injector: this.injector });
    customElements.define('sd-seeds-card-details-ng', el);
  }
}
