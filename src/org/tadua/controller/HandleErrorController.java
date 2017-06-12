/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tadua.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

/**
 *
 * @author NewBee
 */
public class HandleErrorController {

    StockController sc = new StockController();
    PlotController pc = new PlotController();
    //                          Path for Error of Daily Data
    //   Path 10 error data
    String errortraining10DataDaily = "D:\\Kuliah\\Semester VI\\TAII\\errortraining10Daily.csv";
    String errortesting10DataDaily = "D:\\Kuliah\\Semester VI\\TAII\\errortesting10Daily.csv";

    //   Path 50 error data
    String errortraining50DataDaily = "D:\\Kuliah\\Semester VI\\TAII\\errortraining50Daily.csv";
    String errortesting50DataDaily = "D:\\Kuliah\\Semester VI\\TAII\\errortesting50Daily.csv";

    //   Path 100 error data
    String errortraining100DataDaily = "D:\\Kuliah\\Semester VI\\TAII\\errortraining100Daily.csv";
    String errortesting100DataDaily = "D:\\Kuliah\\Semester VI\\TAII\\errortesting100Daily.csv";

    //   Path 500 Data
    String errortraining500DataDaily = "D:\\Kuliah\\Semester VI\\TAII\\errortraining500Daily.csv";
    String errortesting500DataDaily = "D:\\Kuliah\\Semester VI\\TAII\\errortesting500Daily.csv";

    //   Path 2500 Data
    String errortraining2500DataDaily = "D:\\Kuliah\\Semester VI\\TAII\\errortraining2500Daily.csv";
    String errortesting2500DataDaily = "D:\\Kuliah\\Semester VI\\TAII\\errortesting2500Daily.csv";
    
//              Path to Save MAPE value
    String mapeTraining10DataDaily = "D:\\Kuliah\\Semester VI\\TAII\\mapetraining10Daily.csv";
    String mapeTraining50DataDaily = "D:\\Kuliah\\Semester VI\\TAII\\mapetraining50Daily.csv";
    String mapeTraining100DataDaily = "D:\\Kuliah\\Semester VI\\TAII\\mapetraining100Daily.csv";
    String mapeTraining500DataDaily = "D:\\Kuliah\\Semester VI\\TAII\\mapetraining500Daily.csv";
    String mapeTraining2500DataDaily = "D:\\Kuliah\\Semester VI\\TAII\\mapetraining2500Daily.csv";
    
//    Path to Save MAPE of Testing value
    String mapeTesting10DataDaily = "D:\\Kuliah\\Semester VI\\TAII\\mapetesting10Daily.csv";
    String mapeTesting50DataDaily = "D:\\Kuliah\\Semester VI\\TAII\\mapetesting50Daily.csv";
    String mapeTesting100DataDaily = "D:\\Kuliah\\Semester VI\\TAII\\mapetesting100Daily.csv";
    String mapeTesting500DataDaily = "D:\\Kuliah\\Semester VI\\TAII\\mapetesting500Daily.csv";
    String mapeTesting2500DataDaily = "D:\\Kuliah\\Semester VI\\TAII\\mapetesting2500Daily.csv";
    
//    Path to Save MAPE Value of Data Training
    String mapeTraining10Monthly = "D:\\Kuliah\\Semester VI\\TAII\\mapetraining10Monthly.csv";
    String mapeTraining50Monthly = "D:\\Kuliah\\Semester VI\\TAII\\mapetraining50Monthly.csv";
    String mapeTraining120Monthly = "D:\\Kuliah\\Semester VI\\TAII\\mapetraining120Monthly.csv";
    
//    Path to Save MAPE value of Data Testing
    String mapeTesting10Monthly = "D:\\Kuliah\\Semester VI\\TAII\\mapetesting10Monthly.csv";
    String mapeTesting50Monthly = "D:\\Kuliah\\Semester VI\\TAII\\mapetesting50Monthly.csv";
    String mapeTesting120Monthly = "D:\\Kuliah\\Semester VI\\TAII\\mapetesting120Monthly.csv";
    
   
    //SECOND EXPERIMENT
    
    String mapeTraining10MonthlySecond = "D:\\Kuliah\\Semester VI\\TAII\\mapetraining10MonthlySecond.csv";
    String mapeTraining50MonthlySecond = "D:\\Kuliah\\Semester VI\\TAII\\mapetraining50MonthlySecond.csv";
    String mapeTraining120MonthlySecond = "D:\\Kuliah\\Semester VI\\TAII\\mapetraining120MonthlySecond.csv";
    
//    Path to Save MAPE value of Data Testing
    String mapeTesting10MonthlySecond = "D:\\Kuliah\\Semester VI\\TAII\\mapetesting10MonthlySecond.csv";
    String mapeTesting50MonthlySecond = "D:\\Kuliah\\Semester VI\\TAII\\mapetesting50MonthlySecond.csv";
    String mapeTesting120MonthlySecond = "D:\\Kuliah\\Semester VI\\TAII\\mapetesting120MonthlySecond.csv";
    
    //                          Path for Error of Monthly Data
    // Path 10 error Data
    String errortraining10DataMonthly = "D:\\Kuliah\\Semester VI\\TAII\\errortraining10Monthly.csv";
    String errortesting10DataMonthly = "D:\\Kuliah\\Semester VI\\TAII\\errortesting10Monthly.csv";

    // Path 50 error Data
    String errortraining50DataMonthly = "D:\\Kuliah\\Semester VI\\TAII\\errortraining50Monthly.csv";
    String errortesting50DataMonthly = "D:\\Kuliah\\Semester VI\\TAII\\errortesting50Monthly.csv";

    // Path 120 error Data
    String errortraining120DataMonthly = "D:\\Kuliah\\Semester VI\\TAII\\errortraining120Monthly.csv";
    String errortesting120DataMonthly = "D:\\Kuliah\\Semester VI\\TAII\\errortesting120Monthly.csv";

    
    //SECOND EXPERIMENT
     //                          Path for Error of Monthly Data
    // Path 10 error Data
    String errortraining10DataMonthlySecond = "D:\\Kuliah\\Semester VI\\TAII\\errortraining10MonthlySecond.csv";
    String errortesting10DataMonthlySecond = "D:\\Kuliah\\Semester VI\\TAII\\errortesting10MonthlySecond.csv";

    // Path 50 error Data
    String errortraining50DataMonthlySecond = "D:\\Kuliah\\Semester VI\\TAII\\errortraining50MonthlySecond.csv";
    String errortesting50DataMonthlySecond = "D:\\Kuliah\\Semester VI\\TAII\\errortesting50MonthlySecond.csv";

    // Path 120 error Data
    String errortraining120DataMonthlySecond = "D:\\Kuliah\\Semester VI\\TAII\\errortraining120MonthlySecond.csv";
    String errortesting120DataMonthlySecond = "D:\\Kuliah\\Semester VI\\TAII\\errortesting120MonthlySecond.csv";
    
    
    //    Path to Save MAPE of Predict value
    String predict10Value = "D:\\Kuliah\\Semester VI\\TAII\\predict10Daily.csv";
    String predict50Value = "D:\\Kuliah\\Semester VI\\TAII\\predict50Daily.csv";
    String predict100Value = "D:\\Kuliah\\Semester VI\\TAII\\predict100Daily.csv";
    String predict500Value = "D:\\Kuliah\\Semester VI\\TAII\\predict500Daily.csv";
    String predict2500Value = "D:\\Kuliah\\Semester VI\\TAII\\predict2500Daily.csv";
    
    //Delimiter used in CSV file
    private final String COMMA_DELIMITER = ",";
    private final String NEW_LINE_SEPARATOR = "\n";
    
//    Delimimeter used in CSV file
    private final String COMMA_DELIMITER_MAPE = ",";
    private final String NEW_LINE_SEPARATOR_MAPE = "\n";

    //CSV file header Training
    private static final String FILE_HEADER = "id,error";
    
//    CSV file header MAPE
    private static final String FILE_MAPE = "id,MAPE,predictionValue";
    
//    CSV file to save Predict value 
    private static final String FILE_PREDICT = "id,prediction";
    
    
    

//        Prepare Convert data into CSV file
    FileWriter fileWriterTraining;
    FileWriter fileWriterTesting;
    
//    FileWriter MAPE
    FileWriter fileWriterMAPEdata;
    
//    FileWriter for predict
    FileWriter fileWriterPredict;
    /*
        In this part, the training or testing data error of daily scenario will be counted and store it 
        into CSV file.
     */
    public void mapeDataTrainingDaily(List<Double> outputTraining, List<Double> targetTraining, int dataset) throws SQLException, IOException {
        int totalData = outputTraining.size(),id=0;
        double trainingModel, targetTrainingModel, percentageError, MAPE, totalError = 0, nilaiPrediksi = 0, percentage = 0;
        List<Date> dates = pc.getDatesTrainingDaily(dataset);
        if (dataset == 10) {
            fileWriterMAPEdata = new FileWriter(mapeTraining10DataDaily);
            fileWriterTraining = new FileWriter(errortraining10DataDaily);
        } else if (dataset == 50) {
            fileWriterMAPEdata = new FileWriter(mapeTraining50DataDaily);
            fileWriterTraining = new FileWriter(errortraining50DataDaily);
        } else if (dataset == 100) {
            fileWriterMAPEdata = new FileWriter(mapeTraining100DataDaily);
            fileWriterTraining = new FileWriter(errortraining100DataDaily);
        } else if (dataset == 500) {
            fileWriterMAPEdata = new FileWriter(mapeTraining500DataDaily);
            fileWriterTraining = new FileWriter(errortraining500DataDaily);
        } else if (dataset == 2500) {
            fileWriterMAPEdata = new FileWriter(mapeTraining2500DataDaily);
            fileWriterTraining = new FileWriter(errortraining2500DataDaily);
        }

        //Write the Training CSV file header
        fileWriterTraining.append(FILE_HEADER);
        //Add a new line separator after the header
        fileWriterTraining.append(NEW_LINE_SEPARATOR);

        //Write the Training CSV file header
        fileWriterMAPEdata.append(FILE_MAPE);
        //Add a new line separator after the header
        fileWriterMAPEdata.append(NEW_LINE_SEPARATOR_MAPE);
        
        for (int x = 0; x < totalData; ++x) {
            id++;
            trainingModel = sc.denormalizationDailyInput(dataset, outputTraining.get(x));
            targetTrainingModel = targetTraining.get(x);
            percentageError = ((targetTrainingModel - trainingModel) / targetTrainingModel) * 100;

//                Absolute Value
            if (percentageError < 0) {
                percentageError *= -1;
            }

            totalError += percentageError;
//            System.out.println("Date : "+dates.get(x)+" ,Hasil Training : " + trainingModel + " , Target : " + targetTrainingModel+ " ,Error : "+percentageError);
//            System.out.println("\nPercentage Error 2500 Data Training : " + percentageError);
            //                For Column ID
            fileWriterTraining.append(String.valueOf(id));
            fileWriterTraining.append(COMMA_DELIMITER);
            fileWriterTraining.append(String.valueOf(percentageError));
            fileWriterTraining.append(NEW_LINE_SEPARATOR);
        }

        MAPE = totalError / totalData;
        percentage = 100 - MAPE;
//        System.out.println("Total Error : "+totalError+"\n");
        
        fileWriterMAPEdata.append(String.valueOf(1));
        fileWriterMAPEdata.append(COMMA_DELIMITER);
        fileWriterMAPEdata.append(String.valueOf(MAPE));
        fileWriterMAPEdata.append(NEW_LINE_SEPARATOR);
        
        System.out.println("\nNilai MAPE dari Keseluruhan Data Training " + dataset + " Data : " + MAPE + ", Dengan Akurasi : "+percentage+" %");
        fileWriterMAPEdata.flush();
        fileWriterMAPEdata.close();
        fileWriterTraining.flush();
        fileWriterTraining.close();
    }

    public void mapeDataTestingDaily(double[] predictData, List<Double> target, int dataset) throws SQLException, IOException {
        int totalData = 0, id = 0;
        List<Date> dates = pc.getDatesTestingDaily(dataset);
        double prediksi, targetTesting, percentageError, MAPE, totalError = 0, nilaiPrediksi = 0, percentage = 0;
        if (dataset == 10) {
            fileWriterMAPEdata = new FileWriter(mapeTesting10DataDaily);
            fileWriterTesting = new FileWriter(errortesting10DataDaily);
            fileWriterPredict = new FileWriter(predict10Value);
        } else if (dataset == 50) {
            fileWriterMAPEdata = new FileWriter(mapeTesting50DataDaily);
            fileWriterTesting = new FileWriter(errortesting50DataDaily);
            fileWriterPredict = new FileWriter(predict50Value);
        } else if (dataset == 100) {
            fileWriterMAPEdata = new FileWriter(mapeTesting100DataDaily);
            fileWriterTesting = new FileWriter(errortesting100DataDaily);
            fileWriterPredict = new FileWriter(predict100Value);
        } else if (dataset == 500) {
            fileWriterMAPEdata = new FileWriter(mapeTesting500DataDaily);
            fileWriterTesting = new FileWriter(errortesting500DataDaily);
            fileWriterPredict = new FileWriter(predict500Value);
        } else if (dataset == 2500) {
            fileWriterMAPEdata = new FileWriter(mapeTesting2500DataDaily);
            fileWriterTesting = new FileWriter(errortesting2500DataDaily);
            fileWriterPredict = new FileWriter(predict2500Value);
        }

        //Write the Training CSV file header
        fileWriterTesting.append(FILE_HEADER);
        //Add a new line separator after the header
        fileWriterTesting.append(NEW_LINE_SEPARATOR);
        
        //Write the Training CSV file header
        fileWriterMAPEdata.append(FILE_MAPE);
        //Add a new line separator after the header
        fileWriterMAPEdata.append(NEW_LINE_SEPARATOR_MAPE);
        
//        Write the Predict value into CSV
        fileWriterPredict.append(FILE_PREDICT);
        fileWriterPredict.append(NEW_LINE_SEPARATOR);
        
        for (int x = 0; x < target.size(); x++) {
            id++;
            totalData = target.size() - 1;
//            System.out.println("Prediksi : " + sc.denormalizationDailyInput(2500, predict2500Data[x]));
//            System.out.println("Target   : " + target.get(x));

            prediksi = sc.denormalizationDailyInput(dataset, predictData[x]);
            targetTesting = target.get(x);

            if (x < totalData) {
                percentageError = ((targetTesting - prediksi) / targetTesting) * 100;

//                Absolute Value
                if (percentageError < 0) {
                    percentageError *= -1;
                }

                totalError += percentageError;

//                System.out.println("Date : "+dates.get(x)+" ,Hasil Training : " + targetTesting + " , Target : " + targetTesting+ " ,Error : "+percentageError);
                
//                For Column ID
                fileWriterTesting.append(String.valueOf(id));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(percentageError));
                fileWriterTesting.append(NEW_LINE_SEPARATOR);
            }
            if (x == totalData) {
                nilaiPrediksi = sc.denormalizationDailyInput(dataset, predictData[x]);
            }
        }
//        System.out.println("Total Data : " + totalData);
        MAPE = totalError / totalData;
        
        fileWriterMAPEdata.append(String.valueOf(1));
        fileWriterMAPEdata.append(COMMA_DELIMITER);
        fileWriterMAPEdata.append(String.valueOf(MAPE));
        fileWriterMAPEdata.append(COMMA_DELIMITER);
        fileWriterMAPEdata.append(String.valueOf(nilaiPrediksi));
        fileWriterMAPEdata.append(NEW_LINE_SEPARATOR);
        
        percentage = 100 - MAPE;
        System.out.println("\nMAPE dari Keseluruhan Data Testing " + dataset + " Data adalah : " + MAPE + " , Dengan Akurasi : " +percentage+" %");
        System.out.println("Nilai Prediksi " + dataset + " Data : " + nilaiPrediksi);
        
        fileWriterPredict.append(String.valueOf(1));
        fileWriterPredict.append(COMMA_DELIMITER);
        fileWriterPredict.append(String.valueOf(nilaiPrediksi));
        fileWriterPredict.append(NEW_LINE_SEPARATOR);
        
        fileWriterTesting.flush();
        fileWriterTesting.close();
        fileWriterMAPEdata.flush();
        fileWriterMAPEdata.close();
        fileWriterPredict.flush();
        fileWriterPredict.close();
    }

    /*
        In this part, the training or testing data error of monthly scenario will be counted and store it 
        into CSV file.
     */
    public void mapeDataTrainingMonthly(List<Double> outputTraining, List<Double> targetTraining, int dataset) throws SQLException, IOException {
        int totalData = outputTraining.size(), id = 0;
        double trainingModel, targetTrainingModel, percentageError, MAPE, totalError = 0, percentage = 0;
        if (dataset == 10) {
            fileWriterMAPEdata = new FileWriter(mapeTraining10Monthly);
            fileWriterTraining = new FileWriter(errortraining10DataMonthly);
        } else if (dataset == 50) {
            fileWriterMAPEdata = new FileWriter(mapeTraining50Monthly);
            fileWriterTraining = new FileWriter(errortraining50DataMonthly);
        } else if (dataset == 120) {
            fileWriterMAPEdata = new FileWriter(mapeTraining120Monthly);
            fileWriterTraining = new FileWriter(errortraining120DataMonthly);
        }

        //Write the Training CSV file header
        fileWriterTraining.append(FILE_HEADER);
        //Add a new line separator after the header
        fileWriterTraining.append(NEW_LINE_SEPARATOR);
        
        //Write the Training CSV file header
        fileWriterMAPEdata.append(FILE_MAPE);
        //Add a new line separator after the header
        fileWriterMAPEdata.append(NEW_LINE_SEPARATOR_MAPE);
        
        for (int x = 0; x < totalData; ++x) {
            id++;
            trainingModel = sc.denormalizationDailyInput(dataset, outputTraining.get(x));
            targetTrainingModel = targetTraining.get(x);
            percentageError = ((targetTrainingModel - trainingModel) / targetTrainingModel) * 100;

//                Absolute Value
            if (percentageError < 0) {
                percentageError *= -1;
            }

            totalError += percentageError;
//            System.out.println("Data Output : " + trainingModel + " , Target : " + targetTrainingModel);
//            System.out.println("\nPercentage Error : " + percentageError);

            //Write a normalization output into CSV File
//                For Column ID
            fileWriterTraining.append(String.valueOf(id));
            fileWriterTraining.append(COMMA_DELIMITER);
            fileWriterTraining.append(String.valueOf(percentageError));
            fileWriterTraining.append(NEW_LINE_SEPARATOR);
        }

        MAPE = totalError / totalData;
        percentage = 100 - MAPE;
//        System.out.println("Total Error : "+totalError);
//        System.out.println("Total Data : "+totalData);
        fileWriterMAPEdata.append(String.valueOf(1));
        fileWriterMAPEdata.append(COMMA_DELIMITER);
        fileWriterMAPEdata.append(String.valueOf(MAPE));
        fileWriterMAPEdata.append(NEW_LINE_SEPARATOR);
        System.out.println("\nNilai MAPE dari Keseluruhan Data Training " + dataset + " Data : " + MAPE + ", Dengan Akurasi : "+percentage+" %");
        
        fileWriterMAPEdata.flush();
        fileWriterMAPEdata.close();
        fileWriterTraining.flush();
        fileWriterTraining.close();
    }

    public void mapeDataTestingMonthly(double[] dataPredict, List<Double> target, int dataset) throws SQLException, IOException {
        int totalData = 0, id = 0;
        if (dataset == 10) {
            fileWriterMAPEdata = new FileWriter(mapeTesting10Monthly);
            fileWriterTesting = new FileWriter(errortesting10DataMonthly);
        } else if (dataset == 50) {
            fileWriterMAPEdata = new FileWriter(mapeTesting50Monthly);
            fileWriterTesting = new FileWriter(errortesting50DataMonthly);
        } else if (dataset == 120) {
            fileWriterMAPEdata = new FileWriter(mapeTesting120Monthly);
            fileWriterTesting = new FileWriter(errortesting120DataMonthly);
        }

        double prediksi, targetTesting, percentageError, MAPE, totalError = 0, nilaiPrediksi = 0, percentage = 0;
        //Write the Testing CSV file header
        fileWriterTesting.append(FILE_HEADER);
        //Add a new line separator after the header
        fileWriterTesting.append(NEW_LINE_SEPARATOR);
        
        //Write the Training CSV file header
        fileWriterMAPEdata.append(FILE_MAPE);
        //Add a new line separator after the header
        fileWriterMAPEdata.append(NEW_LINE_SEPARATOR_MAPE);
        
        for (int x = 0; x < target.size(); x++) {
            id++;
            totalData = target.size() - 1;
//            System.out.println("Prediksi : " + sc.denormalizationDailyInput(dataset, dataPredict[x]));
//            System.out.println("Target   : " + target.get(x));

            prediksi = sc.denormalizationDailyInput(dataset, dataPredict[x]);
            targetTesting = target.get(x);

            if (x < totalData) {
                percentageError = ((targetTesting - prediksi) / targetTesting) * 100;

//                Absolute Value
                if (percentageError < 0) {
                    percentageError *= -1;
                }

                totalError += percentageError;
                //Write a normalization output into CSV File
//                For Column ID
                fileWriterTesting.append(String.valueOf(id));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(percentageError));
                fileWriterTesting.append(NEW_LINE_SEPARATOR);

//                System.out.println("Percentage Error : " + percentageError);
//                System.out.println("Total Error : " + totalError + "\n");
            }
            if (x == totalData) {
                nilaiPrediksi = sc.denormalizationDailyInput(dataset, dataPredict[x]);
//                System.out.println("\nNilai Prediksi : " + nilaiPrediksi);
            }
        }
//        System.out.println("Total Data : " + totalData);

        MAPE = totalError / totalData;
        fileWriterMAPEdata.append(String.valueOf(1));
        fileWriterMAPEdata.append(COMMA_DELIMITER);
        fileWriterMAPEdata.append(String.valueOf(MAPE));
        fileWriterMAPEdata.append(NEW_LINE_SEPARATOR);
        percentage = 100 - MAPE;
        System.out.println("\nMAPE dari Keseluruhan Data Testing " + dataset + " Data adalah : " + MAPE + " , Dengan Akurasi : " +percentage+" %");
        System.out.println("Nilai Prediksi " + dataset + " Data : " + nilaiPrediksi);
        
        fileWriterTesting.flush();
        fileWriterTesting.close();
        fileWriterMAPEdata.flush();
        fileWriterMAPEdata.close();
    }
    
    public void mapeDataTrainingMonthlySecond(List<Double> outputTraining, List<Double> targetTraining, int dataset) throws SQLException, IOException {
        int totalData = outputTraining.size(), id = 0;
        double trainingModel, targetTrainingModel, percentageError, MAPE, totalError = 0, percentage = 0;
        if (dataset == 10) {
            fileWriterMAPEdata = new FileWriter(mapeTraining10MonthlySecond);
            fileWriterTraining = new FileWriter(errortraining10DataMonthlySecond);
        } else if (dataset == 50) {
            fileWriterMAPEdata = new FileWriter(mapeTraining50MonthlySecond);
            fileWriterTraining = new FileWriter(errortraining50DataMonthlySecond);
        } else if (dataset == 120) {
            fileWriterMAPEdata = new FileWriter(mapeTraining120MonthlySecond);
            fileWriterTraining = new FileWriter(errortraining120DataMonthlySecond);
        }

        //Write the Training CSV file header
        fileWriterTraining.append(FILE_HEADER);
        //Add a new line separator after the header
        fileWriterTraining.append(NEW_LINE_SEPARATOR);
        
        //Write the Training CSV file header
        fileWriterMAPEdata.append(FILE_MAPE);
        //Add a new line separator after the header
        fileWriterMAPEdata.append(NEW_LINE_SEPARATOR_MAPE);
        
        for (int x = 0; x < totalData; ++x) {
            id++;
            trainingModel = sc.denormalizationDailyInput(dataset, outputTraining.get(x));
            targetTrainingModel = targetTraining.get(x);
            percentageError = ((targetTrainingModel - trainingModel) / targetTrainingModel) * 100;

//                Absolute Value
            if (percentageError < 0) {
                percentageError *= -1;
            }

            totalError += percentageError;
//            System.out.println("Data Output : " + trainingModel + " , Target : " + targetTrainingModel);
//            System.out.println("\nPercentage Error : " + percentageError);

            //Write a normalization output into CSV File
//                For Column ID
            fileWriterTraining.append(String.valueOf(id));
            fileWriterTraining.append(COMMA_DELIMITER);
            fileWriterTraining.append(String.valueOf(percentageError));
            fileWriterTraining.append(NEW_LINE_SEPARATOR);
        }

        MAPE = totalError / totalData;
        percentage = 100 - MAPE;
//        System.out.println("Total Error : "+totalError);
//        System.out.println("Total Data : "+totalData);
        fileWriterMAPEdata.append(String.valueOf(1));
        fileWriterMAPEdata.append(COMMA_DELIMITER);
        fileWriterMAPEdata.append(String.valueOf(MAPE));
        fileWriterMAPEdata.append(NEW_LINE_SEPARATOR);
        System.out.println("\nNilai MAPE dari Keseluruhan Data Training " + dataset + " Data : " + MAPE + ", Dengan Akurasi : "+percentage+" %");
        
        fileWriterMAPEdata.flush();
        fileWriterMAPEdata.close();
        fileWriterTraining.flush();
        fileWriterTraining.close();
    }
    
    public void mapeDataTestingMonthlySecond(double[] dataPredict, List<Double> target, int dataset) throws SQLException, IOException {
        int totalData = 0, id = 0;
        if (dataset == 10) {
            fileWriterMAPEdata = new FileWriter(mapeTesting10Monthly);
            fileWriterTesting = new FileWriter(errortesting10DataMonthly);
        } else if (dataset == 50) {
            fileWriterMAPEdata = new FileWriter(mapeTesting50Monthly);
            fileWriterTesting = new FileWriter(errortesting50DataMonthly);
        } else if (dataset == 120) {
            fileWriterMAPEdata = new FileWriter(mapeTesting120Monthly);
            fileWriterTesting = new FileWriter(errortesting120DataMonthly);
        }

        double prediksi, targetTesting, percentageError, MAPE, totalError = 0, nilaiPrediksi = 0, percentage = 0;
        //Write the Testing CSV file header
        fileWriterTesting.append(FILE_HEADER);
        //Add a new line separator after the header
        fileWriterTesting.append(NEW_LINE_SEPARATOR);
        
        //Write the Training CSV file header
        fileWriterMAPEdata.append(FILE_MAPE);
        //Add a new line separator after the header
        fileWriterMAPEdata.append(NEW_LINE_SEPARATOR_MAPE);
        
        for (int x = 0; x < target.size(); x++) {
            id++;
            totalData = target.size() - 1;
//            System.out.println("Prediksi : " + sc.denormalizationDailyInput(dataset, dataPredict[x]));
//            System.out.println("Target   : " + target.get(x));

            prediksi = sc.denormalizationDailyInput(dataset, dataPredict[x]);
            targetTesting = target.get(x);

            if (x < totalData) {
                percentageError = ((targetTesting - prediksi) / targetTesting) * 100;

//                Absolute Value
                if (percentageError < 0) {
                    percentageError *= -1;
                }

                totalError += percentageError;
                //Write a normalization output into CSV File
//                For Column ID
                fileWriterTesting.append(String.valueOf(id));
                fileWriterTesting.append(COMMA_DELIMITER);
                fileWriterTesting.append(String.valueOf(percentageError));
                fileWriterTesting.append(NEW_LINE_SEPARATOR);

//                System.out.println("Percentage Error : " + percentageError);
//                System.out.println("Total Error : " + totalError + "\n");
            }
            if (x == totalData) {
                nilaiPrediksi = sc.denormalizationDailyInput(dataset, dataPredict[x]);
//                System.out.println("\nNilai Prediksi : " + nilaiPrediksi);
            }
        }
//        System.out.println("Total Data : " + totalData);

        MAPE = totalError / totalData;
        fileWriterMAPEdata.append(String.valueOf(1));
        fileWriterMAPEdata.append(COMMA_DELIMITER);
        fileWriterMAPEdata.append(String.valueOf(MAPE));
        fileWriterMAPEdata.append(NEW_LINE_SEPARATOR);
        percentage = 100 - MAPE;
        System.out.println("\nMAPE dari Keseluruhan Data Testing " + dataset + " Data adalah : " + MAPE + " , Dengan Akurasi : " +percentage+" %");
        System.out.println("Nilai Prediksi " + dataset + " Data : " + nilaiPrediksi);
        
        fileWriterTesting.flush();
        fileWriterTesting.close();
        fileWriterMAPEdata.flush();
        fileWriterMAPEdata.close();
    }
}
