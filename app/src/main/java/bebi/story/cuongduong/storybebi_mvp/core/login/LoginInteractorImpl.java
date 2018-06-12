package bebi.story.cuongduong.storybebi_mvp.core.login;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginInteractorImpl implements LoginContract.Interactor {

    private LoginContract.onLoginListener mLoginListener;

    public LoginInteractorImpl(LoginContract.onLoginListener loginListener) {
        this.mLoginListener = loginListener;
    }

    @Override
    public void performFireBaseLogin(Activity activity, String email, String password) {
        FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            mLoginListener.onFailure(task.getException().getMessage());
                        } else {
                            mLoginListener.onSuccess(task.getResult().getUser());
                        }
                    }
                });
    }
}
