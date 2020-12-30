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


  constructor(private secService: JwtClientService) { }

  ngOnInit(): void {


  }

  onSubmit(form: NgForm) {
      console.log('Your form data : ', form.value);
  }

  public getAccessToken(form: NgForm){

    const response = this.secService.generateToken(form.value)
    response.subscribe(
      data => this.accessApi(data)
    );
    
  }

  public accessApi(token){
    console.log("Get request sent")
    this.secService.welcome(token).subscribe(
      data => this.loginMessage=data
    );
    

  }
}
