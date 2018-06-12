package bebi.story.cuongduong.storybebi_mvp.core.registration;

import android.app.Activity;

import com.google.firebase.auth.FirebaseUser;

public class RegistrationPresenterImpl implements RegistrationContract.Presenter, RegistrationContract.onRegistrationListener{

    private RegistrationContract.View mRegisterView;
    private RegistrationInteractorImpl mRegistrationInteractor;

    public RegistrationPresenterImpl(RegistrationContract.View registerView) {
        this.mRegisterView = registerView;
        mRegistrationInteractor = new RegistrationInteractorImpl(this);
    }

    @Override
    public void register(Activity activity, String username, String password) {
        mRegistrationInteractor.performFireBaseRegistration(activity, username, password);
    }

    @Override
    public void onSuccess(FirebaseUser firebaseUser) {
        mRegisterView.onRegistrationSuccess(firebaseUser);
    }

    @Override
    public void onFailure(String message) {
        mRegisterView.onRegistrationFailure(message);
    }
}
