import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class MessageService {
  private subject = new Subject<any>();

  sendMessage({ text, notification, description, range }) {
    this.subject.next({ text, notification, description, range });
  }

  clearMessages() {
    this.subject.next();
  }

  getMessage(): Observable<any> {
    return this.subject.asObservable();
  }
}
