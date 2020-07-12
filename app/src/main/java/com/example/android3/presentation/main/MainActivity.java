package com.example.android3.presentation.main;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.android3.App;
import com.example.android3.R;
import com.example.android3.data.BoredApiClient;
import com.example.android3.model.BoredAction;
import com.example.android3.data.AppPreferences;
import com.example.android3.presentation.intro.IntroActivity;
import com.google.android.material.slider.RangeSlider;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private Spinner spinner;
    private RangeSlider rangeBar,rangeBar2;
    private TextView categoryText,exploreText,priceText;
    private ImageView person,person2,person3,person4,dopPerson;
    String spinnerValue;
    Float minPrice;
    Float maxPrice;
    Float minAccessibility;
    Float maxAccessibility;
    private List<Float> price,accessibility ;

  public static void start(Context context) {
    context.startActivity(new Intent(context, MainActivity.class));
}



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isShow();
        onInitView();
        onSpinner();
        onRangeBar();


    }
    public void isShow(){
        boolean isShow = new AppPreferences(this).isFirstLaunch(true);

        if(isShow){
            startActivity(new Intent(this, IntroActivity.class));
//            finish();
//            return;
        }}




//
       public void onSpinner(){
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.spinner_array,
                        android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 spinnerValue = spinner.getSelectedItem().toString();
                categoryText.setText(spinnerValue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


        @SuppressLint("WrongViewCast")
        public void onInitView()
       {
            spinner = findViewById(R.id.spinner);
        progressBar = findViewById(R.id.progress_bar);
        rangeBar = findViewById(R.id.range_bar);
        rangeBar2 = findViewById(R.id.range_bar_second);
        categoryText = findViewById(R.id.text_view_category);
        exploreText = findViewById(R.id.text_view_emp);
        priceText = findViewById(R.id.text_view_price);
        person = findViewById(R.id.person);
        person2 = findViewById(R.id.two_person);
        person3 = findViewById(R.id.three_person);
        person4 = findViewById(R.id.four_person);
        dopPerson = findViewById(R.id.dop_icons);

    }
    public void forVisible(){
      dopPerson.setVisibility(View.VISIBLE);
      person.setVisibility(View.GONE);
      person2.setVisibility(View.GONE);
      person3.setVisibility(View.GONE);
      person4.setVisibility(View.GONE);
    }



    public void onPersons(BoredAction boredAction){

      switch (boredAction.getParticipants()){

          case 1:
              forVisible();
              if (boredAction.getParticipants() ==1)
                  dopPerson.setImageResource(R.drawable.ic_user_icon);
              break;
          case 2:
              forVisible();
              if (boredAction.getParticipants() ==2)
                  dopPerson.setImageResource(R.drawable.ic_user_icon__);
              break;
          case 3:
              forVisible();
              if (boredAction.getParticipants() ==3)
                  dopPerson.setImageResource(R.drawable.ic_user_icon_);
              break;
          case 4:
              forVisible();
              if (boredAction.getParticipants() ==4)
                  dopPerson.setImageResource(R.drawable.ic_group_1);
              break;


    }}

    public void onRangeBar() {
      rangeBar.addOnChangeListener((slider, value, fromUser) -> {
                  try {
                      price = slider.getValues();
                      minPrice = price.get(0);
                      maxPrice = price.get(1);
                      Log.e("yoyo", price.toString());

                  } catch (ArrayIndexOutOfBoundsException exception) {
                      if (price == null) {


                          exception.getMessage();
                      }
                  }
              });


    rangeBar2.addOnChangeListener((slider, value, fromUser) -> {
        try {
            accessibility = slider.getValues();
            minAccessibility = accessibility.get(0);
            maxAccessibility = accessibility.get(1);
            Log.e("yoyo", accessibility.toString());
        } catch (ArrayIndexOutOfBoundsException exception) {
            if (accessibility == null) {

            }
        }


    } );
   }




        public void next(View view){
        App.boredApiClient.getAction(spinnerValue,
                null,
                null,
                minPrice,
                maxPrice,
                null,
                minAccessibility,
                maxAccessibility,
                new BoredApiClient.BoredActionCallback() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onSuccess(BoredAction boredAction) {
                exploreText.setText(boredAction.getActivity());
                priceText.setText(boredAction.getPrice().toString() + '$');
                onPersons(boredAction);
                progressBar.setProgress((int)(boredAction.getAccessibility()*100),true );
                Log.e("ololo", boredAction.toString());


    }

            @Override
            public void onFailure(Exception exception) {
                Log.e("ololo", exception.getMessage());

            }
        });




}
}



