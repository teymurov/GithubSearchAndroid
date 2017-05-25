package ru.teymurov.githubsearch.dagger.application;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;
import ru.teymurov.githubsearch.dagger.application.modules.AppModule;
import ru.teymurov.githubsearch.dagger.application.modules.RetrofitModule;
import ru.teymurov.githubsearch.dagger.application.modules.UtilsModule;
import ru.teymurov.githubsearch.mvp.activities.SearchActivity;
import ru.teymurov.githubsearch.mvp.activities.SplashActivity;
import ru.teymurov.githubsearch.mvp.presenters.AuthPresenter;
import ru.teymurov.githubsearch.mvp.presenters.SearchPresenter;
import ru.teymurov.githubsearch.retrofit.api.GithubApi;

@Singleton
@Component(modules = {AppModule.class, RetrofitModule.class, UtilsModule.class})
public interface AppComponent {
    Context getContext();
    GithubApi getGithubApi();
    Retrofit getRetrofit();

    void inject(SplashActivity activity);
    void inject(AuthPresenter presenter);
    void inject(SearchActivity activity);
    void inject(SearchPresenter presenter);
}