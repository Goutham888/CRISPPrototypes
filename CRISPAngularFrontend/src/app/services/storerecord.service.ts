import { Injectable } from '@angular/core';
import { HttpClientModule, HttpClient} from '@angular/common/http'; /*needed to connect to spring backend*/
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import {  Storerecord } from '../models/storerecord';

@Injectable({
  providedIn: 'root'
})
export class StorerecordService {

  private getURL= 'http://localhost:8080/api/v1/records';//this is the url from where the store records are displayed
  //also make sure this is an equal sign bc it won't work otherwise

  constructor(private _httpClient: HttpClient) { }//just a constructor for the service that accepts an http client

  getRecords(): Observable<Storerecord[]> {//takes the records from the above defined url
    return this._httpClient.get<Storerecord[]>(this.getURL).pipe(//what does pipe mean?
      map(response => response)//why does this need to exist here?
    )
  }

  saveRecord(record: Storerecord): Observable<Storerecord> {//this method requires a Storerecord and returns and Observable with Storerecord type
    return this._httpClient.post<Storerecord>(this.getURL, record);//posts the record to the get URL.
    //the getURL is used here b/c the same url is used for posts and gets in the java code
  }

  getRecord(id: number): Observable<Storerecord> {
    return this._httpClient.get<Storerecord>(`${this.getURL}/${id}`).pipe(
      map(response => response)
    )
  }
}
