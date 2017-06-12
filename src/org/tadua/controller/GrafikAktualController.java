/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tadua.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.tadua.model.Data;

/**
 * FXML Controller class
 *
 * @author NewBee
 */
public class GrafikAktualController implements Initializable {
    PlotController pc = new PlotController();
    DatabaseController Db = new DatabaseController();
    
    @FXML
    private Label predictValue;
    @FXML
    private LineChart<String, Double> chartLine;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    @FXML
    private Button exitButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            chartLine.setData(getData());
        } catch (SQLException ex) {
            Logger.getLogger(PrediksiController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PrediksiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnExit(ActionEvent event) {
        System.exit(0);
    }
    
    private ObservableList<XYChart.Series<String, Double>> getData() throws SQLException, IOException {
        //        Connect to Database
        int counter=0;
        List<Date> ds = pc.getDates7DataDaily();
        List<Data> aktual = pc.aktual7Data();
        
        ObservableList<XYChart.Series<String, Double>> data = FXCollections.observableArrayList();
        XYChart.Series<String, Double> aktual50Hari = new XYChart.Series<>();
        
        aktual50Hari.setName("Data Akutal selama 7 Hari");
        
        for(Data d : aktual){
            aktual50Hari.getData().add(new XYChart.Data<>(ds.get(counter).toString(), d.getClose()));
//            aktual50Hari.getData().add(new XYChart.Data<>("50 Data", errorTesting10DataHarian.get(0)));
            counter+=1;
        }
       
        data.addAll(aktual50Hari);
        return data;
    }
}
