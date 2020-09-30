import { Component, Input, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ModalComponent } from '../modal/modal.component';
import { Notification } from '../notification.model';
import { environment } from '../../environments/environment';
import { Subscription } from 'rxjs';
import { MessageService } from '../message.service';
import { NotificationService } from '../notification.service';

@Component({
  selector: 'app-notification-button',
  templateUrl: './notification-button.component.html',
  styleUrls: ['./notification-button.component.css']
})
export class NotificationButtonComponent implements OnInit {
  env = environment;
  @Input() serviceurl: string;
  @Input() title: string;
  @Input() keycloak;
  notifications: Notification[];
  total = 0;
  srcImg: string = null;

  messages: any[] = [];
  subscription: Subscription;

  constructor(public matDialog: MatDialog, private notificationService: NotificationService, private messageService: MessageService) {
    this.getDati();
  }

  getDati() {
    // subscribe to home component messages
    this.subscription = this.messageService.getMessage().subscribe(message => {
      if (message) {
        const userID = this.keycloak.idTokenParsed.sub;
        switch (message.text) {
          case 'read': {
            this.messages.push(message);
            this.handleClickEmitter(message.notification);
            this.notificationService.getNotifications(userID, this.serviceurl, null).subscribe(
              (response: Notification[]) => {
                this.notifications = this.notificationService.sortNotifications(response);
                this.counter();
              },
              error => console.log(error)
            );
            break;
          }
        }
      } else {
        this.messages = [];
      }
    });
  }

  ngOnInit() {
    if (this.serviceurl) {
      const userID = this.keycloak.idTokenParsed.sub;
      this.srcImg = this.serviceurl + '/entando-de-app/cmsresources/widgets/list-item/static/media/transfermoney.98b87291.svg';

      this.notificationService.getNotifications(userID, this.serviceurl, null).subscribe(
        (response: Notification[]) => {
          this.notifications = this.notificationService.sortNotifications(response);
          this.counter();
        },
        error => console.log(error)
      );
    }
  }

  counter() {
    let count = 0;
    if (this.notifications) {
      this.notifications.forEach(n => (n.read ? null : count++));
    }
    this.total = count;
  }

  openModal() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.id = 'modal-component';
    // dialogConfig.minHeight = '70vh';
    dialogConfig.maxHeight = '80vh';
    dialogConfig.maxWidth = '80vw';
    dialogConfig.width = '50vw';
    const modalDialog = this.matDialog.open(ModalComponent, dialogConfig);
    modalDialog.componentInstance.notifications = this.notifications;
    modalDialog.componentInstance.title = this.title;
    modalDialog.componentInstance.serviceurl = this.serviceurl;
    modalDialog.componentInstance.keycloak = this.keycloak;
  }

  handleClickEmitter(updatedNotifiction) {
    window.open(
      `${this.serviceurl}/entando-de-app/cmsresources/widgets/angular/list-item/static/media/Entando_Development_Methodology.pdf`,
      '_blank'
    );
    if (updatedNotifiction.read) {
      return;
    }
    const docFound = this.notifications.find((el: Notification) => el.id === updatedNotifiction.id);
    if (docFound) {
      docFound.read = true;
      this.notifications.splice(this.notifications.indexOf(updatedNotifiction), 1, docFound);
    }
    this.putDocumentRead(docFound);
  }

  putDocumentRead(newDoc) {
    this.notificationService.putNotification(newDoc, this.serviceurl).subscribe(
      () => {
        const userID = this.keycloak.idTokenParsed.sub;
        this.notificationService.getNotifications(userID, this.serviceurl, null).subscribe(
          (response: Notification[]) => {
            this.notifications = this.notificationService.sortNotifications(response);
            this.counter();
          },
          error => console.log(error)
        );
      },
      error => console.log(error)
    );
  }
}
