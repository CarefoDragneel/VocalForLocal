package com.example.vocalforlocal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.vocalforlocal.databinding.ActivityMainBinding;
import com.example.vocalforlocal.fragments.HomeFragment;
import com.example.vocalforlocal.fragments.MapFragment;
import com.example.vocalforlocal.fragments.MyProductsFragment;
import com.example.vocalforlocal.login_and_signUp.LoginSignUp;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    MeowBottomNavigation bottomNavigation;
    FrameLayout frameLayout;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        frameLayout = binding.frame;
        bottomNavigation = binding.bottomnavigation;
        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_myproducts));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_map));

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;
                switch (item.getId()) {
                    case 1:
                        fragment = new HomeFragment();
                        break;
                    case 2:
                        fragment = new MyProductsFragment();
                        break;
                    case 3:
                        fragment = new MapFragment();
                        break;
                    default : fragment = new HomeFragment();
                }
                loadfragment(fragment);
            }

        });

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {

            }
        });
        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
            }
        });
        bottomNavigation.show(1, true);

        /*----------------------hooks---------------------- */
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        /*-----------------------Tool Bar----------------------------*/
        setSupportActionBar(toolbar);

        /* -------------- Navigation Drawer Menu-----------------*/
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //For making navigation view item clickable
        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);
        //For making navigation calender default selected
        navigationView.setCheckedItem(R.id.calender);

    }

    private void loadfragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().
                replace(R.id.frame,fragment,"").
                commit();
    }

    // For making back button to close the only drawer menu

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else
            super.onBackPressed();
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.calender:
                break;
            case R.id.todo: {
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                startActivity(i);
                break;
            }
            case R.id.notes:
            {
                Intent i = new Intent(MainActivity.this,MainActivity.class);
                startActivity(i);
                break;
            }
            case R.id.Logout:
            {
                FirebaseAuth.getInstance().signOut();//logout
                startActivity(new Intent(getApplicationContext(), LoginSignUp.class));
                finish();
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }



}