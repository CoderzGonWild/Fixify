package com.coderzgonwild.admin.fixify;

import java.util.ArrayList;

public class ServiceProvider extends Account {



    private String companyNameContent;
    private String addressContent;
    private String phoneNumberContent;
    private String licensedContent;
    private String aboutContent;
    private double rating;
    private double ratingString;
    private int numberofratings;
    private int sumofratings;

    private ArrayList<Service> servicesProvided;
    private ArrayList<String> servicesAvail;

    public ServiceProvider(String username, String password, String accountType) {
        super(username, password, accountType);
        servicesProvided = new ArrayList<>();
        servicesAvail = new ArrayList<>();
        rating = 0;
        numberofratings = 0;
        sumofratings = 0;
    }

    public ArrayList<Service> getServicesProvided() {return servicesProvided;}

    public void setServicesProvided(ArrayList<Service> servicesProvided) {
        this.servicesProvided = servicesProvided;
    }



    public ArrayList<String> getServicesAvail() {
        return servicesAvail;}

    public String getAddressContent() {
        return addressContent;
    }

    public void setAddressContent(String addressContent) {
        this.addressContent = addressContent;
    }

    public String getPhoneNumberContent() {
        return phoneNumberContent;
    }

    public void setPhoneNumberContent(String phoneNumberContent) {
        this.phoneNumberContent = phoneNumberContent;
    }

    public String getLicensedContent() {
        return licensedContent;
    }

    public void setLicensedContent(String licensedContent) {
        this.licensedContent = licensedContent;
    }

    public String getAboutContent() {
        return aboutContent;
    }

    public void setAboutContent(String aboutContent) {
        this.aboutContent = aboutContent;
    }


    public String getCompanyNameContent() {
        return companyNameContent;
    }

    public void setCompanyNameContent(String companyNameContent) {
        this.companyNameContent = companyNameContent;
    }
    public void setRating (int rate){
        numberofratings = numberofratings + 1;
        sumofratings = sumofratings + rate;
        rating = sumofratings/numberofratings;

    }
    public double getProviderRating(){return rating;}

    public String toString(){
        if(numberofratings == 0){return super.getUsername()+": no ratings yet.";}
        else{
            return super.getUsername()+": rated "+getProviderRating()+" stars.";
        }
    }

}
