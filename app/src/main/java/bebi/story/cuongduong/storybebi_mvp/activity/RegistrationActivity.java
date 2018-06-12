package bebi.story.cuongduong.storybebi_mvp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;

import bebi.story.cuongduong.storybebi_mvp.R;
import bebi.story.cuongduong.storybebi_mvp.core.registration.RegistrationContract;
import bebi.story.cuongduong.storybebi_mvp.core.registration.RegistrationPresenterImpl;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RegistrationActivity extends AppCompatActivity implements RegistrationContract.View, View.OnClickListener {
    @BindView(R.id.email_register)
    EditText edtEmail;
    @BindView(R.id.password_register)
    EditText edtPassword;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.tv_login)
    TextView btnLogin;

    private RegistrationContract.Presenter mRegistrationPresenter;
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);

        mRegistrationPresenter = new RegistrationPresenterImpl(this);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Please wait, Adding profile to database.");

        btnRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

    }

    @Override
    public void onRegistrationSuccess(FirebaseUser firebaseUser) {
        mProgressDialog.dismiss();
        Toast.makeText(getApplicationContext(), "Successfully Registered", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRegistrationFailure(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(getApplicationContext(), "Failure Registered", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                checkRegistrationDetails();
                break;
            case R.id.tv_login:
                navigateToLoginActivity();
                break;
        }

    }

    private void navigateToLoginActivity() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    private void checkRegistrationDetails() {
        String username = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            initRegister(username, password);
        } else {
            if (TextUtils.isEmpty(username)) {
                edtEmail.setError("Please enter a valid email!");
            }
            if (TextUtils.isEmpty(password)) {
                edtPassword.setError("Please enter a password");
            }
        }

    }

    private void initRegister(String username, String password) {
        mProgressDialog.show();
        mRegistrationPresenter.register(this, username, password);
    }
}
