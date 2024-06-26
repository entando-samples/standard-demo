import { Component, Input, OnInit } from '@angular/core';
import { EVENT_KEY, WidgetEventService } from 'src/app/core/service/widget-event.service';
import { environment } from 'src/environments/environment';
import { SeedCard } from '../card.model';
import { CardService } from './card.service';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.scss']
})
export class CardComponent implements OnInit {
  env = environment;
  assetsPath = "";
  firstCall = true;
  cardName;

  @Input() config;
  @Input() keycloak;

  seedCard: SeedCard;

  constructor(private cardService: CardService, private widgetEventService: WidgetEventService) {}

  ngOnInit() {
    const userID = this.keycloak.idTokenParsed.preferred_username;
    const { params, systemParams } = this.config || {};
    const { cardname } = params || {};
    this.cardName = cardname;
    const { api } = systemParams || {};
    const url = api && api['sd-banking-api'].url;

    this.assetsPath = environment.assetsPath;
    let publicpath = window.entando?.widgets['sd-seeds-card-angular']?.basePath;
    if (publicpath && publicpath.slice(-1) !== '/') {
      this.assetsPath = `${publicpath}/${environment.assetsPath}`;
    }

    this.cardService.getSeedsCardByUserID(url, userID, this.cardName).subscribe(
      (response: SeedCard) => {
        this.seedCard = response;
        if (this.cardName === 'checking' && this.firstCall) {
          this.firstCall = false;
          this.onDetail();
        }
      },
      error => console.error(error)
    );
  }

  onDetail() {
    if (this.seedCard && this.seedCard.id) {
      const payload = { cardname: this.cardName, accountID: this.seedCard.id };
      this.widgetEventService.createWidgetEventPublisher(EVENT_KEY.OUTPUT_EVENT_TYPES.transactionsDetail, payload);
    }
  }
}
