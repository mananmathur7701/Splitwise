import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpClientModule } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class BackServicesService {

  constructor( private _http:HttpClient) { }

  // ***************** LOGIN ******************************************

  login(email:any,password:any):Observable<any>{

    const requestBody = {
      email: email,
      password: password,
    };
    console.log(requestBody);
    const url = `http://localhost:8080/userDetailsByEmail/${email}`;
    console.log("back service login function called..."+email+password);
    console.log(this._http.get(url));
    console.log(this._http.post("http://localhost:8080/login",requestBody));
    return this._http.post("http://localhost:8080/login",requestBody);

  }

// ******************* SIGNUP ******************************************

  signup(firstName:string,lastName:string,email:string,number:string,password:string):Observable<any>{
 
    
      const requestBody = {
        firstName: firstName,
        lastName: lastName,
        email: email,
        number: number,
        password: password,
      };
      console.log("Service Run")
      console.log(this._http.get("http://localhost:8080/userDetailsById/1"));
    return this._http.post("http://localhost:8080/createUser",requestBody);
  }

  
}

