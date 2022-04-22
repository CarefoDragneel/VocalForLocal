package com.example.vocalforlocal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.vocalforlocal.databinding.ActivityMainBinding;
import com.example.vocalforlocal.fragments.HomeFragment;
import com.example.vocalforlocal.fragments.MapFragment;
import com.example.vocalforlocal.fragments.MyProductsFragment;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    MeowBottomNavigation bottomNavigation;
    FrameLayout frameLayout;

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
        bottomNavigation.show(2, true);
    }

    private void loadfragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().
                replace(R.id.frame,fragment,"").
                commit();
    }




}