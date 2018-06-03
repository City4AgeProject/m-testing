package com.city4age.mobile.city4age.API.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Srle on 6/3/2018.
 */
public class TokenRequest {

    @SerializedName("user")
    @Expose
    private String user;

    @SerializedName("token")
    @Expose
    private String token;

    public TokenRequest() {}

    public TokenRequest(String user, String token) {
        this.user = user;
        this.token = token;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
