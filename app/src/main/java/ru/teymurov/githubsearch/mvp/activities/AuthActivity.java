package ru.teymurov.githubsearch.mvp.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import ru.teymurov.githubsearch.R;
import ru.teymurov.githubsearch.databinding.ActivityAuthBinding;
import ru.teymurov.githubsearch.mvp.presenters.AuthPresenter;
import ru.teymurov.githubsearch.mvp.views.AuthView;

public class AuthActivity extends MvpAppCompatActivity implements AuthView {

    @InjectPresenter
    AuthPresenter mAuthPresenter;

    private AlertDialog mErrorDialog;
    private ActivityAuthBinding mAuthBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuthBinding = DataBindingUtil.setContentView(this, R.layout.activity_auth);
    }

    @Override
    public void startAuth() {
        mAuthBinding.authForm.setVisibility(View.GONE);
        mAuthBinding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void finishAuth() {
        mAuthBinding.authForm.setVisibility(View.VISIBLE);
        mAuthBinding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void successAuth() {
        startActivity(new Intent(this, SearchActivity.class));
    }

    @Override
    public void showFormError(boolean isEmailValid, boolean isPasswordValid) {
        mAuthBinding.emailInput.setError(isEmailValid ? null : getString(R.string.empty_field));
        mAuthBinding.passwordInput.setError(isPasswordValid ? null : getString(R.string.empty_field));
    }

    @Override
    public void failedAuth(String message) {
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

    public void onLoginClick(View view) {
        mAuthPresenter.auth(
                mAuthBinding.emailText.getText().toString(),
                mAuthBinding.passwordText.getText().toString()
        );
    }

    public void onSkipClick(View view) {
        successAuth();
    }

    @Override
    protected void onDestroy() {
        if (mErrorDialog != null && mErrorDialog.isShowing()) {
            mErrorDialog.setOnCancelListener(null);
            mErrorDialog.dismiss();
        }
        super.onDestroy();
    }
}