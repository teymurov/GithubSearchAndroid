package ru.teymurov.githubsearch.mvp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import javax.inject.Inject;

import ru.teymurov.githubsearch.R;
import ru.teymurov.githubsearch.dagger.DaggerCore;
import ru.teymurov.githubsearch.utils.PreferencesUtils;

public class SplashActivity extends AppCompatActivity {

    @Inject
    PreferencesUtils mPreferencesUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        DaggerCore.getAppComponent().inject(this);

        final boolean isAuthorized = !TextUtils.isEmpty(mPreferencesUtils.getToken());
        final Intent intent = new Intent(this, isAuthorized ? SearchActivity.class: AuthActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
