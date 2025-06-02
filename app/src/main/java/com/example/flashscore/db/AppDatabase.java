package com.example.flashscore.db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.flashscore.db.dao.FavoriteTeamDao;
import com.example.flashscore.db.entity.FavoriteTeamEntity;

@Database(entities = {FavoriteTeamEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract FavoriteTeamDao favoriteTeamDao();

    private static volatile AppDatabase INSTANCE;
    private static final String DATABASE_NAME = "flashscore_app_db";

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, DATABASE_NAME)
                            // .allowMainThreadQueries() // CHỈ DÙNG CHO DEBUGGING
                            .fallbackToDestructiveMigration() // Xóa và tạo lại DB nếu không có migration path
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}