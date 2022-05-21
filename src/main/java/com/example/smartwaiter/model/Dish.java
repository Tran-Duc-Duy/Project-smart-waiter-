package com.example.smartwaiter.model;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Dish {
    private int id;
    private String nameDish;
    private String type;
    private double totalCalo;
    private double totalTien;
    private double time;
    private ImageView imgView;
    private String linkImgString;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Dish(int id, String nameDish, String type, double totalCalo, double totalTien, double time, String linkImgString, String description) {
        this.id = id;
        this.nameDish = nameDish;
        this.type = type;
        this.totalCalo = totalCalo;
        this.totalTien = totalTien;
        this.time = time;
        this.imgView = new ImageView(new Image(linkImgString));
        this.linkImgString = linkImgString;
        this.description =description;
    }

    public Dish() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameDish() {
        return nameDish;
    }

    public void setNameDish(String nameDish) {
        this.nameDish = nameDish;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getTotalCalo() {
        return totalCalo;
    }

    public void setTotalCalo(double totalCalo) {
        this.totalCalo = totalCalo;
    }

    public double getTotalTien() {
        return totalTien;
    }

    public void setTotalTien(double totalTien) {
        this.totalTien = totalTien;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public ImageView getImgView() {
        return imgView;
    }

    public void setImgView(ImageView imgView) {
        this.imgView = imgView;
    }

    public String getLinkImgString() {
        return linkImgString;
    }

    public void setLinkImgString(String linkImgString) {
        this.linkImgString = linkImgString;
    }
    private String costumDescription(String des){
        return des.replaceAll("\\n","/n");
    }
    public void costumDescription2(){
        this.description=description.replaceAll("/n","\n");
    }

    @Override
    public String toString() {
        return id+";"+nameDish+";"+type+";"+totalCalo+";"+totalTien+";"+time+";"+linkImgString+";"+costumDescription(description)+";";
    }

}

