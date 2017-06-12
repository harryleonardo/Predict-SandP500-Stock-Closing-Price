/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tadua.driver;

import org.tadua.controller.StockController;
import org.tadua.financeapi.Stock;
import org.tadua.financeapi.StockFetcher;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import static javafx.application.Application.launch;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.RList;
import org.tadua.controller.BulananBerurutController;
import org.tadua.controller.HandleErrorController;
import org.tadua.controller.ProsesBulananController;
import org.tadua.controller.TrainingController;

/**
 *
 * @author NewBee
 */
public class Main {

    public static void main(String[] args) throws SQLException, IOException, RserveException, REXPMismatchException {
//        Controller that want to be used
        //        GUI From FX
        launch(Finance.class, args);

//        int month=0;
//        StockController sc = new StockController();
//        TrainingController tc = new TrainingController();
//        HandleErrorController hec = new HandleErrorController();
//        ProsesBulananController bulananController = new ProsesBulananController();
//        BulananBerurutController bbc = new BulananBerurutController();
//        RConnection connection = null;
//        connection = new RConnection();
////
//////        connection.eval("source('D:\\\\Kuliah\\\\Semester V\\\\TA\\\\Implementasi\\\\R\\\\neuralNet.R')");
//        connection.eval("source('D:\\\\Kuliah\\\\Semester VI\\\\TAII\\\\ImplementasiTAII\\\\neuralNetDataSet.R')");
//////        connection.eval("source('D:\\\\Kuliah\\\\Semester VI\\\\TAII\\\\ImplementasiTAII\\\\neuralNet.R')");
////
//        int totalData = 0;
//        double[] predict10DataDaily,predict50DataDaily,predict100DataDaily, predict500DataDaily, predict2500DataDaily;
//        double[] predict10DataMonthly,predict50DataMonthly,predict120DataMonthly;
//        double target, prediksi, percentageError, MAPE, totalError = 0;
        /*
            Process each of daily data set into CSV file
         */
//        sc.proses10Data(10);
//        sc.proses50Data(50);
//        sc.proses100Data(100);
//        sc.proses500Data(500);
//        sc.proses2500Data(2500);

        /*
            Process each of monthly dataset into CSV file
         */
//        sc.count10(17,12,2016);
//        sc.count50(17,12,2016);
//        sc.count120(17,12,2016);
//        bulananController.proses10DataBulanan(1, 10, 2016);
//        bulananController.proses50DataBulanan(1, 10, 2016);
//        bulananController.proses120DataBulanan(1, 10, 2016);
//        bbc.proses10Data();
//        bbc.proses50Data();
//        bbc.proses120Data();
        /*
            Getting DataFrame Training that has been passed from R Language
         */
//        predict10DataDaily = (double[]) connection.eval("neuralNet10()").asDoubles();
//        predict50DataDaily = (double[]) connection.eval("neuralNet50()").asDoubles();
//        predict100DataDaily = (double[]) connection.eval("neuralNet100()").asDoubles();
//        predict500DataDaily = (double[]) connection.eval("neuralNet500()").asDoubles();
//        predict2500DataDaily = (double[]) connection.eval("neuralNet2500()").asDoubles();
        /*
            Getting DataFrame Training that has been passed from R Language
         */
//        predict10DataMonthly = (double[]) connection.eval("neuralNet10Month()").asDoubles();
//        predict50DataMonthly = (double[]) connection.eval("neuralNet50Month()").asDoubles();;
//        predict120DataMonthly = (double[]) connection.eval("neuralNet120Month()").asDoubles();
        /*
            Getting DataFrame Training that has been passed from R Language
         */
//        predict10DataMonthly = (double[]) connection.eval("neuralNet10MonthSecond()").asDoubles();
//        predict50DataMonthly = (double[]) connection.eval("neuralNet50MonthSecond()").asDoubles();;
//        predict120DataMonthly = (double[]) connection.eval("neuralNet120MonthSecond()").asDoubles();
//        /*
//            Retrieve Target data Training Daily
//        */
//        List<Double> targetTraining10DataDaily = sc.targetDataTrainingDaily(10);
//        List<Double> targetTraining50DataDaily = sc.targetDataTrainingDaily(50);
//        List<Double> targetTraining100DataDaily = sc.targetDataTrainingDaily(100);
//        List<Double> targetTraining500DataDaily = sc.targetDataTrainingDaily(500);
//        List<Double> targetTraining2500DataDaily = sc.targetDataTrainingDaily(2500);
//        
//        /*
//            Retrieve Target data Testing value of each data
//         */
//        List<Double> targetTesting10DataDaily = sc.targetDataTestingDaily(10);
//        List<Double> targetTesting50DataDaily = sc.targetDataTestingDaily(50);
//        List<Double> targetTesting100DataDaily = sc.targetDataTestingDaily(100);
//        List<Double> targetTesting500DataDaily = sc.targetDataTestingDaily(500);
//        List<Double> targetTesting2500DataDaily = sc.targetDataTestingDaily(2500);

        /*
            Retrieve Target Data Training Monthly
         */
//        List<Double> targetTraining10DataMonthly = sc.targetDataTrainingMonthly(1, 10, 2016,10);
//        List<Double> targetTraining50DataMonthly = sc.targetDataTrainingMonthly(1, 10, 2016,50);
//        List<Double> targetTraining120DataMonthly = sc.targetDataTrainingMonthly(1, 10, 2016,120);

        /*
            Retrieve Target Data Testing Monthly
         */
//        List<Double> targetTesting10DataMonthly = sc.targetDataTestingMonthly(1, 10, 2016,10);
//        List<Double> targetTesting50DataMonthly = sc.targetDataTestingMonthly(1, 10, 2016,50);
//        List<Double> targetTesting120DataMonthly = sc.targetDataTestingMonthly(1, 10, 2016,120);
//        
//      SECOND EXPERIMENT
//Training
//        List<Double> targetTraining10DataMonthlySecond = sc.targetDataTrainingMonthlySecond(10);
//        List<Double> targetTraining50DataMonthlySecond = sc.targetDataTrainingMonthlySecond(50);
//        List<Double> targetTraining120DataMonthlySecond = sc.targetDataTrainingMonthlySecond(120);
//Testing
//        List<Double> targetTesting10DataMonthlySecond = sc.targetDataTestingMonthlySecond(10);
//        List<Double> targetTesting50DataMonthlySecond = sc.targetDataTestingMonthlySecond(50);
//        List<Double> targetTesting120DataMonthlySecond = sc.targetDataTestingMonthlySecond(120);
        /*
            This function to retrieve Output from Training Model Daily that has been saved in 
            CSV file into current directory path
         */
//        List<Double> trainingModel10DataDaily = tc.Modelfor10DataDaily();
//        List<Double> trainingModel50DataDaily = tc.Modelfor50DataDaily();
//        List<Double> trainingModel100DataDaily = tc.Modelfor100DataDaily();
//        List<Double> trainingModel500DataDaily = tc.Modelfor500DataDaily();
//        List<Double> trainingModel2500DataDaily = tc.Modelfor2500DataDaily();
//        
        /*
            This function to retrieve Output from Training Model Monthly that has been saved in 
            CSV file into current directory path
         */
//        List<Double> trainingModel10DataMonthly = tc.Modelfor10DataMonthly();
//        List<Double> trainingModel50DataMonthly = tc.Modelfor50DataMonthly();
//        List<Double> trainingModel120DataMonthly = tc.Modelfor120DataMonthly();
// SECOND EXPERIMENT
//        List<Double> trainingModel10DataMonthlySecond = tc.Modelfor10DataMonthlySecond();
//        List<Double> trainingModel50DataMonthly = tc.Modelfor50DataMonthlySecond();
//        List<Double> trainingModel120DataMonthly = tc.Modelfor120DataMonthlySecond();
        //                                  SCENARIO FOR DAILY
//        System.out.println("--------------------Data Training--------------------");
//        /*
//            This function is use to calculate MAPE of training Data
//        */
//        hec.mapeDataTrainingDaily(trainingModel10DataDaily, targetTraining10DataDaily,10);
//        hec.mapeDataTrainingDaily(trainingModel50DataDaily, targetTraining50DataDaily,50);
//        hec.mapeDataTrainingDaily(trainingModel100DataDaily, targetTraining100DataDaily,100);
//        hec.mapeDataTrainingDaily(trainingModel500DataDaily, targetTraining500DataDaily,500);
//        hec.mapeDataTrainingDaily(trainingModel2500DataDaily, targetTraining2500DataDaily,2500);
//        System.out.println("--------------------Data Testing--------------------");
//        /*
//            Call this function to give output of total percentage error of each data set
//            Using MAPE function to calculate the error
//        */
//        hec.mapeDataTestingDaily(predict10DataDaily, targetTesting10DataDaily,10);
//        hec.mapeDataTestingDaily(predict50DataDaily, targetTesting50DataDaily,50);
//        hec.mapeDataTestingDaily(predict100DataDaily, targetTesting100DataDaily,100);
//        hec.mapeDataTestingDaily(predict500DataDaily, targetTesting500DataDaily,500);
//        hec.mapeDataTestingDaily(predict2500DataDaily, targetTesting2500DataDaily,2500);
        //                                  SCENARIO FOR MONTHLY
        /*
            This function is use to calculate MAPE of training Data
         */
//        hec.mapeDataTrainingMonthly(trainingModel10DataMonthly, targetTraining10DataMonthly,10);
//        hec.mapeDataTrainingMonthly(trainingModel50DataMonthly, targetTraining50DataMonthly, 50);
//        hec.mapeDataTrainingMonthly(trainingModel120DataMonthly, targetTraining120DataMonthly, 120);
        /*
            This function is use to calculate MAPE of testing Data
         */
//        hec.mapeDataTestingMonthly(predict10DataMonthly, targetTesting10DataMonthly, 10);
//        hec.mapeDataTestingMonthly(predict50DataMonthly, targetTesting50DataMonthly, 50);
//        hec.mapeDataTestingMonthly(predict120DataMonthly, targetTesting120DataMonthly, 120);
//          SECOND EXPERIMENT
//        hec.mapeDataTrainingMonthlySecond(trainingModel10DataMonthlySecond, targetTraining10DataMonthlySecond,10);
//        hec.mapeDataTrainingMonthlySecond(trainingModel50DataMonthlySecond, targetTraining50DataMonthlySecond,50);
//        hec.mapeDataTrainingMonthlySecond(trainingModel120DataMonthlySecond, targetTraining120DataMonthlySecond,120);
//        hec.mapeDataTestingMonthlySecond(predict10DataMonthly, targetTesting10DataMonthlySecond, 10);;
//        hec.mapeDataTestingMonthlySecond(predict50DataMonthly, targetTesting50DataMonthly, 50);
//        hec.mapeDataTestingMonthlySecond(predict120DataMonthly, targetTesting120DataMonthly, 120);


//        Stock snp = StockFetcher.getStock("^GSPC");
//        System.out.println("Price: " + snp.getPrice());
//        System.out.println("Open: " + snp.getOpen());
//        System.out.println("High: " + snp.getDayhigh());
//        System.out.println("Low: " + snp.getDaylow());
//        System.out.println("Volume: " + snp.getVolume());
        
    }
}
