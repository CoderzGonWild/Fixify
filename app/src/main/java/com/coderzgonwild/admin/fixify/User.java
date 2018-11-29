package com.coderzgonwild.admin.fixify;

import java.util.ArrayList;

public class User extends Account {

    private ArrayList<Service> servicesBooked;
    private ArrayList<ServiceProvider> providerBooked;
    private ArrayList<String> bookedTimes;
    private String username;
    private String password;
    private String accountType;


    public User(String username, String password, String accountType) {
        super(username, password, accountType);
        servicesBooked = new ArrayList<>();
        providerBooked = new ArrayList<>();
        bookedTimes = new ArrayList<>();

    }

    public void addServiceBooked(Service service) {
        servicesBooked.add(service);
    }

    public void removeServiceBooked(Service service) {
        servicesBooked.remove(service);
    }

    public void addProviderBooked(ServiceProvider provider) { providerBooked.add(provider); }

    public void removeProviderBooked(ServiceProvider provider) { providerBooked.remove(provider); }

    public void addTime(String time) {
        bookedTimes.add(time);
    }

    public ArrayList<String> getBookedTimes() {return bookedTimes;}

//    @Override
    public String getUsername() {return username;}
//
//    public String getAccountType(){return password;}
//
//    public String getPassword() {return accountType;}

    public ArrayList<Service> getServicesBooked() {return servicesBooked;}
    public ArrayList<ServiceProvider> getProviderBooked(){return providerBooked;}
}
