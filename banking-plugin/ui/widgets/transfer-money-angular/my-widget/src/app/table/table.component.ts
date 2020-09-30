import { Component, EventEmitter, Input, OnDestroy, OnInit, Output, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Notification } from '../notification.model';
import { MessageService } from '../message.service';
import { Subscription } from 'rxjs';
import { NotificationService } from '../notification.service';

@Component({
  selector: 'app-table.table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit, OnDestroy {
  @Input('notifications') notifications: Notification[];
  @Input('serviceurl') serviceurl: string;

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  displayedColumns: string[] = ['description', 'createdAt'];
  dataSource = new MatTableDataSource<Notification>();
  subscription: Subscription;

  constructor(private messageService: MessageService, private notificationService: NotificationService) {
    this.subscription = this.notificationService.getNotificationsObs().subscribe(
      notifications => {
        this.dataSource = new MatTableDataSource<Notification>(notifications);
        this.dataSource.paginator = this.paginator;
        this.notifications = notifications;
      },
      err => console.log(err)
    );
  }

  ngOnInit(): void {
    if (this.notifications) {
      this.dataSource = new MatTableDataSource<Notification>(this.notifications);
      this.dataSource.paginator = this.paginator;
    }
  }

  sendMessage(updatedNotifiction: Notification): void {
    // send message to subscribers via observable subject
    this.messageService.sendMessage({ text: 'read', notification: updatedNotifiction, description: null, range: null });
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
