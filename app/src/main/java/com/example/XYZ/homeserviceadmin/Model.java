package com.example.XYZ.homeserviceadmin;

public class Model {

    String name,phone,skills;

    public Model(String name, String phone, String skills) {
        this.name = name;
        this.phone = phone;
        this.skills = skills;
    }

    public Model() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
