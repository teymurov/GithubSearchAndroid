package ru.teymurov.githubsearch.dagger.application.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.teymurov.githubsearch.utils.PreferencesUtils;

@Module
@Singleton
public class UtilsModule {
    @Provides
    @Singleton
    PreferencesUtils providerPreferencesutils(Context context) {
        return new PreferencesUtils(context);
    }
}