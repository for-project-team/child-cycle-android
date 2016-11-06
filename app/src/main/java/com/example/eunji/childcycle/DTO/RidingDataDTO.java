package com.example.eunji.childcycle.dto;

/**
 * RidingData DTO
 *
 * Created by choihyesun on 2016. 10. 12..
 */

public class RidingDataDTO {
    private String date, totalDistance, avgVelocity, calorie, ridingTime;
    private int safetyCnt, warningCnt;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(String totalDistance) {
        this.totalDistance = totalDistance;
    }

    public String getAvgVelocity() {
        return avgVelocity;
    }

    public void setAvgVelocity(String avgVelocity) {
        this.avgVelocity = avgVelocity;
    }

    public String getCalorie() {
        return calorie;
    }

    public void setCalorie(String calorie) {
        this.calorie = calorie;
    }

    public String getRidingTime() {
        return ridingTime;
    }

    public void setRidingTime(String ridingTime) {
        this.ridingTime = ridingTime;
    }

    public int getSafetyCnt() {
        return safetyCnt;
    }

    public void setSafetyCnt(int safetyCnt) {
        this.safetyCnt = safetyCnt;
    }

    public int getWarningCnt() {
        return warningCnt;
    }

    public void setWarningCnt(int warningCnt) {
        this.warningCnt = warningCnt;
    }
}
