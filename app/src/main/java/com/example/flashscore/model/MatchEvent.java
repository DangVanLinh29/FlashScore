package com.example.flashscore.model;

import com.google.gson.annotations.SerializedName;

public class MatchEvent {
    @SerializedName("event_id") // ID của sự kiện (tùy chọn, nếu API có)
    private String eventId;
    @SerializedName("team_id") // ID của đội gây ra sự kiện
    private int teamId;
    @SerializedName("type") // Ví dụ: "goal", "yellowcard", "redcard", "substitution"
    private String type;
    @SerializedName("minute")
    private int minute;
    @SerializedName("extra_minute")
    private Integer extraMinute;
    @SerializedName("player_name")
    private String playerName;
    @SerializedName("assist_player_name")
    private String assistPlayerName; // Có thể null
    @SerializedName("detail") // Ví dụ: "Penalty", "Own Goal", "Second Yellow"
    private String detail;

    public MatchEvent(String eventId, int teamId, String type, int minute, Integer extraMinute, String playerName, String assistPlayerName, String detail) {
        this.eventId = eventId;
        this.teamId = teamId;
        this.type = type;
        this.minute = minute;
        this.extraMinute = extraMinute;
        this.playerName = playerName;
        this.assistPlayerName = assistPlayerName;
        this.detail = detail;
    }

    // Getters and Setters
    public String getEventId() { return eventId; }
    public void setEventId(String eventId) { this.eventId = eventId; }
    public int getTeamId() { return teamId; }
    public void setTeamId(int teamId) { this.teamId = teamId; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public int getMinute() { return minute; }
    public void setMinute(int minute) { this.minute = minute; }
    public Integer getExtraMinute() { return extraMinute; }
    public void setExtraMinute(Integer extraMinute) { this.extraMinute = extraMinute; }
    public String getPlayerName() { return playerName; }
    public void setPlayerName(String playerName) { this.playerName = playerName; }
    public String getAssistPlayerName() { return assistPlayerName; }
    public void setAssistPlayerName(String assistPlayerName) { this.assistPlayerName = assistPlayerName; }
    public String getDetail() { return detail; }
    public void setDetail(String detail) { this.detail = detail; }
}