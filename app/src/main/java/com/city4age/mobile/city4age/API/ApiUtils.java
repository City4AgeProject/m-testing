package com.city4age.mobile.city4age.API;

/**
 * Created by Srle on 6/3/2018.
 */
public class ApiUtils {

    private ApiUtils() {}

    private static final String BASE_URL = "http://10.0.2.2/mtesting/";

    public static MTestingService getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(MTestingService.class);
    }
}