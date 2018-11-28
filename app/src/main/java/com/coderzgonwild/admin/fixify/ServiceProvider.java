package com.coderzgonwild.admin.fixify;

import java.util.ArrayList;

public class ServiceProvider extends Account {



    private String companyNameContent;
    private String addressContent;
    private String phoneNumberContent;
    private String licensedContent;
    private String aboutContent;

    private ArrayList<Service> servicesProvided;
    private ArrayList<String> servicesAvail;

    public ServiceProvider(String username, String password, String accountType) {
        super(username, password, accountType);
        servicesProvided = new ArrayList<>();
        servicesAvail = new ArrayList<>();
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

}
