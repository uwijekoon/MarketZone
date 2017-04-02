/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ci6225.marketzone.model;

import java.util.Date;

/**
 *
 * @author Ureka
 */
public class Bid {

    private int id;
    private int productId;
    private Date startTime;
    private Date endTime;
    private double minAmount;
    private int winnerBidId;

    public Bid(int productId, Date startTime, Date endTime, double minAmount) {
        this.productId = productId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.minAmount = minAmount;
    }

    public Bid(int id, int productId, Date startTime, Date endTime, double minAmount) {
        this(productId, startTime, endTime, minAmount);
        this.id = id;

    }

    public Bid(int id, int productId, Date startTime, Date endTime, double minAmount, int winnerBidId) {
        this(id, productId, startTime, endTime, minAmount);
        this.winnerBidId = winnerBidId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public double getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(double minAmount) {
        this.minAmount = minAmount;
    }

    public int getWinnerBidId() {
        return winnerBidId;
    }

    public void setWinnerBidId(int winnerBidId) {
        this.winnerBidId = winnerBidId;
    }

}
