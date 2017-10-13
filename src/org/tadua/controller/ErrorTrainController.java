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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

/**
 * FXML Controller class
 *
 * @author NewBee
 */
public class ErrorTrainController implements Initializable {
    HandleErrorController hec = new HandleErrorController();
    StockController stockController = new StockController();
    PlotController pc = new PlotController();
    double err10Data = 0, err50Data, err100Data = 0, err500Data = 0, bestModel = 10;
    double predict10Data = 0, predict50Data = 0, predict100Data = 0, predict500Data = 0,tmpPrediksi=0;
    List<Double> error10Data, error50Data, error100Data, error500Data;
    double[] predict10DataDaily, predict50DataDaily, predict100DataDaily, predict500DataDaily, predict2500DataDaily;
    RConnection rConnection = null;
    
    @FXML
    private BarChart<String, Double> barChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO

            stockController.proses10Data(10);
            stockController.proses50Data(50);
            stockController.proses100Data(100);
            stockController.proses500Data(500);
            rConnection = new RConnection();
            rConnection.eval("source('D:\\\\Kuliah\\\\Semester VI\\\\TAII\\\\ImplementasiTAII\\\\neuralNetDataSet.R')");
            
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
        } catch (SQLException ex) {
            Logger.getLogger(ErrorTrainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ErrorTrainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RserveException ex) {
            Logger.getLogger(ErrorTrainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (REXPMismatchException ex) {
            Logger.getLogger(ErrorTrainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
