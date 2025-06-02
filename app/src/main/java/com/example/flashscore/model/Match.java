package com.example.flashscore.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Match {
    @SerializedName("match_id")
    private int matchId;

    // Thông tin giải đấu của trận này
    @SerializedName("league_id")
    private int leagueId;
    @SerializedName("league_name")
    private String leagueName;
    @SerializedName("league_logo") // Thêm nếu API trả về logo giải trong thông tin trận đấu
    private String leagueLogo;


    @SerializedName("home_team")
    private Team homeTeam;
    @SerializedName("away_team")
    private Team awayTeam;

    @SerializedName("match_date_time_utc") // Ví dụ: "2025-05-27T19:00:00+00:00"
    private String matchDateTimeUtc;
    @SerializedName("status") // Ví dụ: "NS", "LIVE", "FT", "HT", "PST"
    private String status;
    @SerializedName("minute")
    private Integer minute;

    @SerializedName("home_score")
    private int homeScore;
    @SerializedName("away_score")
    private int awayScore;

    @SerializedName("round")
    private String round;

    // Chi tiết trận đấu (có thể null ban đầu)
    @SerializedName("lineups")
    private Lineup lineups;
    @SerializedName("statistics")
    private List<Statistic> statistics;
    @SerializedName("events")
    private List<MatchEvent> events;

    public Match(int matchId, int leagueId, String leagueName, String leagueLogo, Team homeTeam, Team awayTeam, String matchDateTimeUtc, String status, Integer minute, int homeScore, int awayScore, String round) {
        this.matchId = matchId;
        this.leagueId = leagueId;
        this.leagueName = leagueName;
        this.leagueLogo = leagueLogo;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.matchDateTimeUtc = matchDateTimeUtc;
        this.status = status;
        this.minute = minute;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.round = round;
    }

    // Getters and Setters
    public int getMatchId() { return matchId; }
    public void setMatchId(int matchId) { this.matchId = matchId; }
    public int getLeagueId() { return leagueId; }
    public void setLeagueId(int leagueId) { this.leagueId = leagueId; }
    public String getLeagueName() { return leagueName; }
    public void setLeagueName(String leagueName) { this.leagueName = leagueName; }
    public String getLeagueLogo() { return leagueLogo; }
    public void setLeagueLogo(String leagueLogo) { this.leagueLogo = leagueLogo; }
    public Team getHomeTeam() { return homeTeam; }
    public void setHomeTeam(Team homeTeam) { this.homeTeam = homeTeam; }
    public Team getAwayTeam() { return awayTeam; }
    public void setAwayTeam(Team awayTeam) { this.awayTeam = awayTeam; }
    public String getMatchDateTimeUtc() { return matchDateTimeUtc; }
    public void setMatchDateTimeUtc(String matchDateTimeUtc) { this.matchDateTimeUtc = matchDateTimeUtc; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Integer getMinute() { return minute; }
    public void setMinute(Integer minute) { this.minute = minute; }
    public int getHomeScore() { return homeScore; }
    public void setHomeScore(int homeScore) { this.homeScore = homeScore; }
    public int getAwayScore() { return awayScore; }
    public void setAwayScore(int awayScore) { this.awayScore = awayScore; }
    public String getRound() { return round; }
    public void setRound(String round) { this.round = round; }
    public Lineup getLineups() { return lineups; }
    public void setLineups(Lineup lineups) { this.lineups = lineups; }
    public List<Statistic> getStatistics() { return statistics; }
    public void setStatistics(List<Statistic> statistics) { this.statistics = statistics; }
    public List<MatchEvent> getEvents() { return events; }
    public void setEvents(List<MatchEvent> events) { this.events = events; }
}