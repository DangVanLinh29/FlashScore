package com.example.flashscore;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

// import timber.log.Timber; // Nếu bạn muốn dùng Timber cho logging

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // if (BuildConfig.DEBUG) {
        //     Timber.plant(new Timber.DebugTree());
        // }

        createNotificationChannels();
    }

    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Channel cho thông báo chung (đã định nghĩa trong MyFirebaseMessagingService)
            NotificationChannel channel1 = new NotificationChannel(
                    "flashscore_notifications", // ID này phải khớp với ID trong MyFirebaseMessagingService
                    "FlashScore Notifications",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("Channel for general app notifications and match updates.");

            // (Tùy chọn) Channel cho các thông báo mặc định từ FCM khi app ở background
            NotificationChannel channelDefault = new NotificationChannel(
                    getString(R.string.default_notification_channel_id), // ID từ strings.xml
                    getString(R.string.fcm_fallback_notification_channel_label),
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channelDefault.setDescription("Default channel for Firebase Cloud Messaging.");


            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(channel1);
                manager.createNotificationChannel(channelDefault);
            }
        }
    }
}