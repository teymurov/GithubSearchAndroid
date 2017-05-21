package ru.teymurov.githubsearch.mvp.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

    private ActivityAuthBinding mActivityAuthBinding;
    private AlertDialog mErrorDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityAuthBinding = DataBindingUtil.setContentView(this, R.layout.activity_auth);
    }

    @Override
    public void startAuth() {
        mActivityAuthBinding.authForm.setVisibility(View.GONE);
        mActivityAuthBinding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void finishAuth() {
        mActivityAuthBinding.authForm.setVisibility(View.VISIBLE);
        mActivityAuthBinding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void successAuth() {

    }

    @Override
    public void showFormError(boolean isEmailError, boolean isPasswordError) {
        mActivityAuthBinding.emailInput.setError(isEmailError ? getString(R.string.empty_field) : null);
        mActivityAuthBinding.passwordInput.setError(isPasswordError ? getString(R.string.empty_field) : null);
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
                mActivityAuthBinding.emailText.getText().toString(),
                mActivityAuthBinding.passwordText.getText().toString()
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
