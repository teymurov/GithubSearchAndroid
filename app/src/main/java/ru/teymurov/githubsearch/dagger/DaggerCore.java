package ru.teymurov.githubsearch.dagger;

import android.content.Context;

import ru.teymurov.githubsearch.dagger.application.AppComponent;
import ru.teymurov.githubsearch.dagger.application.DaggerAppComponent;
import ru.teymurov.githubsearch.dagger.application.modules.AppModule;

public final class DaggerCore {

    private static AppComponent sAppComponent;

    private DaggerCore() {
    }

    private static AppComponent createComponent(final Context context) {
        return DaggerAppComponent.builder().appModule(new AppModule(context)).build();
    }

    public static void init(final Context context) {
        sAppComponent = createComponent(context);
    }

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }
}
