package bebi.story.cuongduong.storybebi_mvp.core.login;

import android.app.Activity;

import com.google.firebase.auth.FirebaseUser;

public interface LoginContract {
    interface View {
        void onLoginSuccess(FirebaseUser firebaseUser);

        void onLoginFailure(String message);
    }

    interface Presenter {
        void login(Activity activity, String username, String password);
    }

    interface Interactor {
        void performFireBaseLogin(Activity activity, String email, String password);
    }

    interface onLoginListener {
        void onSuccess(FirebaseUser firebaseUser);

        void onFailure(String message);
    }

}
