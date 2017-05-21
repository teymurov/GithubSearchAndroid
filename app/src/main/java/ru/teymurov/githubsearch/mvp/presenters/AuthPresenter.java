package ru.teymurov.githubsearch.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import ru.teymurov.githubsearch.dagger.DaggerCore;
import ru.teymurov.githubsearch.mvp.views.AuthView;
import ru.teymurov.githubsearch.retrofit.api.GithubApi;

@InjectViewState
public class AuthPresenter extends MvpPresenter<AuthView> {

    @Inject
    GithubApi mGithubApi;

    public AuthPresenter() {
        DaggerCore.getAppComponent().inject(this);
    }


    public void auth(String login, String password) {

    }
}
