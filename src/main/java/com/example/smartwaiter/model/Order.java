package com.example.smartwaiter.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Dish> listOrder=new ArrayList<>();;
    private String myOrder=this.toString();
    private double sum;
    /*private double sumPrice = getSumPrice();
    private double sumCalo= getSumCalo();
    private double sumTime=getSumTime();*/
    public Order(List<Dish> listOrder, int option) {
        this.listOrder = listOrder;
        if(option==1){
            sum=getSumPrice();
        }else if(option==2){
            sum=getSumCalo();
        }else{
            sum=getSumTime();
        }
    }

    /*public Order(Dish dish){
        this.listOrder.add(dish);
    }*/

    public List<Dish> getListOrder() {
        return listOrder;
    }

    public void setListOrder(List<Dish> listOrder) {
        this.listOrder = listOrder;
    }

    public String getMyOrder() {
        return this.toString();
    }

    public Double getSum() {
        return sum;
    }

    @Override
    public String toString() {
        String str="";
        if(!listOrder.isEmpty()) {
            for (int i=0;i<listOrder.size();i++) {
                if(i!=listOrder.size()-1){
                    str += listOrder.get(i).getNameDish() + "-";
                }else{
                    str += listOrder.get(i).getNameDish();
                }
            }
        }else{
            str = "empty";
        }
        return str;
    }
    public double getSumPrice(){
        double sum =0.0;
        for(Dish ds:this.listOrder){
            sum+=ds.getTotalTien();
        }
        return sum;
    }
    public double getSumCalo(){
        double sum =0.0;
        for(Dish ds:this.listOrder){
            sum+=ds.getTotalCalo();
        }
        return sum;
    }
    public double getSumTime(){
        double sum =0.0;
        for(Dish ds:this.listOrder){
            sum+=ds.getTime();
        }
        return sum;
    }
}
