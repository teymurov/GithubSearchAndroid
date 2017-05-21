package ru.teymurov.githubsearch;

import ru.teymurov.githubsearch.dagger.DaggerCore;

public class GithubSearchApplication extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerCore.init(getApplicationContext());
    }

}