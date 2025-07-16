import { inject } from '@angular/core';
import { CanActivateFn } from '@angular/router';
import { SessionStorageService } from '../service/session-storage.service';

export const roleGuard: CanActivateFn = (route, state) => {
  const authorization = inject(SessionStorageService);
  const user = authorization.getUser();
  const allowRoles = route.data['roles'] as string[];

  let verify=false;

  if(user && allowRoles)
  {
    verify = allowRoles.some(role=>user.authoritiesList.includes(role));
  }
  return verify;

};
