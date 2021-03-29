package com.example.btandroid.model;

public class ModelAddTarget {
    private String target;
    private String starDate;
    private String endDate;
    private String count;

    public ModelAddTarget(String target, String starDate, String endDate, String count) {
        this.target = target;
        this.starDate = starDate;
        this.endDate = endDate;
        this.count = count;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getStarDate() {
        return starDate;
    }

    public void setStarDate(String starDate) {
        this.starDate = starDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
