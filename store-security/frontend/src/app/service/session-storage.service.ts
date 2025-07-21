import { computed, Injectable, signal } from '@angular/core';
import { UserDto } from '../model/UserDto';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class SessionStorageService {

  private readonly user = signal<UserDto | null>(null);

  readonly isAuthenticated = computed(() => {
  const user = this.user();
  return user != null && (user.authorization ==='AUTH');
});


  constructor(private router:Router)
   { 
     const userDetails = window.sessionStorage.getItem('user-details');
     if(userDetails)
     {
      try
      {
        const parsed = JSON.parse(userDetails);
        if(parsed.username && parsed.password)
        {
           this.user.set(parsed);
        }    
      }catch(e)
      {
        console.warn("Error authentication user:");
      }
     }
   }

   login(user:UserDto)
   {    
      user.authorization = 'AUTH';
      window.sessionStorage.setItem('user-details',JSON.stringify(user));
      this.user.set(user);
   }

   logout()
   {
    this.user.set(null);
    this.router.navigate(['/login']);
   }

   getUser(): UserDto | null
   {
    return this.user();
   }

   
}
