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
public class ProsesBulananController {
    DatabaseController Db = new DatabaseController();
    int tamp = 0, id = 0, flag = 0;
    
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
    
    //    Fungsi untuk skenario data bulanan 10 Data || 3 Tahun
    public void proses10DataBulanan(int days, int month, int year) throws SQLException, IOException {
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
    
    //    Fungsi untuk skenario data bulanan 50 Data || 5 Tahun
    public void proses50DataBulanan(int days, int month, int year) throws SQLException, IOException {
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
                    System.out.println("Current Month : " + currentMonth);
                    tmpMonth = tmpMonth - 1;
                }
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
    public void proses120DataBulanan(int days, int month, int year) throws SQLException, IOException {
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
                    tmpMonth = tmpMonth - 1;
                }
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

//            System.out.println("Id : " + ids + ", Close : " + closes + ", Target :" + targets + "\n");
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
    
    public Data getSingleData(int days, int month, int year) throws SQLException{
        Data datas = new Data();
        Db.connect();
        ResultSet hasil = null;
        String statement = "SELECT * FROM masterdatabulanan WHERE DAY(dates)=" + days + " AND MONTH(dates)=" + month + " AND YEAR(dates)=" + year + "";
//            System.out.println(i+": "+currentMonth + " " + currentYear);
        hasil = Db.getRs(statement);
        while (hasil.next()){
            double open = hasil.getDouble("open");
            Date d = hasil.getDate("dates");
            double high = hasil.getDouble("high");
            double low = hasil.getDouble("low");
            double close = hasil.getDouble("close");
            double volume = hasil.getDouble("volume");
            double target = hasil.getDouble("target");
            datas.setDate(d);
            datas.setOpen(open);
            datas.setHigh(high);
            datas.setLow(low);
            datas.setClose(close);
            datas.setVolume(volume);
            datas.setTarget(target);
        }
        return datas;
    }
    
    public Date getCurrentDate(int days, int month, int year) throws SQLException{
        Db.connect();
        Date curDate = null;
        ResultSet hasil = null;
        String dates = "SELECT * FROM masterdatabulanan WHERE DAY(dates)=" + days + " AND MONTH(dates)=" + month + " AND YEAR(dates)=" + year + "";
        hasil = Db.getRs(dates);
        while (hasil.next()){
            curDate = hasil.getDate("dates");
        }
        return curDate;
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
    
//    public static void main(String[] args) throws SQLException, IOException {
//        ProsesBulananController bulananController = new ProsesBulananController();
//        Data d = bulananController.getSingleData(1, 11, 2016);
//        System.out.println(d);
//    }
}
