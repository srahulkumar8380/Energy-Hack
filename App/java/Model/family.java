package com.hackthon.srahulkumar.energyhackapp.Model;

public class family {
    public String aadhar;
    public String age;
    public String email;
    public String employed;
    public String name;
    public String phone;
    public String relation;

    public  int id;

    public family() {
    }

    public family(String aadhar, String age, String email, String employed, String name,
                  String phone, String relation) {
        this.aadhar = aadhar;
        this.age = age;
        this.email = email;
        this.employed = employed;
        this.name = name;
        this.phone = phone;
        this.relation = relation;

    }




    public String getEmployed() {
        return employed;
    }

    public void setEmployed(String employed) {
        this.employed = employed;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getphone() {
        return phone;
    }

    public void setphone(String phone) {
        this.phone = phone;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
