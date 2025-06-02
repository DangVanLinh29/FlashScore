package com.example.flashscore.ui.leaguedetails;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
// ... các import khác ...
import androidx.appcompat.app.AppCompatActivity;
// ... các import khác ...
import com.example.flashscore.databinding.ActivityLeagueDetailsBinding;
import com.example.flashscore.model.League; // Giả sử bạn sẽ cần nó sau này
import com.example.flashscore.viewmodel.LeagueDetailViewModel;

public class LeagueDetailsActivity extends AppCompatActivity implements StandingsAdapter.OnTeamClickListener {

    // KHAI BÁO CÁC HẰNG SỐ Ở ĐÂY
    public static final String EXTRA_LEAGUE_ID = "LEAGUE_ID";
    public static final String EXTRA_LEAGUE_NAME = "LEAGUE_NAME";
    public static final String EXTRA_LEAGUE_LOGO_URL = "LEAGUE_LOGO_URL";
    public static final String EXTRA_LEAGUE_SEASON = "LEAGUE_SEASON";

    private ActivityLeagueDetailsBinding binding;
    private LeagueDetailViewModel leagueDetailViewModel;
    private StandingsAdapter standingsAdapter;
    private int leagueId;
    // ... các biến khác ...

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // ... setup binding ...
        // setContentView(binding.getRoot());

        // ... toolbar setup ...

        Intent intent = getIntent();
        leagueId = intent.getIntExtra(EXTRA_LEAGUE_ID, -1); // Sử dụng hằng số
        // ... lấy các extra khác ...

        if (leagueId == -1) {
            // ... xử lý lỗi ...
            finish();
            return;
        }
        // ... phần còn lại của onCreate ...
    }

    // ... các phương thức khác của Activity ...

    @Override
    public void onTeamClick(int teamId, String teamName, String teamLogoUrl) {
        Intent intent = new Intent(this, com.example.flashscore.ui.teamdetails.TeamDetailsActivity.class);
        intent.putExtra(com.example.flashscore.ui.teamdetails.TeamDetailsActivity.EXTRA_TEAM_ID, teamId);
        intent.putExtra(com.example.flashscore.ui.teamdetails.TeamDetailsActivity.EXTRA_TEAM_NAME, teamName);
        intent.putExtra(com.example.flashscore.ui.teamdetails.TeamDetailsActivity.EXTRA_TEAM_LOGO_URL, teamLogoUrl);
        startActivity(intent);
    }
}