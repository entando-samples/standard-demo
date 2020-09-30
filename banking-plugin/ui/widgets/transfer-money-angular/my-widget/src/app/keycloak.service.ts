import { Injectable, OnDestroy, OnInit } from '@angular/core';
import { Observable, Subject, Subscription } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class KeycloakService {
  keycloak = new Subject<object>();
  userId = new Subject<string>();

  constructor() {}

  getUseridObs(): Observable<string> {
    return this.userId.asObservable();
  }
}
