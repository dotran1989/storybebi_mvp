package bebi.story.cuongduong.storybebi_mvp.rest.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import bebi.story.cuongduong.storybebi_mvp.model.Category;

public class ApiResponse {
    @SerializedName("categories")
    private List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }
}
