package bebi.story.cuongduong.storybebi_mvp.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import bebi.story.cuongduong.storybebi_mvp.constants.AppConstants;
import bebi.story.cuongduong.storybebi_mvp.rest.service.ApiService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    private ApiService apiService;

    public RestClient() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public ApiService getApiService() {
        return apiService;
    }
}
