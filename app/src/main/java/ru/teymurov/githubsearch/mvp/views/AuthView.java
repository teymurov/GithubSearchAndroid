package ru.teymurov.githubsearch.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface AuthView extends MvpView {
    void startAuth();
    void finishAuth();
    void successAuth();
    void failedAuth(String message);
    void showFormError(boolean isEmailError, boolean isPasswordError);
}
