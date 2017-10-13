 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tadua.controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NewBee
 */
public class TrainingController {
//    public static void main(String[] args) throws IOException {
//        TrainingController tc = new TrainingController();
//        
//    }
    
    // Daily Data
    public List<Double> Modelfor10DataDaily() throws IOException{
        int x=0,totalData = 0;
        double prediksi, targetTesting, percentageError, MAPE, totalError = 0,nilaiPrediksi=0,percentage=0;
        List<Double> modelTraining = new ArrayList<>();
        File file = new File("D:\\\\\\\\Kuliah\\\\\\\\Semester VI\\\\\\\\TAII\\\\\\\\modelTraining10dailyData.csv");
        List<String> lines = Files.readAllLines(file.toPath(),StandardCharsets.UTF_8);
        for (String line : lines) {
            if (x > 0) {
                String[] array = line.split(",");
//            System.out.println(array[0]);
//                System.out.println(array[1]);
                double parseData = Double.valueOf(array[1]);
                modelTraining.add(parseData);
//                System.out.println("Fungsi : "+parseData);
            }
            x++;
        }
        return modelTraining;
    }
    
    public List<Double> Modelfor50DataDaily() throws IOException{
        int x=0,totalData = 0;
        double prediksi, targetTesting, percentageError, MAPE, totalError = 0,nilaiPrediksi=0,percentage=0;
        List<Double> modelTraining = new ArrayList<>();
        File file = new File("D:\\\\Kuliah\\\\Semester VI\\\\TAII\\\\modelTraining50dailyData.csv");
        List<String> lines = Files.readAllLines(file.toPath(),StandardCharsets.UTF_8);
        for (String line : lines) {
            if (x > 0) {
                String[] array = line.split(",");
//            System.out.println(array[0]);
//                System.out.println(array[1]);
                double parseData = Double.valueOf(array[1]);
                modelTraining.add(parseData);
//                System.out.println("Fungsi : "+parseData);
            }
            x++;
        }
        return modelTraining;
    }
    
    public List<Double> Modelfor100DataDaily() throws IOException{
        int x=0,totalData = 0;
        double prediksi, targetTesting, percentageError, MAPE, totalError = 0,nilaiPrediksi=0,percentage=0;
        List<Double> modelTraining = new ArrayList<>();
        File file = new File("D:\\\\Kuliah\\\\Semester VI\\\\TAII\\\\modelTraining100dailyData.csv");
        List<String> lines = Files.readAllLines(file.toPath(),StandardCharsets.UTF_8);
        for (String line : lines) {
            if (x > 0) {
                String[] array = line.split(",");
//            System.out.println(array[0]);
//                System.out.println(array[1]);
                double parseData = Double.valueOf(array[1]);
                modelTraining.add(parseData);
//                System.out.println("Fungsi : "+parseData);
            }
            x++;
        }
        return modelTraining;
    }
    
    public List<Double> Modelfor500DataDaily() throws IOException{
        int x=0,totalData = 0;
        double prediksi, targetTesting, percentageError, MAPE, totalError = 0,nilaiPrediksi=0,percentage=0;
        List<Double> modelTraining = new ArrayList<>();
        File file = new File("D:\\\\Kuliah\\\\Semester VI\\\\TAII\\\\modelTraining500dailyData.csv");
        List<String> lines = Files.readAllLines(file.toPath(),StandardCharsets.UTF_8);
        for (String line : lines) {
            if (x > 0) {
                String[] array = line.split(",");
//            System.out.println(array[0]);
//                System.out.println(array[1]);
                double parseData = Double.valueOf(array[1]);
                modelTraining.add(parseData);
//                System.out.println("Fungsi : "+parseData);
            }
            x++;
        }
        return modelTraining;
    }
    
    public List<Double> Modelfor2500DataDaily() throws IOException{
        int x=0,totalData = 0;
        double prediksi, targetTesting, percentageError, MAPE, totalError = 0,nilaiPrediksi=0,percentage=0;
        List<Double> modelTraining = new ArrayList<>();
        File file = new File("D:\\\\Kuliah\\\\Semester VI\\\\TAII\\\\modelTraining2500dailyData.csv");
        List<String> lines = Files.readAllLines(file.toPath(),StandardCharsets.UTF_8);
        for (String line : lines) {
            if (x > 0) {
                String[] array = line.split(",");
//            System.out.println(array[0]);
//                System.out.println(array[1]);
                double parseData = Double.valueOf(array[1]);
                modelTraining.add(parseData);
//                System.out.println("Fungsi : "+parseData);
            }
            x++;
        }
        return modelTraining;
    }
    
    // Monthly Data
    public List<Double> Modelfor10DataMonthly() throws IOException{
        int x=0,totalData = 0;
        double prediksi, targetTesting, percentageError, MAPE, totalError = 0,nilaiPrediksi=0,percentage=0;
        List<Double> modelTraining = new ArrayList<>();
        File file = new File("D:\\\\Kuliah\\\\Semester VI\\\\TAII\\\\modelTraining10DataMonth.csv");
        List<String> lines = Files.readAllLines(file.toPath(),StandardCharsets.UTF_8);
        for (String line : lines) {
            if (x > 0) {
                String[] array = line.split(",");
//            System.out.println(array[0]);
//                System.out.println(array[1]);
                double parseData = Double.valueOf(array[1]);
                modelTraining.add(parseData);
//                System.out.println("Fungsi : "+parseData);
            }
            x++;
        }
        return modelTraining;
    }
    
    public List<Double> Modelfor50DataMonthly() throws IOException{
        int x=0,totalData = 0;
        double prediksi, targetTesting, percentageError, MAPE, totalError = 0,nilaiPrediksi=0,percentage=0;
        List<Double> modelTraining = new ArrayList<>();
        File file = new File("D:\\\\Kuliah\\\\Semester VI\\\\TAII\\\\modelTraining50DataMonth.csv");
        List<String> lines = Files.readAllLines(file.toPath(),StandardCharsets.UTF_8);
        for (String line : lines) {
            if (x > 0) {
                String[] array = line.split(",");
//            System.out.println(array[0]);
//                System.out.println(array[1]);
                double parseData = Double.valueOf(array[1]);
                modelTraining.add(parseData);
//                System.out.println("Fungsi : "+parseData);
            }
            x++;
        }
        return modelTraining;
    }
    
    public List<Double> Modelfor120DataMonthly() throws IOException{
        int x=0,totalData = 0;
        double prediksi, targetTesting, percentageError, MAPE, totalError = 0,nilaiPrediksi=0,percentage=0;
        List<Double> modelTraining = new ArrayList<>();
        File file = new File("D:\\\\Kuliah\\\\Semester VI\\\\TAII\\\\modelTraining120DataMonth.csv");
        List<String> lines = Files.readAllLines(file.toPath(),StandardCharsets.UTF_8);
        for (String line : lines) {
            if (x > 0) {
                String[] array = line.split(",");
//            System.out.println(array[0]);
//                System.out.println(array[1]);
                double parseData = Double.valueOf(array[1]);
                modelTraining.add(parseData);
//                System.out.println("Fungsi : "+parseData);
            }
            x++;
        }
        return modelTraining;
    }
    
    //SECOND EXPERIMENT
    public List<Double> Modelfor10DataMonthlySecond() throws IOException{
        int x=0,totalData = 0;
        double prediksi, targetTesting, percentageError, MAPE, totalError = 0,nilaiPrediksi=0,percentage=0;
        List<Double> modelTraining = new ArrayList<>();
        File file = new File("D:\\\\Kuliah\\\\Semester VI\\\\TAII\\\\modelTraining10DataMonthSecond.csv");
        List<String> lines = Files.readAllLines(file.toPath(),StandardCharsets.UTF_8);
        for (String line : lines) {
            if (x > 0) {
                String[] array = line.split(",");
//            System.out.println(array[0]);
//                System.out.println(array[1]);
                double parseData = Double.valueOf(array[1]);
                modelTraining.add(parseData);
//                System.out.println("Fungsi : "+parseData);
            }
            x++;
        }
        return modelTraining;
    }
    
    public List<Double> Modelfor50DataMonthlySecond() throws IOException{
        int x=0,totalData = 0;
        double prediksi, targetTesting, percentageError, MAPE, totalError = 0,nilaiPrediksi=0,percentage=0;
        List<Double> modelTraining = new ArrayList<>();
        File file = new File("D:\\\\Kuliah\\\\Semester VI\\\\TAII\\\\modelTraining50DataMonthSecond.csv");
        List<String> lines = Files.readAllLines(file.toPath(),StandardCharsets.UTF_8);
        for (String line : lines) {
            if (x > 0) {
                String[] array = line.split(",");
//            System.out.println(array[0]);
//                System.out.println(array[1]);
                double parseData = Double.valueOf(array[1]);
                modelTraining.add(parseData);
//                System.out.println("Fungsi : "+parseData);
            }
            x++;
        }
        return modelTraining;
    }
    
    public List<Double> Modelfor120DataMonthlySecond() throws IOException{
        int x=0,totalData = 0;
        double prediksi, targetTesting, percentageError, MAPE, totalError = 0,nilaiPrediksi=0,percentage=0;
        List<Double> modelTraining = new ArrayList<>();
        File file = new File("D:\\\\Kuliah\\\\Semester VI\\\\TAII\\\\modelTraining120DataMonthSecond.csv");
        List<String> lines = Files.readAllLines(file.toPath(),StandardCharsets.UTF_8);
        for (String line : lines) {
            if (x > 0) {
                String[] array = line.split(",");
//            System.out.println(array[0]);
//                System.out.println(array[1]);
                double parseData = Double.valueOf(array[1]);
                modelTraining.add(parseData);
//                System.out.println("Fungsi : "+parseData);
            }
            x++;
        }
        return modelTraining;
    }
}
