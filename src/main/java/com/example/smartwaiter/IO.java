package com.example.smartwaiter;


import com.example.smartwaiter.model.Dish;
import com.example.smartwaiter.model.Meal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IO {
    public static void writeFile(ObservableList<Dish> dishList){
        try {
            FileWriter fw = new FileWriter("testIn.txt");
            for(Dish a : dishList){
                fw.write(a.toString()+"\n");
            }
            fw.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static ObservableList<Dish> readFile(){
        List<Dish> list = new ArrayList<>();
        try{
            Scanner f = new Scanner(new File("testIn.txt"));
            while (f.hasNextLine()) {
                String[] listDishString = f.nextLine().split(";");
                //int id, String nameDish, String type, double totalCalo, double totalTien, double time, String linkImgString, String description
                Dish newDish = new Dish(
                        Integer.parseInt(listDishString[0]),
                        listDishString[1],
                        listDishString[2],
                        Double.parseDouble(listDishString[3]),
                        Double.parseDouble(listDishString[4]),
                        Double.parseDouble(listDishString[5]),
                        listDishString[6],
                        listDishString[7]
                );
                list.add(newDish);
                newDish.costumDescription2();
            }
            f.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        ObservableList<Dish> dishListAll= FXCollections.observableArrayList(list);
        return dishListAll;
    }

    public static String copyImg(String path, String name) throws IOException {
        String currentDirectory = System.getProperty("user.dir");
        //System.out.println(currentDirectory);
        //String newPath="D:\\DuyStudy\\FILE_Ky2_LapTrinh\\SmarterWaiter\\src\\main\\resources\\com\\example\\smarterwaiter\\img\\"+name+".png";
        String newPath= currentDirectory.replaceAll("\\\\","\\\\")+"\\src\\main\\resources\\com\\example\\smartwaiter\\img\\"+name+".png";

        newPath=newPath.replaceAll("%20","-");
        FileInputStream inStream = null;
        FileOutputStream outStream = null;
        try {
            inStream = new FileInputStream(new File(path));
            outStream = new FileOutputStream(new File(newPath));
            int length;
            byte[] buffer = new byte[1024];

            // copy the file content in bytes
            while ((length = inStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, length);
            }
            //System.out.println("File is copied successful!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            inStream.close();
            outStream.close();
        }
        return newPath;
    }

    public static void writeFileMeal(ObservableList<Meal> dishList) {

    }
    public static ObservableList<Meal> readFileMeal(){
        List<Meal> list = new ArrayList<>();
        ObservableList<Meal> dishListAll= FXCollections.observableArrayList(list);
        return dishListAll;
    }
}
