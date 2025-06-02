package com.example.flashscore.db.entity; // Hoặc com.example.flashscoreapp.db.entity

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite_teams")
public class FavoriteTeamEntity {
    @PrimaryKey
    @ColumnInfo(name = "team_id")
    private int teamId;

    @ColumnInfo(name = "team_name")
    private String teamName;

    @ColumnInfo(name = "team_logo_url")
    private String teamLogoUrl;

    @ColumnInfo(name = "team_country") // Thêm
    private String teamCountry;

    @ColumnInfo(name = "team_venue_name") // Thêm
    private String teamVenueName;

    @ColumnInfo(name = "team_founded_year") // Thêm
    private Integer teamFoundedYear;


    @ColumnInfo(name = "added_timestamp")
    private long addedTimestamp;

    // Constructor cập nhật
    public FavoriteTeamEntity(int teamId, String teamName, String teamLogoUrl, String teamCountry, String teamVenueName, Integer teamFoundedYear) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.teamLogoUrl = teamLogoUrl;
        this.teamCountry = teamCountry;
        this.teamVenueName = teamVenueName;
        this.teamFoundedYear = teamFoundedYear;
        this.addedTimestamp = System.currentTimeMillis();
    }

    // Getters
    public int getTeamId() { return teamId; }
    public String getTeamName() { return teamName; }
    public String getTeamLogoUrl() { return teamLogoUrl; }
    public String getTeamCountry() { return teamCountry; }
    public String getTeamVenueName() { return teamVenueName; }
    public Integer getTeamFoundedYear() { return teamFoundedYear; }
    public long getAddedTimestamp() { return addedTimestamp; }

    // Setters
    public void setTeamId(int teamId) { this.teamId = teamId; }
    public void setTeamName(String teamName) { this.teamName = teamName; }
    public void setTeamLogoUrl(String teamLogoUrl) { this.teamLogoUrl = teamLogoUrl; }
    public void setTeamCountry(String teamCountry) { this.teamCountry = teamCountry; }
    public void setTeamVenueName(String teamVenueName) { this.teamVenueName = teamVenueName; }
    public void setTeamFoundedYear(Integer teamFoundedYear) { this.teamFoundedYear = teamFoundedYear; }
    public void setAddedTimestamp(long addedTimestamp) { this.addedTimestamp = addedTimestamp; }
}