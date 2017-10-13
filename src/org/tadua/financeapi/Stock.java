/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tadua.financeapi;

/**
 *
 * @author NewBee
 * Editable from https://github.com/natehefner/yahoostocks-java
 */
public class Stock {

    private String symbol;
    private double open;
    private double dayhigh;
    private double daylow;
    private double price;
    private double volume;
    private double previousClose;

    public Stock(String symbol, double open, double dayhigh, double daylow, double price, double volume, double previousClose) {
        this.symbol = symbol;
        this.open = open;
        this.dayhigh = dayhigh;
        this.daylow = daylow;
        this.price = price;
        this.volume = volume;
        this.previousClose = previousClose;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getDayhigh() {
        return dayhigh;
    }

    public void setDayhigh(double dayhigh) {
        this.dayhigh = dayhigh;
    }

    public double getDaylow() {
        return daylow;
    }

    public void setDaylow(double daylow) {
        this.daylow = daylow;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getPreviousClose() {
        return previousClose;
    }

    public void setPreviousClose(double previousClose) {
        this.previousClose = previousClose;
    }
    
    
}
