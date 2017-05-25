package ru.teymurov.githubsearch.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import ru.teymurov.githubsearch.retrofit.gson.Repository;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface SearchView extends MvpView {
    void hideLoading();
    void showLoading();
    void failedSearch(String message);

    @StateStrategyType(AddToEndStrategy.class)
    void addRepositories(List<Repository> repositories, boolean maybeMore);

    @StateStrategyType(AddToEndStrategy.class)
    void setRepositories(List<Repository> repositories, boolean maybeMore);
}