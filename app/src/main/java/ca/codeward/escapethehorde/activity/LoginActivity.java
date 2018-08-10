package ca.codeward.escapethehorde.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import org.json.JSONObject;

import ca.codeward.escapethehorde.R;
import ca.codeward.escapethehorde.app.AppConfig;
import ca.codeward.escapethehorde.helper.SessionManager;
import ca.codeward.escapethehorde.helper.NetworkAdapter;

public class LoginActivity  extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private SignInButton signIn;
    private Button signOut;
    private GoogleApiClient googleApiClient;
    private static final int GOOGLE_REQ_CODE = 1001;

    private ProgressDialog pDialog;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Session manager
        session = new SessionManager(getApplicationContext());

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // Initialize Variables
        signIn = (SignInButton)findViewById(R.id.login);
        signOut = (Button)findViewById(R.id.btn_signout);

        // Register Listeners
        signIn.setOnClickListener(this);
        signIn.setSize(SignInButton.SIZE_STANDARD);
        signOut.setOnClickListener(this);

        // Google SignIn Stuff
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions).build();

        // Check if user is already logged in or not
//        if (session.isLoggedIn()) {
//            // User is already logged in. Take him to main activity
//            Intent intent = new Intent(LoginActivity.this, LogoutActivity.class);
//            startActivity(intent);
//            finish();
//        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.login:
                signIn();
                break;
            case R.id.btn_signout:
                signOut();
                break;
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        updateUI(false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GOOGLE_REQ_CODE){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);
        }
    }

    private void signIn() {
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent, GOOGLE_REQ_CODE);
    }

    private void signOut() {
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                updateUI(false);
            }
        });
    }

    private void handleResult(GoogleSignInResult result) {
        if(result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();
            if(account != null)
                updateUI(true);
            else
                updateUI(false);
        }
        else {
            updateUI(false);
        }
    }

    private void updateUI(boolean loggedIn) {
        if(loggedIn) {
            signIn.setVisibility(View.GONE);
            signOut.setVisibility(View.VISIBLE);
        }
        else {
            signOut.setVisibility(View.GONE);
            signIn.setVisibility(View.VISIBLE);
        }
    }
}
