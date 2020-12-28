import { Component, OnInit } from '@angular/core';
import { Storerecord } from 'src/app/models/storerecord';
import { StorerecordService } from 'src/app/services/storerecord.service';
import { Router } from '@angular/router';//needed to route between pages

@Component({
  selector: 'app-add-record',
  templateUrl: './add-record.component.html',
  styleUrls: ['./add-record.component.css']
})
export class AddRecordComponent implements OnInit {

  record: Storerecord = new Storerecord();
  constructor(private _storeRecordService: StorerecordService,
              private _router: Router) { }

  ngOnInit(): void {
  }

  saveRecord(){
    this._storeRecordService.saveRecord(this.record).subscribe(//the save record is called with an internal Storerecord object
      data => {//the return value isn't really important b/c its a post method, but we're logging it
        console.log('response', data);
        this._router.navigateByUrl("/records");//this redirects the page to /records after the record was inputted
      }
    )
  }
}
