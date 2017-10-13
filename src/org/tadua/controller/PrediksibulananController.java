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
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import org.tadua.model.Data;

/**
 * FXML Controller class
 *
 * @author NewBee
 */
public class PrediksibulananController implements Initializable {
    int x=1,y=12,z=2016;
    StockController sc = new StockController();
    PlotController pc = new PlotController();
    ProsesBulananController pbc = new ProsesBulananController();
    DecimalFormat df = new DecimalFormat();
    RConnection connection = null;
    Data datas = new Data();
    double predict,hasil,hasil100,aktualSebelumnya,def,predict100;
    int day,month,monthReal,year;
    int daySeb,monthSeb,monthSebReal,yearSeb;
    String tmpBulan,tmpBulanSeb;
    Date dates,tanggalSeb,dateSeb;
    Date currentDate = new Date();
    Calendar cal = Calendar.getInstance();
    Calendar calSeb = Calendar.getInstance();
    @FXML
    private Label tanggalSekarang;
    @FXML
    private Label bulanSekarang;
    @FXML
    private Label nilaiprediksiSekarang;
    private Label defisit;
    @FXML
    private Label tglSebelum;
    @FXML
    private Label bulanSebelum;
    @FXML
    private Label aktualsebelum;
    @FXML
    private Button grafik;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
//            Proses 10 Data Bulanan
            pbc.proses10DataBulanan(x,y,z);

            connection = new RConnection();
            connection.eval("source('D:\\\\Kuliah\\\\Semester VI\\\\TAII\\\\ImplementasiTAII\\\\forPrediction.R')");
            predict = connection.eval("neuralNetFixMonthly()").asDouble();
            hasil = sc.denormalizationMonthlyInput(x,y,z, predict);
            
            dates = pc.getLastDateMonthly(x,y,z);
//            System.out.println("Dates : "+dates);
            datas = pc.getDataMonthly(x,y,z);
            currentDate = pbc.getCurrentDate(x,y,z);
            
            cal.setTime(currentDate);
            calSeb.setTime(dates);
            
            yearSeb = calSeb.get(Calendar.YEAR);
            monthSeb = calSeb.get(Calendar.MONTH);
            monthSebReal = monthSeb+1;
            
            daySeb = calSeb.get(Calendar.DAY_OF_MONTH);
            tmpBulanSeb = pc.bulan(monthSebReal);
            aktualsebelum.setText(""+df.format(datas.getClose()));
//            Set Data Sebelum
            tglSebelum.setText(""+daySeb);
            bulanSebelum.setText(""+tmpBulanSeb+" "+yearSeb);
            
            Data curData = pbc.getSingleData(x,y,z);
//            System.out.println(curData);
            
//            System.out.println("Current Date :"+currentDate);
            
            year = cal.get(Calendar.YEAR);
            month = cal.get(Calendar.MONTH);
            monthReal=month+1;
            tmpBulan = pc.bulan(monthReal);
            day = cal.get(Calendar.DAY_OF_MONTH);
            
//            System.out.println("Day :"+day+" ; Month : "+monthReal+" ; Bulan : "+tmpBulan);
//            Set Tanggal Data yang sedang digunakan untuk memprediksi
            tanggalSekarang.setText(""+day);
            bulanSekarang.setText(""+tmpBulan+" "+year);
            
        } catch (SQLException ex) {
            Logger.getLogger(PrediksibulananController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PrediksibulananController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RserveException ex) {
            Logger.getLogger(PrediksibulananController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (REXPMismatchException ex) {
            Logger.getLogger(PrediksibulananController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        nilaiprediksiSekarang.setText(""+df.format(hasil));
    }

    @FXML
    private void grafikButton(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
            Parent parentDari = FXMLLoader.load(getClass().getResource("/org/tadua/view/grafikdataaktualbulanan.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(parentDari);
            stage.setScene(scene);
            stage.show();
    }
    
}
