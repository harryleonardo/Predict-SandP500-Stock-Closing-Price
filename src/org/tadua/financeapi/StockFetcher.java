/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tadua.financeapi;

/**
 *
 * @author NewBee
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StockFetcher {

    /*
	* Returns a Stock Object that contains info about a specified stock.
	* @param 	symbol the company's stock symbol
	* @return 	a stock object containing info about the company's stock
	* @see Stock
        * Editable from https://github.com/natehefner/yahoostocks-java
     */    
    public static Stock getStock(String symbol) {
        String sym = symbol.toUpperCase();
        double price = 0.0;
        double volume = 0;
        double daylow = 0.0;
        double dayhigh = 0.0;
        String name = "";
        double open = 0.0;
        double previousClose = 0.0;
        String exchange;
        try {

            // Retrieve CSV File
            URL yahoo = new URL("https://finance.yahoo.com/d/quotes.csv?s=" + symbol + "&f=l1vr2ejkghm3j3nc4s7pox");
            URLConnection connection = yahoo.openConnection();
            
            System.out.println(connection);
            
            HttpURLConnection conn = (HttpURLConnection) yahoo.openConnection();
            conn.setInstanceFollowRedirects(true);  //you still need to handle redirect manully.
            HttpURLConnection.setFollowRedirects(true);

            String redirect = connection.getHeaderField("Location");
            if (redirect != null){
                connection = new URL(redirect).openConnection();
            }
            InputStreamReader is = new InputStreamReader(connection.getInputStream());
            BufferedReader br = new BufferedReader(is);

            // Parse CSV Into Array
            String line = br.readLine();
            //Only split on commas that aren't in quotes
            String[] stockinfo = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
            
            // Handle Our Data
            StockHelper sh = new StockHelper();
            open = sh.handleDouble(stockinfo[14]);
            dayhigh = sh.handleDouble(stockinfo[7]);
            daylow = sh.handleDouble(stockinfo[6]);
            price = sh.handleDouble(stockinfo[0]);
            volume = sh.handleDouble(stockinfo[1]);
            previousClose = sh.handleDouble(stockinfo[13]);
            name = stockinfo[10].replace("\"", "");
            
        } catch (IOException e) {
            Logger log = Logger.getLogger(StockFetcher.class.getName());
            log.log(Level.SEVERE, e.toString(), e);
            return null;
        }
        return new Stock(symbol,open,dayhigh,daylow,price,volume,previousClose);
    }
}
