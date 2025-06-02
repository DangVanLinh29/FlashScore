package com.example.flashscore.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.flashscore.db.entity.FavoriteTeamEntity;
import com.example.flashscore.model.Match;
import com.example.flashscore.model.Team;
import com.example.flashscore.repository.MatchRepository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class HomeViewModel extends AndroidViewModel {
    private MatchRepository matchRepository;
    private MutableLiveData<String> selectedDateLiveData;
    public LiveData<List<Match>> matchesLiveData; // Public để Fragment observe trực tiếp
    private MutableLiveData<Boolean> isLoadingLiveData = new MutableLiveData<>();
    private MutableLiveData<String> errorLiveData = new MutableLiveData<>();


    public HomeViewModel(Application application) {
        super(application);
        matchRepository = MatchRepository.getInstance(application);
        selectedDateLiveData = new MutableLiveData<>();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String today = dateFormat.format(calendar.getTime());

        selectedDateLiveData.setValue(today); // Khởi tạo ngày ban đầu

        // Sử dụng Transformations.switchMap để matchesLiveData tự động cập nhật khi selectedDateLiveData thay đổi
        matchesLiveData = Transformations.switchMap(selectedDateLiveData, date -> {
            isLoadingLiveData.setValue(true);
            errorLiveData.setValue(null); // Xóa lỗi cũ
            LiveData<List<Match>> newMatchesSource = matchRepository.getMatchesByDate(date);
            // Cần một cách để biết khi newMatchesSource thực sự có dữ liệu hoặc lỗi để tắt isLoading
            // Có thể observe newMatchesSource ở đây hoặc trong Repository có callback
            // Tạm thời, Fragment sẽ xử lý việc tắt isLoading khi nhận được dữ liệu
            return newMatchesSource;
        });
    }

    public LiveData<String> getSelectedDate() {
        return selectedDateLiveData;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoadingLiveData;
    }
    // Thêm setter để Fragment có thể báo cho ViewModel khi dữ liệu đã được xử lý
    public void setIsLoading(boolean loading) {
        isLoadingLiveData.setValue(loading);
    }


    public LiveData<String> getError() {
        return errorLiveData;
    }
    public void setError(String message) {
        errorLiveData.setValue(message);
    }


    public void changeDate(int daysToAdd) {
        Calendar calendar = Calendar.getInstance();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            if (selectedDateLiveData.getValue() != null) {
                calendar.setTime(dateFormat.parse(selectedDateLiveData.getValue()));
            }
            calendar.add(Calendar.DAY_OF_YEAR, daysToAdd);
            selectedDateLiveData.setValue(dateFormat.format(calendar.getTime())); // Chỉ cần set giá trị này, matchesLiveData sẽ tự cập nhật
        } catch (Exception e) {
            e.printStackTrace();
            errorLiveData.setValue("Error changing date: " + e.getMessage());
        }
    }

    public void toggleFavoriteTeam(Team team) {
        if (team == null) return;
        LiveData<FavoriteTeamEntity> favoriteStatus = matchRepository.getFavoriteTeamById(team.getTeamId());
        favoriteStatus.observeForever(new androidx.lifecycle.Observer<FavoriteTeamEntity>() {
            @Override
            public void onChanged(FavoriteTeamEntity favoriteTeamEntity) {
                favoriteStatus.removeObserver(this);
                if (favoriteTeamEntity != null) {
                    matchRepository.removeFavoriteTeam(team.getTeamId());
                } else {
                    matchRepository.addFavoriteTeam(team);
                }
            }
        });
    }

    public LiveData<Boolean> isTeamFavorite(int teamId) {
        return Transformations.map(matchRepository.getFavoriteTeamById(teamId), entity -> entity != null);
    }
}