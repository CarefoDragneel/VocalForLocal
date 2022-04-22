package com.example.vocalforlocal.login_and_signUp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.example.vocalforlocal.LoginAdapter;
import com.example.vocalforlocal.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class LoginSignUp extends AppCompatActivity {
    public static final String TAG = "TAG";
    LottieAnimationView lottieAnimationView;
    TabLayout tabLayout;
    ViewPager viewPager;
    FloatingActionButton fb, google;
    float velocity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sign_up);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        google = findViewById(R.id.fab_google);
        fb = findViewById(R.id.fab_facebook);
        tabLayout.addTab(tabLayout.newTab().setText("Login"));
        tabLayout.addTab(tabLayout.newTab().setText("SignUp"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        lottieAnimationView = findViewById(R.id.animation);
        lottieAnimationView.animate().translationY(0).setDuration(1000).setStartDelay(3500);


        final LoginAdapter adapter = new LoginAdapter(getSupportFragmentManager(), this, tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                viewPager.setCurrentItem(position);
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        google.setTranslationY(300);
        fb.setTranslationY(300);
        tabLayout.setTranslationY(300);

        fb.setAlpha(velocity);
        google.setAlpha(velocity);
        tabLayout.setAlpha(velocity);

        fb.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        google.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(700).start();
        tabLayout.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(100).start();

    }

}