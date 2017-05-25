package ru.teymurov.githubsearch.mvp.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import javax.inject.Inject;

import ru.teymurov.githubsearch.dagger.DaggerCore;
import ru.teymurov.githubsearch.utils.PreferencesUtils;

public class SplashActivity extends AppCompatActivity {
    private static final int SPLASH_DURATION = 1000;

    @Inject
    PreferencesUtils mPreferencesUtils;

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerCore.getAppComponent().inject(this);

        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startNextActivity();
            }
        }, SPLASH_DURATION);
    }

    private void startNextActivity() {
        final boolean isAuthorized = !TextUtils.isEmpty(mPreferencesUtils.getToken());
        final Intent intent = new Intent(this, isAuthorized ? SearchActivity.class: AuthActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
