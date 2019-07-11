package com.helper;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import API.UserAPI;
import model.Users;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import url.Url;

public class UpdateUserActivity extends AppCompatActivity {
    private ImageView  imgBack;
    EditText etFullname, etAddress, etContact, etPassword;
    private Button btnUpdate;
    private TextView tvUsername;
    private String username;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        username = sharedPreferences.getString("username", "");

        if (username.equals("")){
            Intent intent = new Intent(UpdateUserActivity.this,MainActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Please login first", Toast.LENGTH_LONG).show();
            return;
        }

        etFullname = findViewById(R.id.etFullname);
        etAddress = findViewById(R.id.etAddress);
        etContact = findViewById(R.id.etContact);
        tvUsername = findViewById(R.id.tvUsername);
        etPassword = findViewById(R.id.etPassword);

        btnUpdate = findViewById(R.id.btnUpdate);
        imgBack = findViewById(R.id.imgBack);
        getUser();


        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // DescriptionActivity.super.onBackPressed();
                Intent intent = new Intent(UpdateUserActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etFullname.getText().toString().equals("")){
                    Toast.makeText(UpdateUserActivity.this, "Please enter fullname", Toast.LENGTH_LONG).show();
                }else if (etAddress.getText().toString().equals("")){
                    Toast.makeText(UpdateUserActivity.this, "Please enter address", Toast.LENGTH_LONG).show();
                }else if (etContact.getText().toString().equals("")){
                    Toast.makeText(UpdateUserActivity.this, "Please enter contact", Toast.LENGTH_LONG).show();
                }else if (etPassword.getText().toString().equals("")){
                    Toast.makeText(UpdateUserActivity.this, "Please enter password", Toast.LENGTH_LONG).show();
                }else if (etPassword.getText().toString().equals("Enter new password")){
                    Toast.makeText(UpdateUserActivity.this, "Please enter new password", Toast.LENGTH_LONG).show();
                }else{
                    updateProfile();
                }

            }
        });

    }
    public void getUser(){
        UserAPI api = Url.getInstance().create(UserAPI.class);
        Call<Users> listCall = api.getUserByUsername(username);

        listCall.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                etFullname.setText(response.body().getFullname());
                etAddress.setText(response.body().getAddress());
                etContact.setText(response.body().getContact());
                tvUsername.setText(response.body().getUsername());


            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(UpdateUserActivity.this, "error" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void updateProfile(){

        String fullname = etFullname.getText().toString();
        String address = etAddress.getText().toString();
        String contact = etContact.getText().toString();
        String password = etPassword.getText().toString();

        UserAPI api = Url.getInstance().create(UserAPI.class);

        Users users = new Users();
        users.setFullname(fullname);
        users.setAddress(address);
        users.setContact(contact);
        users.setPassword(password);

        Call<Void> newsCall = api.updateUser(username,users);

        newsCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(UpdateUserActivity.this, "code " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(UpdateUserActivity.this, "Updated successfully", Toast.LENGTH_LONG).show();


            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(UpdateUserActivity.this, "error " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }}
