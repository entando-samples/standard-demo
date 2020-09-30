import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { Notification } from './notification.model';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {
  notifications = new Subject<Notification[]>();
  constructor(private httpClient: HttpClient) {}

  getNotifications(id, serviceurl, filters) {
    const url = `${serviceurl}/banking/api/alerts`;
    let params = new HttpParams();
    params = params.append('userId.equals', id);
    if (filters) {
      filters.forEach(filter => {
        params = params.append(filter.type, filter.value);
      });
    }
    const options = {
      params
    };
    return this.httpClient.get<Notification[]>(url, options);
  }

  sortNotifications(notifications: Notification[]) {
    return notifications.slice().sort((a: Notification, b: Notification) => {
      const aDate: any = new Date(a.createdAt);
      const bDate: any = new Date(b.createdAt);
      let rv: any;
      rv = bDate - aDate;
      if (rv === 0) {
        rv = a.description.localeCompare(b.description);
      }
      return rv;
    });
  }

  putNotification(notification, serviceurl) {
    const url = serviceurl + '/banking/api/alerts';
    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    return this.httpClient.put<any>(url, notification, options);
  }

  getNotificationsObs(): Observable<any> {
    return this.notifications.asObservable();
  }
}
