import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { MessageService } from '../message.service';
import { getDateFromRange, getStartMonthFromDate, parseDateToString } from '../filters/utils';
import { Notification } from '../notification.model';
import { Subscription } from 'rxjs';
import { NotificationService } from '../notification.service';

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.css']
})
export class ModalComponent implements OnInit, OnDestroy {
  @Input('notifications') notifications: Notification[];
  @Input('title') title: string;
  @Input('serviceurl') serviceurl: string;
  @Input() keycloak;
  srcImg: string = null;
  descriptionFilter = '';
  range: null;
  subscription: Subscription;

  constructor(
    public dialogRef: MatDialogRef<ModalComponent>,
    private messageService: MessageService,
    private notificationService: NotificationService
  ) {}

  ngOnInit() {
    const userID = this.keycloak.idTokenParsed.sub;
    this.srcImg = this.serviceurl + '/entando-de-app/cmsresources/widgets/list-item/static/media/transfermoney.98b87291.svg';
    this.subscription = this.messageService.getMessage().subscribe(message => {
      this.notificationService.getNotifications(userID, this.serviceurl, null).subscribe(
        (response: Notification[]) => {
          this.notifications = this.notificationService.sortNotifications(response);
          this.notificationService.notifications.next(this.notifications);
          // this.counter();
        },
        error => console.log(error)
      );
    });
  }

  closeModal() {
    this.descriptionFilter = '';
    this.range = null;
    this.getNotificationsWithFilters(null);
    this.dialogRef.close();
  }

  onChangeDescription(evt) {
    this.descriptionFilter = evt.target.value;
  }

  sendMessage(event): void {
    // send message to subscribers via observable subject
    if (event && event.target && event.target.value && event.target.value !== '' && event.target.value !== this.range) {
      this.range = event.target.value;
    }
    const today = new Date();
    let dateA = parseDateToString({ date: today });
    const dateB = parseDateToString({ date: today });

    let newRange = {
      dateA,
      dateB
    };
    switch (this.range) {
      case '90': {
        dateA = getDateFromRange({ d: today, range: 90 });
        newRange.dateA = dateA;
        break;
      }
      case 'month': {
        dateA = getStartMonthFromDate({ d: new Date() });
        newRange.dateA = dateA;
        break;
      }
      default: {
        newRange = null;
      }
    }
    // this.messageService.sendMessage({text: 'get', notification: null, description: this.descriptionFilter, range: newRange});
    this.getNotificationsWithFilters(newRange);
  }

  getNotificationsWithFilters(newRange) {
    const filters = [];
    if (this.descriptionFilter !== '') {
      filters.push({
        type: 'description.contains',
        value: this.descriptionFilter
      });
    }
    if (newRange) {
      filters.push({
        type: 'createdAt.lessThanOrEqual',
        value: newRange.dateB
      });
      filters.push({
        type: 'createdAt.greaterThanOrEqual',
        value: newRange.dateA
      });
    }
    this.notificationService.getNotifications(this.keycloak.idTokenParsed.sub, this.serviceurl, filters).subscribe(
      (response: Notification[]) => {
        this.notifications = this.notificationService.sortNotifications(response);
        this.notificationService.notifications.next(this.notifications);
      },
      error => console.log(error)
    );
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
