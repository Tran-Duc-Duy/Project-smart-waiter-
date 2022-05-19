package com.example.smartwaiter;


import com.example.smartwaiter.model.Dish;
import com.example.smartwaiter.model.Meal;
import com.example.smartwaiter.model.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

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
                newDish.costumDescription2();
                list.add(newDish);
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

    public static void writeFileMeal(ObservableList<Meal> mealList) {
        try {
            FileWriter fw = new FileWriter("testHistory.txt");
            for(Meal a : mealList){
                fw.append(a+"\n");
            }
            fw.close();

        } catch (Exception e) {
            System.out.println(e);
        }


    }
    public static ObservableList<Meal> readFileMeal(){
        List<Meal> list = new ArrayList<>();
        List<Dish> newListDish=new ArrayList<>();
        List<Order> listOrder = new ArrayList<>();

        //List<Dish> listDish = readFile();
        // em chua chinh date
        Calendar cale = Calendar.getInstance();
        Date date = cale.getTime();
        SimpleDateFormat formatter1=new SimpleDateFormat("dd:MM:yyyy");

        double price = 0;
        double calo = 0;
        int i=0;
        try{

            Scanner f = new Scanner(new File("testHistory.txt"));
            while (f.hasNextLine()) {
                i=0;
                listOrder=new ArrayList<>();
                String[] listString = f.nextLine().split("#");
                Date date1=new Date();
                for(int a=0;a<listString.length;a++){
                    if(i==0){
                        //d.setTime(listDishString[1]);
                        date1=formatter1.parse(listString[0]);
                        i++;
                        continue;
                    }

                    String[] listOrderString = listString[a].split("@");
                    newListDish=new ArrayList<>();
                    for(int b=0;b<listOrderString.length;b++){
                        String[] listDishString = listOrderString[b].split(";");
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
                        newDish.costumDescription2();
                        newListDish.add(newDish);
                    }
                    Order o =new Order(newListDish,1);
                    listOrder.add(o);
                }
                Meal m =new Meal(listOrder,date1);
                list.add(m);
                /*for (String thisDish : listDishString) {
                    for(Dish dish : listDish ) {
                        if(thisDish.equals(dish.getNameDish())) {
                            newListDish.add(dish);
                            price += dish.getTotalTien();
                            calo +=dish.getTotalCalo();
                        }
                    }
                }
                Order order = new Order(newListDish,1);
                listOrder.add(order);
                newListDish.clear();

                char k = f.nextLine().charAt(f.nextLine().length()-1);
                if (k == '.')  {
                    //List<Order> listOrder, Date date, double price, double calo
                    Meal meal = new Meal(listOrder,date, price, calo );
                    list.add(meal);
                    listOrder.clear();
                    price = 0;
                    calo = 0;
                }*/
            }
            f.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        ObservableList<Meal> dishListAll= FXCollections.observableArrayList(list);
        return dishListAll;
    }
}
