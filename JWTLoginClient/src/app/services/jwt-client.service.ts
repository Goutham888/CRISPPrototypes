import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import {  Storerecord } from '../models/store-record';

@Injectable({
  providedIn: 'root'
})
export class JwtClientService {

  tokenString:any;
  constructor(private _httpClient: HttpClient) { }

  public generateToken(request): Observable<any>{//I have it an observable of any b/c it won't allow me to just do string
    this.tokenString= this._httpClient.post('/api/v1/authenticate', request,{responseType:'text' as 'json'});
    //sends the request object with the uname and pass
    return this.tokenString;
    //returns the token string from the /authenticate mapping
    //takes the post url, the request (uname and pass), and the type of result it gets which is text, but converts it to json
  } 

  public welcome(token){
    let tokenStr = 'Bearer '+token;///takes the token input
    const headers = new HttpHeaders().set('Authorization', tokenStr);//puts it into the header
    return this._httpClient.get('/api/v1/', {headers, responseType:'text' as 'json'});//sends the header with the the client
  }

  public getRecords(token): Observable<Storerecord[]>{
    let tokenStr = 'Bearer '+token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this._httpClient.get<Storerecord[]>("/api/v1/records",{headers}).pipe(
      map(response => response)
    )
  }

  public zipItemRequest(recordRequest,token): Observable<Storerecord[]> {
    let tokenStr = 'Bearer '+token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this._httpClient.post<Storerecord[]>("/api/v1/recordRequest",recordRequest,{headers}).pipe(
      map(response => response)
    )
  }

}
