package bebi.story.cuongduong.storybebi_mvp.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import bebi.story.cuongduong.storybebi_mvp.constants.AppConstants;
import bebi.story.cuongduong.storybebi_mvp.rest.service.ApiService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    private ApiService apiService;

    public RestClient() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .setDateFormat(AppConstants.DATE_FORMAT)
                .create();

        // add logging into retrofit 2.0
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.interceptors().add(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public ApiService getApiService() {
        return apiService;
    }
}
