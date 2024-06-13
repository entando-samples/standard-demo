import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SeedCard } from '../card.model';

@Injectable({
  providedIn: 'root'
})
export class CardService {
  constructor(private httpClient: HttpClient) {}

  getSeedscard(params: any = {}): Observable<any> {
    const { url, id, cardname } = params;
    const fullUrl = `${url}/api/${cardname}s/${id}`;
    return this.httpClient.get<any>(fullUrl);
  }

  getSeedsCardByUserID(url, userID, cardName) {
    const fullUrl = `${url}/api/${cardName}/user/${userID}`;
    return this.httpClient.get<SeedCard>(fullUrl);
  }
}
