package ru.teymurov.githubsearch.mvp.activities;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import javax.inject.Inject;

import ru.teymurov.githubsearch.R;
import ru.teymurov.githubsearch.dagger.DaggerCore;
import ru.teymurov.githubsearch.databinding.ActivitySearchBinding;
import ru.teymurov.githubsearch.mvp.adapters.RepositoryAdapter;
import ru.teymurov.githubsearch.mvp.presenters.SearchPresenter;
import ru.teymurov.githubsearch.mvp.views.SearchView;
import ru.teymurov.githubsearch.retrofit.gson.Repository;
import ru.teymurov.githubsearch.utils.PreferencesUtils;

public class SearchActivity extends MvpAppCompatActivity implements SearchView, RepositoryAdapter.OnScrollToBottomListener {
    private final static String TAG_SAVE_QUERY = "tag_save_query";

    @InjectPresenter
    SearchPresenter mSearchPresenter;

    @Inject
    PreferencesUtils mPreferencesUtils;

    private ActivitySearchBinding mBinding;
    private RepositoryAdapter mAdapter;
    private AlertDialog mErrorDialog;

    private String mSaveQuery;
    private android.support.v7.widget.SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerCore.getAppComponent().inject(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_search);

        setSupportActionBar(mBinding.toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.app_name);
            actionBar.setDisplayShowHomeEnabled(false);
        }

        mAdapter = new RepositoryAdapter(this);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBinding.recyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem itemSearch = menu.findItem(R.id.search_contacts);
        initSearchView(itemSearch);

        final boolean isAuthorized = !TextUtils.isEmpty(mPreferencesUtils.getToken());
        MenuItem itemLogin = menu.findItem(R.id.login);
        MenuItem itemLogout = menu.findItem(R.id.logout);
        itemLogin.setVisible(!isAuthorized);
        itemLogout.setVisible(isAuthorized);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.login:
                startAuth();
                return true;

            case R.id.logout:
                mPreferencesUtils.setToken(null);
                startAuth();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void initSearchView(MenuItem searchItem) {
        mSearchView = (android.support.v7.widget.SearchView) searchItem.getActionView();

        if (mSearchView != null) {
            mSearchView.setSaveEnabled(true);
            mSearchView.setMaxWidth(Integer.MAX_VALUE);
            SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

            if (!TextUtils.isEmpty(mSaveQuery)) {
                searchItem.expandActionView();
                mSearchView.setQuery(mSaveQuery, true);
            }

            mSearchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    mSearchPresenter.search(query);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    mSearchPresenter.search(newText);
                    return true;
                }
            });

            //fix hide search button after rotation
            MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener() {
                @Override
                public boolean onMenuItemActionCollapse(MenuItem item) {
                    mSaveQuery = null;
                    invalidateOptionsMenu();
                    return true;
                }

                @Override
                public boolean onMenuItemActionExpand(MenuItem item) {
                    return true;
                }
            });
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(TAG_SAVE_QUERY, mSearchView.getQuery().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mSaveQuery = savedInstanceState.getString(TAG_SAVE_QUERY, null);
    }

    private void startAuth() {
        final Intent intent = new Intent(this, AuthActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void hideLoading() {
        setLoading(false);
    }

    @Override
    public void showLoading() {
        setLoading(true);
    }

    private void setLoading(boolean loading) {
        mBinding.recyclerView.setVisibility(loading ? View.GONE : View.VISIBLE);
        mBinding.progressBar.setVisibility(loading ? View.VISIBLE : View.GONE);
    }

    @Override
    public void failedSearch(String message) {
        mErrorDialog = new AlertDialog.Builder(this)
                .setTitle(R.string.app_name)
                .setMessage(message)
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        if (mErrorDialog != null && mErrorDialog.isShowing()) {
                            mErrorDialog.cancel();
                        }
                    }
                })
                .show();
    }

    @Override
    public void addRepositories(List<Repository> repositories, boolean maybeMore) {
        mAdapter.addRepositories(repositories, maybeMore);
    }

    @Override
    public void setRepositories(List<Repository> repositories, boolean maybeMore) {
        mAdapter.setRepositories(repositories, maybeMore);
    }

    @Override
    protected void onDestroy() {
        if (mErrorDialog != null && mErrorDialog.isShowing()) {
            mErrorDialog.setOnCancelListener(null);
            mErrorDialog.dismiss();
        }
        super.onDestroy();
    }

    @Override
    public void onScrollToBottom() {
        mSearchPresenter.loadNextRepositories(mAdapter.getRepositoriesCount());
    }
}