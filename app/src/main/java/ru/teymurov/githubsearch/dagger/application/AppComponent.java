package ru.teymurov.githubsearch.dagger.application;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import ru.teymurov.githubsearch.dagger.application.modules.AppModule;
import ru.teymurov.githubsearch.dagger.application.modules.RetrofitModule;
import ru.teymurov.githubsearch.retrofit.api.GithubApi;

@Singleton
@Component(modules = {AppModule.class, RetrofitModule.class})
public interface AppComponent {
    Context getContext();
    GithubApi getGithubApi();
}