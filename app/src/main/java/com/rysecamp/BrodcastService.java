package com.rysecamp;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;


public class BrodcastService extends Service {

    private String TAG ="BroadcastService";
    public static final String COUNTDOWN_BR ="com.rysecamp";
    Intent intent = new Intent(COUNTDOWN_BR);
    CountDownTimer countDownTimer = null;

    SharedPreferences sharedPreferences;

    @Override
    public void onCreate(){
        super.onCreate();
        Log.i(TAG,"Starting timer...");
        sharedPreferences = getSharedPreferences(getPackageName(),MODE_PRIVATE);
        long millis = sharedPreferences.getLong("time",3000);
        if(millis/1000==0){
            millis = 30000;
        }
        countDownTimer = new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.i(TAG,"Countdown seconds remaining:" + millisUntilFinished);
                intent.putExtra("coundown",millisUntilFinished);
                sendBroadcast(intent);

            }

            @Override
            public void onFinish() {

            }
        };
        countDownTimer.start();
    }

    @Override
    public void onDestroy() {
        countDownTimer.cancel();
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent){
        return null;
    }



}
