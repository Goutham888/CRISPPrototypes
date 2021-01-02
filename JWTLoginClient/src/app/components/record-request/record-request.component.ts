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
  constructor(private _jwtClientService: JwtClientService) { }

  ngOnInit(): void {
  }

  public sendRequest(){
    this._jwtClientService.tokenString.subscribe(
      data => this.setRequest(this.record, data)
    );
  }

  public setRequest(request, token){
    this._jwtClientService.zipItemRequest(request,token).subscribe(
      data => this.results = data
    );
  }



}
