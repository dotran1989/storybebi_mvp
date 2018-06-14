package bebi.story.cuongduong.storybebi_mvp.model;

import java.util.List;
//import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

//@Generated("com.robohorse.robopojogenerator")
public class Response{

	@SerializedName("categories")
	private List<CategoriesItem> categories;

	public void setCategories(List<CategoriesItem> categories){
		this.categories = categories;
	}

	public List<CategoriesItem> getCategories(){
		return categories;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"categories = '" + categories + '\'' + 
			"}";
		}
}