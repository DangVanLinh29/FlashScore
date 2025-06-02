package com.example.flashscore; // Thay bằng package name của bạn

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.flashscore.databinding.ActivityMainBinding;
import com.example.flashscore.ui.favorites.FavoritesFragment;
import com.example.flashscore.ui.home.HomeFragment;
import com.example.flashscore.ui.leagues.LeaguesFragment;
import com.example.flashscore.ui.settings.SettingsFragment;
import com.google.firebase.messaging.FirebaseMessaging;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    Toast.makeText(this, "Notification permission granted.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Notification permission denied.", Toast.LENGTH_LONG).show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Không cần EdgeToEdge và setOnApplyWindowInsetsListener nếu dùng theme NoActionBar
        // và quản lý padding bằng cách khác (ví dụ: fitSystemWindows trong layout)

        if (savedInstanceState == null) {
            loadFragment(new HomeFragment(), "HOME_FRAGMENT");
        }

        binding.navView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            String tag = "";
            int itemId = item.getItemId();

            if (itemId == R.id.navigation_home) {
                selectedFragment = new HomeFragment();
                tag = "HOME_FRAGMENT";
            } else if (itemId == R.id.navigation_leagues) {
                selectedFragment = new LeaguesFragment();
                tag = "LEAGUES_FRAGMENT";
            } else if (itemId == R.id.navigation_favorites) {
                selectedFragment = new FavoritesFragment();
                tag = "FAVORITES_FRAGMENT";
            } else if (itemId == R.id.navigation_settings) {
                selectedFragment = new SettingsFragment();
                tag = "SETTINGS_FRAGMENT";
            }

            if (selectedFragment != null) {
                return loadFragment(selectedFragment, tag);
            }
            return false;
        });

        askNotificationPermission();
        retrieveAndLogFCMToken();
    }

    private boolean loadFragment(Fragment fragment, String tag) {
        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            Fragment existingFragment = getSupportFragmentManager().findFragmentByTag(tag);

            // Animation (tùy chọn)
            // transaction.setCustomAnimations(
            //     R.anim.slide_in_right, R.anim.slide_out_left,
            //     R.anim.slide_in_left, R.anim.slide_out_right
            // );

            if (existingFragment != null && existingFragment.isVisible()) {
                return true; // Fragment đã hiển thị, không cần làm gì
            }
            transaction.replace(R.id.nav_host_fragment_activity_main, fragment, tag);
            // Không addToBackStack để BottomNav hoạt động đúng chuẩn (không tạo stack cho mỗi tab)
            transaction.commit();
            return true;
        }
        return false;
    }

    private void askNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) !=
                    PackageManager.PERMISSION_GRANTED) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                    requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
                } else {
                    requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
                }
            }
        }
    }

    private void retrieveAndLogFCMToken() {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.w("FCM_TOKEN", "Fetching FCM registration token failed", task.getException());
                        return;
                    }
                    String token = task.getResult();
                    Log.d("FCM_TOKEN", "Current token: " + token);
                    // Gửi token lên server của bạn nếu cần
                });
    }
}