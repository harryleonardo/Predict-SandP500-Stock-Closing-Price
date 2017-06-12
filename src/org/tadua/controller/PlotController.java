/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tadua.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javafx.util.Pair;
import org.tadua.model.Data;

/**
 *
 * @author NewBee
 */
public class PlotController {

    DatabaseController Db = new DatabaseController();
    StockController sc = new StockController();
    File file;

//    For Daily Data set
    public List<Double> getErrorOfTrainingDaily(int dataset) throws IOException {
        int x = 0, totalData = 0;
        double prediksi, targetTesting, percentageError, MAPE, totalError = 0, nilaiPrediksi = 0, percentage = 0;
        List<Double> dataError = new ArrayList<>();

        if (dataset == 10) {
            file = new File("D:\\\\Kuliah\\\\Semester VI\\\\TAII\\\\errortraining10Daily.csv");
        } else if (dataset == 50) {
            file = new File("D:\\\\Kuliah\\\\Semester VI\\\\TAII\\\\errortraining50Daily.csv");
        } else if (dataset == 100) {
            file = new File("D:\\\\Kuliah\\\\Semester VI\\\\TAII\\\\errortraining100Daily.csv");
        } else if (dataset == 500) {
            file = new File("D:\\\\Kuliah\\\\Semester VI\\\\TAII\\\\errortraining500Daily.csv");
        } else if (dataset == 2500) {
            file = new File("D:\\\\Kuliah\\\\Semester VI\\\\TAII\\\\errortraining2500Daily.csv");
        }

        List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        for (String line : lines) {
            if (x > 0) {
                String[] array = line.split(",");
//            System.out.println(array[0]);
//                System.out.println(array[1]);
                double parseData = Double.valueOf(array[1]);
                dataError.add(parseData);
//                System.out.println("Fungsi : "+parseData);
            }
            x++;
        }
        return dataError;
    }

    public List<Double> getMAPEOfTestingDaily(int dataset) throws IOException {
        int x = 0, totalData = 0;
        double prediksi, targetTesting, percentageError, MAPE, totalError = 0, nilaiPrediksi = 0, percentage = 0;
        List<Double> dataError = new ArrayList<>();

        if (dataset == 10) {
            file = new File("D:\\\\Kuliah\\\\Semester VI\\\\TAII\\\\mapetesting10Daily.csv");
        } else if (dataset == 50) {
            file = new File("D:\\\\Kuliah\\\\Semester VI\\\\TAII\\\\mapetesting50Daily.csv");
        } else if (dataset == 100) {
            file = new File("D:\\\\Kuliah\\\\Semester VI\\\\TAII\\\\mapetesting100Daily.csv");
        } else if (dataset == 500) {
            file = new File("D:\\\\Kuliah\\\\Semester VI\\\\TAII\\\\mapetesting500Daily.csv");
        } else if (dataset == 2500) {
            file = new File("D:\\\\Kuliah\\\\Semester VI\\\\TAII\\\\mapetesting2500Daily.csv");
        }

        List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        for (String line : lines) {
            if (x > 0) {
                String[] array = line.split(",");
//            System.out.println(array[0]);
//                System.out.println(array[1]);
                double parseData = Double.valueOf(array[1]);
                dataError.add(parseData);
//                System.out.println("Fungsi : "+parseData);
            }
            x++;
        }
        return dataError;
    }

    public List<Double> MAPEofTestingDaily(int dataset) throws IOException {
        int x = 0, totalData = 0;
        double prediksi, targetTesting, percentageError, MAPE, totalError = 0, nilaiPrediksi = 0, percentage = 0;
        List<Double> data = new ArrayList<>();
        double error = 0;
        if (dataset == 10) {
            file = new File("D:\\\\Kuliah\\\\Semester VI\\\\TAII\\\\mapetesting10Daily.csv");
        } else if (dataset == 50) {
            file = new File("D:\\\\Kuliah\\\\Semester VI\\\\TAII\\\\mapetesting50Daily.csv");
        } else if (dataset == 100) {
            file = new File("D:\\\\Kuliah\\\\Semester VI\\\\TAII\\\\mapetesting100Daily.csv");
        } else if (dataset == 500) {
            file = new File("D:\\\\Kuliah\\\\Semester VI\\\\TAII\\\\mapetesting500Daily.csv");
        } else if (dataset == 2500) {
            file = new File("D:\\\\Kuliah\\\\Semester VI\\\\TAII\\\\mapetesting2500Daily.csv");
        }

        List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        for (String line : lines) {
            if (x > 0) {
                String[] array = line.split(",");
//            System.out.println(array[0]);
//                System.out.println(array[1]);
                double parseData = Double.valueOf(array[1]);
                double prediction = Double.valueOf(array[2]);
                data.add(parseData);
                data.add(prediction);
//                System.out.println("Fungsi : "+parseData);
            }
            x++;
        }
        return data;
    }

//    Getting Dates of each Data Training
    public List<Date> getDatesTrainingDaily(int dataset) throws SQLException {
        Db.connect();
        int rowTotal = 0, flag = 0;
        List<Date> fetchDate = new ArrayList<>();
        ResultSet hasil = null, data = null, training = null, testing = null;
        String totalRow = "SELECT * FROM masterdata";

        hasil = Db.getRs(totalRow);
//        Loop to count total Row
        while (hasil.next()) {
            rowTotal += 1;
        }

        int tamp = rowTotal - dataset;
        String statement = "SELECT * FROM masterdata LIMIT " + dataset + " OFFSET " + tamp;
        hasil = Db.getRs(statement);

        while (hasil.next()) {
            java.sql.Date date = hasil.getDate("dates");
            flag += 1;
            if (dataset == 10) {
                if (flag <= 8) {
                    fetchDate.add(date);
                }
            } else if (dataset == 50) {
                if (flag <= 40) {
                    fetchDate.add(date);
                }
            } else if (dataset == 100) {
                if (flag <= 80) {
                    fetchDate.add(date);
                }
            } else if (dataset == 500) {
                if (flag <= 400) {
                    fetchDate.add(date);
                }
            } else if (dataset == 2500) {
                if (flag <= 2000) {
                    fetchDate.add(date);
                }
            }
        }
        return fetchDate;
    }

    public List<Date> getDatesTestingDaily(int dataset) throws SQLException {
        Db.connect();
        int rowTotal = 0, flag = 0;
        List<Date> fetchDate = new ArrayList<>();
        ResultSet hasil = null, data = null, training = null, testing = null;
        String totalRow = "SELECT * FROM masterdata";

        hasil = Db.getRs(totalRow);
//        Loop to count total Row
        while (hasil.next()) {
            rowTotal += 1;
        }

        int tamp = rowTotal - dataset;
        String statement = "SELECT * FROM masterdata LIMIT " + dataset + " OFFSET " + tamp;
        hasil = Db.getRs(statement);

        while (hasil.next()) {
            java.sql.Date date = hasil.getDate("dates");
            flag += 1;
            if (dataset == 10) {
                if (flag > 8) {
                    if (flag == dataset) {
                        break;
                    } else {
                        fetchDate.add(date);
                    }
                }
            } else if (dataset == 50) {
                if (flag > 40) {
                    if (flag == dataset) {
                        break;
                    } else {
                        fetchDate.add(date);
                    }
                }
            } else if (dataset == 100) {
                if (flag > 80) {
                    if (flag == dataset) {
                        break;
                    } else {
                        fetchDate.add(date);
                    }
                }
            } else if (dataset == 500) {
                if (flag > 400) {
                    if (flag == dataset) {
                        break;
                    } else {
                        fetchDate.add(date);
                    }
                }
            } else if (dataset == 2500) {
                if (flag > 2000) {
                    if (flag == dataset) {
                        break;
                    } else {
                        fetchDate.add(date);
                    }
                }
            }
        }
        return fetchDate;
    }

//    For Monthly Data set
    public List<Double> getErrorOfTrainingMonthly(int dataset) throws IOException {
        int x = 0, totalData = 0;
        double prediksi, targetTesting, percentageError, MAPE, totalError = 0, nilaiPrediksi = 0, percentage = 0;
        List<Double> dataError = new ArrayList<>();

        if (dataset == 10) {
            file = new File("D:\\\\Kuliah\\\\Semester VI\\\\TAII\\\\errortraining10Monthly.csv");
        } else if (dataset == 50) {
            file = new File("D:\\\\Kuliah\\\\Semester VI\\\\TAII\\\\errortraining50Monthly.csv");
        } else if (dataset == 120) {
            file = new File("D:\\\\Kuliah\\\\Semester VI\\\\TAII\\\\errortraining120Monthly.csv");
        }

        List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        for (String line : lines) {
            if (x > 0) {
                String[] array = line.split(",");
//            System.out.println(array[0]);
//                System.out.println(array[1]);
                double parseData = Double.valueOf(array[1]);
                dataError.add(parseData);
//                System.out.println("Fungsi : "+parseData);
            }
            x++;
        }
        return dataError;
    }

    public List<Double> getMAPEOfTestingMonthly(int dataset) throws IOException {
        int x = 0, totalData = 0;
        double prediksi, targetTesting, percentageError, MAPE, totalError = 0, nilaiPrediksi = 0, percentage = 0;
        List<Double> dataError = new ArrayList<>();

        if (dataset == 10) {
            file = new File("D:\\\\Kuliah\\\\Semester VI\\\\TAII\\\\mapetesting10Monthly.csv");
        } else if (dataset == 50) {
            file = new File("D:\\\\Kuliah\\\\Semester VI\\\\TAII\\\\mapetesting50Monthly.csv");
        } else if (dataset == 120) {
            file = new File("D:\\\\Kuliah\\\\Semester VI\\\\TAII\\\\mapetesting120Monthly.csv");
        }

        List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        for (String line : lines) {
            if (x > 0) {
                String[] array = line.split(",");
//            System.out.println(array[0]);
//                System.out.println(array[1]);
                double parseData = Double.valueOf(array[1]);
                dataError.add(parseData);
//                System.out.println("Fungsi : "+parseData);
            }
            x++;
        }
        return dataError;
    }

//    Getting Dates of each Data Testing
    public List<Date> getDatesTrainingMonthly(int days, int month, int year, int dataset) throws SQLException {
        Db.connect();
        int countTesting = 0, counterId = 0;
        List<Date> fetchDate = new ArrayList<>();
        ResultSet hasil = null;
        int interval = 3, tmpDays = 0, tmpMonth = 0;
        int iteration = dataset, flag = 1;

        int a = Math.floorDiv(interval * iteration, 12);
        int remainderMonth = (interval * iteration) - (12 * a);

        int currentYear = year - a;
        int currentMonth = month - remainderMonth;
        if (currentMonth <= 0) {
            currentMonth = 12 + currentMonth;
            currentYear -= 1;
        }
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
                java.sql.Date date = hasil.getDate("dates");
                fetchDate.add(date);
                if (dataset == 10) {
                    if (flag <= 8) {
                        fetchDate.add(date);
                    }
                } else if (dataset == 50) {
                    if (flag <= 40) {
                        fetchDate.add(date);
                    }
                } else if (dataset == 120) {
                    if (flag <= 96) {
                        fetchDate.add(date);
                    }
                }
                flag += 1;
            } while (hasil.next());
        }
        return fetchDate;
    }

    public List<Date> getDatesTestingMonthly(int days, int month, int year, int dataset) throws SQLException {
        Db.connect();
        int countTesting = 0, counterId = 0;
        List<Date> fetchDate = new ArrayList<>();
        ResultSet hasil = null;
        int interval = 3, tmpDays = 0, tmpMonth = 0;
        int iteration = dataset, flag = 1;

        int a = Math.floorDiv(interval * iteration, 12);
        int remainderMonth = (interval * iteration) - (12 * a);
        System.out.println(a + " " + remainderMonth);

        int currentYear = year - a;
        int currentMonth = month - remainderMonth;
        if (currentMonth <= 0) {
            currentMonth = 12 + currentMonth;
            currentYear -= 1;
        }
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
                java.sql.Date date = hasil.getDate("dates");
                fetchDate.add(date);
                if (dataset == 10) {
                    if (flag > 8) {
                        fetchDate.add(date);
                    }
                } else if (dataset == 50) {
                    if (flag > 40) {
                        fetchDate.add(date);
                    }
                } else if (dataset == 120) {
                    if (flag > 96) {
                        fetchDate.add(date);
                    }
                }
                flag += 1;
            } while (hasil.next());
        }
        return fetchDate;
    }

    public List<Date> getDates7DataDaily() throws SQLException {
        Db.connect();
        int dataset = 7;
        int rowTotal = 0, flag = 0;
        List<Date> fetchDate = new ArrayList<>();
        ResultSet hasil = null, data = null, training = null, testing = null;
        String totalRow = "SELECT * FROM masterdata";

        hasil = Db.getRs(totalRow);
//        Loop to count total Row
        while (hasil.next()) {
            rowTotal += 1;
        }

        int tamp = rowTotal - dataset;
        String statement = "SELECT * FROM masterdata LIMIT " + dataset + " OFFSET " + tamp;
        hasil = Db.getRs(statement);

        while (hasil.next()) {
            java.sql.Date date = hasil.getDate("dates");
            flag += 1;
            fetchDate.add(date);
        }
        return fetchDate;
    }

    public Date getLastDate() throws SQLException {
        Db.connect();
        Date curDate = null;
        ResultSet hasil = null;
        String dates = "SELECT dates FROM masterdata ORDER BY ID DESC LIMIT 1";
        hasil = Db.getRs(dates);
        while (hasil.next()) {
            curDate = hasil.getDate("dates");
        }
        return curDate;
    }

    public Date getLastDateMonthly(int days, int month, int year) throws SQLException {
        Db.connect();
        int countTesting = 0, counterId = 0;
        Date fetchDate = new Date();
        ResultSet hasil = null;
        int interval = 3, tmpDays = 0, tmpMonth = 0;
        int iteration = 10, flag = 1;

        int a = Math.floorDiv(interval * iteration, 12);
        int remainderMonth = (interval * iteration) - (12 * a);

        int currentYear = year - a;
        int currentMonth = month - remainderMonth;
        if (currentMonth <= 0) {
            currentMonth = 12 + currentMonth;
            currentYear -= 1;
        }
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
                java.sql.Date date = hasil.getDate("dates");
                if (flag == 9) {
                    fetchDate = date;
                    System.out.println("Last Date : " + fetchDate);
                }
                flag += 1;
            } while (hasil.next());
        }
        return fetchDate;
    }

    public Data getDataMonthly(int days, int month, int year) throws SQLException {
        Db.connect();
        int countTesting = 0, counterId = 0;
        Date fetchDate = new Date();
        Data ret = new Data();
        ResultSet hasil = null;
        int interval = 3, tmpDays = 0, tmpMonth = 0;
        int iteration = 10, flag = 1;

        int a = Math.floorDiv(interval * iteration, 12);
        int remainderMonth = (interval * iteration) - (12 * a);

        int currentYear = year - a;
        int currentMonth = month - remainderMonth;
        if (currentMonth <= 0) {
            currentMonth = 12 + currentMonth;
            currentYear -= 1;
        }
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

                fetchDate = hasil.getDate("dates");
                double opens = hasil.getDouble("open");
                double highs = hasil.getDouble("high");
                double lows = hasil.getDouble("low");
                double closes = hasil.getDouble("close");
                double volumes = hasil.getDouble("volume");
                if (flag == 9) {
                    ret.setDate(fetchDate);
                    ret.setOpen(opens);
                    ret.setHigh(highs);
                    ret.setLow(lows);
                    ret.setClose(closes);
                    ret.setVolume(volumes);
                }
                flag += 1;
            } while (hasil.next());
        }
        return ret;
    }

    public String bulan(int input) {
        if (input == 1) {
            return "Jan";
        } else if (input == 2) {
            return "Feb";
        } else if (input == 3) {
            return "Maret";
        } else if (input == 4) {
            return "April";
        } else if (input == 5) {
            return "Mei";
        } else if (input == 6) {
            return "Juni";
        } else if (input == 7) {
            return "Juli";
        } else if (input == 8) {
            return "Agust";
        } else if (input == 9) {
            return "Sept";
        } else if (input == 10) {
            return "Okt";
        } else if (input == 11) {
            return "Nov";
        } else if (input == 12) {
            return "Des";
        }
        return null;
    }

    public Data dataSebelumnya() throws SQLException {
        Db.connect();
//        Date curDate = null;
        Data datas = new Data();
        ResultSet hasil = null;
        String dates = "SELECT * FROM masterdata ORDER BY ID DESC LIMIT 1,1";
        hasil = Db.getRs(dates);
        while (hasil.next()) {
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

    public Data dataBulananSebelumnya(int days, int month, int year) throws SQLException {
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
        return null;
    }

    public List<Data> aktual7Data() throws SQLException, IOException {
        int countTraining = 0, countforTraining = 0, countTesting = 0, dataSet = 7;
        Db.connect();
        List<Data> dataAktual = new ArrayList<>();
        int rowTotal = 0;
        ResultSet hasil = null;
        String totalRow = "SELECT * FROM masterdata";
        hasil = Db.getRs(totalRow);

//        Loop to count total Row
        while (hasil.next()) {
            rowTotal += 1;
        }

        int tamp = rowTotal - dataSet;
        String statement = "SELECT * FROM masterdata LIMIT " + dataSet + " OFFSET " + tamp;
        hasil = Db.getRs(statement);

//        Loop to get max and min value from result set
        while (hasil.next()) {
            double open = hasil.getDouble("open");
            double high = hasil.getDouble("high");
            double low = hasil.getDouble("low");
            double close = hasil.getDouble("close");
            double volume = hasil.getDouble("volume");
            //            Adding new Data
            Data data = new Data();
            data.setOpen(open);
            data.setHigh(high);
            data.setLow(low);
            data.setClose(close);
            data.setVolume(volume);
            dataAktual.add(data);
        }
        return dataAktual;
    }

    public List<Data> aktual10DataBulanan() throws SQLException, IOException {
        int countTraining = 0, countforTraining = 0, countTesting = 0, dataSet = 10;
        Db.connect();
        List<Data> dataAktual = new ArrayList<>();
        int rowTotal = 0;
        ResultSet hasil = null;
        String totalRow = "SELECT * FROM masterdatabulanan";
        hasil = Db.getRs(totalRow);

//        Loop to count total Row
        while (hasil.next()) {
            rowTotal += 1;
        }

        int tamp = rowTotal - dataSet;
        String statement = "SELECT * FROM masterdatabulanan LIMIT " + dataSet + " OFFSET " + tamp;
        hasil = Db.getRs(statement);

//        Loop to get max and min value from result set
        while (hasil.next()) {
            Date d = hasil.getDate("dates");
            double open = hasil.getDouble("open");
            double high = hasil.getDouble("high");
            double low = hasil.getDouble("low");
            double close = hasil.getDouble("close");
            double volume = hasil.getDouble("volume");
            //            Adding new Data
            Data data = new Data();
            data.setDate(d);
            data.setOpen(open);
            data.setHigh(high);
            data.setLow(low);
            data.setClose(close);
            data.setVolume(volume);
            dataAktual.add(data);
        }
        return dataAktual;
    }

    public List<Date> getDates10DataMonthly() throws SQLException {
        Db.connect();
        int dataset = 10;
        int rowTotal = 0, flag = 0;
        List<Date> fetchDate = new ArrayList<>();
        ResultSet hasil = null, data = null, training = null, testing = null;
        String totalRow = "SELECT * FROM masterdatabulanan";

        hasil = Db.getRs(totalRow);
//        Loop to count total Row
        while (hasil.next()) {
            rowTotal += 1;
        }

        int tamp = rowTotal - dataset;
        String statement = "SELECT * FROM masterdatabulanan LIMIT " + dataset + " OFFSET " + tamp;
        hasil = Db.getRs(statement);

        while (hasil.next()) {
            java.sql.Date date = hasil.getDate("dates");
            flag += 1;
            fetchDate.add(date);
        }
        return fetchDate;
    }

//    public Double compareError() throws IOException{
//        double error10Data,error50Data,error100Data,error500Data;
//        error10Data = pc.MAPEofTestingDaily(10);
//        System.out.println(error10Data);
//        
//    }
//    public static void main(String[] args) throws IOException { 
//        List<Double> mape = new ArrayList<>();
//        List<Double> error = new ArrayList<>();
//        PlotController pc = new PlotController();
//        double err10Data = 0, err50Data = 0, err100Data = 0, err500Data = 0, bestModel = 10;
//        double predict10Data = 0, predict50Data = 0, predict100Data = 0, predict500Data = 0;
//        double[] predict10DataDaily, predict50DataDaily, predict100DataDaily, predict500DataDaily, predict2500DataDaily;
//        List<Double> error10Data, error50Data, error100Data, error500Data;
//        
//        mape = pc.MAPEofTestingDaily(50);
//
//        error10Data = pc.MAPEofTestingDaily(10);
//        error50Data = pc.MAPEofTestingDaily(50);
//        error100Data = pc.MAPEofTestingDaily(100);
//        error500Data = pc.MAPEofTestingDaily(500);
//
//        for (int x = 0; x < 2; x++) {
//            if (x == 0) {
//                err10Data = error10Data.get(x);
//                err50Data = error50Data.get(x);
//                err100Data = error100Data.get(x);
//                err500Data = error500Data.get(x);
//            }
//            if (x == 1) {
//                predict10Data = error10Data.get(x);
//                predict50Data = error50Data.get(x);
//                predict100Data = error100Data.get(x);
//                predict500Data = error500Data.get(x);
//            }
//        }
//        
//        error.add(err10Data);
//        error.add(err50Data);
//        error.add(err100Data);
//        error.add(err500Data);
//        
//        for(Double m : error){
//            System.out.println("Nilai : "+m);
//            
//            if(m<bestModel){
//                bestModel = m;
//            }
//        }
//        
////        if(bestModel == err10Data){
////            predict10Data
////        } else if(bestModel == err50Data){
////            
////        }else if(bestModel == err100Data){
////            
////        } else if(bestModel==err500Data){
////            
////        }
//        
//        System.out.println("\nBest Model : "+bestModel+"\n");
//        System.out.println("\nError 10 Data : "+err10Data);
//        System.out.println("Error 50 Data : "+err50Data);
//        System.out.println("Error 100 Data : "+err100Data);
//        System.out.println("Error 500 Data : "+err500Data+"\n");
//        
//        System.out.println("Prediksi 10 Data : "+predict10Data);
//        System.out.println("Prediksi 50 Data : "+predict50Data);
//        System.out.println("Prediksi 100 Data : "+predict100Data);
//        System.out.println("Prediksi 500 Data : "+predict500Data);
//        double error10Data,error50Data,error100Data,error500Data,bestModel=10;
//        error10Data = pc.MAPEofTestingDaily(10);
//        error50Data = pc.MAPEofTestingDaily(50);
//        error100Data = pc.MAPEofTestingDaily(100);
//        error500Data = pc.MAPEofTestingDaily(500);
//        
//        mape.add(error10Data);
//        mape.add(error50Data);
//        mape.add(error100Data);
//        mape.add(error500Data);
//        
//        for(Double best : mape){
//            if(best<bestModel){
//                bestModel = best;
//            }
//        }
//        System.out.println("Best Model : "+bestModel);
//        System.out.println(error10Data+" : "+error50Data+" : "+error100Data+" : "+error500Data);
}
