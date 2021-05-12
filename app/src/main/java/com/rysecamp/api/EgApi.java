package com.rysecamp.api;

import com.rysecamp.model.Register;
import com.rysecamp.model.RegisterRes;
import com.rysecamp.model.SendOtp;
import com.rysecamp.model.SendOtpRes;
import com.rysecamp.model.VerifyOtp;
import com.rysecamp.model.VerifyOtpRes;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface EgApi
{

   @POST(ApiUrls.UserManagement.SEND_OTP)
    Call<SendOtpRes> sendOtp(@Body SendOtp sendOtp);

    @POST(ApiUrls.UserManagement.VERIFY_OTP)
    Call<VerifyOtpRes> verifyOtpCode(@Body VerifyOtp verifyotp);

    @POST(ApiUrls.UserManagement.REGISTER)
    Call<RegisterRes> Register(@Body Register register);


    @POST(ApiUrls.UserManagement.HOME)
    Call<String> homeAuth(@Header("Authorization") String authorization);

}
