import { Component, OnInit } from '@angular/core';
import { JwtClientService } from 'src/app/services/jwt-client.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-security',
  templateUrl: './security.component.html',
  styleUrls: ['./security.component.css']
})
export class SecurityComponent  {
  secToken:any='unchanged';
  loginMessage:any='unchanged';


  constructor(private secService: JwtClientService) { }//the client service is injected into the constructor

  ngOnInit(): void {


  }


  public getAccessToken(form: NgForm){//the form is an argument

    const response = this.secService.generateToken(form.value)//the token is generated directly form the form data
    response.subscribe(//the subscribe method is used to get values from Observable<> objects
      data => this.accessApi(data)//calls the accessAPI method
    );
    
  }

  public accessApi(token){//this uses the token to get a login confirmation from the backend
    console.log("Get request sent")
    this.secService.welcome(token).subscribe(
      data => this.loginMessage=data
    );
    

  }
}
