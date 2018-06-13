package bebi.story.cuongduong.storybebi_mvp.rest.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import bebi.story.cuongduong.storybebi_mvp.model.Category;

public class ApiResponse {
    @SerializedName("categories")
    private ArrayList<Category> categories;

    public ArrayList<Category> getCategories() {
        return categories;
    }
}
