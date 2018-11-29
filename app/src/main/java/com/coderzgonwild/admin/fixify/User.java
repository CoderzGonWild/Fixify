package com.coderzgonwild.admin.fixify;

import java.util.ArrayList;

public class User extends Account {

    private ArrayList<Service> servicesBooked;
    private String username;
    private String password;
    private String accountType;


    public User(String username, String password, String accountType) {
        super(username, password, accountType);
        servicesBooked = new ArrayList<>();


    }


    @Override
    public String getUsername() {return username;}

    public String getAccountType(){return password;}

    public String getPassword() {return accountType;}

    public ArrayList<Service> getServicesBooked() {return servicesBooked;}
}
