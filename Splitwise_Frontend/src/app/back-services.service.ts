import { Injectable } from '@angular/core';
import { Observable, pipe } from 'rxjs';
import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { createMayBeForwardRefExpression } from '@angular/compiler';
import { group } from '@angular/animations';

@Injectable({
  providedIn: 'root'
})
export class BackServicesService {

  groupKaId: any;
  constructor( private _http:HttpClient) { }
  
  getToken(){
    return localStorage.getItem("token");
}

  // ***************** LOGIN ******************************************

  login(email:any,password:any):Observable<any>{
    

    const requestBody = {
      username: email,
      password: password,
    };
    return this._http.post("http://localhost:8080/auth/login",requestBody);

  }

  public getCurrentUser(){
    return this._http.get("http://localhost:8080/auth/get-current-user");
  }

  //*****************Check For Login*******************************/
  public isLoggedIn(){
    let tokenstr =  localStorage.getItem('token');
    if(tokenstr==undefined || tokenstr==null || tokenstr==''){
      return false;
    }
    else{
      return true;
    }
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
  addNewGroup(id:String, groupName:any):Observable<any>
  {
    const requestBody ={
       userId:id,
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

  
 

  //*******EK GROUP K SAARE EXPENSES DIKHAYEGA */
  showAllGroupExpense(groupId:number): Observable<any>
  {
    const header= new HttpHeaders({
      "Access-Control-Allow-Origin" : "*"
    });
    return this._http.get("http://localhost:8080/showAllGroupExpense/"+groupId, {headers: header});
  }

  

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



  //

  showGroupKaName(groupId:number): Observable<any>
  {
    const header= new HttpHeaders({
      "Access-Control-Allow-Origin" : "*"
    });
    return this._http.get("http://localhost:8080/groupInfoById/"+groupId, {headers: header})
    .pipe(
      map((response: any) => response.groupName) // Extract the group name from the response
    );
    
  }

  //      ADD NEW MEMBERS TO A GROUP

  addMembersToGroup(id: any, email:any) : Observable<any>
  {
    
    return this._http.post("http://localhost:8080/addUserToGroup/"+id,email);

  }

  //     REMOVE MEMBERS FROM GROUP

  removeMemberFromGroup(userEmail: string, groupId: any) :Observable<any>
  {
    const requestBody = {
      userEmail:userEmail
    };
    return this._http.post("http://localhost:8080/deleteUserFromGroup/"+groupId,requestBody);
  }

  //      SHOW GROUP K SAARE MEMBERS

  showGroupKeMembers(groupId: number) : Observable<any>
  {
    this.groupKaId=groupId;
    const header = new HttpHeaders({
      "Access-Control-Allow-Origin" : "*"
    });
    return this._http.get("http://localhost:8080/groupMembers/"+groupId, {headers: header})
  }

  //   FRIENDS YA FIR LEDGER KA LIST

  showUserKeFriends(Id: number) : Observable<any>
  {
    const header = new HttpHeaders({
      "Access-Control-Allow-Origin" : "*"
    });
    return this._http.get("http://localhost:8080/LedgerOfUser/"+Id, {headers: header})
  }


  //           ADD EXPENSE



  addExpense(payload:any) : Observable<any>
  {
    console.log("yeh data jara hai add expense mein",payload);
    
   return this._http.post("http://localhost:8080/addExpenseToGroup",payload);
  }



  //    USER KA SAARA EXPENSE LIST FOR HOME PAGE


  showUserKaExpenses():Observable<any>
  {
    const header = new HttpHeaders({
      "Access-Control-Allow-Origin" : "*"
    });
    return this._http.get("http://localhost:8080//", {headers: header})
  }

  //     EDIT GROUP KA NAME

  changeGroupKaName(groupId: any,groupName:any): Observable<any>
  {

    const requestBody = {
      groupName: groupName
    };
    return this._http.post("http://localhost:8080/editGroup/"+groupId,requestBody);
  }

  //      DELETE ENTIRE GROUP

  deleteEntireGroup(groupId:any): Observable<any>
  {
    
    return this._http.delete("http://localhost:8080/deleteGroup/"+groupId);
  }

  //    HOME PAGE K LIYE TRANSACTIONS KI LIST K LIYE BACKSERVICES

  listOfPayementsDoneByUser(id:number):Observable<any>
  {
    const header= new HttpHeaders({
      "Access-Control-Allow-Origin" : "*"

    });
    return this._http.get("http://localhost:8080/showAllPaymentsDoneByUser/"+id, {headers : header});
  }

  listOfPaymentsDoneForUser(id:number) : Observable<any>
  {
    const header= new HttpHeaders({
      "Access-Control-Allow-Origin" : "*"

    });
    return this._http.get("http://localhost:8080/expenseSplitWhereUserNeedsToPay/"+id, {headers : header});
  }


  //     EXPENSE DETAILS KI BACK SERVICES

  showExpenseKaDetails(expenseId:any): Observable<any>
  {
    const header = new HttpHeaders({
      "Access-Control-Allow-Origin" : "*"
    });
    return this._http.get("http://localhost:8080/expenseInfoById/"+expenseId, {headers: header})
  }
  

  expenseDetailsKeLiyeExpenseSplitKaData(expenseId:any):Observable<any>
  {
    const header = new HttpHeaders({
      "Access-Control-Allow-Origin" : "*"
    });
    return this._http.get("http://localhost:8080/SharesOfParticularTransaction/"+expenseId, {headers: header})
  }


  expenseDetailsKeLiyePaymentSplitKaData(expenseId:any):Observable<any>
  {
    const header = new HttpHeaders({
      "Access-Control-Allow-Origin" : "*"
    });
    return this._http.get("http://localhost:8080/showAllPaymentsDoneOfExpense/"+expenseId, {headers: header})
  }


  //    PROFILE PAGE KI BACK SERVICES

  editProfile(id:number,firstName:string,lastName:string,email:string,number:string): Observable<any>
  {
    const requestBody = {
      firstName: firstName,
      lastName: lastName,
      email: email,
      number: number
    };
    return this._http.put("http://localhost:8080/editDetails/"+id, requestBody);
  }



  editPassword(id:number, oldPassword:string,newPassword:string): Observable<any>
  {
    const requestBody = {
      newPassword: newPassword ,
      oldPassword: oldPassword
      
    };
    return this._http.put("http://localhost:8080/editPassword/"+id, requestBody);
  }

  deleteProfile(id:number,password:string) : Observable<any>
  {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      body: password
    };
    return this._http.delete("http://localhost:8080/deleteUser/"+id,httpOptions);
  }


  showUserDetails(id:number) : Observable<any>
  {
    const header = new HttpHeaders({
      "Access-Control-Allow-Origin" : "*"
    });
    return this._http.get("http://localhost:8080/userDetailsById/"+id, {headers: header})
  }

  //    FRIENDS K LIYE SETTLEMENT TRANSACTION WLA BACKSERVICES

  addNewSquareOffTransaction(payeeId:number, payerId:number, settlementAmount:number) : Observable<any>
  {

    const requestBody = {
      amount: settlementAmount,
      payerId:payerId,
      payeeId: payeeId
        
    };
    console.log('this is going to backend', requestBody);
    
    return this._http.post("http://localhost:8080/addSquareOffTransaction",requestBody);
  }


  deleteExpense(id:number) : Observable<any>
  {
    return this._http.delete("http://localhost:8080/deleteExpense/"+id);
  }


  listOfSquareOffTransactionOfUser(id:number) : Observable<any>
  {
    const header= new HttpHeaders({
      "Access-Control-Allow-Origin" : "*"

    });
    return this._http.get("http://localhost:8080/showTransactionListOfUser/"+id, {headers : header});
  }


  editExpense(id:number, payload:any) : Observable<any>
  {
    return this._http.put("http://localhost:8080/editExpense/"+id, payload);
  }
}




