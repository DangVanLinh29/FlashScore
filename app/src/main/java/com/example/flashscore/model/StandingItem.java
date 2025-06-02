package com.example.flashscore.model;

import com.google.gson.annotations.SerializedName;

public class StandingItem {
    @SerializedName("rank")
    private int rank;
    @SerializedName("team") // API nên trả về đối tượng Team ở đây
    private Team team;
    @SerializedName("played")
    private int played;
    @SerializedName("win")
    private int win;
    @SerializedName("draw")
    private int draw;
    @SerializedName("lose")
    private int lose;
    @SerializedName("goals_for")
    private int goalsFor;
    @SerializedName("goals_against")
    private int goalsAgainst;
    @SerializedName("goal_difference")
    private int goalDifference;
    @SerializedName("points")
    private int points;
    @SerializedName("form") // Ví dụ: "WWLDW"
    private String form;
    @SerializedName("description") // Ví dụ: "Promotion - Champions League" (có thể null)
    private String description;

    public StandingItem(int rank, Team team, int played, int win, int draw, int lose, int goalsFor, int goalsAgainst, int goalDifference, int points, String form, String description) {
        this.rank = rank;
        this.team = team;
        this.played = played;
        this.win = win;
        this.draw = draw;
        this.lose = lose;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
        this.goalDifference = goalDifference;
        this.points = points;
        this.form = form;
        this.description = description;
    }

    // Getters and Setters
    public int getRank() { return rank; }
    public void setRank(int rank) { this.rank = rank; }
    public Team getTeam() { return team; }
    public void setTeam(Team team) { this.team = team; }
    public int getPlayed() { return played; }
    public void setPlayed(int played) { this.played = played; }
    public int getWin() { return win; }
    public void setWin(int win) { this.win = win; }
    public int getDraw() { return draw; }
    public void setDraw(int draw) { this.draw = draw; }
    public int getLose() { return lose; }
    public void setLose(int lose) { this.lose = lose; }
    public int getGoalsFor() { return goalsFor; }
    public void setGoalsFor(int goalsFor) { this.goalsFor = goalsFor; }
    public int getGoalsAgainst() { return goalsAgainst; }
    public void setGoalsAgainst(int goalsAgainst) { this.goalsAgainst = goalsAgainst; }
    public int getGoalDifference() { return goalDifference; }
    public void setGoalDifference(int goalDifference) { this.goalDifference = goalDifference; }
    public int getPoints() { return points; }
    public void setPoints(int points) { this.points = points; }
    public String getForm() { return form; }
    public void setForm(String form) { this.form = form; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}