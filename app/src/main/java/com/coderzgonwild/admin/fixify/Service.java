package com.coderzgonwild.admin.fixify;

public class Service {



    private String name;
    private Double rate;
    private int rating;

    public Service (String name, Double rate ) {
        this.name = name;
        this.rate = rate;
    }

    public String getName() {return name;}
    public Double getRate() {return rate;}
    public int getRating() {return rating;}
    public String toString(){
        return name+rate;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setRate(Double rate) {
        this.rate = rate;
    }

}
