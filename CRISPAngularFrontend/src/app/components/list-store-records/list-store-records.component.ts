import { Component, OnInit } from '@angular/core';
import { StorerecordService } from 'src/app/services/storerecord.service'
import { Storerecord } from 'src/app/models/storerecord'

@Component({
  selector: 'app-list-store-records',
  templateUrl: './list-store-records.component.html',
  styleUrls: ['./list-store-records.component.css']
})
export class ListStoreRecordsComponent implements OnInit {

  records: Storerecord[] = [ ];//this is the array referenced in the .html part of the component
  constructor(private _storeRecordService: StorerecordService) { }//the constructor for the component has the service in it

  ngOnInit(): void {
    this._storeRecordService.getRecords().subscribe(//what does subscribe mean? It is a way of extracting data from an observable<> object
      data => this.records = data//why is data set in this weird way?
    )
  }

}
