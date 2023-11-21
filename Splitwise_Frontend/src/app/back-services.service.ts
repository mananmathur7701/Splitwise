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
  addNewGroup(id:number, groupName:any):Observable<any>
  {
    const requestBody ={
       id:id,
       groupName: groupName,
        
    };
    return this._http.post("http://localhost:8080/createGroup",requestBody);
  }  


  //********EK USER KO KAHAN SE KITNE PAISE MILENGE WO LIST */
  expensesOfUserWhereOwes(id:number):Observable<any>
  {
    const header= new HttpHeaders({
      "Access-Control-Allow-Origin" : "*"

    });
    return this._http.get("http://localhost:8080/showAllPaymentsDoneOfExpense/"+id, {headers : header});
  }

  //**********EK USER KO KIDHR KITNE PAISE DENE HAI WO LIST */
  expensesOfUserWhereOwed(id:number):Observable<any>
  {
    const header= new HttpHeaders({
      "Access-Control-Allow-Origin" : "*"
    });
    return this._http.get("http://localhost:8080/SharesOfParticularTransaction/"+id,{headers: header});
  }

  //*************ADD EXPENSE */
  // addExpense():Observable<any>
  // {
  //   const requestBody={
  //     id:id,

  //   };

  // }
 

  //*******EK GROUP K SAARE EXPENSES DIKHAYEGA */
  showAllGroupExpense(groupId:number): Observable<any>
  {
    const header= new HttpHeaders({
      "Access-Control-Allow-Origin" : "*"
    });
    return this._http.get("http://localhost:8080/showAllGroupExpense/"+groupId, {headers: header});
  }

  //*****EK USER KO TOTAL KITNE PAISE DENE HAI USKA SUM */

  // sumOfDeneKaMoney():Observable<any>
  // {
  //   const header = new HttpHeaders({

  //   });
  //   return this._http.get("")
  // }

  //*************EK USER KO TOTAL KITNE PAISE MILNE HAI USKA SUM */
  

  // sumOfLeneKaMoney():Observable<any>
  // {
  //   const header = new HttpHeaders({

  //   });
  //   return this._http.get("")
  // }

  //***********TOTAL BALANCE DIKHANE KO LENE MINUS DENE KA EITHER PLUS OR MINUS  */
  // totalBalance():Observable<any>
  // {
  //   const header = new HttpHeaders({

  //   });
  //   return this._http.get("")
  // }

  //***********LENE KA SUM, DENE KA SUM & BALANCE SAB SATH MEIN */

  lenaDenaBalance(id:number): Observable<any>
  {
    const header = new HttpHeaders({
      
    });
    return this._http.get("http://localhost:8080/BalancesOfUsers/"+id)
  }

  dashboardKaData(id:number): Observable<any>
  {
  //   const header = new HttpHeaders({
      
  //   });
  //   return this._http.get("http://localhost:8080/BalancesOfUsers/{id}")
  // }
  const header= new HttpHeaders({
    "Access-Control-Allow-Origin" : "*"
  });
  return this._http.get("http://localhost:8080/BalancesOfUsers/"+id,{headers: header});
  }
  
}
 