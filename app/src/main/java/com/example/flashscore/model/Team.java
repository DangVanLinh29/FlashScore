package com.example.flashscore.model;

import com.google.gson.annotations.SerializedName;

public class Team {
    @SerializedName("team_id")
    private int teamId;
    @SerializedName("name")
    private String name;
    @SerializedName("logo_url")
    private String logoUrl;

    @SerializedName("country")
    private String country;
    @SerializedName("venue_name")
    private String venueName;
    @SerializedName("founded")
    private Integer foundedYear;

    public Team(int teamId, String name, String logoUrl, String country, String venueName, Integer foundedYear) {
        this.teamId = teamId;
        this.name = name;
        this.logoUrl = logoUrl;
        this.country = country;
        this.venueName = venueName;
        this.foundedYear = foundedYear;
    }

    // Getters and Setters
    public int getTeamId() { return teamId; }
    public void setTeamId(int teamId) { this.teamId = teamId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLogoUrl() { return logoUrl; }
    public void setLogoUrl(String logoUrl) { this.logoUrl = logoUrl; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    public String getVenueName() { return venueName; }
    public void setVenueName(String venueName) { this.venueName = venueName; }
    public Integer getFoundedYear() { return foundedYear; }
    public void setFoundedYear(Integer foundedYear) { this.foundedYear = foundedYear; }
}