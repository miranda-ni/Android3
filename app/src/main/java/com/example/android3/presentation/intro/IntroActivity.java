package com.example.android3.presentation.intro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android3.R;
import com.example.android3.data.AppPreferences;
import com.example.android3.presentation.main.MainActivity;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;

import static androidx.viewpager.widget.ViewPager.*;

public class IntroActivity extends AppCompatActivity {
    private Button next;
    private Button skip;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        skip = findViewById(R.id.btn_skip);
        next = findViewById(R.id.btn_next);



        viewPager = findViewById(R.id.ViewPager);
        viewPager.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager()));
        SpringDotsIndicator springDotsIndicator = findViewById(R.id.spring_dots_indicator);
        springDotsIndicator.setViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        skip.setVisibility(View.VISIBLE);
                        next.setVisibility(View.VISIBLE);

                        break;
                    case 1:
                        next.setVisibility(View.VISIBLE);
                        skip.setVisibility(View.VISIBLE);

                        break;
                    case 2:
                        skip.setVisibility(INVISIBLE);
                        next.setText("START");
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });




    }

    public void onClickSkip(View view) {
        new AppPreferences(IntroActivity.this).setLaunched();

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void onClickNext(View view) {
        if (viewPager.getCurrentItem() < 2) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
        } else {
//            MainActivity.start(this);
            finish();
        }


    }

    class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(@NonNull FragmentManager fm) {
            super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);


        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            bundle.putInt("position", position);
            IntroFragment fragment = new IntroFragment();
            fragment.setArguments(bundle);


            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

}
