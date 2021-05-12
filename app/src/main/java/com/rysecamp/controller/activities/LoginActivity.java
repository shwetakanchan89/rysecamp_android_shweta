package com.rysecamp.controller.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.rysecamp.R;
import com.rysecamp.api.ApiClient;
import com.rysecamp.api.EgApi;
import com.rysecamp.databinding.ActivityLoginBinding;
import com.rysecamp.model.SendOtp;
import com.rysecamp.model.SendOtpRes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {

    private static String data = "hello";
    private static String data1 = "1245";
    public EditText et;

    EgApi api;
    EditText number;
    String userId, redirect, numberstr; String help;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        activityLoginBinding.setHandlers(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        et = findViewById(R.id.mobileNumber);
        help = et.getText().toString();
        data = help;
        data1 ="123545";

        api = ApiClient.getClient().create(EgApi.class);


        number = findViewById(R.id.mobileNumber);

         numberstr=number.getText().toString();


    }
    public static String getData(){ return data; }
    public static String getData1(){ return data1; }

    public void onNextClick(View view) {//submit Button

       if(number.length()<10){
            number.setError("Enter correct number");
            return;
        }
        api.sendOtp(new SendOtp(number.getText().toString())).enqueue(new Callback<SendOtpRes>() {
            @Override
            public void onResponse(Call<SendOtpRes> call, Response<SendOtpRes> response) {
                userId = response.body().getUserId();
                redirect = response.body().getRedirect();

                //Toast.makeText(LoginActivity.this, "welcome", Toast.LENGTH_SHORT).show();
                Log.d("userId", response.body().getUserId());
                Log.d("Redirect", response.body().getRedirect());
                Log.d("success", Boolean.toString(response.body().isSuccess()));

                if (redirect.equals("home")) {

                    //   Toast.makeText(LoginActivity.this, "Home", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, LoginOTPActivity.class);
                    intent.putExtra("userId", userId);
                    intent.putExtra("help", number.getText().toString());
                    intent.putExtra("trigger", "home");

                    startActivity(intent);
                } else if (redirect.equals("signUp")) {
                    Intent intent = new Intent(LoginActivity.this, LoginOTPActivity.class);
                    intent.putExtra("userId", userId);
                    intent.putExtra("help", number.getText().toString());
                    intent.putExtra("trigger", "signUp");
                    startActivity(intent);
                }


            }

            @Override
            public void onFailure(Call<SendOtpRes> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    public void onSignupClick(View view) {//sign up text
        int z1=0;
        //startActivity(new Intent(LoginActivity.this, SignupActivity.class));
    }

}
