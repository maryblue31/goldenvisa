/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.alma.dto;

/**
 *
 * @author gianalex
 */
public class FilterDTO {
    private String keyword;
    private String location;
    private String propType;
    private String propStatus;
    private String agents;
    private String minBeds;
    private String minBathroom;
    private String minPrice;
    private String maxPrice;
    private String minArea;
    private String maxArea;

    public FilterDTO(){

        this.keyword = "";
        this.location = "";
        this.propStatus = "";
        this.propType = "";
        this.agents = "";
        this.minBeds = "";
        this.minBathroom = "";
        this.minPrice = "";
        this.maxPrice = "";
        this.minArea = "";
        this.maxArea = "";
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Object getPropType() {
        return propType;
    }

    public void setPropType(String propType) {
        this.propType = propType;
    }

    public String getPropStatus() {
        return propStatus;
    }

    public void setPropStatus(String propStatus) {
        this.propStatus = propStatus;
    }

    public String getAgents() {
        return agents;
    }

    public void setAgents(String agents) {
        this.agents = agents;
    }

    public String getMinBeds() {
        return minBeds;
    }

    public void setMinBeds(String minBeds) {
        this.minBeds = minBeds;
    }

    public String getMinBathroom() {
        return minBathroom;
    }

    public void setMinBathroom(String minBathroom) {
        this.minBathroom = minBathroom;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getMinArea() {
        return minArea;
    }

    public void setMinArea(String minArea) {
        this.minArea = minArea;
    }

    public String getMaxArea() {
        return maxArea;
    }

    public void setMaxArea(String maxArea) {
        this.maxArea = maxArea;
    }    
}
