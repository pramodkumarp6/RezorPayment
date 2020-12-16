package com.example.rezorpayment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.TwoStatePreference;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rezorpayment.app.RetrofitClient;
import com.example.rezorpayment.model.LoginModel;
import com.example.rezorpayment.model.LoginResult;
import com.example.rezorpayment.model.googlecapcha.GoogleResultCapcha;
import com.example.rezorpayment.navigation.Dash;
import com.example.rezorpayment.services.SharedPrefManager;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "LoginActivity";


    public SharedPrefManager SharedPrefManager;
    private Button userforget;
    private Button  loginbutton;
    private EditText Temail;
    private EditText tpass;
    private Button textViewReg;
    private ProgressDialog progressDialog;
    private Context mCtx;
    private CheckBox checkBox;
    private LoginActivity loginActivity;
    private TwoStatePreference GoogleCaptcha;
    private TextView textView;
    private Button buttonSkip;
    String userResponseToken;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setTitle("Login");
        /*  if(connection())
        {
            // Toast.makeText(getApplicationContext(),"Internet is Connected",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(), " Internet is  not Avilable", Toast.LENGTH_SHORT).show();
        }*/

        getSupportActionBar().setTitle("LOGIN");
        loginbutton = (Button) findViewById(R.id.buttonLogin);

        buttonSkip = findViewById(R.id.skip);
        textView = findViewById(R.id.GoogleCaptchaText);

        userforget = (Button) findViewById(R.id.forgetuser);


        Temail = (EditText)findViewById(R.id.editTextemail);
        tpass = (EditText)findViewById(R.id.editTextPassword);
        textViewReg = (Button) findViewById(R.id.Register);
        progressDialog = new ProgressDialog(this);

        progressDialog.setMessage("Please wait...");




        textViewReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Register.class);
                startActivity(i);
            }
        });

        checkBox = findViewById(R.id.GoogleCaptcha);

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Captcha();


            }
        });
        buttonSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,Dash.class);
                startActivity(intent);
            }
        });


        userforget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Reset_password.class);
                startActivity(i);
            }
        });

        loginbutton.setOnClickListener(this);
    }

    public void userlogin()
    {
        final String email = Temail.getText().toString().trim();

        final String password = tpass.getText().toString().trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Temail.setError("Enter a valid email");
            Temail.requestFocus();
            return;
        }



        if (password.isEmpty()) {
            tpass.setError("Password is required");
            tpass.requestFocus();
            return;
        }

        Log.d("username",email);
        Log.d("password",password);
        progressDialog.show();

        LoginModel loginModel = new LoginModel(email,password);

        Call<LoginResult> call = RetrofitClient.getInstance().getApi().userLogin(loginModel);

        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                 progressDialog.dismiss();
                LoginResult loginResponse = response.body();
                progressDialog.dismiss();
                if (loginResponse.getStatus() ==200) {


                    SharedPrefManager.getInstance(LoginActivity.this).saveUser(loginResponse.getUser());


                    Intent intent = new Intent(LoginActivity.this, Dash.class);
                   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                  //   mCtx.startActivity(new Intent(mCtx, LoginActivity.class));
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(getApplicationContext(), "Invalid email or password", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                // finish();
            }
        });


    }


    private  void  Captcha(){
        String site_Key ="6Ld73vQZAAAAACwUexVezyQ24OB99lyFh6vcOUVa";



        SafetyNet.getClient(this).verifyWithRecaptcha(site_Key)
                .addOnSuccessListener(this,
                        new OnSuccessListener<SafetyNetApi.RecaptchaTokenResponse>() {
                            @Override
                            public void onSuccess(SafetyNetApi.RecaptchaTokenResponse response) {
                                // Indicates communication with reCAPTCHA service was
                                // successful.
                                userResponseToken = response.getTokenResult();
                                if (!userResponseToken.isEmpty()) {
                                    // Validate the user response token using the
                                    // reCAPTCHA siteverify API.
                                    // new SendPostRequest().execute();

                                    Log.d("Pramod",userResponseToken);
                                    Toast.makeText(getApplicationContext(), userResponseToken, Toast.LENGTH_LONG).show();

                                    sendRequest();
                                }
                            }
                        })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (e instanceof ApiException) {
                            // An error occurred when communicating with the
                            // reCAPTCHA service. Refer to the status code to
                            // handle the error appropriately.
                            ApiException apiException = (ApiException) e;
                            int statusCode = apiException.getStatusCode();
                            Log.d(TAG, "Error: " + CommonStatusCodes
                                    .getStatusCodeString(statusCode));
                        } else {
                            // A different, unknown type of error occurred.
                            Log.d(TAG, "Error: " + e.getMessage());
                        }
                    }
                });




          }



    protected  void sendRequest(){



        String SITE_SECRET_KEY ="6Ld73vQZAAAAALeOgZXqfoQ3TsEdxDYBlFu9_5Oh";
        Call<GoogleResultCapcha> call = RetrofitClient.getInstance().getApi().capchaTest(userResponseToken,SITE_SECRET_KEY);
           call.enqueue(new Callback<GoogleResultCapcha>() {
               @Override
               public void onResponse(Call<GoogleResultCapcha> call, Response<GoogleResultCapcha> response) {

                   GoogleResultCapcha googleResultCapcha = response.body();
                   if(googleResultCapcha.isSucess()){
                       textView.setText("I m not reboart");


                      // GoogleCaptcha.setChecked(true);

                       Toast.makeText(getApplicationContext(), "Invalid email or password", Toast.LENGTH_LONG).show();

                   }else{
                       textView.setText("you are not Roboat");
                    //   GoogleCaptcha.setChecked(false);


                   }



               }

               @Override
               public void onFailure(Call<GoogleResultCapcha> call, Throwable t) {

               }
           });

        /*call.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);
*/
    }

   

    @Override
    public void onClick(View v) {

        userlogin();

    }



  /*  private boolean connection()
    {
        ConnectivityManager connectivityManager =(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return  activeNetwork != null  && activeNetwork.isConnected();
    }*/
}
