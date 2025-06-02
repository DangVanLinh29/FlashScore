package com.example.flashscore.ui.leaguedetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flashscore.R;
import com.example.flashscore.databinding.ItemStandingLayoutBinding;
import com.example.flashscore.model.StandingItem;

import java.util.ArrayList;
import java.util.List;

public class StandingsAdapter extends RecyclerView.Adapter<StandingsAdapter.StandingViewHolder> {

    private List<StandingItem> standingItemList;
    private final Context context;
    private final OnTeamClickListener listener; // Listener

    // Interface được định nghĩa ở đây
    public interface OnTeamClickListener {
        void onTeamClick(int teamId, String teamName, String teamLogoUrl);
    }

    public StandingsAdapter(Context context, OnTeamClickListener listener) {
        this.context = context;
        this.standingItemList = new ArrayList<>();
        this.listener = listener;
    }

    public void setStandingItemList(List<StandingItem> standingItems) {
        this.standingItemList.clear();
        if (standingItems != null) {
            this.standingItemList.addAll(standingItems);
        }
        notifyDataSetChanged(); // Nên dùng DiffUtil cho hiệu suất tốt hơn
    }

    @NonNull
    @Override
    public StandingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Đảm bảo file layout item_standing_layout.xml tồn tại và đúng tên
        ItemStandingLayoutBinding binding = ItemStandingLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new StandingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull StandingViewHolder holder, int position) {
        StandingItem standingItem = standingItemList.get(position);
        holder.bind(standingItem, listener, context);
    }

    @Override
    public int getItemCount() {
        return standingItemList.size();
    }

    static class StandingViewHolder extends RecyclerView.ViewHolder {
        private final ItemStandingLayoutBinding binding;

        public StandingViewHolder(ItemStandingLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(final StandingItem standingItem, final OnTeamClickListener listener, Context context) {
            binding.textViewRank.setText(String.valueOf(standingItem.getRank()));
            if (standingItem.getTeam() != null) {
                binding.textViewTeamNameStanding.setText(standingItem.getTeam().getName());
                Glide.with(context)
                        .load(standingItem.getTeam().getLogoUrl())
                        .placeholder(R.drawable.ic_placeholder_team_logo) // Đảm bảo drawable này tồn tại
                        .error(R.drawable.ic_placeholder_team_logo)
                        .into(binding.imageViewTeamLogoStanding);

                itemView.setOnClickListener(v -> {
                    if (listener != null) {
                        // Truyền đủ thông tin cho listener
                        listener.onTeamClick(standingItem.getTeam().getTeamId(), standingItem.getTeam().getName(), standingItem.getTeam().getLogoUrl());
                    }
                });
            } else {
                binding.textViewTeamNameStanding.setText("N/A");
                binding.imageViewTeamLogoStanding.setImageResource(R.drawable.ic_placeholder_team_logo);
                itemView.setOnClickListener(null);
            }

            binding.textViewPlayed.setText(String.valueOf(standingItem.getPlayed()));
            binding.textViewGoalDifference.setText(String.valueOf(standingItem.getGoalDifference()));
            binding.textViewPoints.setText(String.valueOf(standingItem.getPoints()));
        }
    }
}