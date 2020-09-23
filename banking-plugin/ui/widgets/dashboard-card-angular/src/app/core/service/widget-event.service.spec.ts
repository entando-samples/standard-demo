import { TestBed } from '@angular/core/testing';

import { WidgetEventService } from './widget-event.service';

describe('WidgetEventService', () => {
  let service: WidgetEventService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WidgetEventService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
