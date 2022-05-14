package com.example.smartwaiter.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Meal {
    private List<Order> listOrder =new ArrayList<Order>();
    private String name =listOrder.toString();
    private Date date;
    private double price;
    private double calo;

    public Meal(List<Order> listOrder, Date date, double price, double calo) {
        this.listOrder = listOrder;
        this.date = date;
        this.price = price;
        this.calo = calo;
        this.name =this.toString();
    }
    public String getName() {
        return name;
    }

    public List<Order> getListOrder() {
        return listOrder;
    }

    public void setListOrder(List<Order> listOrder) {
        this.listOrder = listOrder;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCalo() {
        return calo;
    }

    public void setCalo(double calo) {
        this.calo = calo;
    }

    @Override
    public String toString() {
        String str="";
        for(Order order : listOrder) {
            str+= order.toString()+"\n";
        }
        return str;
    }
}
