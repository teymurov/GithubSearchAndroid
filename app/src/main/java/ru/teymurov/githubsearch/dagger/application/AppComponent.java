package ru.teymurov.githubsearch.dagger.application;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import ru.teymurov.githubsearch.dagger.application.modules.AppModule;
import ru.teymurov.githubsearch.dagger.application.modules.RetrofitModule;
import ru.teymurov.githubsearch.dagger.application.modules.UtilsModule;
import ru.teymurov.githubsearch.mvp.activities.SplashActivity;
import ru.teymurov.githubsearch.mvp.presenters.AuthPresenter;
import ru.teymurov.githubsearch.mvp.presenters.SearchPresenter;
import ru.teymurov.githubsearch.retrofit.api.GithubApi;

@Singleton
@Component(modules = {AppModule.class, RetrofitModule.class, UtilsModule.class})
public interface AppComponent {
    Context getContext();
    GithubApi getGithubApi();

    void inject(SplashActivity activity);
    void inject(AuthPresenter presenter);
    void inject(SearchPresenter presenter);
}