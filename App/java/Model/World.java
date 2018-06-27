package com.hackthon.srahulkumar.energyhackapp.Model;

public class World {
    public String number,name,from,to,seat,date,bill;

    public World(String number, String name, String from, String to, String date, String bill) {
        this.number = number;
        this.name = name;
        this.from = from;
        this.to = to;
        this.date = date;
        this.bill = bill;
    }

    public World() {
    }

    public World(String number, String name, String from, String to, String seat, String date, String bill) {
        this.number = number;
        this.name = name;
        this.from = from;
        this.to = to;
        this.seat = seat;
        this.date = date;
        this.bill = bill;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }
}


