/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tadua.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import org.tadua.controller.PlotController;
import org.tadua.controller.PlotController;
import org.tadua.controller.StockController;
import org.tadua.controller.StockController;
import org.tadua.model.Data;

/**
 * FXML Controller class
 *
 * @author NewBee
 */
public class PrediksiharianController implements Initializable {

    HandleErrorController hec = new HandleErrorController();
    StockController stockController = new StockController();
    PlotController pc = new PlotController();
    DecimalFormat df = new DecimalFormat();
    DecimalFormat dfAktual = new DecimalFormat();
    DecimalFormat dfDefisit = new DecimalFormat();
    List<Double> mape = new ArrayList<>();
    List<Double> error = new ArrayList<>();
    double err10Data = 0, err50Data, err100Data = 0, err500Data = 0, bestModel = 10;
    double predict10Data = 0, predict50Data = 0, predict100Data = 0, predict500Data = 0,tmpPrediksi=0;
    
    List<Double> error10Data, error50Data, error100Data, error500Data;
    RConnection rConnection = null;
    Data datas = new Data();
//    double predict10DataDaily,predict50DataDaily,predict100DataDaily,predict500DataDaily;
    double[] predict10DataDaily, predict50DataDaily, predict100DataDaily, predict500DataDaily, predict2500DataDaily;

    double predict, hasil, hasil100, aktualSebelumnya, def, predict100;
    int day, month, monthReal, year;
    int daySeb, monthSeb, monthSebReal, yearSeb;
    String tmpBulan, tmpBulanSeb;
    Date dates, tanggalSeb;
    Calendar cal = Calendar.getInstance();
    Calendar calSeb = Calendar.getInstance();
    @FXML
    private Label tanggalSekarang;
    @FXML
    private Label bulanSekarang;
    @FXML
    private Label nilaiprediksiSekarang;
    @FXML
    private Label kenaikan;
    @FXML
    private Label tglSebelum;
    @FXML
    private Label bulanSebelum;
    @FXML
    private Label aktualsebelum;
    @FXML
    private Button lihatGrafik;
    @FXML
    private ImageView imgUp;
    @FXML
    private ImageView imgDown;

//    @FXML
//    private Button backButton;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
//            sc.proses50Data(50);

            stockController.proses10Data(10);
            stockController.proses50Data(50);
            stockController.proses100Data(100);
            stockController.proses500Data(500);

//            sc.proses100Data(100);
        } catch (SQLException ex) {
            Logger.getLogger(PrediksiharianController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PrediksiharianController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            rConnection = new RConnection();
            
            rConnection.eval("source('D:\\\\Kuliah\\\\Semester VI\\\\TAII\\\\ImplementasiTAII\\\\neuralNetDataSet.R')");
        } catch (RserveException ex) {
            Logger.getLogger(PrediksiharianController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
//            predict100 = connection.eval("neuralNetFix100()").asDouble();
            predict10DataDaily = (double[]) rConnection.eval("neuralNet10()").asDoubles();
            predict50DataDaily = (double[]) rConnection.eval("neuralNet50()").asDoubles();
            predict100DataDaily = (double[]) rConnection.eval("neuralNet100()").asDoubles();
            predict500DataDaily = (double[]) rConnection.eval("neuralNet500()").asDoubles();

            List<Double> targetTesting10DataDaily = stockController.targetDataTestingDaily(10);
            List<Double> targetTesting50DataDaily = stockController.targetDataTestingDaily(50);
            List<Double> targetTesting100DataDaily = stockController.targetDataTestingDaily(100);
            List<Double> targetTesting500DataDaily = stockController.targetDataTestingDaily(500);

            hec.mapeDataTestingDaily(predict10DataDaily, targetTesting10DataDaily, 10);
            hec.mapeDataTestingDaily(predict50DataDaily, targetTesting50DataDaily, 50);
            hec.mapeDataTestingDaily(predict100DataDaily, targetTesting100DataDaily, 100);
            hec.mapeDataTestingDaily(predict500DataDaily, targetTesting500DataDaily, 500);

            error10Data = pc.MAPEofTestingDaily(10);
            error50Data = pc.MAPEofTestingDaily(50);
            error100Data = pc.MAPEofTestingDaily(100);
            error500Data = pc.MAPEofTestingDaily(500);

            for (int x = 0; x < 2; x++) {
                if (x == 0) {
                    err10Data = error10Data.get(x);
                    err50Data = error50Data.get(x);
                    err100Data = error100Data.get(x);
                    err500Data = error500Data.get(x);
                }
                if (x == 1) {
                    predict10Data = error10Data.get(x);
                    predict50Data = error50Data.get(x);
                    predict100Data = error100Data.get(x);
                    predict500Data = error500Data.get(x);
                }
            }

            error.add(err10Data);
            error.add(err50Data);
            error.add(err100Data);
            error.add(err500Data);

            for (Double m : error) {
                if (m < bestModel) {
                    bestModel = m;
                }
            }

            if (bestModel == err10Data) {
                tmpPrediksi = predict10Data;
                kenaikan.setText("10 Data");
            } else if (bestModel == err50Data) {
                tmpPrediksi = predict50Data;
                kenaikan.setText("50 Data");
            } else if (bestModel == err100Data) {
                tmpPrediksi = predict100Data;
                kenaikan.setText("100 Data");
            } else if (bestModel == err500Data) {
                tmpPrediksi = predict500Data;
                kenaikan.setText("500 Data");
            }
            
            dates = pc.getLastDate();

            datas = pc.dataSebelumnya();

            aktualSebelumnya = datas.getClose();

            tanggalSeb = datas.getDate();
//            System.out.println("Aktual Sebelumnya "+aktualSebelumnya);
            cal.setTime(dates);
            calSeb.setTime(tanggalSeb);

            year = cal.get(Calendar.YEAR);
            month = cal.get(Calendar.MONTH);
            monthReal = month + 1;

            day = cal.get(Calendar.DAY_OF_MONTH);

            yearSeb = calSeb.get(Calendar.YEAR);
            monthSeb = calSeb.get(Calendar.MONTH);
            monthSebReal = monthSeb + 1;

            daySeb = calSeb.get(Calendar.DAY_OF_MONTH);
        } catch (RserveException ex) {
            Logger.getLogger(PrediksiharianController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (REXPMismatchException ex) {
            Logger.getLogger(PrediksiharianController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PrediksiharianController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PrediksiharianController.class.getName()).log(Level.SEVERE, null, ex);
        }
        df.setMaximumFractionDigits(2);
        dfDefisit.setMaximumFractionDigits(3);
        dfAktual.setMaximumFractionDigits(2);

        tmpBulan = pc.bulan(monthReal);
        tmpBulanSeb = pc.bulan(monthSebReal);
        if (tmpPrediksi > aktualSebelumnya) {
//            kenaikan.setText("kenaikan");
            imgUp.setVisible(true);
            imgDown.setVisible(false);
        } else if (tmpPrediksi < aktualSebelumnya) {
//            kenaikan.setText("penurunan");
            imgDown.setVisible(true);
            imgUp.setVisible(false);
        }

        def = aktualSebelumnya - tmpPrediksi;

        if (def < 0) {
            def *= -1;
        }

//        defisit.setText("" + dfDefisit.format(def));
        
//        Set Nilai Prediksi
//        nilaiprediksiSekarang.setText("" + df.format(hasil));
        nilaiprediksiSekarang.setText("" +df.format(tmpPrediksi));

        if (day < 10) {
            tanggalSekarang.setText(" " + day);
        } else {
            tanggalSekarang.setText("" + day);
        }

        bulanSekarang.setText("" + tmpBulan + " " + year);

        tglSebelum.setText("" + daySeb);
        bulanSebelum.setText("" + tmpBulanSeb + " " + yearSeb);
        aktualsebelum.setText("" + dfAktual.format(aktualSebelumnya));
//        prediksiSebelum.setText();
    }

    @FXML
    private void lihatGrafik(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent parentDari = FXMLLoader.load(getClass().getResource("/org/tadua/view/grafikdataaktualharian.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(parentDari);
        stage.setScene(scene);
        stage.show();
    }

}
