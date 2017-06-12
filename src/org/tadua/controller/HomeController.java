/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tadua.controller;

import org.tadua.financeapi.Stock;
import org.tadua.financeapi.StockFetcher;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author NewBee
 */
public class HomeController implements Initializable {

    @FXML
    private Button sahamHarian;
    @FXML
    private Button sahamBulanan;
    @FXML
    private Button exitButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnsahamHarian(ActionEvent event) throws IOException {
//        1. Update Data From Yahoo Finance
//        fetchData();
//        2. Insert Data into Database
        
//        3. Integrate With R
//        4. Predict Data using 50 Data
//        5. Pass into next Screen
        
//        To Next Screen
        ((Node) (event.getSource())).getScene().getWindow().hide();
            Parent parentDari = FXMLLoader.load(getClass().getResource("/org/tadua/view/training.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(parentDari);
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void btnsahamBulanan(ActionEvent event) throws IOException {
        
//        To Next Screen
        ((Node) (event.getSource())).getScene().getWindow().hide();
            Parent parentDari = FXMLLoader.load(getClass().getResource("/org/tadua/view/prediksibulanan.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(parentDari);
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void btnExit(ActionEvent event) {
        System.exit(0);
    }
}
