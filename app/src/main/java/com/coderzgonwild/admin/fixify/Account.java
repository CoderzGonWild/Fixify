package com.coderzgonwild.admin.fixify;
import java.io.Serializable;
import java.util.ArrayList;

public class Account implements Serializable {

    private String username;
    private String password;
    private String accountType;
    public ArrayList<Service> servicesProvided = new ArrayList<>();

    public Account(String username, String password, String accountType) {
        this.username = username;
        this.password = password;
        this.accountType = accountType;
    }

    public ArrayList<Service> getServicesProvided() {return servicesProvided;}

    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public String getAccountType() {return accountType;}

    public String toString(){
        return username+password+accountType;
    }


}
