package bebi.story.cuongduong.storybebi_mvp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import bebi.story.cuongduong.storybebi_mvp.R;
import bebi.story.cuongduong.storybebi_mvp.app.App;
import bebi.story.cuongduong.storybebi_mvp.constants.AppConstants;
import bebi.story.cuongduong.storybebi_mvp.core.logout.LogOutContract;
import bebi.story.cuongduong.storybebi_mvp.core.logout.LogOutPresenterImpl;
import bebi.story.cuongduong.storybebi_mvp.model.CategoriesItem;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, LogOutContract.View, NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private LogOutContract.Presenter mLogOutPresenter;

    private TextView txtHello;
    private TextView btnLogOut;
    private NavigationView naviView;
    private View header;
    private Toolbar toolbar;

    ArrayList<CategoriesItem> categories;

    public static void start(Context context, Parcelable parcelable) {
        Intent starter = new Intent(context, MainActivity.class);
        starter.putExtra(AppConstants.PARCELABLE_CURRENT_USER, parcelable);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setActionBar();
        sayHelloCurrentUser();

        btnLogOut.setOnClickListener(this);
        mLogOutPresenter = new LogOutPresenterImpl(this);

        getListCategories();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_logout:
                mLogOutPresenter.logOut();
                break;
        }
    }

    @Override
    public void onLogOutSuccess() {
        Toast.makeText(MainActivity.this, "Log out", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        return false;
    }

    private void initViews() {
        naviView = findViewById(R.id.nav_view);
        header = naviView.getHeaderView(0);
        txtHello = header.findViewById(R.id.hello);
        btnLogOut = header.findViewById(R.id.btn_logout);

        naviView.setNavigationItemSelectedListener(this);
    }

    private void setActionBar() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toogle);
        toogle.syncState();
    }

    private void sayHelloCurrentUser() {
        FirebaseUser currentUser = getIntent().getParcelableExtra(AppConstants.PARCELABLE_CURRENT_USER);
        Log.d(TAG, "currentUser: " + currentUser.getEmail());

        StringBuilder builder = new StringBuilder();
        builder.append(txtHello.getText().toString());
        builder.append(" ");
        builder.append(currentUser.getEmail());
        txtHello.setText(builder.toString());
    }

    private void getListCategories() {
        Call<ArrayList<CategoriesItem>> call = App.getRestClient().getApiService().getCategories();
        call.enqueue(new Callback<ArrayList<CategoriesItem>>() {
                         @Override
                         public void onResponse(Call<ArrayList<CategoriesItem>> call, retrofit2.Response<ArrayList<CategoriesItem>> response) {
                             Log.d(TAG, "response code: " + response.code());
                             if (response.isSuccessful()) {
                                 categories = response.body();
                                 Log.d(TAG, "size: " + categories.size());

                                 addMenuItemInNavMenuDrawer(categories);
                             }
                         }

                         @Override
                         public void onFailure(Call<ArrayList<CategoriesItem>> call, Throwable t) {
                             Log.d(TAG, "onFailure: " + t.getMessage());
                         }
                     }
        );
    }

    // add menu item
    private void addMenuItemInNavMenuDrawer(ArrayList<CategoriesItem> categories) {
        Menu menu = naviView.getMenu();
        Menu subMenu = menu.addSubMenu("Story BeBi");

        for (int i = 0; i < categories.size(); i++) {
            subMenu.add(categories.get(i).getName());
        }

        naviView.invalidate();
    }
}
