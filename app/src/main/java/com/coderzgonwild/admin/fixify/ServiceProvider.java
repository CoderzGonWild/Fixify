package com.coderzgonwild.admin.fixify;

import java.util.ArrayList;

public class ServiceProvider {

    private String username;
    private String password;
    private String accountType;
    private ArrayList<Service> servicesProvided = new ArrayList<>();

    public ServiceProvider(String username, String password, String accountType) {
        this.username = username;
        this.password = password;
        this.accountType = accountType;
    }

    public ArrayList<Service> getServicesProvided() {return servicesProvided;}

    public void addService(Service service) {
        servicesProvided.add(service);
    }
    public void deleteService(Service service) {
        servicesProvided.remove(service);
    }

    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public String getAccountType() {return accountType;}

    public String toString(){
        return username + password + accountType;
    }
}
