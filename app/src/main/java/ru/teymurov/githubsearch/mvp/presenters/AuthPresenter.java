package ru.teymurov.githubsearch.mvp.presenters;

import android.text.TextUtils;
import android.util.Base64;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.teymurov.githubsearch.dagger.DaggerCore;
import ru.teymurov.githubsearch.mvp.views.AuthView;
import ru.teymurov.githubsearch.retrofit.api.GithubApi;
import ru.teymurov.githubsearch.retrofit.errors.ApiError;
import ru.teymurov.githubsearch.retrofit.gson.User;
import ru.teymurov.githubsearch.utils.ErrorUtils;
import ru.teymurov.githubsearch.utils.PreferencesUtils;

@InjectViewState
public class AuthPresenter extends MvpPresenter<AuthView> {

    @Inject
    GithubApi mGithubApi;

    @Inject
    PreferencesUtils mPreferencesUtils;

    public AuthPresenter() {
        DaggerCore.getAppComponent().inject(this);
    }

    public void auth(String email, String password) {
        boolean isEmailValid = isFieldValid(email);
        boolean isPasswordValid = isFieldValid(password);
        getViewState().showFormError(isEmailValid, isPasswordValid);

        if (isEmailValid && isPasswordValid) {
            getViewState().startAuth();

            final String credentials = String.format("%s:%s", email, password);
            final String token = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

            mGithubApi.auth(token).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    getViewState().finishAuth();

                    if (response.isSuccessful()) {
                        mPreferencesUtils.setToken(token);
                        getViewState().successAuth();
                    } else {
                        ApiError error = ErrorUtils.parseError(response);
                        getViewState().failedAuth(error.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    getViewState().finishAuth();
                    getViewState().failedAuth(t.getMessage());
                }
            });
        }
    }

    private boolean isFieldValid(String field) {
        return !TextUtils.isEmpty(field);
    }
}
