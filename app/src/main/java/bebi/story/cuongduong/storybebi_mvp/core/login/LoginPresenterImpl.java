package bebi.story.cuongduong.storybebi_mvp.core.login;

import android.app.Activity;

import com.google.firebase.auth.FirebaseUser;

public class LoginPresenterImpl implements LoginContract.Presenter, LoginContract.onLoginListener {

    private LoginContract.View mLoginView;
    private LoginContract.Interactor mLoginInteractor;

    public LoginPresenterImpl(LoginContract.View loginView) {
        this.mLoginView = loginView;
        mLoginInteractor = new LoginInteractorImpl(this);
    }

    @Override
    public void login(Activity activity, String username, String password) {
        mLoginInteractor.performFireBaseLogin(activity, username, password);
    }

    @Override
    public void onSuccess(FirebaseUser firebaseUser) {
        mLoginView.onLoginSuccess(firebaseUser);
    }

    @Override
    public void onFailure(String message) {
        mLoginView.onLoginFailure(message);
    }
}
