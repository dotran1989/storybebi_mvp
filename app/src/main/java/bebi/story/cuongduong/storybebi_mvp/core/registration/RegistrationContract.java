package bebi.story.cuongduong.storybebi_mvp.core.registration;

import android.app.Activity;

import com.google.firebase.auth.FirebaseUser;

public interface RegistrationContract {
    interface View {
        void onRegistrationSuccess(FirebaseUser firebaseUser);
        void onRegistrationFailure(String message);
    }

    interface Presenter {
        void register(Activity activity, String username, String password);
    }

    interface onRegistrationListener {
        void onSuccess(FirebaseUser firebaseUser);
        void onFailure(String message);
    }
}
