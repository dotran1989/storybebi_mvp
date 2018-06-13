package bebi.story.cuongduong.storybebi_mvp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;

import bebi.story.cuongduong.storybebi_mvp.R;
import bebi.story.cuongduong.storybebi_mvp.constants.AppConstants;
import bebi.story.cuongduong.storybebi_mvp.core.logout.LogOutContract;
import bebi.story.cuongduong.storybebi_mvp.core.logout.LogOutPresenterImpl;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, LogOutContract.View{

    private static final String TAG = MainActivity.class.getSimpleName();

    private LogOutContract.Presenter mLogOutPresenter;

    private TextView txtHello;
    private TextView btnLogOut;
    private NavigationView naviView;
    private View header;
    private Toolbar toolbar;

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
    }

    private void initViews() {
        naviView = findViewById(R.id.nav_view);
        header = naviView.getHeaderView(0);
        txtHello = header.findViewById(R.id.hello);
        btnLogOut = header.findViewById(R.id.btn_logout);
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
}
