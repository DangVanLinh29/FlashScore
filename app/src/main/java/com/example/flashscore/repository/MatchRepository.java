package com.example.flashscore.repository; // Hoặc com.example.flashscoreapp.repository

import android.app.Application;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.flashscore.db.AppDatabase; //
import com.example.flashscore.db.dao.FavoriteTeamDao; //
import com.example.flashscore.db.entity.FavoriteTeamEntity; //
import com.example.flashscore.model.Match; //
import com.example.flashscore.model.Team; //
import com.example.flashscore.network.ApiClient; //
import com.example.flashscore.network.ApiService; //
import com.example.flashscore.util.Constants; //

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchRepository {
    private ApiService apiService;
    private FavoriteTeamDao favoriteTeamDao;
    private ExecutorService executorService;
    private static MatchRepository instance;

    private MatchRepository(Application application) {
        apiService = ApiClient.getApiService();
        AppDatabase db = AppDatabase.getDatabase(application.getApplicationContext());
        favoriteTeamDao = db.favoriteTeamDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    public static synchronized MatchRepository getInstance(Application application) {
        if (instance == null) {
            instance = new MatchRepository(application);
        }
        return instance;
    }

    public LiveData<List<Match>> getMatchesByDate(String date) {
        MutableLiveData<List<Match>> data = new MutableLiveData<>();
        // Gọi phương thức mà không cần truyền API key nữa
        apiService.getMatchesByDate(date).enqueue(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    data.setValue(response.body());
                } else {
                    // Bạn có thể log lỗi chi tiết hơn ở đây nếu muốn
                    // String errorBody = "";
                    // if (response.errorBody() != null) { try { errorBody = response.errorBody().string(); } catch (IOException e) { } }
                    // Log.e("MatchRepository", "API Error for getMatchesByDate: " + response.code() + " - " + errorBody);
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {
                // Log.e("MatchRepository", "API Failure for getMatchesByDate: ", t);
                data.setValue(null);
            }
        });
        return data;
    }


    public void addFavoriteTeam(Team team) {
        executorService.execute(() -> {
            FavoriteTeamEntity entity = new FavoriteTeamEntity(team.getTeamId(), team.getName(), team.getLogoUrl(), team.getCountry(), team.getVenueName(), team.getFoundedYear());
            favoriteTeamDao.insertFavoriteTeam(entity);
        });
    }

    public void removeFavoriteTeam(int teamId) {
        executorService.execute(() -> {
            favoriteTeamDao.deleteFavoriteTeamById(teamId);
        });
    }

    public LiveData<FavoriteTeamEntity> getFavoriteTeamById(int teamId) {
        return favoriteTeamDao.getFavoriteTeamById(teamId);
    }
}