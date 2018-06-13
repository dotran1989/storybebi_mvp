package bebi.story.cuongduong.storybebi_mvp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;

import bebi.story.cuongduong.storybebi_mvp.R;
import bebi.story.cuongduong.storybebi_mvp.core.login.LoginContract;
import bebi.story.cuongduong.storybebi_mvp.core.login.LoginPresenterImpl;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginContract.View, View.OnClickListener {
    @BindView(R.id.email_login)
    EditText edtEmail;
    @BindView(R.id.password_login)
    EditText edtPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_register)
    TextView btnRegister;

    private LoginContract.Presenter mLoginPresenter;
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mLoginPresenter = new LoginPresenterImpl(this);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Please wait, on logging to app...");

        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onLoginSuccess(FirebaseUser firebaseUser) {
        mProgressDialog.dismiss();
        Toast.makeText(getApplicationContext(), "Successfully Login", Toast.LENGTH_SHORT).show();
        MainActivity.start(getApplicationContext(), firebaseUser);
    }

    @Override
    public void onLoginFailure(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(getApplicationContext(), "Failure Login", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                checkLoginDetails();
                break;
            case R.id.tv_register:
                navigateToRegistrationActivity();
                break;
        }
    }

    private void navigateToRegistrationActivity() {
        Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
        startActivity(intent);
    }

    private void checkLoginDetails() {
        String username = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            initLogin(username, password);
        } else {
            if (TextUtils.isEmpty(username)) {
                edtEmail.setError("Please enter a valid email!");
            }
            if (TextUtils.isEmpty(password)) {
                edtPassword.setError("Please enter a password");
            }
        }
    }

    private void initLogin(String username, String password) {
        mProgressDialog.show();
        mLoginPresenter.login(this, username, password);
    }
}
