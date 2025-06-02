package com.example.flashscore.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.flashscore.R;
import com.example.flashscore.databinding.FragmentHomeBinding;
import com.example.flashscore.model.Match;
import com.example.flashscore.ui.matchdetails.MatchDetailsActivity;
import com.example.flashscore.util.DateTimeUtils;
import com.example.flashscore.viewmodel.HomeViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class HomeFragment extends Fragment implements MatchAdapter.OnMatchClickListener {

    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;
    private MatchAdapter matchAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getActivity() instanceof AppCompatActivity) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(binding.toolbarHome);
        }

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        setupRecyclerView();
        setupDateNavigation();
        observeViewModel();
    }

    private void setupRecyclerView() {
        matchAdapter = new MatchAdapter(getContext(), this, homeViewModel, getViewLifecycleOwner());
        binding.recyclerViewMatches.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewMatches.setAdapter(matchAdapter);
    }

    private void setupDateNavigation() {
        binding.buttonPreviousDay.setOnClickListener(v -> homeViewModel.changeDate(-1));
        binding.buttonNextDay.setOnClickListener(v -> homeViewModel.changeDate(1));
    }

    private void observeViewModel() {
        homeViewModel.getSelectedDate().observe(getViewLifecycleOwner(), date -> {
            if (date != null) {
                binding.textViewCurrentDate.setText(DateTimeUtils.formatDateForDisplay(date));
                if (getActivity() instanceof AppCompatActivity && ((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
                    if (isToday(date)) {
                        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.todays_matches));
                    } else {
                        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(DateTimeUtils.formatDateForDisplay(date));
                    }
                }
                // Việc fetch dữ liệu đã được xử lý bởi Transformations.switchMap trong ViewModel
            }
        });

        homeViewModel.getIsLoading().observe(getViewLifecycleOwner(), isLoading -> {
            binding.progressBarHome.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            if(isLoading) { // Nếu đang load thì ẩn list và text no matches
                binding.recyclerViewMatches.setVisibility(View.GONE);
                binding.textViewNoMatches.setVisibility(View.GONE);
            }
        });

        homeViewModel.getError().observe(getViewLifecycleOwner(), error -> {
            if (error != null && !error.isEmpty()) {
                homeViewModel.setIsLoading(false); // Tắt loading nếu có lỗi
                binding.recyclerViewMatches.setVisibility(View.GONE);
                binding.textViewNoMatches.setText(getString(R.string.error_loading_matches_param, error));
                binding.textViewNoMatches.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
            }
        });

        homeViewModel.matchesLiveData.observe(getViewLifecycleOwner(), matches -> {
            homeViewModel.setIsLoading(false); // Dữ liệu đã về (kể cả null), tắt loading
            if (matches != null && !matches.isEmpty()) {
                matchAdapter.setMatchList(matches);
                binding.recyclerViewMatches.setVisibility(View.VISIBLE);
                binding.textViewNoMatches.setVisibility(View.GONE);
            } else if (homeViewModel.getError().getValue() == null || homeViewModel.getError().getValue().isEmpty()){ // Không có lỗi nhưng list rỗng
                matchAdapter.setMatchList(null); // Xóa list cũ
                binding.recyclerViewMatches.setVisibility(View.GONE);
                binding.textViewNoMatches.setText(R.string.no_matches_available);
                binding.textViewNoMatches.setVisibility(View.VISIBLE);
            }
            // Trường hợp có lỗi đã được xử lý bởi error observer
        });
    }

    @Override
    public void onMatchItemClicked(Match match) {
        Intent intent = new Intent(getActivity(), MatchDetailsActivity.class);
        intent.putExtra("MATCH_ID", match.getMatchId());
        startActivity(intent);
    }

    private boolean isToday(String dateYYYYMMDD) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String todayDate = sdf.format(calendar.getTime());
        return todayDate.equals(dateYYYYMMDD);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Quan trọng để tránh memory leak với ViewBinding trong Fragment
    }
}