package com.rysecamp.controller.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.rysecamp.R;
import com.rysecamp.api.ApiClient;
import com.rysecamp.api.EgApi;
import com.rysecamp.databinding.ActivityLoginBinding;
import com.rysecamp.databinding.ActivitySignupBinding;
import com.rysecamp.model.Register;
import com.rysecamp.model.RegisterRes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends BaseActivity {

    private static String userName = " ";
    //EditText et;

    EgApi api;
    String userId;
    EditText name,email;
    String nameStr,emailStr;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySignupBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_signup);
        binding.setHandlers(this);

        //et = findViewById(R.id.name);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


        name=findViewById(R.id.name);   userName = name.getText().toString();
        email=findViewById(R.id.email);

        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");
        Log.d("userId",userId);

        api = ApiClient.getClient().create(EgApi.class);


        nameStr = name.getText().toString();
        emailStr = email.getText().toString();

        //nameStr = "YOu are!";


    }

    public static String getData1(){ return userName; }
//    public void letsee() {
//        setContentView(R.layout.nav_header_home);
//        TextView tv = (TextView) findViewById(R.id.nav_username);
//        tv.setText(nameStr);
//    }

    public void onCreateAccountClick(View view)
    {
        //letsee();
//        TextView tv = (TextView) findViewById(R.id.testview);
//        tv.setText("DOES THIS EVEN WORK?");
//        if(name.length()==0){
//            name.setError("Name can't be Empty");
//            return;
//        }
//        if(email.length()==0){
//            email.setError("Name can't be Empty");
//            return;
//        }
        api.Register(new Register(nameStr,emailStr,userId)).enqueue(new Callback<RegisterRes>() {
            @Override
            public void onResponse(Call<RegisterRes> call, Response<RegisterRes> response)
            {
                Intent intent=new Intent(SignupActivity.this,HomeActivity.class);
                intent.putExtra("userId",userId);
                intent.putExtra("nameStr",name.getText().toString());
                intent.putExtra("token",response.body().getAuthToken());
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<RegisterRes> call, Throwable t) {

            }
        });

    }
}
