package bebi.story.cuongduong.storybebi_mvp.app;

import android.app.Application;

import bebi.story.cuongduong.storybebi_mvp.rest.RestClient;

public class App extends Application {

    private static RestClient restClient;

    @Override
    public void onCreate() {
        super.onCreate();

        restClient = new RestClient();
    }

    public static RestClient getRestClient() {
        return restClient;
    }
}
