import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable()
export class SecurityInterceptor implements HttpInterceptor {
  getKeycloakToken() {
    if (
      window &&
      window['entando'] &&
      window['entando'].keycloak &&
      window['entando'].keycloak.authenticated
    ) {
      return window['entando'].keycloak.token;
    }
    return '';
  }

  getDefaultOptions() {
    const token = this.getKeycloakToken();

    return {
      Authorization: `Bearer ${token}`,
      'Content-Type': 'application/json',
    };
  }

  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    const headers = this.getDefaultOptions();

    request = request.clone({
      setHeaders: headers,
    });

    return next.handle(request);
  }
}
