/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tadua.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
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
public class BarController extends Application {

    DatabaseController Db = new DatabaseController();
    PlotController plotController = new PlotController();

    @FXML
    private Label predictValue;
    @FXML
    private Button backButton;
    @FXML
    private Button exitButton;
    @FXML
    private BarChart<String, Double> barChart;

    @Override
    public void start(Stage stage) throws Exception {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList("MAPE")));

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Error");

        //Creating the Bar chart
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Error Keseluruhan Data");

        //Prepare XYChart.Series objects by setting data       
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("MAPE 10 Data");        
        series1.getData().add(new XYChart.Data<>("MAPE", 0.1966641240346539));


        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("MAPE 50 Data");
        series2.getData().add(new XYChart.Data<>("MAPE", 0.26968192106827793));


        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
        series3.setName("MAPE 100 Data");
        series3.getData().add(new XYChart.Data<>("MAPE", 1.0022740760255675));
        
        
        XYChart.Series<String, Number> series4 = new XYChart.Series<>();
        series4.setName("MAPE 500 Data");
        series4.getData().add(new XYChart.Data<>("MAPE", 0.704445811935031));

        
        XYChart.Series<String, Number> series5 = new XYChart.Series<>();
        series5.setName("MAPE 2500 Data");
        series5.getData().add(new XYChart.Data<>("MAPE", 1.1300553112151952));
        
        //Setting the data to bar chart       
        barChart.getData().addAll(series1, series2, series3, series4, series5);
//        barChart.getData().addAll(series1, series2, series3);

        //Creating a Group object 
        Group root = new Group(barChart);

        //Creating a scene object
        Scene scene = new Scene(root, 600, 400);

        //Setting title to the Stage
        stage.setTitle("MAPE Keseluruhan Data");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }

    public static void main(String args[]) {
        launch(args);
    }
    
}

