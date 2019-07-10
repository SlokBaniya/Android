package com.helper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import fragments.AllFragment;
//import fragments.EducationalFragment;
//import fragments.PoliticalFragment;
//import fragments.SportsFragment;

//

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private NavigationView navBar;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private EditText etSearch;
    private TextView tvProfileName, tvLogout;
    private ImageView imgSearch, imgProfile;
    String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSearch = findViewById(R.id.etSearch);

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


        //hidedrawermenu();
    }
//    private void hidedrawermenu(){
//        Menu menu = navBar.getMenu();
//        menu.findItem(R.id.navBar).setVisible(false);
//    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.Home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new AllFragment()).commit();
                break;
//            case R.id.educational:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new EducationalFragment()).commit();
//                break;
//            case R.id.sports:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new SportsFragment()).commit();
//                break;
//            case R.id.political:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new PoliticalFragment()).commit();
//                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}

