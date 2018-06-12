package bebi.story.cuongduong.storybebi_mvp.core.logout;

public interface LogOutContract {
    interface View {
        void onLogOutSuccess();
    }

    interface Presenter {
        void logOut();
    }
}
