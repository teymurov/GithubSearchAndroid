package ru.teymurov.githubsearch.mvp.presenters;

import android.text.TextUtils;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.teymurov.githubsearch.dagger.DaggerCore;
import ru.teymurov.githubsearch.mvp.views.SearchView;
import ru.teymurov.githubsearch.retrofit.api.GithubApi;
import ru.teymurov.githubsearch.retrofit.errors.ApiError;
import ru.teymurov.githubsearch.retrofit.gson.Repository;
import ru.teymurov.githubsearch.retrofit.gson.SearchResult;
import ru.teymurov.githubsearch.utils.ErrorUtils;
import ru.teymurov.githubsearch.utils.PreferencesUtils;

@InjectViewState
public class SearchPresenter extends MvpPresenter<SearchView> {
    private final static int PAGE_SIZE = 50;

    @Inject
    GithubApi mGithubApi;

    @Inject
    ErrorUtils mErrorUtils;

    @Inject
    PreferencesUtils mPreferencesUtils;

    private Call<SearchResult> mSearchResultCall;

    private SearchResult mSearchResult;
    private String mLastQuery;
    private String mToken;

    public SearchPresenter() {
        DaggerCore.getAppComponent().inject(this);
        mToken = mPreferencesUtils.getToken();
    }

    private List<Repository> getRepositories() {
        return mSearchResult == null ? new ArrayList<Repository>() : mSearchResult.getRepositories();
    }

    public void search(String query) {
        if (query.equals(mLastQuery)) {
            return;
        }

        mLastQuery = query;
        if (TextUtils.isEmpty(mLastQuery)) {
            stopLoadingIfExecuted();
            onLoadingSuccess(new ArrayList<Repository>(), false);
            onLoadingFinish();
        } else {
            getViewState().showLoading();
            loadRepositories(mLastQuery, 1, false);
        }
    }

    private void stopLoadingIfExecuted() {
        if (mSearchResultCall != null && mSearchResultCall.isExecuted()) {
            mSearchResultCall.cancel();
        }
    }

    public void loadNextRepositories(int currentCount) {
        final int page = currentCount / PAGE_SIZE + 1;
        loadRepositories(mLastQuery, page, true);
    }

    private void loadRepositories(final String query, final int page, final boolean isPageLoading) {
        stopLoadingIfExecuted();

        mSearchResultCall = mGithubApi.search(mToken, query, page, PAGE_SIZE);
        mSearchResultCall.enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                if (response.isSuccessful()) {
                    mSearchResult = response.body();
                    onLoadingSuccess(getRepositories(), isPageLoading);
                } else {
                    final ApiError error = ErrorUtils.parseError(response);
                    final String errorText = mErrorUtils.getErrorMessageFromCode(response.code());
                    getViewState().failedSearch(errorText == null ? error.getMessage() : errorText);
                }

                onLoadingFinish();
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                if (!call.isCanceled()) {
                    final String errorText = mErrorUtils.getErrorMessageFromThrowable(t);
                    getViewState().failedSearch(errorText == null ? t.getMessage() : errorText);
                    onLoadingFinish();
                }
            }
        });
    }

    private void onLoadingSuccess(List<Repository> repositories, boolean isPageLoading) {
        final boolean maybeMore = repositories.size() >= PAGE_SIZE;
        if (isPageLoading) {
            getViewState().addRepositories(repositories, maybeMore);
        } else {
            getViewState().setRepositories(repositories, maybeMore);
        }
    }

    private void onLoadingFinish() {
        getViewState().hideLoading();
    }
}