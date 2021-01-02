import { Component, OnInit } from '@angular/core';
import { JwtClientService } from 'src/app/services/jwt-client.service';
import { NgForm } from '@angular/forms';
import { RecordRequest } from 'src/app/models/record-request';
import { Storerecord } from 'src/app/models/store-record';

@Component({
  selector: 'app-record-request',
  templateUrl: './record-request.component.html',
  styleUrls: ['./record-request.component.css']
})
export class RecordRequestComponent implements OnInit {

  record: RecordRequest = new RecordRequest();
  results: Storerecord[] = [ ];
  constructor(private _jwtClientService: JwtClientService) { }//service is injected into the constructor

  ngOnInit(): void {
  }

  public sendRequest(){//the constructor takes the tokenString from the client service
    this._jwtClientService.tokenString.subscribe(
      data => this.setRequest(this.record, data)//the token is used to send the request
    );
  }

  public setRequest(request, token){//actually sends the request object to the client service
    //the record request object is used to fill in the user's fields and send it to the backend
    this._jwtClientService.zipItemRequest(request,token).subscribe(
      data => this.results = data
    );
  }



}
