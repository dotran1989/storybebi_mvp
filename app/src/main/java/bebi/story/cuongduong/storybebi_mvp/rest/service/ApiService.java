package bebi.story.cuongduong.storybebi_mvp.rest.service;

import java.util.List;

import bebi.story.cuongduong.storybebi_mvp.model.Category;
import bebi.story.cuongduong.storybebi_mvp.rest.model.ApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/categories")
    Call<ApiResponse> getCategories();
}
