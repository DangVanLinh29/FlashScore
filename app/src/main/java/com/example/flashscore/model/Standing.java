package com.example.flashscore.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Standing {
    @SerializedName("league_id") // ID của giải đấu mà BXH này thuộc về
    private int leagueId;
    @SerializedName("league_name")
    private String leagueName; // Tên giải đấu
    @SerializedName("season_year")
    private String seasonYear; // Năm của mùa giải, ví dụ "2024-2025"

    @SerializedName("standings") // API có thể trả về một list các bảng (tổng, sân nhà, sân khách)
    // Hoặc một list các StandingItem nếu chỉ có 1 bảng chính.
    // Giả sử API trả về một list các StandingItem cho một loại bảng cụ thể.
    private List<StandingItem> table; // Danh sách các đội và vị trí của họ


    public Standing(int leagueId, String leagueName, String seasonYear, List<StandingItem> table) {
        this.leagueId = leagueId;
        this.leagueName = leagueName;
        this.seasonYear = seasonYear;
        this.table = table;
    }

    // Getters and Setters
    public int getLeagueId() { return leagueId; }
    public void setLeagueId(int leagueId) { this.leagueId = leagueId; }
    public String getLeagueName() { return leagueName; }
    public void setLeagueName(String leagueName) { this.leagueName = leagueName; }
    public String getSeasonYear() { return seasonYear; }
    public void setSeasonYear(String seasonYear) { this.seasonYear = seasonYear; }
    public List<StandingItem> getTable() { return table; }
    public void setTable(List<StandingItem> table) { this.table = table; }
}