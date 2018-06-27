package com.hackthon.srahulkumar.energyhackapp.Model;

public class Member {

    String name;
    String email;
    String aadhar;
    String  age;
    String employed;
    String relation;
    String phone;

    public Member() {
    }

    public Member(String name, String aadhar, String phone) {
        this.name = name;
        this.aadhar = aadhar;
        this.phone = phone;
    }

    public Member(String name, String email, String aadhar, String age, String employed, String relation, String phone) {
        this.name = name;
        this.email = email;
        this.aadhar = aadhar;
        this.age = age;
        this.employed = employed;
        this.relation = relation;
        this.phone = phone;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmployed() {
        return employed;
    }

    public void setEmployed(String employed) {
        this.employed = employed;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
