import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';

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

    return this._http.post("http://localhost:8080/createUser",requestBody);
  }

  // ******************* OTP ******************************************

  otpverify(otp:string)
  {

    console.log(otp+"2")
    return this._http.post("http://localhost:8080/otpVerify", otp);
    
  }

  // ********************* TO SHOW ALL GROUPS OF USER ****************

  groupsOfUser(id:number):Observable<any>
  {

    const header = new HttpHeaders({
      'Access-Control-Allow-Origin' : '*'
    });
    return this._http.get("http://localhost:8080/groupsOfUser/"+id, {headers : header}); 
  
  }

  //************* TO ADD NEW GROUP *************************
  addNewGroup(id:number, groupName:string):Observable<any>
  {
    const requestBody ={
       id:id,
       groupName: groupName,
        
    };
    return this._http.post("http://localhost:8080/createGroup",requestBody);
  }  
 
  

  
}

