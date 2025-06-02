package com.example.flashscore.model;

import com.google.gson.annotations.SerializedName;

public class Player {
    @SerializedName("player_id")
    private int playerId;
    @SerializedName("name")
    private String name;
    @SerializedName("number")
    private Integer number; // Số áo, có thể null
    @SerializedName("position") // Vị trí: "G", "D", "M", "F"
    private String position;

    public Player(int playerId, String name, Integer number, String position) {
        this.playerId = playerId;
        this.name = name;
        this.number = number;
        this.position = position;
    }

    // Getters and Setters
    public int getPlayerId() { return playerId; }
    public void setPlayerId(int playerId) { this.playerId = playerId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getNumber() { return number; }
    public void setNumber(Integer number) { this.number = number; }
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
}