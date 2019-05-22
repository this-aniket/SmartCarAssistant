package com.smartcarassistant;

/**
 * Created by lenovo on 25-02-2018.
 */

public class Car_user {
    String name;
    String email;
    String mobileno;
    String carno;
    String dop;
    String username;
    String password;
    String confirmpassword;

    public Car_user(String Name, String Email,String Mobileno, String Carno, String Dop, String Username, String Password, String Confirmpassword) {
        name=Name;
        email=Email;
        mobileno=Mobileno;
        carno=Carno;
        dop=Dop;
        username=Username;
       // password=Password;
       // confirmpassword=Confirmpassword;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileno() {
        return mobileno;
    }

    public String getCarno() {
        return carno;
    }

    public String getDop() {
        return dop;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }


}
