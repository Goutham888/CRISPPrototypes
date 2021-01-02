import { Component, OnInit } from '@angular/core';
import { JwtClientService } from 'src/app/services/jwt-client.service';
import { Storerecord } from 'src/app/models/store-record';

@Component({
  selector: 'app-record-display',
  templateUrl: './record-display.component.html',
  styleUrls: ['./record-display.component.css']
})
export class RecordDisplayComponent implements OnInit {

  tokenString:any;
  records: Storerecord[] = [ ];//this is the array referenced in the .html part of the component
  constructor(private _jwtClientService: JwtClientService) { }//the constructor for the component has the service in it

  ngOnInit(): void {//on init it calls the getRecords
    this._jwtClientService.tokenString.subscribe(
      data => this.getRecords(data)
    );
    
  }

  public getRecords(token){//uses the token to get all the records and sets the record object to the output
    console.log("Records are being brought");
    console.log(token);
    this._jwtClientService.getRecords(token).subscribe(
      data => this.records = data
    );
  }

}
