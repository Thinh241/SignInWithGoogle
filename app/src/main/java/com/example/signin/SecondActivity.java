    package com.example.signin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;

import java.util.concurrent.SubmissionPublisher;

    public class SecondActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    TextView tv_Email, tv_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Toolbar tool_bar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        tv_Email = findViewById(R.id.tvemail);
        tv_name = findViewById(R.id.tvname);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount gsa = GoogleSignIn.getLastSignedInAccount(this);

        navigationView.setNavigationItemSelectedListener(this);

        View headingView = navigationView.getHeaderView(0);
        TextView tv = headingView.findViewById(R.id.textview_name);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, tool_bar, R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        if(gsa != null) {
            String userName = gsa.getDisplayName();
            String userEmail = gsa.getEmail();
            tv_name.setText(userName);
            tv_Email.setText(userEmail);
            tv.setText(userName);

        }

    }

    private void clickSignOut() {
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Intent i2 = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(i2);
                finish();
            }
        });
    }

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()){
                case R.id.logOut:
                    clickSignOut();
                    break;
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }

        @Override
        public void onBackPressed() {
            if(drawerLayout.isDrawerOpen(GravityCompat.START)){
                drawerLayout.closeDrawer(GravityCompat.START);
            }else{
                super.onBackPressed();
            }
        }
    }