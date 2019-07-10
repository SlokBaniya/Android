package com.helper;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.helper.BusinessLogicLayer.RegisterBLL;

import API.UserAPI;
import model.Users;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import url.Url;

public class RegisterActivity extends AppCompatActivity {
    private EditText etFullname,etAddress, etContact, etUsername, etPassword,etCPassword;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etFullname = findViewById(R.id.etFullname);
        etAddress = findViewById(R.id.etAddress);
        etContact = findViewById(R.id.etContact);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etCPassword = findViewById(R.id.etCPassword);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etFullname.getText().toString().equals("") || etContact.getText().toString().equals("") || etAddress.getText().toString().equals("")  || etUsername.getText().toString().equals("") || etPassword.getText().toString().equals("")) {
                    Toast.makeText(RegisterActivity.this, "please fill all the fields", Toast.LENGTH_LONG).show();
                } else if (!etPassword.getText().toString().equals(etCPassword.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "Confirm password did not match", Toast.LENGTH_LONG).show();
                } else {
                    String fullname = etFullname.getText().toString();
                    String address = etAddress.getText().toString();
                    String contact = etContact.getText().toString();
                    String username = etUsername.getText().toString();
                    String password = etPassword.getText().toString();

                    final RegisterBLL registerBLL = new RegisterBLL(fullname,address, contact,username,password);
                    StrictMode();
                    if (registerBLL.register()){
                        Toast.makeText(RegisterActivity.this, RegisterBLL.m, Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(RegisterActivity.this, "Failed to register", Toast.LENGTH_LONG).show();
                    }


                    //register();
                }
            }
        });
    }
    private void StrictMode() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    private void register(){
        UserAPI userAPI = Url.getInstance().create(UserAPI.class);

        String fullname= etFullname.getText().toString();
        String address = etAddress.getText().toString();
        String contact = etContact.getText().toString();
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();


        Users users = new Users(fullname,address,contact,username,password);


        Call<Void> usersCall = userAPI.addUsers(users);
        usersCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(RegisterActivity.this,"Code" +response.code(),Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(RegisterActivity.this,"Successfully Saved",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(RegisterActivity.this,"Error" +t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });


    }
}
