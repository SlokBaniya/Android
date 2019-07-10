package com.helper.BusinessLogicLayer;

import java.io.IOException;


import API.UserAPI;
import model.LoginSignupResponse;
import model.Users;
import retrofit2.Call;
import retrofit2.Response;
import url.Url;

public class RegisterBLL {
    private String fullname, address, contact, username, password;
    boolean isSuccess = false;
    public static String m;

    public RegisterBLL(String fullname, String address, String contact, String username, String password) {
        this.fullname = fullname;
        this.address = address;
        this.contact = contact;
        this.username = username;
        this.password = password;
    }


    public boolean register(){
        UserAPI api = Url.getInstance().create(UserAPI.class);
        Users users = new Users(fullname,address,contact,username,password);
        Call<Void> usersCall = api.addUsers(users);


        try {
            Response<Void> responseResponse = usersCall.execute();
            if (responseResponse.isSuccessful()){
                isSuccess = true;
            }
          //  m = responseResponse.body().getMessage();

        }catch (IOException e){
            e.printStackTrace();
        }
        return isSuccess;
    }
}
