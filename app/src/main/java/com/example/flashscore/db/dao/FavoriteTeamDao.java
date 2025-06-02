package com.example.flashscore.db.dao; // Hoặc com.example.flashscoreapp.db.dao

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.flashscore.db.entity.FavoriteTeamEntity;

import java.util.List;

@Dao
public interface FavoriteTeamDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavoriteTeam(FavoriteTeamEntity team);

    @Delete
    void deleteFavoriteTeam(FavoriteTeamEntity team);

    @Query("DELETE FROM favorite_teams WHERE team_id = :teamId")
    void deleteFavoriteTeamById(int teamId);

    @Query("SELECT * FROM favorite_teams WHERE team_id = :teamId")
    LiveData<FavoriteTeamEntity> getFavoriteTeamById(int teamId); // Room sẽ tự tạo lệnh return

    @Query("SELECT * FROM favorite_teams ORDER BY added_timestamp DESC")
    LiveData<List<FavoriteTeamEntity>> getAllFavoriteTeams(); // Room sẽ tự tạo lệnh return

    @Query("SELECT * FROM favorite_teams WHERE team_id = :teamId LIMIT 1")
    FavoriteTeamEntity getFavoriteTeamByIdSync(int teamId); // Room sẽ tự tạo lệnh return
}