/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tadua.controller;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.tadua.financeapi.Stock;
import org.tadua.financeapi.StockFetcher;
import org.tadua.model.Data;

/**
 *
 * @author NewBee
 */
public class StockController {

    DatabaseController Db = new DatabaseController();
    int tamp = 0, id = 0, flag = 0;

    //Path to Save the CSV File with kind of dataset
//    Daily
//   Path 10 Data
    String training10Data = "D:\\Kuliah\\Semester VI\\TAII\\training10.csv";
    String testing10Data = "D:\\Kuliah\\Semester VI\\TAII\\testing10.csv";

//    Path 50 Data
    String training50Data = "D:\\Kuliah\\Semester VI\\TAII\\training50.csv";
    String testing50Data = "D:\\Kuliah\\Semester VI\\TAII\\testing50.csv";

//    Path 100 Data
    String training100Data = "D:\\Kuliah\\Semester VI\\TAII\\training100.csv";
    String testing100Data = "D:\\Kuliah\\Semester VI\\TAII\\testing100.csv";

//    Path 500 Data
    String training500Data = "D:\\Kuliah\\Semester VI\\TAII\\training500.csv";
    String testing500Data = "D:\\Kuliah\\Semester VI\\TAII\\testing500.csv";

//    Path 2500 Data
    String training2500Data = "D:\\Kuliah\\Semester VI\\TAII\\training2500.csv";
    String testing2500Data = "D:\\Kuliah\\Semester VI\\TAII\\testing2500.csv";

//    Monthly Scenario
//    Path 10 Month Data
    String training10DataMonthly = "D:\\Kuliah\\Semester VI\\TAII\\training10Month.csv";
    String testing10DataMonthly = "D:\\Kuliah\\Semester VI\\TAII\\testing10Month.csv";

//    Path 50 Month Data
    String training50DataMonthly = "D:\\Kuliah\\Semester VI\\TAII\\training50Month.csv";
    String testing50DataMonthly = "D:\\Kuliah\\Semester VI\\TAII\\testing50Month.csv";

//    Path 120 Month Data
    String training120DataMonthly = "D:\\Kuliah\\Semester VI\\TAII\\training120Month.csv";
    String testing120DataMonthly = "D:\\Kuliah\\Semester VI\\TAII\\testing120Month.csv";

    //Delimiter used in CSV file
    private final String COMMA_DELIMITER = ",";
    private final String NEW_LINE_SEPARATOR = "\n";

    //CSV file header Training
    private static final String FILE_HEADER = "id,open,high,low,close,volume,target";

//    CSV file header Testing
    private static final String FILE_HEADER_TESTING = "id,open,high,low,close,volume";

//    Declaration of max and min of each variable input for daily
    double maxHigh = 0, minHigh = 10000, maxClose = 0, minClose = 10000, maxLow = 0, minLow = 10000, maxOpen = 0, minOpen = 10000, maxVolume = 0, minTarget = 10000, maxTarget = 0;
    long minVolume = 100000000000000L;
//    Declaration of max and min of each variable input for monthly
    double maxHighs = 0, minHighs = 10000, maxCloses = 0, minCloses = 10000, maxLows = 0, minLows = 10000, maxOpens = 0, minOpens = 10000, maxVolumes = 0, minVolumes = 10000000, minTargets = 10000, maxTargets = 0;

//  Declaration of total from each field in table of Database
    double tempHigh = 0, tempClose = 0, tempLow = 0, tempOpen = 0, tempVolume = 0, tempTarget = 0;

//  Declaration of average velue from each parameter
    double aveHigh = 0, aveClose = 0, aveLow = 0, aveOpen = 0, aveVolume = 0, aveTarget = 0;

//    Declaration variable for normalization data
    double highNormalization, closeNormalization, lowNormalization, openNormalization, volumeNormalization, targetNormalization, finalOutput;

    /*
        Function to insert new Data into Database
     */
    public void insertNewData(String date, double open, double high, double low, double close, double volume) {
        Db.connect();
        String statement = "INSERT INTO input SET date='" + date + "',open='" + open + "',high='" + high + "',low='" + low + "',close='" + close + "',volume='" + volume + "'";
        Db.Execute(statement);
    }

    /*
        Function to Proses each of Data set. In this function will take data with query from Database
        and then the data that has been selected will be Nomalize with Min-Max Normalization Function.
     */
//      Fitting 10 Data
    public void proses10Data(int dataSet) throws SQLException, IOException {
        int countTraining = 0, countforTraining = 0, countTesting = 0;
        Db.connect();
        int rowTotal = 0;
        ResultSet hasil = null, data = null, training = null, testing = null;
        String totalRow = "SELECT * FROM masterdata";
        hasil = Db.getRs(totalRow);
//        Loop to count total Row
        while (hasil.next()) {
            rowTotal += 1;
        }

        int tamp = rowTotal - dataSet;
        String statement = "SELECT * FROM masterdata LIMIT " + dataSet + " OFFSET " + tamp;
        hasil = Db.getRs(statement);
        training = Db.getRs(statement);
        testing = Db.getRs(statement);

//        Loop to get max and min value from result set
        while (hasil.next()) {
            Date date = hasil.getDate("dates");
            double open = hasil.getDouble("open");
            double high = hasil.getDouble("high");
            double low = hasil.getDouble("low");
            double close = hasil.getDouble("close");
            double volume = hasil.getDouble("volume");
            double target = hasil.getDouble("target");

//            System.out.println("Date : "+date+" , Open : " +open+" , High : "+high+" , Low : "+low+" , Close : "+close+" , Volume : "+volume+" , Target : "+target);

//            Getting te max value from each attribute
            if (high > maxHigh) {
                maxHigh = high;
            }
            if (close > maxClose) {
                maxClose = close;
            }
            if (low > maxLow) {
                maxLow = low;
            }
            if (open > maxOpen) {
                maxOpen = open;
            }
            if (volume > maxVolume) {
                maxVolume = volume;
            }

            if (target > maxTarget) {
                maxTarget = target;
            }

//            Getting the min value from each attribute
            if (high < minHigh) {
                minHigh = high;
            }
            if (close < minClose) {
                minClose = close;
            }
            if (low < minLow) {
                minLow = low;
            }
            if (open < minOpen) {
                minOpen = open;
            }
            if (volume < minVolume) {
                minVolume = (long) volume;
            }

            if (target != 0) {
                if (target < minTarget) {
                    minTarget = target;
                }
            }
        }

//        System.out.println("Max Volume: "+maxVolume);
//        System.out.println("Min Volume: "+minVolume+"\n");
//        System.out.println("Max Open :" + maxOpen);
//        Prepare Convert data into CSV file
        FileWriter fileWriterTraining = new FileWriter(training10Data);
        FileWriter fileWriterTesting = new FileWriter(testing10Data);

        //Write the Training CSV file header
        fileWriterTraining.append(FILE_HEADER);
        //Add a new line separator after the header
        fileWriterTraining.append(NEW_LINE_SEPARATOR);

        //Write the Testing CSV file header
        fileWriterTesting.append(FILE_HEADER);
        //Add a new line separator after the header
        fileWriterTesting.append(NEW_LINE_SEPARATOR);

        while (training.next()) {
            countTraining += 1;
            double open = training.getDouble("open");
            double high = training.getDouble("high");
            double low = training.getDouble("low");
            double close = training.getDouble("close");
            double volume = training.getDouble("volume");
            double target = training.getDouble("target");
//                Normalization Function
            highNormalization = (high - minHigh) / (maxHigh - minHigh);
            closeNormalization = (close - minClose) / (maxClose - minClose);
            lowNormalization = (low - minLow) / (maxLow - minLow);
            openNormalization = (open - minOpen) / (maxOpen - minOpen);
            volumeNormalization = (volume - minVolume) / (maxVolume - minVolume);
            targetNormalization = (target - minClose) / (maxClose - minClose);
//            System.out.println("Volume : "+volume+ " - "+minVolume+" / "+maxVolume+" - "+minVolume+" = "+volumeNormalization);
//            System.out.println("Close Value "+close+" Close Normalization : "+closeNormalization);
//            Divide into Training and Testing
            if (countTraining <= 8) {
                //Write a normalization output into CSV File
                fileWriterTraining.append(String.valueOf(countTraining));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(openNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(highNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(lowNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(closeNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(volumeNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(targetNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(NEW_LINE_SEPARATOR);
            }

            if (countTraining > 8) {
//                Write a normalization Testing into CSV File
                countTesting += 1;
                fileWriterTesting.append(String.valueOf(countTesting));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(openNormalization));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(highNormalization));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(lowNormalization));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(closeNormalization));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(volumeNormalization));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(targetNormalization));
                fileWriterTesting.append(NEW_LINE_SEPARATOR);
            }
        }

        fileWriterTraining.flush();
        fileWriterTraining.close();
        fileWriterTesting.flush();
        fileWriterTesting.close();
    }

//      Fitting 50 Data
    public void proses50Data(int dataSet) throws SQLException, IOException {
        int countTraining = 0, countforTraining = 0, countTesting = 0;
        Db.connect();
        int rowTotal = 0;
        ResultSet hasil = null, data = null, training = null, testing = null;
        String totalRow = "SELECT * FROM masterdata";
        hasil = Db.getRs(totalRow);
//        Loop to count total Row
        while (hasil.next()) {
            rowTotal += 1;
        }

        int tamp = rowTotal - dataSet;
        String statement = "SELECT * FROM masterdata LIMIT " + dataSet + " OFFSET " + tamp;
        hasil = Db.getRs(statement);
        training = Db.getRs(statement);
        testing = Db.getRs(statement);

//        Loop to get max and min value from result set
        while (hasil.next()) {
            Date date = hasil.getDate("dates");
            double open = hasil.getDouble("open");
            double high = hasil.getDouble("high");
            double low = hasil.getDouble("low");
            double close = hasil.getDouble("close");
            double volume = hasil.getDouble("volume");
            double target = hasil.getDouble("target");
            
//            System.out.println("Date : "+date+" , Open : " +open+" , High : "+high+" , Low : "+low+" , Close : "+close+" , Volume : "+volume+" , Target : "+target);
            
//            Getting te max value from each attribute
            if (high > maxHigh) {
                maxHigh = high;
            }
            if (close > maxClose) {
                maxClose = close;
            }
            if (low > maxLow) {
                maxLow = low;
            }
            if (open > maxOpen) {
                maxOpen = open;
            }
            if (volume > maxVolume) {
                maxVolume = volume;
            }

            if (target > maxTarget) {
                maxTarget = target;
            }

//            Getting the min value from each attribute
            if (high < minHigh) {
                minHigh = high;
            }
            if (close < minClose) {
                minClose = close;
            }
            if (low < minLow) {
                minLow = low;
            }
            if (open < minOpen) {
                minOpen = open;
            }
            if (volume < minVolume) {
                minVolume = (long) volume;
            }

            if (target != 0) {
                if (target < minTarget) {
                    minTarget = target;
                }
            }
        }

//        Prepare Convert data into CSV file
        FileWriter fileWriterTraining = new FileWriter(training50Data);
        FileWriter fileWriterTesting = new FileWriter(testing50Data);

        //Write the Training CSV file header
        fileWriterTraining.append(FILE_HEADER);
        //Add a new line separator after the header
        fileWriterTraining.append(NEW_LINE_SEPARATOR);

        //Write the Testing CSV file header
        fileWriterTesting.append(FILE_HEADER);
        //Add a new line separator after the header
        fileWriterTesting.append(NEW_LINE_SEPARATOR);

        while (training.next()) {
            countTraining += 1;
            double open = training.getDouble("open");
            double high = training.getDouble("high");
            double low = training.getDouble("low");
            double close = training.getDouble("close");
            double volume = training.getDouble("volume");
            double target = training.getDouble("target");

//                Normalization Function
            highNormalization = (high - minHigh) / (maxHigh - minHigh);
            closeNormalization = (close - minClose) / (maxClose - minClose);
            lowNormalization = (low - minLow) / (maxLow - minLow);
            openNormalization = (open - minOpen) / (maxOpen - minOpen);
            volumeNormalization = (volume - minVolume) / (maxVolume - minVolume);
            targetNormalization = (target - minClose) / (maxClose - minClose);

//            Divide into Training and Testing
            if (countTraining <= 40) {
                //Write a normalization output into CSV File
//                For Column ID
                fileWriterTraining.append(String.valueOf(countTraining));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(openNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(highNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(lowNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(closeNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(volumeNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(targetNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(NEW_LINE_SEPARATOR);
            }

            if (countTraining > 40) {
//                Write a normalization Testing into CSV File
                countTesting += 1;
                fileWriterTesting.append(String.valueOf(countTesting));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(openNormalization));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(highNormalization));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(lowNormalization));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(closeNormalization));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(volumeNormalization));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(targetNormalization));
                fileWriterTesting.append(NEW_LINE_SEPARATOR);
//                System.out.println("Target Normalization Data Testing "+targetNormalization);
//                System.out.println("Target : " + target);
            }
        }
        fileWriterTraining.flush();
        fileWriterTraining.close();
        fileWriterTesting.flush();
        fileWriterTesting.close();
    }

//      Fitting 100 Data
    public void proses100Data(int dataSet) throws SQLException, IOException {
        int countTraining = 0, countforTraining = 0, countTesting = 0;
        Db.connect();
        int rowTotal = 0;
        ResultSet hasil = null, data = null, training = null, testing = null;
        String totalRow = "SELECT * FROM masterdata";
        hasil = Db.getRs(totalRow);
//        Loop to count total Row
        while (hasil.next()) {
            rowTotal += 1;
        }

        int tamp = rowTotal - dataSet;
        String statement = "SELECT * FROM masterdata LIMIT " + dataSet + " OFFSET " + tamp;
        hasil = Db.getRs(statement);
        training = Db.getRs(statement);
        testing = Db.getRs(statement);

//        Loop to get max and min value from result set
        while (hasil.next()) {
            Date date = hasil.getDate("dates");
            double open = hasil.getDouble("open");
            double high = hasil.getDouble("high");
            double low = hasil.getDouble("low");
            double close = hasil.getDouble("close");
            double volume = hasil.getDouble("volume");
            double target = hasil.getDouble("target");
            
//            System.out.println("Date : "+date+" , Open : " +open+" , High : "+high+" , Low : "+low+" , Close : "+close+" , Volume : "+volume+" , Target : "+target);
            
//            Getting te max value from each attribute
            if (high > maxHigh) {
                maxHigh = high;
            }
            if (close > maxClose) {
                maxClose = close;
            }
            if (low > maxLow) {
                maxLow = low;
            }
            if (open > maxOpen) {
                maxOpen = open;
            }
            if (volume > maxVolume) {
                maxVolume = volume;
            }

            if (target > maxTarget) {
                maxTarget = target;
            }

//            Getting the min value from each attribute
            if (high < minHigh) {
                minHigh = high;
            }
            if (close < minClose) {
                minClose = close;
            }
            if (low < minLow) {
                minLow = low;
            }
            if (open < minOpen) {
                minOpen = open;
            }
            if (volume < minVolume) {
                minVolume = (long) volume;
            }

            if (target != 0) {
                if (target < minTarget) {
                    minTarget = target;
                }
            }
        }

//        Prepare Convert data into CSV file
        FileWriter fileWriterTraining = new FileWriter(training100Data);
        FileWriter fileWriterTesting = new FileWriter(testing100Data);

        //Write the Training CSV file header
        fileWriterTraining.append(FILE_HEADER);
        //Add a new line separator after the header
        fileWriterTraining.append(NEW_LINE_SEPARATOR);

        //Write the Testing CSV file header
        fileWriterTesting.append(FILE_HEADER);
        //Add a new line separator after the header
        fileWriterTesting.append(NEW_LINE_SEPARATOR);

        while (training.next()) {
            countTraining += 1;
            double open = training.getDouble("open");
            double high = training.getDouble("high");
            double low = training.getDouble("low");
            double close = training.getDouble("close");
            double volume = training.getDouble("volume");
            double target = training.getDouble("target");
//                Normalization Function
            highNormalization = (high - minHigh) / (maxHigh - minHigh);
            closeNormalization = (close - minClose) / (maxClose - minClose);
            lowNormalization = (low - minLow) / (maxLow - minLow);
            openNormalization = (open - minOpen) / (maxOpen - minOpen);
            volumeNormalization = (volume - minVolume) / (maxVolume - minVolume);
            targetNormalization = (target - minClose) / (maxClose - minClose);

//            Divide into Training and Testing
            if (countTraining <= 80) {
                //Write a normalization output into CSV File
//                For Column ID
                fileWriterTraining.append(String.valueOf(countTraining));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(openNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(highNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(lowNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(closeNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(volumeNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(targetNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(NEW_LINE_SEPARATOR);
            }

            if (countTraining > 80) {
//                Write a normalization Testing into CSV File
                countTesting += 1;
                fileWriterTesting.append(String.valueOf(countTesting));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(openNormalization));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(highNormalization));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(lowNormalization));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(closeNormalization));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(volumeNormalization));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(targetNormalization));
                fileWriterTesting.append(NEW_LINE_SEPARATOR);
//                System.out.println("Target Normalization Data Testing "+targetNormalization);
            }
        }
        fileWriterTraining.flush();
        fileWriterTraining.close();
        fileWriterTesting.flush();
        fileWriterTesting.close();
    }

//      Fitting 500 Data
    public void proses500Data(int dataSet) throws SQLException, IOException {
        int countTraining = 0, countforTraining = 0, countTesting = 0;
        Db.connect();
        int rowTotal = 0;
        ResultSet hasil = null, data = null, training = null, testing = null;
        String totalRow = "SELECT * FROM masterdata";
        hasil = Db.getRs(totalRow);
//        Loop to count total Row
        while (hasil.next()) {
            rowTotal += 1;
        }

        int tamp = rowTotal - dataSet;
        String statement = "SELECT * FROM masterdata LIMIT " + dataSet + " OFFSET " + tamp;
        hasil = Db.getRs(statement);
        training = Db.getRs(statement);
        testing = Db.getRs(statement);

//        Loop to get max and min value from result set
        while (hasil.next()) {
            Date date = hasil.getDate("dates");
            double open = hasil.getDouble("open");
            double high = hasil.getDouble("high");
            double low = hasil.getDouble("low");
            double close = hasil.getDouble("close");
            double volume = hasil.getDouble("volume");
            double target = hasil.getDouble("target");
            
//            System.out.println("Date : "+date+" , Open : " +open+" , High : "+high+" , Low : "+low+" , Close : "+close+" , Volume : "+volume+" , Target : "+target);
            
//            Getting te max value from each attribute
            if (high > maxHigh) {
                maxHigh = high;
            }
            if (close > maxClose) {
                maxClose = close;
            }
            if (low > maxLow) {
                maxLow = low;
            }
            if (open > maxOpen) {
                maxOpen = open;
            }
            if (volume > maxVolume) {
                maxVolume = volume;
            }

            if (target > maxTarget) {
                maxTarget = target;
            }

//            Getting the min value from each attribute
            if (high < minHigh) {
                minHigh = high;
            }
            if (close < minClose) {
                minClose = close;
            }
            if (low < minLow) {
                minLow = low;
            }
            if (open < minOpen) {
                minOpen = open;
            }
            if (volume < minVolume) {
                minVolume = (long) volume;
            }

            if (target != 0) {
                if (target < minTarget) {
                    minTarget = target;
                }
            }
        }

//        Prepare Convert data into CSV file
        FileWriter fileWriterTraining = new FileWriter(training500Data);
        FileWriter fileWriterTesting = new FileWriter(testing500Data);

        //Write the Training CSV file header
        fileWriterTraining.append(FILE_HEADER);
        //Add a new line separator after the header
        fileWriterTraining.append(NEW_LINE_SEPARATOR);

        //Write the Testing CSV file header
        fileWriterTesting.append(FILE_HEADER);
        //Add a new line separator after the header
        fileWriterTesting.append(NEW_LINE_SEPARATOR);

        while (training.next()) {
            countTraining += 1;
            double open = training.getDouble("open");
            double high = training.getDouble("high");
            double low = training.getDouble("low");
            double close = training.getDouble("close");
            double volume = training.getDouble("volume");
            double target = training.getDouble("target");
//                Normalization Function
            highNormalization = (high - minHigh) / (maxHigh - minHigh);
            closeNormalization = (close - minClose) / (maxClose - minClose);
            lowNormalization = (low - minLow) / (maxLow - minLow);
            openNormalization = (open - minOpen) / (maxOpen - minOpen);
            volumeNormalization = (volume - minVolume) / (maxVolume - minVolume);
            targetNormalization = (target - minClose) / (maxClose - minClose);

//            Divide into Training and Testing
            if (countTraining <= 400) {
                //Write a normalization output into CSV File
//                For Column ID
                fileWriterTraining.append(String.valueOf(countTraining));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(openNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(highNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(lowNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(closeNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(volumeNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(targetNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(NEW_LINE_SEPARATOR);
            }

            if (countTraining > 400) {
//                Write a normalization Testing into CSV File
                countTesting += 1;
                fileWriterTesting.append(String.valueOf(countTesting));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(openNormalization));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(highNormalization));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(lowNormalization));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(closeNormalization));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(volumeNormalization));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(targetNormalization));
                fileWriterTesting.append(NEW_LINE_SEPARATOR);
//                System.out.println("Target Normalization Data Testing "+targetNormalization);
            }
        }
        fileWriterTraining.flush();
        fileWriterTraining.close();
        fileWriterTesting.flush();
        fileWriterTesting.close();
    }

//      Fitting 2500 Data
    public void proses2500Data(int dataSet) throws SQLException, IOException {
        int countTraining = 0, countforTraining = 0, countTesting = 0;
        Db.connect();
        int rowTotal = 0;
        ResultSet hasil = null, data = null, training = null, testing = null;
        String totalRow = "SELECT * FROM masterdata";
        hasil = Db.getRs(totalRow);
//        Loop to count total Row
        while (hasil.next()) {
            rowTotal += 1;
        }

        int tamp = rowTotal - dataSet;
        String statement = "SELECT * FROM masterdata LIMIT " + dataSet + " OFFSET " + tamp;
        hasil = Db.getRs(statement);
        training = Db.getRs(statement);
        testing = Db.getRs(statement);

//        Loop to get max and min value from result set
        while (hasil.next()) {
            Date date = hasil.getDate("dates");
            double open = hasil.getDouble("open");
            double high = hasil.getDouble("high");
            double low = hasil.getDouble("low");
            double close = hasil.getDouble("close");
            double volume = hasil.getDouble("volume");
            double target = hasil.getDouble("target");
            
//            System.out.println("Date : "+date+" , Open : " +open+" , High : "+high+" , Low : "+low+" , Close : "+close+" , Volume : "+volume+" , Target : "+target);
            
//            Getting the max value from each attribute
            if (high > maxHigh) {
                maxHigh = high;
            }
            if (close > maxClose) {
                maxClose = close;
            }
            if (low > maxLow) {
                maxLow = low;
            }
            if (open > maxOpen) {
                maxOpen = open;
            }
            if (volume > maxVolume) {
                maxVolume = volume;
            }

            if (target > maxTarget) {
                maxTarget = target;
            }

//            Getting the min value from each attribute
            if (high < minHigh) {
                minHigh = high;
            }
            if (close < minClose) {
                minClose = close;
            }
            if (low < minLow) {
                minLow = low;
            }
            if (open < minOpen) {
                minOpen = open;
            }
            if (volume < minVolume) {
                minVolume = (long) volume;
            }

            if (target != 0) {
                if (target < minTarget) {
                    minTarget = target;
                }
            }
        }

//        Prepare Convert data into CSV file
        FileWriter fileWriterTraining = new FileWriter(training2500Data);
        FileWriter fileWriterTesting = new FileWriter(testing2500Data);

        //Write the Training CSV file header
        fileWriterTraining.append(FILE_HEADER);
        //Add a new line separator after the header
        fileWriterTraining.append(NEW_LINE_SEPARATOR);

        //Write the Testing CSV file header
        fileWriterTesting.append(FILE_HEADER);
        //Add a new line separator after the header
        fileWriterTesting.append(NEW_LINE_SEPARATOR);

        while (training.next()) {
            countTraining += 1;
            double open = training.getDouble("open");
            double high = training.getDouble("high");
            double low = training.getDouble("low");
            double close = training.getDouble("close");
            double volume = training.getDouble("volume");
            double target = training.getDouble("target");
//                Normalization Function
            highNormalization = (high - minHigh) / (maxHigh - minHigh);
            closeNormalization = (close - minClose) / (maxClose - minClose);
            lowNormalization = (low - minLow) / (maxLow - minLow);
            openNormalization = (open - minOpen) / (maxOpen - minOpen);
            volumeNormalization = (volume - minVolume) / (maxVolume - minVolume);
            targetNormalization = (target - minClose) / (maxClose - minClose);

//            Divide into Training and Testing
            if (countTraining <= 2000) {
                //Write a normalization output into CSV File
//                For Column ID
                fileWriterTraining.append(String.valueOf(countTraining));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(openNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(highNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(lowNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(closeNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(volumeNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(targetNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(NEW_LINE_SEPARATOR);
            }

            if (countTraining > 2000) {
//                Write a normalization Testing into CSV File
                countTesting += 1;
                fileWriterTesting.append(String.valueOf(countTesting));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(openNormalization));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(highNormalization));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(lowNormalization));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(closeNormalization));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(volumeNormalization));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(targetNormalization));
                fileWriterTesting.append(NEW_LINE_SEPARATOR);
//                System.out.println("Target Normalization Data Testing "+targetNormalization);
            }
        }
        fileWriterTraining.flush();
        fileWriterTraining.close();
        fileWriterTesting.flush();
        fileWriterTesting.close();
    }

    public void prosesData(int dataSet) throws SQLException, IOException {
        FileWriter fileWriterTraining;
        FileWriter fileWriterTesting;
        int countTraining = 0, countforTraining = 0, countTesting = 0;
        Db.connect();
        int rowTotal = 0;
        ResultSet hasil = null, data = null, training = null, testing = null;
        String totalRow = "SELECT * FROM masterdata";
        hasil = Db.getRs(totalRow);
//        Loop to count total Row
        while (hasil.next()) {
            rowTotal += 1;
        }

        int tamp = rowTotal - dataSet;
        String statement = "SELECT * FROM masterdata LIMIT " + dataSet + " OFFSET " + tamp;
        hasil = Db.getRs(statement);
        training = Db.getRs(statement);
        testing = Db.getRs(statement);

//        Loop to get max and min value from result set
        while (hasil.next()) {
            double open = hasil.getDouble("open");
            double high = hasil.getDouble("high");
            double low = hasil.getDouble("low");
            double close = hasil.getDouble("close");
            double volume = hasil.getDouble("volume");
            double target = hasil.getDouble("target");

//            Getting the max value from each attribute
            if (high > maxHigh) {
                maxHigh = high;
            }
            if (close > maxClose) {
                maxClose = close;
            }
            if (low > maxLow) {
                maxLow = low;
            }
            if (open > maxOpen) {
                maxOpen = open;
            }
            if (volume > maxVolume) {
                maxVolume = volume;
            }

            if (target > maxTarget) {
                maxTarget = target;
            }

//            Getting the min value from each attribute
            if (high < minHigh) {
                minHigh = high;
            }
            if (close < minClose) {
                minClose = close;
            }
            if (low < minLow) {
                minLow = low;
            }
            if (open < minOpen) {
                minOpen = open;
            }
            if (volume < minVolume) {
                minVolume = (long) volume;
            }

            if (target != 0) {
                if (target < minTarget) {
                    minTarget = target;
                }
            }
        }

        if (dataSet == 10) {
            fileWriterTraining = new FileWriter(training10Data);
            fileWriterTesting = new FileWriter(testing10Data);
            //Write the Training CSV file header
            fileWriterTraining.append(FILE_HEADER);
            //Add a new line separator after the header
            fileWriterTraining.append(NEW_LINE_SEPARATOR);

            //Write the Testing CSV file header
            fileWriterTesting.append(FILE_HEADER);
            //Add a new line separator after the header
            fileWriterTesting.append(NEW_LINE_SEPARATOR);

            while (training.next()) {
                countTraining += 1;
                double open = training.getDouble("open");
                double high = training.getDouble("high");
                double low = training.getDouble("low");
                double close = training.getDouble("close");
                double volume = training.getDouble("volume");
                double target = training.getDouble("target");
//                Normalization Function
                highNormalization = (high - minHigh) / (maxHigh - minHigh);
                closeNormalization = (close - minClose) / (maxClose - minClose);
                lowNormalization = (low - minLow) / (maxLow - minLow);
                openNormalization = (open - minOpen) / (maxOpen - minOpen);
                volumeNormalization = (volume - minVolume) / (maxVolume - minVolume);
                targetNormalization = (target - minClose) / (maxClose - minClose);
                
                System.out.println(countTraining+" : Nilai Open :"+openNormalization);
//            Divide into Training and Testing
                if (countTraining <= 8) {
                    //Write a normalization output into CSV File
//                For Column ID
                    fileWriterTraining.append(String.valueOf(countTraining));
                    fileWriterTraining.append(COMMA_DELIMITER);
                    fileWriterTraining.append(String.valueOf(openNormalization));
                    fileWriterTraining.append(COMMA_DELIMITER);
                    fileWriterTraining.append(String.valueOf(highNormalization));
                    fileWriterTraining.append(COMMA_DELIMITER);
                    fileWriterTraining.append(String.valueOf(lowNormalization));
                    fileWriterTraining.append(COMMA_DELIMITER);
                    fileWriterTraining.append(String.valueOf(closeNormalization));
                    fileWriterTraining.append(COMMA_DELIMITER);
                    fileWriterTraining.append(String.valueOf(volumeNormalization));
                    fileWriterTraining.append(COMMA_DELIMITER);
                    fileWriterTraining.append(String.valueOf(targetNormalization));
                    fileWriterTraining.append(COMMA_DELIMITER);
                    fileWriterTraining.append(NEW_LINE_SEPARATOR);
                }
                if (countTraining > 8) {
//                Write a normalization Testing into CSV File
                    countTesting += 1;
                    fileWriterTesting.append(String.valueOf(countTesting));
                    fileWriterTesting.append(COMMA_DELIMITER);
                    fileWriterTesting.append(String.valueOf(openNormalization));
                    fileWriterTesting.append(COMMA_DELIMITER);
                    fileWriterTesting.append(String.valueOf(highNormalization));
                    fileWriterTesting.append(COMMA_DELIMITER);
                    fileWriterTesting.append(String.valueOf(lowNormalization));
                    fileWriterTesting.append(COMMA_DELIMITER);
                    fileWriterTesting.append(String.valueOf(closeNormalization));
                    fileWriterTesting.append(COMMA_DELIMITER);
                    fileWriterTesting.append(String.valueOf(volumeNormalization));
                    fileWriterTesting.append(COMMA_DELIMITER);
                    fileWriterTesting.append(String.valueOf(targetNormalization));
                    fileWriterTesting.append(NEW_LINE_SEPARATOR);
                }
            }
        } else if (dataSet == 50) {
            fileWriterTraining = new FileWriter(training50Data);
            fileWriterTesting = new FileWriter(testing50Data);
            //Write the Training CSV file header
            fileWriterTraining.append(FILE_HEADER);
            //Add a new line separator after the header
            fileWriterTraining.append(NEW_LINE_SEPARATOR);

            //Write the Testing CSV file header
            fileWriterTesting.append(FILE_HEADER);
            //Add a new line separator after the header
            fileWriterTesting.append(NEW_LINE_SEPARATOR);

            while (training.next()) {
                countTraining += 1;
                double open = training.getDouble("open");
                double high = training.getDouble("high");
                double low = training.getDouble("low");
                double close = training.getDouble("close");
                double volume = training.getDouble("volume");
                double target = training.getDouble("target");

//                Normalization Function
                highNormalization = (high - minHigh) / (maxHigh - minHigh);
                closeNormalization = (close - minClose) / (maxClose - minClose);
                lowNormalization = (low - minLow) / (maxLow - minLow);
                openNormalization = (open - minOpen) / (maxOpen - minOpen);
                volumeNormalization = (volume - minVolume) / (maxVolume - minVolume);
                targetNormalization = (target - minClose) / (maxClose - minClose);
                
                System.out.println(countTraining+" : Nilai Open :"+openNormalization);
                
//            Divide into Training and Testing
                if (countTraining <= 40) {
                    //Write a normalization output into CSV File
//                For Column ID
                    fileWriterTraining.append(String.valueOf(countTraining));
                    fileWriterTraining.append(COMMA_DELIMITER);
                    fileWriterTraining.append(String.valueOf(openNormalization));
                    fileWriterTraining.append(COMMA_DELIMITER);
                    fileWriterTraining.append(String.valueOf(highNormalization));
                    fileWriterTraining.append(COMMA_DELIMITER);
                    fileWriterTraining.append(String.valueOf(lowNormalization));
                    fileWriterTraining.append(COMMA_DELIMITER);
                    fileWriterTraining.append(String.valueOf(closeNormalization));
                    fileWriterTraining.append(COMMA_DELIMITER);
                    fileWriterTraining.append(String.valueOf(volumeNormalization));
                    fileWriterTraining.append(COMMA_DELIMITER);
                    fileWriterTraining.append(String.valueOf(targetNormalization));
                    fileWriterTraining.append(COMMA_DELIMITER);
                    fileWriterTraining.append(NEW_LINE_SEPARATOR);
                }

                if (countTraining > 40) {
//                Write a normalization Testing into CSV File
                    countTesting += 1;
                    fileWriterTesting.append(String.valueOf(countTesting));
                    fileWriterTesting.append(COMMA_DELIMITER);
                    fileWriterTesting.append(String.valueOf(openNormalization));
                    fileWriterTesting.append(COMMA_DELIMITER);
                    fileWriterTesting.append(String.valueOf(highNormalization));
                    fileWriterTesting.append(COMMA_DELIMITER);
                    fileWriterTesting.append(String.valueOf(lowNormalization));
                    fileWriterTesting.append(COMMA_DELIMITER);
                    fileWriterTesting.append(String.valueOf(closeNormalization));
                    fileWriterTesting.append(COMMA_DELIMITER);
                    fileWriterTesting.append(String.valueOf(volumeNormalization));
                    fileWriterTesting.append(COMMA_DELIMITER);
                    fileWriterTesting.append(String.valueOf(targetNormalization));
                    fileWriterTesting.append(NEW_LINE_SEPARATOR);
                }
            }
        } else if (dataSet == 100) {
            fileWriterTraining = new FileWriter(training100Data);
            fileWriterTesting = new FileWriter(testing100Data);
            //Write the Training CSV file header
            fileWriterTraining.append(FILE_HEADER);
            //Add a new line separator after the header
            fileWriterTraining.append(NEW_LINE_SEPARATOR);

            //Write the Testing CSV file header
            fileWriterTesting.append(FILE_HEADER);
            //Add a new line separator after the header
            fileWriterTesting.append(NEW_LINE_SEPARATOR);

            while (training.next()) {
                countTraining += 1;
                double open = training.getDouble("open");
                double high = training.getDouble("high");
                double low = training.getDouble("low");
                double close = training.getDouble("close");
                double volume = training.getDouble("volume");
                double target = training.getDouble("target");
                
//                Normalization Function
                highNormalization = (high - minHigh) / (maxHigh - minHigh);
                closeNormalization = (close - minClose) / (maxClose - minClose);
                lowNormalization = (low - minLow) / (maxLow - minLow);
                openNormalization = (open - minOpen) / (maxOpen - minOpen);
                volumeNormalization = (volume - minVolume) / (maxVolume - minVolume);
                targetNormalization = (target - minClose) / (maxClose - minClose);

                System.out.println(countTraining+" : Nilai Open :"+openNormalization);
                
//            Divide into Training and Testing
                if (countTraining <= 80) {
                    //Write a normalization output into CSV File
//                For Column ID
                    fileWriterTraining.append(String.valueOf(countTraining));
                    fileWriterTraining.append(COMMA_DELIMITER);
                    fileWriterTraining.append(String.valueOf(openNormalization));
                    fileWriterTraining.append(COMMA_DELIMITER);
                    fileWriterTraining.append(String.valueOf(highNormalization));
                    fileWriterTraining.append(COMMA_DELIMITER);
                    fileWriterTraining.append(String.valueOf(lowNormalization));
                    fileWriterTraining.append(COMMA_DELIMITER);
                    fileWriterTraining.append(String.valueOf(closeNormalization));
                    fileWriterTraining.append(COMMA_DELIMITER);
                    fileWriterTraining.append(String.valueOf(volumeNormalization));
                    fileWriterTraining.append(COMMA_DELIMITER);
                    fileWriterTraining.append(String.valueOf(targetNormalization));
                    fileWriterTraining.append(COMMA_DELIMITER);
                    fileWriterTraining.append(NEW_LINE_SEPARATOR);
                }

                if (countTraining > 80) {
//                Write a normalization Testing into CSV File
                    countTesting += 1;
                    fileWriterTesting.append(String.valueOf(countTesting));
                    fileWriterTesting.append(COMMA_DELIMITER);
                    fileWriterTesting.append(String.valueOf(openNormalization));
                    fileWriterTesting.append(COMMA_DELIMITER);
                    fileWriterTesting.append(String.valueOf(highNormalization));
                    fileWriterTesting.append(COMMA_DELIMITER);
                    fileWriterTesting.append(String.valueOf(lowNormalization));
                    fileWriterTesting.append(COMMA_DELIMITER);
                    fileWriterTesting.append(String.valueOf(closeNormalization));
                    fileWriterTesting.append(COMMA_DELIMITER);
                    fileWriterTesting.append(String.valueOf(volumeNormalization));
                    fileWriterTesting.append(COMMA_DELIMITER);
                    fileWriterTesting.append(String.valueOf(targetNormalization));
                    fileWriterTesting.append(NEW_LINE_SEPARATOR);
                }
            }
        } else if (dataSet == 500) {
            fileWriterTraining = new FileWriter(training500Data);
            fileWriterTesting = new FileWriter(testing500Data);
            //Write the Training CSV file header
            fileWriterTraining.append(FILE_HEADER);
            //Add a new line separator after the header
            fileWriterTraining.append(NEW_LINE_SEPARATOR);

            //Write the Testing CSV file header
            fileWriterTesting.append(FILE_HEADER);
            //Add a new line separator after the header
            fileWriterTesting.append(NEW_LINE_SEPARATOR);

            while (training.next()) {
                countTraining += 1;
                double open = training.getDouble("open");
                double high = training.getDouble("high");
                double low = training.getDouble("low");
                double close = training.getDouble("close");
                double volume = training.getDouble("volume");
                double target = training.getDouble("target");
                System.out.println("Nilai Open :"+open);
//                Normalization Function
                highNormalization = (high - minHigh) / (maxHigh - minHigh);
                closeNormalization = (close - minClose) / (maxClose - minClose);
                lowNormalization = (low - minLow) / (maxLow - minLow);
                openNormalization = (open - minOpen) / (maxOpen - minOpen);
                volumeNormalization = (volume - minVolume) / (maxVolume - minVolume);
                targetNormalization = (target - minClose) / (maxClose - minClose);
                
                System.out.println(countTraining+" : Nilai Open :"+openNormalization);
                
//            Divide into Training and Testing
                if (countTraining <= 400) {
                    //Write a normalization output into CSV File
//                For Column ID
                    fileWriterTraining.append(String.valueOf(countTraining));
                    fileWriterTraining.append(COMMA_DELIMITER);
                    fileWriterTraining.append(String.valueOf(openNormalization));
                    fileWriterTraining.append(COMMA_DELIMITER);
                    fileWriterTraining.append(String.valueOf(highNormalization));
                    fileWriterTraining.append(COMMA_DELIMITER);
                    fileWriterTraining.append(String.valueOf(lowNormalization));
                    fileWriterTraining.append(COMMA_DELIMITER);
                    fileWriterTraining.append(String.valueOf(closeNormalization));
                    fileWriterTraining.append(COMMA_DELIMITER);
                    fileWriterTraining.append(String.valueOf(volumeNormalization));
                    fileWriterTraining.append(COMMA_DELIMITER);
                    fileWriterTraining.append(String.valueOf(targetNormalization));
                    fileWriterTraining.append(COMMA_DELIMITER);
                    fileWriterTraining.append(NEW_LINE_SEPARATOR);
                }

                if (countTraining > 400) {
//                Write a normalization Testing into CSV File
                    countTesting += 1;
                    fileWriterTesting.append(String.valueOf(countTesting));
                    fileWriterTesting.append(COMMA_DELIMITER);
                    fileWriterTesting.append(String.valueOf(openNormalization));
                    fileWriterTesting.append(COMMA_DELIMITER);
                    fileWriterTesting.append(String.valueOf(highNormalization));
                    fileWriterTesting.append(COMMA_DELIMITER);
                    fileWriterTesting.append(String.valueOf(lowNormalization));
                    fileWriterTesting.append(COMMA_DELIMITER);
                    fileWriterTesting.append(String.valueOf(closeNormalization));
                    fileWriterTesting.append(COMMA_DELIMITER);
                    fileWriterTesting.append(String.valueOf(volumeNormalization));
                    fileWriterTesting.append(COMMA_DELIMITER);
                    fileWriterTesting.append(String.valueOf(targetNormalization));
                    fileWriterTesting.append(NEW_LINE_SEPARATOR);
                }
            }
        } else if (dataSet == 2500) {
            fileWriterTraining = new FileWriter(training2500Data);
            fileWriterTesting = new FileWriter(testing2500Data);
            //Write the Training CSV file header
            fileWriterTraining.append(FILE_HEADER);
            //Add a new line separator after the header
            fileWriterTraining.append(NEW_LINE_SEPARATOR);

            //Write the Testing CSV file header
            fileWriterTesting.append(FILE_HEADER);
            //Add a new line separator after the header
            fileWriterTesting.append(NEW_LINE_SEPARATOR);

            while (training.next()) {
                countTraining += 1;
                double open = training.getDouble("open");
                double high = training.getDouble("high");
                double low = training.getDouble("low");
                double close = training.getDouble("close");
                double volume = training.getDouble("volume");
                double target = training.getDouble("target");
                System.out.println("Nilai Open :"+open);
//                Normalization Function
                highNormalization = (high - minHigh) / (maxHigh - minHigh);
                closeNormalization = (close - minClose) / (maxClose - minClose);
                lowNormalization = (low - minLow) / (maxLow - minLow);
                openNormalization = (open - minOpen) / (maxOpen - minOpen);
                volumeNormalization = (volume - minVolume) / (maxVolume - minVolume);
                targetNormalization = (target - minClose) / (maxClose - minClose);
                
                System.out.println(countTraining+" : Nilai Open :"+openNormalization);
                
//            Divide into Training and Testing
                if (countTraining <= 2000) {
                    //Write a normalization output into CSV File
//                For Column ID
                    fileWriterTraining.append(String.valueOf(countTraining));
                    fileWriterTraining.append(COMMA_DELIMITER);
                    fileWriterTraining.append(String.valueOf(openNormalization));
                    fileWriterTraining.append(COMMA_DELIMITER);
                    fileWriterTraining.append(String.valueOf(highNormalization));
                    fileWriterTraining.append(COMMA_DELIMITER);
                    fileWriterTraining.append(String.valueOf(lowNormalization));
                    fileWriterTraining.append(COMMA_DELIMITER);
                    fileWriterTraining.append(String.valueOf(closeNormalization));
                    fileWriterTraining.append(COMMA_DELIMITER);
                    fileWriterTraining.append(String.valueOf(volumeNormalization));
                    fileWriterTraining.append(COMMA_DELIMITER);
                    fileWriterTraining.append(String.valueOf(targetNormalization));
                    fileWriterTraining.append(COMMA_DELIMITER);
                    fileWriterTraining.append(NEW_LINE_SEPARATOR);
                }

                if (countTraining > 2000) {
//                Write a normalization Testing into CSV File
                    countTesting += 1;
                    fileWriterTesting.append(String.valueOf(countTesting));
                    fileWriterTesting.append(COMMA_DELIMITER);
                    fileWriterTesting.append(String.valueOf(openNormalization));
                    fileWriterTesting.append(COMMA_DELIMITER);
                    fileWriterTesting.append(String.valueOf(highNormalization));
                    fileWriterTesting.append(COMMA_DELIMITER);
                    fileWriterTesting.append(String.valueOf(lowNormalization));
                    fileWriterTesting.append(COMMA_DELIMITER);
                    fileWriterTesting.append(String.valueOf(closeNormalization));
                    fileWriterTesting.append(COMMA_DELIMITER);
                    fileWriterTesting.append(String.valueOf(volumeNormalization));
                    fileWriterTesting.append(COMMA_DELIMITER);
                    fileWriterTesting.append(String.valueOf(targetNormalization));
                    fileWriterTesting.append(NEW_LINE_SEPARATOR);
                }
            }
            fileWriterTraining.flush();
            fileWriterTraining.close();
            fileWriterTesting.flush();
            fileWriterTesting.close();
        }
    }
        /*
        Function to Denormallization output that has been compute by R Language
         */
    public double denormalizationDailyInput(int query, double value) throws SQLException, IOException {
        DatabaseController Db = new DatabaseController(); // handle any errors
        Db.connect();
        int rowTotal = 0;
        ResultSet hasil = null, norm = null;
        String totalRow = "SELECT * FROM masterdata";
        hasil = Db.getRs(totalRow);
//        Loop to count total Row
        while (hasil.next()) {
            rowTotal += 1;
        }

        tamp = rowTotal - query;
        String statement = "SELECT * FROM masterdata LIMIT " + query + " OFFSET " + tamp;

        hasil = Db.getRs(statement);
        norm = Db.getRs(statement);
        ResultSetMetaData rsm = hasil.getMetaData();
        int columns = rsm.getColumnCount();

//        Loop for getting max and min Values from data
        while (hasil.next()) {
            double open = hasil.getDouble("open");
            double high = hasil.getDouble("high");
            double low = hasil.getDouble("low");
            double close = hasil.getDouble("close");
            double volume = hasil.getDouble("volume");
            double target = hasil.getDouble("target");

//            Getting te max value from each attribute
            if (high > maxHigh) {
                maxHigh = high;
            }
            if (close > maxClose) {
                maxClose = close;
            }
            if (low > maxLow) {
                maxLow = low;
            }
            if (open > maxOpen) {
                maxOpen = open;
            }
            if (volume > maxVolume) {
                maxVolume = volume;
            }

            if (target > maxTarget) {
                maxTarget = target;
            }

//            Getting the min value from each attribute
            if (high < minHigh) {
                minHigh = high;
            }
            if (close < minClose) {
                minClose = close;
            }
            if (low < minLow) {
                minLow = low;
            }
            if (open < minOpen) {
                minOpen = open;
            }
            if (volume < minVolume) {
                minVolume = (long) volume;
            }

            if (target < minTarget) {
                minTarget = target;
            }
        }
        finalOutput = (value * (maxClose - minClose) + minClose);
        return finalOutput;
    }

    public double denormalizationMonthlyInput(int days, int month, int year, double value) throws SQLException, IOException {
        Db.connect();
        double finaloutput = 0;
        int countTesting = 0, counterId = 0;
        List<Data> data = new ArrayList<>();
        ResultSet hasil = null;
        int interval = 3, tmpDays = 0, tmpMonth = 0;
        int iteration = 10;

        int a = Math.floorDiv(interval * iteration, 12);
        int remainderMonth = (interval * iteration) - (12 * a);
//        System.out.println(a + " " + remainderMonth);

        int currentYear = year - a;
        int currentMonth = month - remainderMonth;
        if (currentMonth <= 0) {
            currentMonth = 12 + currentMonth;
            currentYear -= 1;
        }

//        System.out.println(currentMonth + " " + currentYear);
        for (int i = 1; i <= iteration; i++) {
            currentMonth += interval;

            if (currentMonth > 12) {
                currentMonth = currentMonth - 12;
                currentYear++;
            }

            String statement = "SELECT * FROM masterdatabulanan WHERE DAY(dates)=" + days + " AND MONTH(dates)=" + currentMonth + " AND YEAR(dates)=" + currentYear + "";
//            System.out.println(i+": "+currentMonth + " " + currentYear);
            hasil = Db.getRs(statement);

//            Function to check result set exist or not
            tmpDays = days;
            tmpMonth = currentMonth;
            while (!hasil.next()) {
                tmpDays = tmpDays - 1;
                if (tmpDays == 0) {
                    tmpDays = 31;
//                    System.out.println("Current Month : " + currentMonth);
                    tmpMonth = tmpMonth - 1;
//                    System.out.println("Month : " + tmpMonth);
                }
//                System.out.println("Tmp Value : " + tmpDays);
                String updStatement = "SELECT * FROM masterdatabulanan WHERE DAY(dates)=" + tmpDays + " AND MONTH(dates)=" + tmpMonth + " AND YEAR(dates)=" + currentYear + "";
                hasil = Db.getRs(updStatement);
            }

//            Function to check result set exist or not
            do {
                double closes = hasil.getDouble("close");
                counterId += 1;

                //            Getting te max value from each attribute
                if (closes > maxCloses) {
                    maxCloses = closes;
                }

//            Getting the min value from each attribute
                if (closes < minCloses) {
                    minCloses = closes;
                }
            } while (hasil.next());
        }
        finaloutput = (value * (maxCloses - minCloses) + minCloses);
        return finaloutput;
    }

    /*
        Return Data Target from each Daily data set 
     */
    public List<Double> targetDataTestingDaily(int dataSet) throws SQLException, IOException {
        List<Double> targetTesting = new ArrayList<>();
        int counter = 0;
        Db.connect();
        int rowTotal = 0;
        ResultSet hasil = null, data = null, training = null, testing = null;
        String totalRow = "SELECT * FROM masterdata";
        hasil = Db.getRs(totalRow);
//        Loop to count total Row
        while (hasil.next()) {
            rowTotal += 1;
        }

        int tamp = rowTotal - dataSet;
        String statement = "SELECT * FROM masterdata LIMIT " + dataSet + " OFFSET " + tamp;
        hasil = Db.getRs(statement);
        training = Db.getRs(statement);
        testing = Db.getRs(statement);

//        Loop to get max and min value from result set
        while (hasil.next()) {
            double open = hasil.getDouble("open");
            double high = hasil.getDouble("high");
            double low = hasil.getDouble("low");
            double close = hasil.getDouble("close");
            double volume = hasil.getDouble("volume");
            double target = hasil.getDouble("target");
            counter += 1;

            if (dataSet == 10) {
                if (counter > 8) {
                    targetTesting.add(target);
                }
            } else if (dataSet == 50) {
                if (counter > 40) {
                    targetTesting.add(target);
                }
            } else if (dataSet == 100) {
                if (counter > 80) {
                    targetTesting.add(target);
                }
            } else if (dataSet == 500) {
                if (counter > 400) {
                    targetTesting.add(target);
                }
            } else if (dataSet == 2500) {
                if (counter > 2000) {
                    targetTesting.add(target);
                }
            }
        }
        return targetTesting;
    }

    public List<Double> targetDataTrainingDaily(int dataSet) throws SQLException, IOException {
        List<Double> targetTraining = new ArrayList<>();
        int counter = 0;
        Db.connect();
        int rowTotal = 0;
        ResultSet hasil = null, data = null, training = null, testing = null;
        String totalRow = "SELECT * FROM masterdata";
        hasil = Db.getRs(totalRow);
//        Loop to count total Row
        while (hasil.next()) {
            rowTotal += 1;
        }

        int tamp = rowTotal - dataSet;
        String statement = "SELECT * FROM masterdata LIMIT " + dataSet + " OFFSET " + tamp;
        hasil = Db.getRs(statement);
        training = Db.getRs(statement);
        testing = Db.getRs(statement);

//        Loop to get max and min value from result set
        while (hasil.next()) {
            double open = hasil.getDouble("open");
            double high = hasil.getDouble("high");
            double low = hasil.getDouble("low");
            double close = hasil.getDouble("close");
            double volume = hasil.getDouble("volume");
            double target = hasil.getDouble("target");
            counter += 1;

            if (dataSet == 10) {
                if (counter <= 8) {
                    targetTraining.add(target);
                }
            } else if (dataSet == 50) {
                if (counter <= 40) {
                    targetTraining.add(target);
                }
            } else if (dataSet == 100) {
                if (counter <= 80) {
                    targetTraining.add(target);
                }
            } else if (dataSet == 500) {
                if (counter <= 400) {
                    targetTraining.add(target);
                }
            } else if (dataSet == 2500) {
                if (counter <= 2000) {
                    targetTraining.add(target);
                }
            }
        }
        return targetTraining;
    }

    public List<Double> targetDataTrainingMonthlySecond(int dataSet) throws SQLException, IOException {
        List<Double> targetTraining = new ArrayList<>();
        int counter = 0;
        Db.connect();
        int rowTotal = 0;
        ResultSet hasil = null, data = null, training = null, testing = null;
        String totalRow = "SELECT * FROM masterdatabulanan";
        hasil = Db.getRs(totalRow);
//        Loop to count total Row
        while (hasil.next()) {
            rowTotal += 1;
        }

        int tamp = rowTotal - dataSet;
        String statement = "SELECT * FROM masterdatabulanan LIMIT " + dataSet + " OFFSET " + tamp;
        hasil = Db.getRs(statement);
        training = Db.getRs(statement);
        testing = Db.getRs(statement);

//        Loop to get max and min value from result set
        while (hasil.next()) {
            double open = hasil.getDouble("open");
            double high = hasil.getDouble("high");
            double low = hasil.getDouble("low");
            double close = hasil.getDouble("close");
            double volume = hasil.getDouble("volume");
            double target = hasil.getDouble("target");
            counter += 1;

            if (dataSet == 10) {
                if (counter <= 8) {
                    targetTraining.add(target);
                }
            } else if (dataSet == 50) {
                if (counter <= 40) {
                    targetTraining.add(target);
                }
            } else if (dataSet == 120) {
                if (counter <= 96) {
                    targetTraining.add(target);
                }
            }
        }
        return targetTraining;
    }

    public List<Double> targetDataTestingMonthlySecond(int dataSet) throws SQLException, IOException {
        List<Double> targetTesting = new ArrayList<>();
        int counter = 0;
        Db.connect();
        int rowTotal = 0;
        ResultSet hasil = null, data = null, training = null, testing = null;
        String totalRow = "SELECT * FROM masterdatabulanan";
        hasil = Db.getRs(totalRow);
//        Loop to count total Row
        while (hasil.next()) {
            rowTotal += 1;
        }

        int tamp = rowTotal - dataSet;
        String statement = "SELECT * FROM masterdatabulanan LIMIT " + dataSet + " OFFSET " + tamp;
        hasil = Db.getRs(statement);
        training = Db.getRs(statement);
        testing = Db.getRs(statement);

//        Loop to get max and min value from result set
        while (hasil.next()) {
            double open = hasil.getDouble("open");
            double high = hasil.getDouble("high");
            double low = hasil.getDouble("low");
            double close = hasil.getDouble("close");
            double volume = hasil.getDouble("volume");
            double target = hasil.getDouble("target");
            counter += 1;

            if (dataSet == 10) {
                if (counter > 8) {
                    targetTesting.add(target);
                }
            } else if (dataSet == 50) {
                if (counter > 40) {
                    targetTesting.add(target);
                }
            } else if (dataSet == 120) {
                if (counter > 96) {
                    targetTesting.add(target);
                }
            }
        }
        return targetTesting;
    }

    /*
        Return Data Target from each Monthly Data Set 
     */
    public List<Double> targetDataTrainingMonthly(int days, int month, int year, int dataset) throws SQLException, IOException {
        List<Double> targetMonthlyTraining = new ArrayList<>();
        Db.connect();
        int countTesting = 0, counterId = 0, counter = 0;
        List<Data> data = new ArrayList<>();
        ResultSet hasil = null;
        int interval = 3, tmpDays = 0, tmpMonth = 0;
        int iteration = dataset;

        int a = Math.floorDiv(interval * iteration, 12);
        int remainderMonth = (interval * iteration) - (12 * a);
//        System.out.println(a + " " + remainderMonth);

        int currentYear = year - a;
        int currentMonth = month - remainderMonth;
        if (currentMonth <= 0) {
            currentMonth = 12 + currentMonth;
            currentYear -= 1;
        }

//        System.out.println(currentMonth + " " + currentYear);
        for (int i = 1; i <= iteration; i++) {
            currentMonth += interval;

            if (currentMonth > 12) {
                currentMonth = currentMonth - 12;
                currentYear++;
            }

            String statement = "SELECT * FROM masterdatabulanan WHERE DAY(dates)=" + days + " AND MONTH(dates)=" + currentMonth + " AND YEAR(dates)=" + currentYear + "";
//            System.out.println(i+": "+currentMonth + " " + currentYear);
            hasil = Db.getRs(statement);

//            Function to check result set exist or not
            tmpDays = days;
            tmpMonth = currentMonth;
            while (!hasil.next()) {
                tmpDays = tmpDays - 1;
                if (tmpDays == 0) {
                    tmpDays = 31;
//                    System.out.println("Current Month : " + currentMonth);
                    tmpMonth = tmpMonth - 1;
//                    System.out.println("Month : " + tmpMonth);
                }
//                System.out.println("Tmp Value : " + tmpDays);
                String updStatement = "SELECT * FROM masterdatabulanan WHERE DAY(dates)=" + tmpDays + " AND MONTH(dates)=" + tmpMonth + " AND YEAR(dates)=" + currentYear + "";
                hasil = Db.getRs(updStatement);
            }

//            Function to check result set exist or not
            do {
                int id = hasil.getInt("id");
                java.sql.Date date = hasil.getDate("dates");
                double opens = hasil.getDouble("open");
                double highs = hasil.getDouble("high");
                double lows = hasil.getDouble("low");
                double closes = hasil.getDouble("close");
                double volumes = hasil.getDouble("volume");

//                System.out.println(id + " : " + date + " : " + opens + " : " + highs + " : " + lows + " : " + closes + " : " + volumes);
                counterId += 1;

                //            Adding new Data
                Data dataBulanan = new Data();
                dataBulanan.setId(counterId);
                dataBulanan.setOpen(opens);
                dataBulanan.setHigh(highs);
                dataBulanan.setLow(lows);
                dataBulanan.setClose(closes);
                dataBulanan.setVolume(volumes);

                data.add(dataBulanan);
            } while (hasil.next());
        }
        // Set target
        setDataTarget(data);

        for (Data datas : data) {
            counter += 1;
            double targets = datas.getTarget();
            if (dataset == 10) {
                if (counter <= 8) {
//                    System.out.println(targets);
                    targetMonthlyTraining.add(targets);
                }
            } else if (dataset == 50) {
                if (counter <= 40) {
                    targetMonthlyTraining.add(targets);
                }
            } else if (dataset == 120) {
                if (counter <= 96) {
                    targetMonthlyTraining.add(targets);
                }
            }
        }
        return targetMonthlyTraining;
    }

    public List<Double> targetDataTestingMonthly(int days, int month, int year, int dataset) throws SQLException, IOException {
        List<Double> targetMonthlyTesting = new ArrayList<>();
        Db.connect();
        int countTesting = 0, counterId = 0, counter = 0;
        List<Data> data = new ArrayList<>();
        ResultSet hasil = null;
        int interval = 3, tmpDays = 0, tmpMonth = 0;
        int iteration = dataset;

        int a = Math.floorDiv(interval * iteration, 12);
        int remainderMonth = (interval * iteration) - (12 * a);
//        System.out.println(a + " " + remainderMonth);

        int currentYear = year - a;
        int currentMonth = month - remainderMonth;
        if (currentMonth <= 0) {
            currentMonth = 12 + currentMonth;
            currentYear -= 1;
        }

//        System.out.println(currentMonth + " " + currentYear);
        for (int i = 1; i <= iteration; i++) {
            currentMonth += interval;

            if (currentMonth > 12) {
                currentMonth = currentMonth - 12;
                currentYear++;
            }

            String statement = "SELECT * FROM masterdatabulanan WHERE DAY(dates)=" + days + " AND MONTH(dates)=" + currentMonth + " AND YEAR(dates)=" + currentYear + "";
//            System.out.println(i+": "+currentMonth + " " + currentYear);
            hasil = Db.getRs(statement);

//            Function to check result set exist or not
            tmpDays = days;
            tmpMonth = currentMonth;
            while (!hasil.next()) {
                tmpDays = tmpDays - 1;
                if (tmpDays == 0) {
                    tmpDays = 31;
//                    System.out.println("Current Month : " + currentMonth);
                    tmpMonth = tmpMonth - 1;
//                    System.out.println("Month : " + tmpMonth);
                }
//                System.out.println("Tmp Value : " + tmpDays);
                String updStatement = "SELECT * FROM masterdatabulanan WHERE DAY(dates)=" + tmpDays + " AND MONTH(dates)=" + tmpMonth + " AND YEAR(dates)=" + currentYear + "";
                hasil = Db.getRs(updStatement);
            }

//            Function to check result set exist or not
            do {
                int id = hasil.getInt("id");
                java.sql.Date date = hasil.getDate("dates");
                double opens = hasil.getDouble("open");
                double highs = hasil.getDouble("high");
                double lows = hasil.getDouble("low");
                double closes = hasil.getDouble("close");
                double volumes = hasil.getDouble("volume");

//                System.out.println(id + " : " + date + " : " + opens + " : " + highs + " : " + lows + " : " + closes + " : " + volumes);
                counterId += 1;

                //            Adding new Data
                Data dataBulanan = new Data();
                dataBulanan.setId(counterId);
                dataBulanan.setDate(date);
                dataBulanan.setOpen(opens);
                dataBulanan.setHigh(highs);
                dataBulanan.setLow(lows);
                dataBulanan.setClose(closes);
                dataBulanan.setVolume(volumes);

                data.add(dataBulanan);
            } while (hasil.next());
        }
        // Set target
        setDataTarget(data);

//        System.out.println("Jumlah Data Set : " + dataset);
        for (Data datas : data) {
            counter += 1;
            double targets = datas.getTarget();
//            System.out.println("Targets : " + targets);
            if (dataset == 10) {
                if (counter > 8) {
//                    System.out.println(targets);
                    targetMonthlyTesting.add(targets);
                }
            } else if (dataset == 50) {
                if (counter > 40) {
                    targetMonthlyTesting.add(targets);
                }
            } else if (dataset == 120) {
                if (counter > 96) {
                    targetMonthlyTesting.add(targets);
                }
            }
        }
        return targetMonthlyTesting;
    }

    /*
        This function has been prepared for monthly scenario data set
     */
//    Fungsi untuk skenario data bulanan 10 Data || 3 Tahun
    public void count10(int days, int month, int year) throws SQLException, IOException {
        Db.connect();
        int countTesting = 0, counterId = 0;
        List<Data> data = new ArrayList<>();
        ResultSet hasil = null;
        int interval = 3, tmpDays = 0, tmpMonth = 0;
        int iteration = 10;

        int a = Math.floorDiv(interval * iteration, 12);
        int remainderMonth = (interval * iteration) - (12 * a);
//        System.out.println(a + " " + remainderMonth);

        int currentYear = year - a;
        int currentMonth = month - remainderMonth;
        if (currentMonth <= 0) {
            currentMonth = 12 + currentMonth;
            currentYear -= 1;
        }

//        System.out.println(currentMonth + " " + currentYear);
        for (int i = 1; i <= iteration; i++) {
            currentMonth += interval;

            if (currentMonth > 12) {
                currentMonth = currentMonth - 12;
                currentYear++;
            }

            String statement = "SELECT * FROM masterdata WHERE DAY(dates)=" + days + " AND MONTH(dates)=" + currentMonth + " AND YEAR(dates)=" + currentYear + "";
//            System.out.println(i+": "+currentMonth + " " + currentYear);
            hasil = Db.getRs(statement);

//            Function to check result set exist or not
            tmpDays = days;
            tmpMonth = currentMonth;
            while (!hasil.next()) {
                tmpDays = tmpDays - 1;
                if (tmpDays == 0) {
                    tmpDays = 31;
//                    System.out.println("Current Month : " + currentMonth);
                    tmpMonth = tmpMonth - 1;
//                    System.out.println("Month : " + tmpMonth);
                }
//                System.out.println("Tmp Value : " + tmpDays);
                String updStatement = "SELECT * FROM masterdata WHERE DAY(dates)=" + tmpDays + " AND MONTH(dates)=" + tmpMonth + " AND YEAR(dates)=" + currentYear + "";
                hasil = Db.getRs(updStatement);
            }

//            Function to check result set exist or not
            do {
                int id = hasil.getInt("id");
                java.sql.Date date = hasil.getDate("dates");
                double opens = hasil.getDouble("open");
                double highs = hasil.getDouble("high");
                double lows = hasil.getDouble("low");
                double closes = hasil.getDouble("close");
                double volumes = hasil.getDouble("volume");

//                System.out.println(id + " : " + date + " : " + opens + " : " + highs + " : " + lows + " : " + closes + " : " + volumes);
                counterId += 1;
                //            Getting te max value from each attribute
                if (highs > maxHighs) {
                    maxHighs = highs;
                }
                if (closes > maxCloses) {
                    maxCloses = closes;
                }
                if (lows > maxLows) {
                    maxLows = lows;
                }
                if (opens > maxOpens) {
                    maxOpens = opens;
                }
                if (volumes > maxVolumes) {
                    maxVolumes = volumes;
                }

//            Getting the min value from each attribute
                if (highs < minHighs) {
                    minHighs = highs;
                }
                if (closes < minCloses) {
                    minCloses = closes;
                }
                if (lows < minLows) {
                    minLows = lows;
                }
                if (opens < minOpens) {
                    minOpens = opens;
                }
                if (volumes < minVolumes) {
                    minVolumes = volumes;
                }

                //            Adding new Data
                Data dataBulanan = new Data();
                dataBulanan.setId(counterId);
                dataBulanan.setOpen(opens);
                dataBulanan.setHigh(highs);
                dataBulanan.setLow(lows);
                dataBulanan.setClose(closes);
                dataBulanan.setVolume(volumes);

//                if(flag>1){
//                    dataBulanan.setTarget(closes);
//                }
                data.add(dataBulanan);
            } while (hasil.next());
        }
        // Set target
        setDataTarget(data);

        //        Prepare Convert data into CSV file
        FileWriter fileWriterTraining = new FileWriter(training10DataMonthly);
        FileWriter fileWriterTesting = new FileWriter(testing10DataMonthly);

        //Write the Training CSV file header
        fileWriterTraining.append(FILE_HEADER);
        //Add a new line separator after the header
        fileWriterTraining.append(NEW_LINE_SEPARATOR);

        //Write the Testing CSV file header
        fileWriterTesting.append(FILE_HEADER);
        //Add a new line separator after the header
        fileWriterTesting.append(NEW_LINE_SEPARATOR);

        for (Data datas : data) {
            int ids = datas.getId();
            Date dates = datas.getDate();
            double opens = datas.getOpen();
            double highs = datas.getHigh();
            double lows = datas.getLow();
            double closes = datas.getClose();
            double volumes = datas.getVolume();
            double targets = datas.getTarget();

//            System.out.println("Id : " + ids + ", Close : " + closes + ", Target :" + targets + "\n");
//                Normalization Function
            highNormalization = (highs - minHighs) / (maxHighs - minHighs);
            closeNormalization = (closes - minCloses) / (maxCloses - minCloses);
            lowNormalization = (lows - minLows) / (maxLows - minLows);
            openNormalization = (opens - minOpens) / (maxOpens - minOpens);
            volumeNormalization = (volumes - minVolumes) / (maxVolumes - minVolumes);
            targetNormalization = (targets - minCloses) / (maxCloses - minCloses);

//            Divide into Training and Testing
            if (ids <= 8) {
                //Write a normalization output into CSV File
//                For Column ID
                fileWriterTraining.append(String.valueOf(ids));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(openNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(highNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(lowNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(closeNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(volumeNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(targetNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(NEW_LINE_SEPARATOR);
            }

            if (ids > 8) {
//                Write a normalization Testing into CSV File
                countTesting += 1;
                fileWriterTesting.append(String.valueOf(countTesting));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(openNormalization));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(highNormalization));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(lowNormalization));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(closeNormalization));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(volumeNormalization));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(targetNormalization));
                fileWriterTesting.append(NEW_LINE_SEPARATOR);
//                System.out.println("Target Normalization Data Testing "+targetNormalization);
            }
        }
        fileWriterTraining.flush();
        fileWriterTraining.close();
        fileWriterTesting.flush();
        fileWriterTesting.close();
    }

//    Fungsi untuk skenario data bulanan 50 Data || 15 Tahun
    public void count50(int days, int month, int year) throws SQLException, IOException {
        Db.connect();
        int countTesting = 0, counterId = 0;
        List<Data> data = new ArrayList<>();
        ResultSet hasil = null;
        int interval = 3, tmpDays = 0, tmpMonth = 0;
        int iteration = 50;

        int a = Math.floorDiv(interval * iteration, 12);
        int remainderMonth = (interval * iteration) - (12 * a);
        System.out.println(a + " " + remainderMonth);

        int currentYear = year - a;
        int currentMonth = month - remainderMonth;
        if (currentMonth <= 0) {
            currentMonth = 12 + currentMonth;
            currentYear -= 1;
        }

//        System.out.println(currentMonth + " " + currentYear);
        for (int i = 1; i <= iteration; i++) {
            currentMonth += interval;

            if (currentMonth > 12) {
                currentMonth = currentMonth - 12;
                currentYear++;
            }

            String statement = "SELECT * FROM masterdata WHERE DAY(dates)=" + days + " AND MONTH(dates)=" + currentMonth + " AND YEAR(dates)=" + currentYear + "";
//            System.out.println(i+": "+currentMonth + " " + currentYear);
            hasil = Db.getRs(statement);

//            Function to check result set exist or not
            tmpDays = days;
            tmpMonth = currentMonth;
            while (!hasil.next()) {
                tmpDays = tmpDays - 1;
                if (tmpDays == 0) {
                    tmpDays = 31;
                    System.out.println("Current Month : " + currentMonth);
                    tmpMonth = tmpMonth - 1;
                    System.out.println("Month : " + tmpMonth);
                }
                System.out.println("Tmp Value : " + tmpDays);
                String updStatement = "SELECT * FROM masterdata WHERE DAY(dates)=" + tmpDays + " AND MONTH(dates)=" + tmpMonth + " AND YEAR(dates)=" + currentYear + "";
                hasil = Db.getRs(updStatement);
            }

//            Function to check result set exist or not
            do {
                int id = hasil.getInt("id");
                java.sql.Date date = hasil.getDate("dates");
                double opens = hasil.getDouble("open");
                double highs = hasil.getDouble("high");
                double lows = hasil.getDouble("low");
                double closes = hasil.getDouble("close");
                double volumes = hasil.getDouble("volume");

                System.out.println(id + " : " + date + " : " + opens + " : " + highs + " : " + lows + " : " + closes + " : " + volumes);
                counterId += 1;
                //            Getting te max value from each attribute
                if (highs > maxHighs) {
                    maxHighs = highs;
                }
                if (closes > maxCloses) {
                    maxCloses = closes;
                }
                if (lows > maxLows) {
                    maxLows = lows;
                }
                if (opens > maxOpens) {
                    maxOpens = opens;
                }
                if (volumes > maxVolumes) {
                    maxVolumes = volumes;
                }

//            Getting the min value from each attribute
                if (highs < minHighs) {
                    minHighs = highs;
                }
                if (closes < minCloses) {
                    minCloses = closes;
                }
                if (lows < minLows) {
                    minLows = lows;
                }
                if (opens < minOpens) {
                    minOpens = opens;
                }
                if (volumes < minVolumes) {
                    minVolumes = volumes;
                }

                //            Adding new Data
                Data dataBulanan = new Data();
                dataBulanan.setId(counterId);
                dataBulanan.setOpen(opens);
                dataBulanan.setHigh(highs);
                dataBulanan.setLow(lows);
                dataBulanan.setClose(closes);
                dataBulanan.setVolume(volumes);
                data.add(dataBulanan);
            } while (hasil.next());
        }

        // Set target
        setDataTarget(data);

        //        Prepare Convert data into CSV file
        FileWriter fileWriterTraining = new FileWriter(training50DataMonthly);
        FileWriter fileWriterTesting = new FileWriter(testing50DataMonthly);

        //Write the Training CSV file header
        fileWriterTraining.append(FILE_HEADER);
        //Add a new line separator after the header
        fileWriterTraining.append(NEW_LINE_SEPARATOR);

        //Write the Testing CSV file header
        fileWriterTesting.append(FILE_HEADER);
        //Add a new line separator after the header
        fileWriterTesting.append(NEW_LINE_SEPARATOR);

        for (Data datas : data) {
            int ids = datas.getId();
            double opens = datas.getOpen();
            double highs = datas.getHigh();
            double lows = datas.getLow();
            double closes = datas.getClose();
            double volumes = datas.getVolume();
            double targets = datas.getTarget();

            System.out.println("Id : " + ids + ", Close : " + closes + ", Target :" + targets + "\n");
//                Normalization Function
            highNormalization = (highs - minHighs) / (maxHighs - minHighs);
            closeNormalization = (closes - minCloses) / (maxCloses - minCloses);
            lowNormalization = (lows - minLows) / (maxLows - minLows);
            openNormalization = (opens - minOpens) / (maxOpens - minOpens);
            volumeNormalization = (volumes - minVolumes) / (maxVolumes - minVolumes);
            targetNormalization = (targets - minCloses) / (maxCloses - minCloses);

            if (ids <= 40) {
                //Write a normalization output into CSV File
//                For Column ID
                fileWriterTraining.append(String.valueOf(ids));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(openNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(highNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(lowNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(closeNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(volumeNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(targetNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(NEW_LINE_SEPARATOR);
            }

            if (ids > 40) {
//                Write a normalization Testing into CSV File
                countTesting += 1;
                fileWriterTesting.append(String.valueOf(countTesting));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(openNormalization));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(highNormalization));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(lowNormalization));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(closeNormalization));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(volumeNormalization));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(targetNormalization));
                fileWriterTesting.append(NEW_LINE_SEPARATOR);
//                System.out.println("Target Normalization Data Testing "+targetNormalization);
            }
        }
        fileWriterTraining.flush();
        fileWriterTraining.close();
        fileWriterTesting.flush();
        fileWriterTesting.close();
    }

    //    Fungsi untuk skenario data bulanan 25 Data || 8 Tahun
    public void count120(int days, int month, int year) throws SQLException, IOException {
        Db.connect();
        int countTesting = 0, counterId = 0;
        List<Data> data = new ArrayList<>();
        ResultSet hasil = null;
        int interval = 3, tmpDays = 0, tmpMonth = 0;
        int iteration = 120;

        int a = Math.floorDiv(interval * iteration, 12);
        int remainderMonth = (interval * iteration) - (12 * a);
        System.out.println(a + " " + remainderMonth);

        int currentYear = year - a;
        int currentMonth = month - remainderMonth;
        if (currentMonth <= 0) {
            currentMonth = 12 + currentMonth;
            currentYear -= 1;
        }

//        System.out.println(currentMonth + " " + currentYear);
        for (int i = 1; i <= iteration; i++) {
            currentMonth += interval;

            if (currentMonth > 12) {
                currentMonth = currentMonth - 12;
                currentYear++;
            }

            String statement = "SELECT * FROM masterdata WHERE DAY(dates)=" + days + " AND MONTH(dates)=" + currentMonth + " AND YEAR(dates)=" + currentYear + "";
//            System.out.println(i+": "+currentMonth + " " + currentYear);
            hasil = Db.getRs(statement);

//            Function to check result set exist or not
            tmpDays = days;
            tmpMonth = currentMonth;
            while (!hasil.next()) {
                tmpDays = tmpDays - 1;
                if (tmpDays == 0) {
                    tmpDays = 31;
                    System.out.println("Current Month : " + currentMonth);
                    tmpMonth = tmpMonth - 1;
                    System.out.println("Month : " + tmpMonth);
                }
                System.out.println("Tmp Value : " + tmpDays);
                String updStatement = "SELECT * FROM masterdata WHERE DAY(dates)=" + tmpDays + " AND MONTH(dates)=" + tmpMonth + " AND YEAR(dates)=" + currentYear + "";
                hasil = Db.getRs(updStatement);
            }

//            Function to check result set exist or not
            do {
                int id = hasil.getInt("id");
                java.sql.Date date = hasil.getDate("dates");
                double opens = hasil.getDouble("open");
                double highs = hasil.getDouble("high");
                double lows = hasil.getDouble("low");
                double closes = hasil.getDouble("close");
                double volumes = hasil.getDouble("volume");

                System.out.println(id + " : " + date + " : " + opens + " : " + highs + " : " + lows + " : " + closes + " : " + volumes);
                counterId += 1;
                //            Getting te max value from each attribute
                if (highs > maxHighs) {
                    maxHighs = highs;
                }
                if (closes > maxCloses) {
                    maxCloses = closes;
                }
                if (lows > maxLows) {
                    maxLows = lows;
                }
                if (opens > maxOpens) {
                    maxOpens = opens;
                }
                if (volumes > maxVolumes) {
                    maxVolumes = volumes;
                }

//            Getting the min value from each attribute
                if (highs < minHighs) {
                    minHighs = highs;
                }
                if (closes < minCloses) {
                    minCloses = closes;
                }
                if (lows < minLows) {
                    minLows = lows;
                }
                if (opens < minOpens) {
                    minOpens = opens;
                }
                if (volumes < minVolumes) {
                    minVolumes = volumes;
                }

                //            Adding new Data
                Data dataBulanan = new Data();
                dataBulanan.setId(counterId);
                dataBulanan.setOpen(opens);
                dataBulanan.setHigh(highs);
                dataBulanan.setLow(lows);
                dataBulanan.setClose(closes);
                dataBulanan.setVolume(volumes);

                data.add(dataBulanan);
            } while (hasil.next());
        }

        // Set target
        setDataTarget(data);

        //        Prepare Convert data into CSV file
        FileWriter fileWriterTraining = new FileWriter(training120DataMonthly);
        FileWriter fileWriterTesting = new FileWriter(testing120DataMonthly);

        //Write the Training CSV file header
        fileWriterTraining.append(FILE_HEADER);
        //Add a new line separator after the header
        fileWriterTraining.append(NEW_LINE_SEPARATOR);

        //Write the Testing CSV file header
        fileWriterTesting.append(FILE_HEADER);
        //Add a new line separator after the header
        fileWriterTesting.append(NEW_LINE_SEPARATOR);

        for (Data datas : data) {
            int ids = datas.getId();
            double opens = datas.getOpen();
            double highs = datas.getHigh();
            double lows = datas.getLow();
            double closes = datas.getClose();
            double volumes = datas.getVolume();
            double targets = datas.getTarget();

            System.out.println("Id : " + ids + ", Close : " + closes + ", Target :" + targets + "\n");
//                Normalization Function
            highNormalization = (highs - minHighs) / (maxHighs - minHighs);
            closeNormalization = (closes - minCloses) / (maxCloses - minCloses);
            lowNormalization = (lows - minLows) / (maxLows - minLows);
            openNormalization = (opens - minOpens) / (maxOpens - minOpens);
            volumeNormalization = (volumes - minVolumes) / (maxVolumes - minVolumes);
            targetNormalization = (targets - minCloses) / (maxCloses - minCloses);

//            Divide into Training and Testing
            if (ids <= 96) {
                //Write a normalization output into CSV File
//                For Column ID
                fileWriterTraining.append(String.valueOf(ids));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(openNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(highNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(lowNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(closeNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(volumeNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(String.valueOf(targetNormalization));
                fileWriterTraining.append(COMMA_DELIMITER);
                fileWriterTraining.append(NEW_LINE_SEPARATOR);
            }

            if (ids > 96) {
//                Write a normalization Testing into CSV File
                countTesting += 1;
                fileWriterTesting.append(String.valueOf(countTesting));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(openNormalization));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(highNormalization));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(lowNormalization));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(closeNormalization));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(volumeNormalization));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(targetNormalization));
                fileWriterTesting.append(NEW_LINE_SEPARATOR);
//                System.out.println("Target Normalization Data Testing "+targetNormalization);
            }
        }
        fileWriterTraining.flush();
        fileWriterTraining.close();
        fileWriterTesting.flush();
        fileWriterTesting.close();
    }

    public void setDataTarget(List<Data> list) {
        for (int i = 0; i < list.size(); i++) {
            Data currentSingle = list.get(i);
            if (i == list.size() - 1) {
                currentSingle.setTarget(0);
            } else {
                Data nextSingle = list.get(i + 1);
                currentSingle.setTarget(nextSingle.getClose());
            }
        }
    }
    
}
//}
