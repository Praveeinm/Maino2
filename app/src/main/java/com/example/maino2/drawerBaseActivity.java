package com.example.maino2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class drawerBaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

   DrawerLayout drawerLayout;




    @Override
    public void setContentView(View view) {

        drawerLayout = (DrawerLayout)getLayoutInflater().inflate(R.layout.activity_drawer_base,null);
        FrameLayout container = drawerLayout.findViewById(R.id.containerActivity);
        container.addView(view);
        super.setContentView(drawerLayout);



        Toolbar toolbar = drawerLayout.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = drawerLayout.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

         ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_nav,R.string.close_nav);
         drawerLayout.addDrawerListener(toggle);
         toggle.syncState();


    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        drawerLayout.closeDrawer(GravityCompat.START);

        int id = item.getItemId();
         if(id == R.id.nav_home){
           startActivity(new Intent(this,MainActivity.class));
           overridePendingTransition(0,0);

        }

         if(id == R.id.nav_benefits){
             startActivity(new Intent(this, BenefitsActivity.class));
             overridePendingTransition(0,0);
         }

         if(id == R.id.nav_Methodology){
             startActivity(new Intent(this, MethodologyActivity.class));
             overridePendingTransition(1,0);

         }

        return false;
    }



    protected void allocateActitvityTitle(String titleString){
       if(getSupportActionBar()!= null){
           getSupportActionBar().setTitle(titleString);
       }

    }


}