package com.example.android3.presentation.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.android3.R;
import com.example.android3.data.AppPreferences;
import com.example.android3.presentation.favorite.FavoriteFragment;
import com.example.android3.presentation.intro.IntroActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.example.android3.R.id.favorite_nav;
import static com.example.android3.R.id.setting_nav;


public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;


  public static void start(Context context) {
    context.startActivity(new Intent(context, MainActivity.class));
}

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isShow();
        bottomNavigationView  = findViewById(R.id.bottom_navigation);
        viewPager = findViewById(R.id.view_pager);
        forOnNav();


    }
    public void forOnNav() {
        viewPager.setAdapter(new MainPagerAdepter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(2);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home_nav:
                        viewPager.setCurrentItem(0, false);
                        break;
                    case favorite_nav:
                        viewPager.setCurrentItem(1, false);
                        break;
                }
                return true;
            }

        });
    }





    public void isShow(){
        boolean isShow = new AppPreferences(this).isFirstLaunch(true);

        if(isShow){
            startActivity(new Intent(this, IntroActivity.class));

        }}

        public class MainPagerAdepter extends FragmentPagerAdapter {
            public MainPagerAdepter(@NonNull FragmentManager fm) {
                super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
            }

            @NonNull
            @Override
            public Fragment getItem(int position) {
                Fragment fragment;
                switch (position) {
                    case 0:
                        fragment = MainFragment.newInstance();
                        break;
                    case 1:
                        fragment = FavoriteFragment.newInstance();
                        break;
                    default:
                        fragment = MainFragment.newInstance();
                        break;
                }
                return fragment;
            }

            @Override
            public int getCount() {
                return 3;
            }
        }


}



