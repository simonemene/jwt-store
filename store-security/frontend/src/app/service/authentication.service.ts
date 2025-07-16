import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { UserDto } from '../model/UserDto'
import { URL } from '../constant/url.constants';
import { SessionStorageService } from './session-storage.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  urlBase = environment.apiBaseUrl;

  constructor(private http:HttpClient,private sessioneStorageAuth:SessionStorageService) { }


  authentication(user:UserDto)
  {
    this.sessioneStorageAuth.login(user);
    return this.http.get(this.urlBase + URL.AUTH,{observe:'response',withCredentials:true});
  }

  registration(user:UserDto):Observable<UserDto>
  {
    return this.http.post<UserDto>(this.urlBase + URL.REGISTRATION,user);
  }

  getUser():Observable<UserDto>
  {
    return this.http.get<UserDto>(`${this.urlBase}${URL.AUTH}`,{withCredentials:true});
  }

  expired()
  {
    return this.http.post(`${this.urlBase}${URL.SESSIONEXPIRED}`,{},{withCredentials:true});  
  }
}
