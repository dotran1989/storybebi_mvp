package bebi.story.cuongduong.storybebi_mvp.rest.service;

import java.util.ArrayList;

import bebi.story.cuongduong.storybebi_mvp.model.CategoriesItem;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("categories")
    Call<ArrayList<CategoriesItem>> getCategories();
}
