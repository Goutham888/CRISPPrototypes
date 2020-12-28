import { Component, OnInit } from '@angular/core';
import { Storerecord } from 'src/app/models/storerecord';
import { StorerecordService } from 'src/app/services/storerecord.service';
import { Router, ActivatedRoute } from '@angular/router';//needed to route between pages

@Component({
  selector: 'app-add-record',
  templateUrl: './add-record.component.html',
  styleUrls: ['./add-record.component.css']
})
export class AddRecordComponent implements OnInit {

  record: Storerecord = new Storerecord();
  constructor(private _storeRecordService: StorerecordService,
              private _router: Router,
              private _activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {//the stuff in here makes sure that the values are filled in when editing a record
    const idPresent = this._activatedRoute.snapshot.paramMap.has('id');
    if(idPresent){
      const id = +this._activatedRoute.snapshot.paramMap.get('id');//the + in the beginning converts it to a number
      this._storeRecordService.getRecord(id).subscribe(
        data => this.record = data
      )
    }
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
