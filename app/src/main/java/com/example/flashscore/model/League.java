package com.example.flashscore.model;

import com.google.gson.annotations.SerializedName;

public class League {
    @SerializedName("league_id")
    private int leagueId;
    @SerializedName("name")
    private String name;
    @SerializedName("country")
    private String country;
    @SerializedName("logo_url")
    private String logoUrl;
    // API có thể trả về current_season, ví dụ: "2024-2025"
    @SerializedName("season") // Hoặc "current_season" tùy theo API
    private String season;


    public League(int leagueId, String name, String country, String logoUrl, String season) {
        this.leagueId = leagueId;
        this.name = name;
        this.country = country;
        this.logoUrl = logoUrl;
        this.season = season;
    }

    // Getters and Setters
    public int getLeagueId() { return leagueId; }
    public void setLeagueId(int leagueId) { this.leagueId = leagueId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    public String getLogoUrl() { return logoUrl; }
    public void setLogoUrl(String logoUrl) { this.logoUrl = logoUrl; }
    public String getSeason() { return season; }
    public void setSeason(String season) { this.season = season; }
}