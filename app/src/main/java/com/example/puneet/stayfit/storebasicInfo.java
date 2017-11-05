package com.example.puneet.stayfit;

/**
 * Created by Puneet on 4/23/2017.
 */

public class storebasicInfo {
    public String uid;
    public String name;
    public double height;
    public double weight;
    public int age;
    public double bmi;
    public String category;
    public String gendervalue;
    public String model;


    public storebasicInfo(){

    }

    public storebasicInfo(String uid, String name, double height, double weight, int age, double bmi, String category, String gendervalue, String model){
    this.uid=uid;
    this.name = name;
    this.height= height;
    this.weight= weight;
    this.age= age;
    this.bmi= bmi;
    this.category = category;
    this.gendervalue = gendervalue;
    this.model = model;
    }
}

