import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CardComponent } from './card.component';
import { CardService } from './card.service';
import {TranslateModule} from '@ngx-translate/core';
import {PipesModule} from '../../core/pipes/pipes.module';
import {Observable} from 'rxjs';

describe('CardComponent', () => {
  let component: CardComponent;
  let fixture: ComponentFixture<CardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      providers: [{
         provide: CardService,
         useValue: jasmine.createSpyObj('CardService', {
           getSeedsCardByUserID: new Observable(),
           getSeedscard: new Observable(),
         })
      }],
      imports: [TranslateModule.forRoot(), PipesModule],
      declarations: [ CardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CardComponent);
    component = fixture.componentInstance;
    component.keycloak = {
      idTokenParsed: {
        preferred_username: 'sampleUserName'
      }
    };
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
