package com.example.android3.presentation.main;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android3.App;
import com.example.android3.R;
import com.example.android3.data.BoredApiClient;
import com.example.android3.model.BoredAction;
import com.google.android.material.slider.RangeSlider;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.List;


public class MainFragment extends Fragment {
    private LikeButton likeButton;
    private ProgressBar progressBar;
    private Spinner spinner;
    private RangeSlider rangeBar,rangeBar2;
    private TextView categoryText,exploreText,priceText;
    private ImageView person,person2,person3,person4,dopPerson;
    private Button nextBt;
    String get_key;
    String spinnerValue;
    Float minPrice;
    Float maxPrice;
    Float minAccessibility;
    Float maxAccessibility;
    private List<Float> price,accessibility ;
    BoredAction boredAction;

    public static Fragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onInitView(view);
        onSpinner();
        onRangeBar();
        onRangeBarForAccess();
        nextBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                                try {
                                App.boredStorage.saveBoredAction(boredAction);
                                for (BoredAction action : App.boredStorage.getAllActions()){
                                    Log.d("ololo",action.toString());
                                }
                                get_key = boredAction.getKey();
                                exploreText.setText(boredAction.getActivity());
                                priceText.setText(boredAction.getPrice().toString() + '$');
                                progressBar.setProgress((int)(boredAction.getAccessibility()*100),true );
                                onPersons(boredAction);
                                Log.e("ololo", boredAction.toString());


                            } catch (Exception e){
                                    priceText.setText("");
                                    exploreText.setText("press next button");
                                    categoryText.setText("");
                                    progressBar.setProgress(0);
                                    Toast.makeText(getContext(),"press next",Toast.LENGTH_SHORT).show();

                                }
                                BoredAction boredAction1 = App.boredStorage.getBoredAction(get_key);
                                if (boredAction1 != null){
                                    likeButton.setLiked(true);
                                }
                            }

                            @Override
                            public void onFailure(Exception exception) {
                                Log.e("ololo", exception.getMessage());

                            }
                        });
                forLikeInFav();


            }
        });
    }

    public void forLikeInFav(){
        likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                App.boredStorage.saveBoredAction(boredAction);
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                App.boredStorage.deleteBoredAction(boredAction);

            }
        });

    }

    public void onSpinner(){
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(requireContext(), R.array.spinner_array,
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
    public void onInitView(View view)
    {
        spinner =view.findViewById(R.id.spinner);
        progressBar = view.findViewById(R.id.progress_bar);
        rangeBar = view.findViewById(R.id.range_bar);
        rangeBar2 = view.findViewById(R.id.range_bar_second);
        categoryText = view.findViewById(R.id.text_view_category);
        exploreText = view.findViewById(R.id.text_view_emp);
        priceText = view.findViewById(R.id.text_view_price);
        person =view.findViewById(R.id.person);
        person2 = view.findViewById(R.id.two_person);
        person3 = view.findViewById(R.id.three_person);
        person4 = view.findViewById(R.id.four_person);
        dopPerson =view.findViewById(R.id.dop_icons);
        nextBt = view.findViewById(R.id.next_btn);
        likeButton = view.findViewById(R.id.like_btn);

    }

    public void onPersons(BoredAction boredAction){

        switch (boredAction.getParticipants()){
            case 1:
                dopPerson.setImageResource(R.drawable.ic_user_icon);
                break;
            case 2:

                dopPerson.setImageResource(R.drawable.ic_user_icon__);
                break;
            case 3:
                dopPerson.setImageResource(R.drawable.ic_user_icon_);
                break;
            case 4:
                dopPerson.setImageResource(R.drawable.ic_group_1);
                break;


        }}

    public void onRangeBar() {
        rangeBar.addOnChangeListener(new RangeSlider.OnChangeListener() {

            @Override
            public void onValueChange(@NonNull RangeSlider slider, float value, boolean fromUser) {
                price = slider.getValues();
                minPrice = price.get(0);
                maxPrice = price.get(1);
                Log.e("yoyo", price.toString());
            }

        });
    }

public void onRangeBarForAccess(){
        rangeBar2.addOnChangeListener(new RangeSlider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull RangeSlider slider, float value, boolean fromUser) {
                accessibility = slider.getValues();
                minAccessibility = accessibility.get(0);
                maxAccessibility = accessibility.get(1);
                Log.e("yoyo", accessibility.toString());
            }
        });


    }









    }
