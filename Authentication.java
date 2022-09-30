package com.nogravity.learnit;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

public class Authentication extends AppCompatActivity {

    private static final String TAG = "report";
    ImageView passwordVisibility,l_visibilityPassword;
    ImageView google,meta;
    ImageView profile;

    Boolean showingPassword = false;
    Boolean l_showingPassword = false;

    EditText s_email,s_password;
    EditText l_email,l_password;

    Button s_submitButton,l_submitButton;

    TextView l_text,s_text;
    TextView SignUp,LogIn;

    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference reference;
    GoogleSignInClient mGoogleSignInClient;
    CallbackManager callbackManager;

    final static int RC_SIGN_IN = 100;
    private boolean LOGIN_META = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("LearnIt");

        setContentView(R.layout.activity_authentication);

       Objects.requireNonNull(getSupportActionBar()).hide();

        Binding();
        OnClicks();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }


    //onActivity Throw Result
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(this, "SignIn Successful", Toast.LENGTH_SHORT).show();
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }

        if(LOGIN_META){
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }


    }



    //udf
    void OnClicks(){
        passwordVisibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(showingPassword){
                    passwordVisibility.setImageResource(R.drawable.show_password_icon);
                    showingPassword = !showingPassword;
                    s_password.setTransformationMethod(new PasswordTransformationMethod());
                    s_password.setSelection(s_password.getText().length());


                }else {
                    passwordVisibility.setImageResource(R.drawable.hide_password_icon);
                    showingPassword = !showingPassword;
                    s_password.setTransformationMethod(new HideReturnsTransformationMethod());
                    s_password.setSelection(s_password.getText().length());


                }
            }
        });

        l_visibilityPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(l_showingPassword){
                    l_visibilityPassword.setImageResource(R.drawable.show_password_icon);
                    l_showingPassword = !l_showingPassword;
                    l_password.setTransformationMethod(new PasswordTransformationMethod());
                    l_password.setSelection(l_password.getText().length());


                }else {
                    l_visibilityPassword.setImageResource(R.drawable.hide_password_icon);
                    l_showingPassword = !l_showingPassword;
                    l_password.setTransformationMethod(new HideReturnsTransformationMethod());
                    l_password.setSelection(l_password.getText().length());


                }
            }
        });

        s_submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = s_email.getText().toString();
                String password = s_password.getText().toString();

                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
                    Toast.makeText(getApplicationContext(), "E-mail or Password Cannot Be Empty", Toast.LENGTH_SHORT).show();
                else
                    SignUpUser(email,password);
            }
        });

        l_submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = l_email.getText().toString();
                String password = l_password.getText().toString();

                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
                    Toast.makeText(getApplicationContext(), "E-mail or Password Cannot Be Empty", Toast.LENGTH_SHORT).show();
                else
                    SignInUser(email,password);
            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                l_email.setVisibility(View.VISIBLE);
                l_password.setVisibility(View.VISIBLE);
                l_submitButton.setVisibility(View.VISIBLE);
                l_text.setVisibility(View.VISIBLE);
                LogIn.setVisibility(View.VISIBLE);
                l_visibilityPassword.setVisibility(View.VISIBLE);


                s_email.setVisibility(View.INVISIBLE);
                s_password.setVisibility(View.INVISIBLE);
                s_submitButton.setVisibility(View.INVISIBLE);
                s_text.setVisibility(View.INVISIBLE);
                SignUp.setVisibility(View.INVISIBLE);
                passwordVisibility.setVisibility(View.INVISIBLE);

            }
        });

        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                l_email.setVisibility(View.INVISIBLE);
                l_password.setVisibility(View.INVISIBLE);
                l_submitButton.setVisibility(View.INVISIBLE);
                l_text.setVisibility(View.INVISIBLE);
                LogIn.setVisibility(View.INVISIBLE);
                l_visibilityPassword.setVisibility(View.INVISIBLE);

                s_email.setVisibility(View.VISIBLE);
                s_password.setVisibility(View.VISIBLE);
                s_submitButton.setVisibility(View.VISIBLE);
                s_text.setVisibility(View.VISIBLE);
                SignUp.setVisibility(View.VISIBLE);
                passwordVisibility.setVisibility(View.VISIBLE);

            }
        });

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LOGIN_META = false;
                signIn();

            }
        });

        meta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LOGIN_META = true;
                callbackManager = CallbackManager.Factory.create();

                LoginManager.getInstance().logInWithReadPermissions(Authentication.this, Collections.singletonList("public_profile"));
                LoginManager.getInstance().registerCallback(callbackManager,
                        new FacebookCallback<LoginResult>() {
                            @Override
                            public void onSuccess(LoginResult loginResult) {
                                // App code
                                handleFacebookAccessToken(loginResult.getAccessToken());
                            }

                            @Override
                            public void onCancel() {
                                // App code
                                Toast.makeText(Authentication.this, "Something Went Wrong :(", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onError(@NonNull FacebookException exception) {
                                // App code
                                Toast.makeText(Authentication.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }


    //onCreate Stuff
    void Binding(){
        auth = FirebaseAuth.getInstance();


        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("UserDetails");

        passwordVisibility = findViewById(R.id.visibilityPassword);
        l_visibilityPassword = findViewById(R.id.visibilityPassword_log);
        s_email = findViewById(R.id.emailText_id);
        s_password = findViewById(R.id.passwordText_id);
        s_submitButton = findViewById(R.id.submitButton_id);
        s_password.setTransformationMethod(new PasswordTransformationMethod());
        s_text = findViewById(R.id.l_text);
        SignUp = findViewById(R.id.s_text_sighup);
        profile = findViewById(R.id.profile);

        l_email = findViewById(R.id.emailText_id_log);
        l_password = findViewById(R.id.passwordText_id_log);
        l_submitButton = findViewById(R.id.submitButton_id_log);
        l_text = findViewById(R.id.s_text);
        LogIn = findViewById(R.id.l_text_sighin);

        google = findViewById(R.id.google);
        meta = findViewById(R.id.meta);



    }



    //Storing User Data
    void saveUserData(String email , int requestCode){

        String username = "";
        SharedPreferences sharedPreferences = getSharedPreferences("userLogs",MODE_PRIVATE);
        SharedPreferences.Editor myedit = sharedPreferences.edit();

        if(requestCode==0){
            username = email.substring(0,email.indexOf("@"));
        }
        if(requestCode==1)
            username = email;

        Toast.makeText(getApplicationContext(), username, Toast.LENGTH_SHORT).show();
        myedit.putString("username",username);
        myedit.apply();
        startActivity(new Intent(Authentication.this,MainActivity.class));
    }



    //GOOGLE Auth
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            saveUserData(Objects.requireNonNull(account.getDisplayName()),1);


        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
        }
    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }



    //META Auth
    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(Authentication.this, "Login Successful ", Toast.LENGTH_SHORT).show();
                            String name =  Objects.requireNonNull(auth.getCurrentUser()).getDisplayName();
                            saveUserData(name,1);



                        } else {

                            Toast.makeText(Authentication.this, "Login Failed ", Toast.LENGTH_SHORT).show();


                        }
                    }
                });
    }



    //EMAIL_PASSWORD Auth
    void SignUpUser(String email,String password){

        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "User SignUp Successfully !!", Toast.LENGTH_SHORT).show();
                            saveUserData(email,0);

                        }else{
                            Toast.makeText(getApplicationContext(), "Error!!" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    void SignInUser(String email,String password){

        auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "User SignIn Successfully !!", Toast.LENGTH_SHORT).show();
                            saveUserData(email,0);

                        }else{
                            Toast.makeText(getApplicationContext(), "Error!!" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}