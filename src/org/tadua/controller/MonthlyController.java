/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tadua.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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

/**
 * FXML Controller class
 *
 * @author NewBee
 */
public class MonthlyController implements Initializable {
    PlotController plotController = new PlotController();
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
    private Button backButton;
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
            Logger.getLogger(MonthlyController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MonthlyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void backButton(ActionEvent event) throws IOException{
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
    
    private ObservableList<XYChart.Series<String, Double>> getData() throws SQLException, IOException {
        //        Connect to Database
//        Db.connect();
        List<Double> errorTesting10DataMonthly = plotController.getMAPEOfTestingMonthly(10);
        List<Double> errorTesting50DataMonthly = plotController.getMAPEOfTestingMonthly(50);
        List<Double> errorTesting120DataMonthly = plotController.getMAPEOfTestingMonthly(120);
        
        ObservableList<XYChart.Series<String, Double>> data = FXCollections.observableArrayList();
        XYChart.Series<String, Double> MAPE10 = new XYChart.Series<>();
        XYChart.Series<String, Double> MAPE50 = new XYChart.Series<>();
        XYChart.Series<String, Double> MAPE120 = new XYChart.Series<>();
        
        MAPE10.setName("MAPE 10 Data");
        MAPE50.setName("MAPE 50 Data");
        MAPE120.setName("MAPE 120 Data");
        
        MAPE10.getData().add(new XYChart.Data<>("10 Data", errorTesting10DataMonthly.get(0)));
        MAPE50.getData().add(new XYChart.Data<>("50 Data", errorTesting50DataMonthly.get(0)));
        MAPE120.getData().add(new XYChart.Data<>("100 Data", errorTesting120DataMonthly.get(0)));
        
        data.addAll(MAPE10,MAPE50,MAPE120);
        return data;
    }
}
