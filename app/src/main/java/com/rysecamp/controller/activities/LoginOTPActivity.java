package com.rysecamp.controller.activities;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.goodiebag.pinview.Pinview;
import com.rysecamp.BrodcastService;
import com.rysecamp.R;
import com.rysecamp.api.ApiClient;
import com.rysecamp.api.EgApi;
import com.rysecamp.databinding.ActivityLoginBinding;
import com.rysecamp.databinding.ActivityLoginOtpBinding;
import com.rysecamp.model.SendOtp;
import com.rysecamp.model.SendOtpRes;
import com.rysecamp.model.VerifyOtp;
import com.rysecamp.model.VerifyOtpRes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginOTPActivity extends BaseActivity {

    String data1 = LoginActivity.getData();
    String data11 = LoginActivity.getData1();
    public TextView tv;

    EgApi api;
    String userId, trigger, redirect, phone;
    Pinview otpView;

   // String TAG = "Main";
    //TextView txt;
   public int counter;
    @TargetApi(Build.VERSION_CODES.O)

    //String userId, redirect, numberstr; String help;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginOtpBinding activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login_otp);
        activityLoginBinding.setHandlers(this);

        tv = findViewById(R.id.tvPhoneNum);//"You will receive an OTP on \n"+
        //tv.setText( data1 +" to ");

      //  txt = findViewById(R.id.txtTimer);
        final TextView counttime=findViewById(R.id.txtTimer);
        new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                counttime.setText(String.valueOf(counter));
                counter++;
            }
            @Override
            public void onFinish() {
                counttime.setText("");
            }
        }.start();



        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        api = ApiClient.getClient().create(EgApi.class);

        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");
        trigger = intent.getStringExtra("trigger");
        phone = intent.getStringExtra("help");

        //You will receive an OTP on\n999XXXXXXX ;

        tv.setText("You will receive an OTP on \n" + phone);


        otpView = findViewById(R.id.otp_view);


    }


    public void onResentOtp(View view) {

        api.sendOtp(new SendOtp(phone)).enqueue(new Callback<SendOtpRes>() {
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
                    Intent intent = new Intent(LoginOTPActivity.this, LoginOTPActivity.class);
                    intent.putExtra("userId", userId);
                    intent.putExtra("help", phone);
                    intent.putExtra("trigger", "home");

                    startActivity(intent);
                } else if (redirect.equals("signUp")) {
                    Intent intent = new Intent(LoginOTPActivity.this, LoginOTPActivity.class);
                    intent.putExtra("userId", userId);
                    intent.putExtra("help", phone);
                    intent.putExtra("trigger", "signUp");
                    startActivity(intent);
                }

            }

            @Override
            public void onFailure(Call<SendOtpRes> call, Throwable t) {
                Toast.makeText(LoginOTPActivity.this, "Wrong OTP!", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

    }

    public void onVerifyClick(View view) {
        Log.d("userId", userId);
        Log.d("trigger", trigger);


        if (trigger.equals("home")) {

            api.verifyOtpCode(new VerifyOtp(otpView.getValue(), userId)).enqueue(new Callback<VerifyOtpRes>() {
                @Override
                public void onResponse(Call<VerifyOtpRes> call, Response<VerifyOtpRes> response) {
                    Intent intent = new Intent(LoginOTPActivity.this, HomeActivity.class);
                    intent.putExtra("userId", userId);
                    intent.putExtra("token", response.body().getToken());
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<VerifyOtpRes> call, Throwable t) {
                    t.printStackTrace();
                }
            });


        } else if (trigger.equals("signUp")) {

            api.verifyOtpCode(new VerifyOtp(otpView.getValue(), userId)).enqueue(new Callback<VerifyOtpRes>() {
                @Override
                public void onResponse(Call<VerifyOtpRes> call, Response<VerifyOtpRes> response) {
                    Intent intent = new Intent(LoginOTPActivity.this, SignupActivity.class);
                    intent.putExtra("userId", userId);
                    intent.putExtra("token", response.body().getToken());
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<VerifyOtpRes> call, Throwable t) {
                    t.printStackTrace();
                }
            });

        } else {
           Toast.makeText(this, "Wrong OTP!", Toast.LENGTH_SHORT).show();
       }

    }

}

