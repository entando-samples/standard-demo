import { Injectable } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import * as en from '../i18n/en.json';
import * as it from '../i18n/it.json';

@Injectable({
  providedIn: 'root',
})
export class AppInitializerService {
  constructor(private translate: TranslateService) {}

  initialize() {
    this.translate.use(window.navigator.language.split('-')[0]);
    this.translate.setTranslation('en', en['default']);
    this.translate.setTranslation('it', it['default']);
  }
}
