/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tadua.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
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
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author NewBee
 */
public class PrediksiController implements Initializable {
    PlotController plotController = new PlotController();
    DatabaseController Db = new DatabaseController();
    
    @FXML
    private LineChart<String, Double> chartLine;

    @FXML
    private Button backButton;
    @FXML
    private Button exitButton;
    @FXML
    private Label predictValue;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
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
    private void backButton(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent parentDari = FXMLLoader.load(getClass().getResource("/org/tadua/view/home.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(parentDari);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void btnExit(ActionEvent event) {
        System.exit(0);
    }

//    Prepare for fetch data from DB and display in List
//    Data that want to display are 7 days before
//    private ObservableList<XYChart.Series<String, Double>> getData() throws SQLException, IOException {
//        //        Connect to Database
//        Db.connect();
//        ResultSet prediksiResultSet = null, aktualResultSet = null;
//        String statement = "SELECT * FROM output LIMIT 7 OFFSET 4";
//
////        Compile the query
//        prediksiResultSet = Db.getRs(statement);
//        aktualResultSet = Db.getRs(statement);
//
//        ObservableList<XYChart.Series<String, Double>> data = FXCollections.observableArrayList();
//        XYChart.Series<String, Double> prediksi = new XYChart.Series<>();
//        XYChart.Series<String, Double> aktual = new XYChart.Series<>();
//        
//        prediksi.setName("Data Prediksi");
//        aktual.setName("Data Aktual");
//
//        while (prediksiResultSet.next()) {
//            prediksi.getData().add(new XYChart.Data<>(prediksiResultSet.getString("dates"), prediksiResultSet.getDouble("prediction10Data")));
//        }
//
//        while (aktualResultSet.next()) {
//            aktual.getData().add(new XYChart.Data<>(aktualResultSet.getString("dates"), aktualResultSet.getDouble("actual")));
//        }
//
//        data.addAll(prediksi, aktual);
//        return data;
//    }
    
//    private ObservableList<XYChart.Series<String, Double>> getData() throws SQLException, IOException {
//        //        Connect to Database
//        Db.connect();
//        int counter=0;
//        List<Double> errorTraining2500DataHarian = plotController.getErrorOfTrainingDaily(2500);
//        List<Date> dates2500Data = plotController.getDatesTrainingDaily(2500);
//        
//        ObservableList<XYChart.Series<String, Double>> data = FXCollections.observableArrayList();
//        XYChart.Series<String, Double> error = new XYChart.Series<>();
//        
//        error.setName("Error 2500 Data");
//        
//        for(Date date : dates2500Data){
//            error.getData().add(new XYChart.Data<>(date.toString(), errorTraining2500DataHarian.get(counter)));
//            counter+=1;
//        }
//        data.addAll(error);
//        return data;
//    }
    
    private ObservableList<XYChart.Series<String, Double>> getData() throws SQLException, IOException {
        //        Connect to Database
        Db.connect();
        int counter=0;
        List<Double> errorTesting10DataHarian = plotController.getMAPEOfTestingDaily(10);
        List<Double> errorTesting50DataHarian = plotController.getMAPEOfTestingDaily(50);
        List<Double> errorTesting100DataHarian = plotController.getMAPEOfTestingDaily(100);
        List<Double> errorTesting500DataHarian = plotController.getMAPEOfTestingDaily(500);
        List<Double> errorTesting2500DataHarian = plotController.getMAPEOfTestingDaily(2500);
        
        ObservableList<XYChart.Series<String, Double>> data = FXCollections.observableArrayList();
        XYChart.Series<String, Double> MAPE10 = new XYChart.Series<>();
        XYChart.Series<String, Double> MAPE50 = new XYChart.Series<>();
        XYChart.Series<String, Double> MAPE100 = new XYChart.Series<>();
        XYChart.Series<String, Double> MAPE500 = new XYChart.Series<>();
        XYChart.Series<String, Double> MAPE2500 = new XYChart.Series<>();
        
        MAPE10.setName("MAPE 10 Data");
        MAPE50.setName("MAPE 50 Data");
        MAPE100.setName("MAPE 100 Data");
        MAPE500.setName("MAPE 500 Data");
        MAPE2500.setName("MAPE 2500 Data");
        
        MAPE10.getData().add(new XYChart.Data<>("10 Data", errorTesting10DataHarian.get(0)));
        MAPE50.getData().add(new XYChart.Data<>("50 Data", errorTesting50DataHarian.get(0)));
        MAPE100.getData().add(new XYChart.Data<>("100 Data", errorTesting100DataHarian.get(0)));
        MAPE500.getData().add(new XYChart.Data<>("500 Data", errorTesting500DataHarian.get(0)));
        MAPE2500.getData().add(new XYChart.Data<>("2500 Data", errorTesting2500DataHarian.get(0)));
        
        data.addAll(MAPE10,MAPE50,MAPE100,MAPE500,MAPE2500);
        return data;
    }
    
//    public static void main(String[] args) throws IOException{
//        PlotController plotController = new PlotController();
////        List<Double> errorTesting10DataHarian = plotController.getMAPEOfTestingDaily(10);
//        List<Double> errorTesting50DataHarian = plotController.getMAPEOfTestingDaily(50);
////        List<Double> errorTesting100DataHarian = plotController.getMAPEOfTestingDaily(100);
////        List<Double> errorTesting500DataHarian = plotController.getMAPEOfTestingDaily(500);
////        List<Double> errorTesting2500DataHarian = plotController.getMAPEOfTestingDaily(2500);
//        
//        for(Double d : errorTesting50DataHarian){
//            System.out.println(d);
//        }
//        
//    }
}
