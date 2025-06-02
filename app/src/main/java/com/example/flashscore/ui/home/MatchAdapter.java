package com.example.flashscore.ui.home; // Hoặc com.example.flashscoreapp.ui.home

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.flashscore.R; //
import com.example.flashscore.databinding.ItemMatchLayoutBinding; //
import com.example.flashscore.model.Match; //
import com.example.flashscore.ui.leaguedetails.LeagueDetailsActivity; //
import com.example.flashscore.ui.matchdetails.MatchDetailsActivity; //
import com.example.flashscore.util.DateTimeUtils; //
import com.example.flashscore.viewmodel.HomeViewModel; //

import java.util.ArrayList;
import java.util.List;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MatchViewHolder> {

    private List<Match> matchList;
    private final Context context;
    private final OnMatchClickListener listener;
    private final HomeViewModel viewModel;
    private final LifecycleOwner lifecycleOwner;

    public interface OnMatchClickListener {
        void onMatchItemClicked(Match match); // Đổi tên để rõ ràng hơn
    }

    public MatchAdapter(Context context, OnMatchClickListener listener, HomeViewModel viewModel, LifecycleOwner lifecycleOwner) {
        this.context = context;
        this.matchList = new ArrayList<>();
        this.listener = listener;
        this.viewModel = viewModel;
        this.lifecycleOwner = lifecycleOwner;
    }

    public void setMatchList(List<Match> matchList) {
        this.matchList.clear();
        if (matchList != null) {
            this.matchList.addAll(matchList);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMatchLayoutBinding binding = ItemMatchLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MatchViewHolder(binding, viewModel, lifecycleOwner);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchViewHolder holder, int position) {
        Match match = matchList.get(position);
        holder.bind(match, listener);
    }

    @Override
    public int getItemCount() {
        return matchList.size();
    }

    static class MatchViewHolder extends RecyclerView.ViewHolder {
        private final ItemMatchLayoutBinding binding;
        private final HomeViewModel viewModel;
        private final LifecycleOwner lifecycleOwner;

        public MatchViewHolder(ItemMatchLayoutBinding binding, HomeViewModel viewModel, LifecycleOwner lifecycleOwner) {
            super(binding.getRoot());
            this.binding = binding;
            this.viewModel = viewModel;
            this.lifecycleOwner = lifecycleOwner;
        }

        public void bind(final Match match, final OnMatchClickListener listener) {
            // League Name
            String leagueInfo = match.getLeagueName();
            if (!TextUtils.isEmpty(match.getRound())) {
                leagueInfo += (" - " + match.getRound());
            }
            binding.textViewLeagueName.setText(leagueInfo);
            binding.textViewLeagueName.setOnClickListener(v -> {
                if (match.getLeagueId() > 0 && match.getLeagueName() != null) {
                    Intent intent = new Intent(itemView.getContext(), LeagueDetailsActivity.class);
                    intent.putExtra(LeagueDetailsActivity.EXTRA_LEAGUE_ID, match.getLeagueId());
                    intent.putExtra(LeagueDetailsActivity.EXTRA_LEAGUE_NAME, match.getLeagueName());
                    // Lấy logo giải đấu từ model League nếu có, hoặc để trống
                    // intent.putExtra(LeagueDetailsActivity.EXTRA_LEAGUE_LOGO_URL, match.getLeagueLogoUrl());
                    itemView.getContext().startActivity(intent);
                } else {
                    Toast.makeText(itemView.getContext(), "League info not available.", Toast.LENGTH_SHORT).show();
                }
            });

            // Match Time/Status
            String status = match.getStatus();
            String timeToDisplay = status; // Default
            binding.textViewMatchTime.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.black)); // Màu mặc định

            if ("NS".equalsIgnoreCase(status)) { // Not Started
                timeToDisplay = DateTimeUtils.formatTime(match.getMatchDateTimeUtc());
            } else if ("FT".equalsIgnoreCase(status) || "AET".equalsIgnoreCase(status) || "PEN".equalsIgnoreCase(status)) {
                timeToDisplay = status; // FT, AET, PEN
            } else if (match.getMinute() != null && match.getMinute() > 0) { // Live or HT with minute
                timeToDisplay = match.getMinute() + "'";
                if ("LIVE".equalsIgnoreCase(status) || (!"HT".equalsIgnoreCase(status) && !"FT".equalsIgnoreCase(status)) ) {
                    binding.textViewMatchTime.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.live_match_color));
                }
            } else if ("HT".equalsIgnoreCase(status)){
                timeToDisplay = "HT";
            }
            binding.textViewMatchTime.setText(timeToDisplay);


            // Home Team
            if (match.getHomeTeam() != null) {
                binding.textViewHomeTeamName.setText(match.getHomeTeam().getName());
                Glide.with(itemView.getContext())
                        .load(match.getHomeTeam().getLogoUrl())
                        .placeholder(R.drawable.ic_placeholder_team_logo)
                        .error(R.drawable.ic_placeholder_team_logo)
                        .into(binding.imageViewHomeLogo);
            } else {
                binding.textViewHomeTeamName.setText("N/A");
                binding.imageViewHomeLogo.setImageResource(R.drawable.ic_placeholder_team_logo);
            }
            binding.textViewHomeScore.setText(String.valueOf(match.getHomeScore()));

            // Away Team
            if (match.getAwayTeam() != null) {
                binding.textViewAwayTeamName.setText(match.getAwayTeam().getName());
                Glide.with(itemView.getContext())
                        .load(match.getAwayTeam().getLogoUrl())
                        .placeholder(R.drawable.ic_placeholder_team_logo)
                        .error(R.drawable.ic_placeholder_team_logo)
                        .into(binding.imageViewAwayLogo);
            } else {
                binding.textViewAwayTeamName.setText("N/A");
                binding.imageViewAwayLogo.setImageResource(R.drawable.ic_placeholder_team_logo);
            }
            binding.textViewAwayScore.setText(String.valueOf(match.getAwayScore()));

            // Favorite Button (ví dụ, theo dõi đội nhà)
            if (match.getHomeTeam() != null) {
                viewModel.isTeamFavorite(match.getHomeTeam().getTeamId()).observe(lifecycleOwner, isFavorite -> {
                    if (isFavorite) {
                        binding.imageViewFavorite.setImageResource(R.drawable.ic_star_filled);
                    } else {
                        binding.imageViewFavorite.setImageResource(R.drawable.ic_star_border);
                    }
                });
                binding.imageViewFavorite.setOnClickListener(v -> viewModel.toggleFavoriteTeam(match.getHomeTeam()));
                binding.imageViewFavorite.setVisibility(android.view.View.VISIBLE);
            } else {
                binding.imageViewFavorite.setVisibility(android.view.View.GONE);
            }


            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onMatchItemClicked(match);
                }
            });
        }
    }
}