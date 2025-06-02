package com.example.flashscore.model;

import com.google.gson.annotations.SerializedName;

public class Statistic {
    @SerializedName("type") // Ví dụ: "Shots on Goal", "Fouls"
    private String type;
    @SerializedName("home_value")
    private String homeValue; // Dùng String để linh hoạt (ví dụ: "55%" hoặc "5")
    @SerializedName("away_value")
    private String awayValue;

    public Statistic(String type, String homeValue, String awayValue) {
        this.type = type;
        this.homeValue = homeValue;
        this.awayValue = awayValue;
    }

    // Getters and Setters
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getHomeValue() { return homeValue; }
    public void setHomeValue(String homeValue) { this.homeValue = homeValue; }
    public String getAwayValue() { return awayValue; }
    public void setAwayValue(String awayValue) { this.awayValue = awayValue; }
}