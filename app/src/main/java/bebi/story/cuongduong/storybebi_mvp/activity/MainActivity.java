package bebi.story.cuongduong.storybebi_mvp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
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
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, LogOutContract.View{

    private static final String TAG = MainActivity.class.getSimpleName();

    private LogOutContract.Presenter mLogOutPresenter;

    @BindView(R.id.hello)
    TextView txtHello;
    @BindView(R.id.btn_logout)
    TextView btnLogOut;

    public static void start(Context context, Parcelable parcelable) {
        Intent starter = new Intent(context, MainActivity.class);
        starter.putExtra(AppConstants.PARCELABLE_CURRENT_USER, parcelable);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        FirebaseUser currentUser = getIntent().getParcelableExtra(AppConstants.PARCELABLE_CURRENT_USER);
        Log.d(TAG, "currentUser: " + currentUser.getEmail());

        StringBuilder builder = new StringBuilder();
        builder.append(txtHello.getText().toString());
        builder.append(" ");
        builder.append(currentUser.getEmail());
        txtHello.setText(builder.toString());

        btnLogOut.setOnClickListener(this);

        mLogOutPresenter = new LogOutPresenterImpl(this);
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
