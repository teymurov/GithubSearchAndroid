package ru.teymurov.githubsearch.dagger.application.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
@Singleton
public class AppModule {

    private final Context mContext;

    public AppModule(final Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    Context providerApplication() {
        return mContext;
    }
}