import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms'
import { RouterModule, Routes } from '@angular/router';//needed to route between pages

import { AppComponent } from './app.component';
import { SecurityComponent } from './components/security/security.component';
import { RecordDisplayComponent } from './components/record-display/record-display.component';
import { RecordRequestComponent } from './components/record-request/record-request.component';

const routes: Routes = [//defines what routes go to what components
  {path: 'records', component: RecordDisplayComponent},//when /records is called use the recordsdisplay component
  {path: 'security', component: SecurityComponent},//when /security is called use the security component
  {path: 'recordRequest', component: RecordRequestComponent},//when /recordRequest is called use the recordRequest component
  {path: '', redirectTo: '/security', pathMatch:'full'}//the default mapping is to security
];

@NgModule({
  declarations: [
    AppComponent,
    SecurityComponent,
    RecordDisplayComponent,
    RecordRequestComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(routes)//need to add the router module to the import section
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
