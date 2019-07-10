package com.helper.BusinessLogicLayer;

import java.io.IOException;


import API.UserAPI;
import model.LoginSignupResponse;
import model.Users;
import retrofit2.Call;
import retrofit2.Response;
import url.Url;

public class LoginBLL {
    private String username;
    private String password;
    boolean isSuccess = false;

    public LoginBLL(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean checkUser(){
        UserAPI api = Url.getInstance().create(UserAPI.class);
        Users users = new Users(username,password);
        Call<LoginSignupResponse> usersCall = api.check(users);

        try {
            Response<LoginSignupResponse> responseResponse = usersCall.execute();
            if (responseResponse.body().isSuccess()){
                isSuccess = true;
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        return isSuccess;
    }
}
