import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { SeedCard } from '../card.model';

@Injectable({
  providedIn: 'root'
})
export class CardService {
  constructor(private httpClient: HttpClient) {}

  getSeedscard(params: any = {}): Observable<any> {
    const { id, options, cardname } = params;
    const url = `${environment}/banking/api/${cardname}s/${id}`;
    return this.httpClient.get<any>(url);
  }

  getSeedsCardByUserID(userID, cardName) {
    const url = `${environment.domainUrl}/banking/api/${cardName}s/user/${userID}`;
    return this.httpClient.get<SeedCard>(url);
  }
}
