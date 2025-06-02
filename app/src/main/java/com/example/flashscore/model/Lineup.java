package com.example.flashscore.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Lineup {
    @SerializedName("home_team_formation")
    private String homeTeamFormation;
    @SerializedName("away_team_formation")
    private String awayTeamFormation;

    @SerializedName("home_team_starting_lineup")
    private List<Player> homeTeamStartingLineup;
    @SerializedName("away_team_starting_lineup")
    private List<Player> awayTeamStartingLineup;

    @SerializedName("home_team_substitutes")
    private List<Player> homeTeamSubstitutes;
    @SerializedName("away_team_substitutes")
    private List<Player> awayTeamSubstitutes;

    public Lineup(String homeTeamFormation, String awayTeamFormation, List<Player> homeTeamStartingLineup, List<Player> awayTeamStartingLineup, List<Player> homeTeamSubstitutes, List<Player> awayTeamSubstitutes) {
        this.homeTeamFormation = homeTeamFormation;
        this.awayTeamFormation = awayTeamFormation;
        this.homeTeamStartingLineup = homeTeamStartingLineup;
        this.awayTeamStartingLineup = awayTeamStartingLineup;
        this.homeTeamSubstitutes = homeTeamSubstitutes;
        this.awayTeamSubstitutes = awayTeamSubstitutes;
    }

    // Getters and Setters
    public String getHomeTeamFormation() { return homeTeamFormation; }
    public void setHomeTeamFormation(String homeTeamFormation) { this.homeTeamFormation = homeTeamFormation; }
    public String getAwayTeamFormation() { return awayTeamFormation; }
    public void setAwayTeamFormation(String awayTeamFormation) { this.awayTeamFormation = awayTeamFormation; }
    public List<Player> getHomeTeamStartingLineup() { return homeTeamStartingLineup; }
    public void setHomeTeamStartingLineup(List<Player> homeTeamStartingLineup) { this.homeTeamStartingLineup = homeTeamStartingLineup; }
    public List<Player> getAwayTeamStartingLineup() { return awayTeamStartingLineup; }
    public void setAwayTeamStartingLineup(List<Player> awayTeamStartingLineup) { this.awayTeamStartingLineup = awayTeamStartingLineup; }
    public List<Player> getHomeTeamSubstitutes() { return homeTeamSubstitutes; }
    public void setHomeTeamSubstitutes(List<Player> homeTeamSubstitutes) { this.homeTeamSubstitutes = homeTeamSubstitutes; }
    public List<Player> getAwayTeamSubstitutes() { return awayTeamSubstitutes; }
    public void setAwayTeamSubstitutes(List<Player> awayTeamSubstitutes) { this.awayTeamSubstitutes = awayTeamSubstitutes; }
}