package com.example.maino2;

public class user {
    public String UserName;
    public String password;
    public String PhoneNo;
    public String Emailid;

    public user(){

    }

    public user(String userName,String Password,String phoneNo,String mailId){
       this.UserName=userName;
       this.password=Password;
       this.PhoneNo=phoneNo;
       this.Emailid=mailId;
    }


}
