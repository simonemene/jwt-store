import { HttpErrorResponse, HttpHeaders, HttpInterceptorFn } from '@angular/common/http';
import { UserDto } from '../model/UserDto';
import { tap } from 'rxjs';
import { inject } from '@angular/core';
import { Router } from '@angular/router';


export const httpInterceptor: HttpInterceptorFn = (req, next) => {
   
   const router=inject(Router);

  let user = new UserDto();
  let httpHeaders = new HttpHeaders();

  if(window.sessionStorage.getItem('user-details'))
  {
     user = JSON.parse(window.sessionStorage.getItem('user-details')!);
  }
  
  if(user.username && user.password)
  {
     httpHeaders = httpHeaders.append('Authorization','Basic ' + window.btoa(user.username + ":" + user.password));
  }

  let csrf = sessionStorage.getItem('XSRF-TOKEN');
  if(csrf)
  {   
      httpHeaders = httpHeaders.append('X-XSRF-TOKEN',csrf);
  }
  httpHeaders = httpHeaders.append('X-Requested-With','XMLHttpRequest');

  const handleHeader = req.clone(
   {
      headers:httpHeaders
   }
  )


   return next(handleHeader).pipe(tap(
      (err:any)=>
      {
         if(err instanceof HttpErrorResponse && err.status !== 401)
         {
            router.navigate(['/login']);
         }
      }
   ));
};
