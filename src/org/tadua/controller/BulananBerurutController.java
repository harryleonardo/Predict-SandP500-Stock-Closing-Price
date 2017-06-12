/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tadua.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.tadua.model.Data;

/**
 *
 * @author NewBee
 */
public class BulananBerurutController {

    DatabaseController Db = new DatabaseController();
    int tamp = 0, id = 0, flag = 0;

    //    Monthly Scenario
//    Path 10 Month Data
    String training10DataMonthly = "D:\\Kuliah\\Semester VI\\TAII\\training10MonthSecond.csv";
    String testing10DataMonthly = "D:\\Kuliah\\Semester VI\\TAII\\testing10MonthSecond.csv";

//    Path 50 Month Data
    String training50DataMonthly = "D:\\Kuliah\\Semester VI\\TAII\\training50MonthSecond.csv";
    String testing50DataMonthly = "D:\\Kuliah\\Semester VI\\TAII\\testing50MonthSecond.csv";

//    Path 120 Month Data
    String training120DataMonthly = "D:\\Kuliah\\Semester VI\\TAII\\training120MonthSecond.csv";
    String testing120DataMonthly = "D:\\Kuliah\\Semester VI\\TAII\\testing120MonthSecond.csv";

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

    //    Fungsi untuk skenario data bulanan 10 Data || 3 Tahun
    public void proses10Data() throws SQLException, IOException {
        int countTraining = 0, countforTraining = 0, countTesting = 0,dataSet=10;
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
//            System.out.println("Open " +open);
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
    
    public void proses50Data() throws SQLException, IOException {
        int countTraining = 0, countforTraining = 0, countTesting = 0,dataSet=50;
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
//            System.out.println("Open " +open);
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
            if (countTraining <= 40) {
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

        fileWriterTraining.flush();
        fileWriterTraining.close();
        fileWriterTesting.flush();
        fileWriterTesting.close();
    }
    
    public void proses120Data() throws SQLException, IOException {
        int countTraining = 0, countforTraining = 0, countTesting = 0,dataSet=120;
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
            System.out.println("Open " +open);
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
            if (countTraining <= 96) {
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

            if (countTraining > 96) {
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
    
    public static void main(String[] args) throws SQLException, IOException {
        BulananBerurutController bbc = new BulananBerurutController();
        bbc.proses10Data();
        bbc.proses50Data();
        bbc.proses120Data();
        
    }
}
