package bebi.story.cuongduong.storybebi_mvp.core.logout;

import com.google.firebase.auth.FirebaseAuth;

public class LogOutPresenterImpl implements LogOutContract.Presenter {

    private LogOutContract.View mLogOutView;

    public LogOutPresenterImpl(LogOutContract.View logOutView) {
        this.mLogOutView = logOutView;
    }

    @Override
    public void logOut() {
        FirebaseAuth.getInstance().signOut();
        mLogOutView.onLogOutSuccess();
    }
}
