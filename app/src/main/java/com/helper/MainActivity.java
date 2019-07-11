package com.helper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import fragments.AllFragment;
//import fragments.EducationalFragment;
//import fragments.PoliticalFragment;
//import fragments.SportsFragment;

//

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private NavigationView navBar;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;

    private TextView tvProfileName, tvLogout;

    String username;
    SensorManager sensorManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorGyro();
        sensorAccelero();


        navBar = findViewById(R.id.navBar);
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //toolbar.setTitle("NewsPortal");


        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        username = sharedPreferences.getString("username", "");
        NavigationView navigationView = findViewById(R.id.navBar);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(mDrawerToggle);
        View headView = navBar.getHeaderView(0);
        tvLogout = headView.findViewById(R.id.tvLogout);
        tvProfileName = headView.findViewById(R.id.tvName);


        tvProfileName.setText(username);
        tvProfileName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,UpdateUserActivity.class);
                startActivity(intent);
            }
        });
        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences logout = getSharedPreferences("user",MODE_PRIVATE);
                logout.edit().remove("username").commit();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });



        mDrawerToggle.syncState();

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new AllFragment()).commit();
            navigationView.setCheckedItem(R.id.Home);
        }



    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.Home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new AllFragment()).commit();
                break;
            case R.id.Profile:
                    Intent intent = new Intent(MainActivity.this,UpdateUserActivity.class);
                    startActivity(intent);
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }


    public void sensorGyro(){
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        SensorEventListener gyro = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.values[1]<-2){
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }else if(event.values[1]>2){
                    Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                    startActivity(intent);
                }
            }


            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        if (sensor != null){
            sensorManager.registerListener(gyro,sensor,sensorManager.SENSOR_DELAY_NORMAL);
        }else{
            Toast.makeText(this,"No sensor found",Toast.LENGTH_LONG).show();
        }
    }
    private void sensorAccelero(){
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        SensorEventListener sel = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float[] values = event.values;
                double xAxis = values[0];
                double yAxis = values[1];
                double zAxis = values[2];
                if (yAxis<9){
                    Intent intent = new Intent(MainActivity.this, UpdateUserActivity.class);
                    startActivity(intent);
                }
            }


            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        if (sensor != null){
            sensorManager.registerListener(sel,sensor,sensorManager.SENSOR_DELAY_NORMAL);
        }else{
            Toast.makeText(this,"No sensor found",Toast.LENGTH_LONG).show();
        }
    }


}

