package com.example.smartwaiter.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Dish> listDish=new ArrayList<>();;
    private String myOrder=this.toString();
    private double sum;
    /*private double sumPrice = getSumPrice();
    private double sumCalo= getSumCalo();
    private double sumTime=getSumTime();*/
    public Order(List<Dish> listDish, int option) {
        this.listDish = listDish;
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

    public List<Dish> getListDish() {
        return listDish;
    }

    public void setListDish(List<Dish> listDish) {
        this.listDish = listDish;
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
        if(!listDish.isEmpty()) {
            for (int i=0;i<listDish.size();i++) {
                if(i!=listDish.size()-1){
                    str += listDish.get(i).getNameDish() + "-";
                }else{
                    str += listDish.get(i).getNameDish();
                }
            }
        }else{
            str = "empty";
        }
        return str;
    }
    public double getSumPrice(){
        double sum =0.0;
        for(Dish ds:this.listDish){
            sum+=ds.getTotalTien();
        }
        return sum;
    }
    public double getSumCalo(){
        double sum =0.0;
        for(Dish ds:this.listDish){
            sum+=ds.getTotalCalo();
        }
        return sum;
    }
    public double getSumTime(){
        double sum =0.0;
        for(Dish ds:this.listDish){
            sum+=ds.getTime();
        }
        return sum;
    }
}
