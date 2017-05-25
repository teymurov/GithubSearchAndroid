package ru.teymurov.githubsearch.mvp.activities;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import ru.teymurov.githubsearch.R;
import ru.teymurov.githubsearch.databinding.ActivitySearchBinding;
import ru.teymurov.githubsearch.mvp.adapters.RepositoryAdapter;
import ru.teymurov.githubsearch.mvp.presenters.SearchPresenter;
import ru.teymurov.githubsearch.mvp.views.SearchView;
import ru.teymurov.githubsearch.retrofit.gson.Repository;

public class SearchActivity extends MvpAppCompatActivity implements SearchView, RepositoryAdapter.OnScrollToBottomListener {

    @InjectPresenter
    SearchPresenter mSearchPresenter;

    private ActivitySearchBinding mBinding;
    private RepositoryAdapter mAdapter;
    private AlertDialog mErrorDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        MenuItem searchItem = menu.findItem(R.id.search_contacts);
        android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView) searchItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        initSearchView(searchView);
        return true;
    }

    public void initSearchView(android.support.v7.widget.SearchView searchView) {
        if (searchView != null) {
            SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
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
        }
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