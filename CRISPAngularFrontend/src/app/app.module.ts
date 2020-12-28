import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HttpClient} from '@angular/common/http'; /*needed to connect to spring backend*/
import { RouterModule, Routes } from '@angular/router';//needed to route between pages
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { ListStoreRecordsComponent } from './components/list-store-records/list-store-records.component';
import { AddRecordComponent } from './components/add-record/add-record.component';

const routes: Routes = [
  {path: 'records', component: ListStoreRecordsComponent},//when /records is called use the list-store-records component
  {path: 'addrecord', component: AddRecordComponent},//when /addrecords is called use the add-record component
  {path: 'editrecord/:id', component: AddRecordComponent},//when /editrecords/:id it goes to the addrecord component. the :id is path variable
  {path: '', redirectTo: '/records', pathMatch:'full'}//the default mapping is to records
];

@NgModule({
  declarations: [
    AppComponent,
    ListStoreRecordsComponent,
    AddRecordComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,//need to add the http client module to the import section
    RouterModule.forRoot(routes),//need to add the router module to the import section
    FormsModule//for forms
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
