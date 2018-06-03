package com.city4age.mobile.city4age.API;

import com.city4age.mobile.city4age.API.Model.AnswerRequest;
import com.city4age.mobile.city4age.API.Model.MTestingResponse;
import com.city4age.mobile.city4age.API.Model.TokenRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Srle on 6/3/2018.
 */
public interface MTestingService {

    @POST("token/")
    Call<MTestingResponse> sendToken(@Body TokenRequest request);

    @POST("answer/")
    Call<MTestingResponse> sendAnswer(@Body AnswerRequest request);
}