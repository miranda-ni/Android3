package com.example.android3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static androidx.viewpager.widget.ViewPager.*;

public class IntroActivity extends AppCompatActivity {
    private Button next;
    private Button skip;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        skip = findViewById(R.id.skip);
        next = findViewById(R.id.next);

        viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager()));

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
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void onClickNext(View view) {
        if (viewPager.getCurrentItem() < 2) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
        } else {
            onClickSkip(view);
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
            bundle.putInt("pager", position);
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
