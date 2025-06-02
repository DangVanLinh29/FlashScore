package com.example.flashscore.network; // Hoặc com.example.flashscoreapp.network

import com.example.flashscore.model.League;
import com.example.flashscore.model.Match;
import com.example.flashscore.model.Standing;
import com.example.flashscore.model.Team;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query; // Giữ lại Query cho các tham số khác nếu cần

public interface ApiService {

    @GET("leagues")
    Call<List<League>> getTopLeagues(); // Đã bỏ apiKey

    @GET("fixtures/date/{date}")
    Call<List<Match>> getMatchesByDate(
            @Path("date") String date // Đã bỏ apiKey
    );

    @GET("fixtures/league/{leagueId}")
    Call<List<Match>> getMatchesByLeague(
            @Path("leagueId") int leagueId,
            // Đã bỏ apiKey
            @Query("season") String season
    );

    @GET("fixtures/id/{matchId}")
    Call<Match> getMatchDetails(
            @Path("matchId") int matchId // Đã bỏ apiKey
    );

    @GET("standings/league/{leagueId}")
    Call<List<Standing>> getStandingsByLeague(
            @Path("leagueId") int leagueId,
            // Đã bỏ apiKey
            @Query("season") String season
    );

    @GET("fixtures/league/{leagueId}/results")
    Call<List<Match>> getLeagueResults(
            @Path("leagueId") int leagueId,
            // Đã bỏ apiKey
            @Query("season") String season,
            @Query("status") String status
    );

    @GET("teams/id/{teamId}")
    Call<Team> getTeamDetails(
            @Path("teamId") int teamId
    );

    @GET("fixtures/team/{teamId}")
    Call<List<Match>> getMatchesByTeam(
            @Path("teamId") int teamId,
            @Query("season") String season,
            @Query("status") String status
    );
}