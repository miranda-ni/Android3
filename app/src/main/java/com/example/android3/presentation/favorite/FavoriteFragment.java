package com.example.android3.presentation.favorite;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android3.App;
import com.example.android3.R;
import com.example.android3.model.BoredAction;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.ArrayList;


public class FavoriteFragment extends Fragment {
    private FavoriteAdepter favoriteAdepter;
    private int pos;
    private ArrayList<BoredAction>list = new ArrayList<>();
    LikeButton likeButton;
    BoredAction boredAction;

    public FavoriteFragment(){

    }
    public static Fragment newInstance(){
        return new FavoriteFragment();
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.favorite_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_fav);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        favoriteAdepter = new FavoriteAdepter(list);
        recyclerView.setAdapter(favoriteAdepter);
        likeButton = view.findViewById(R.id.like_btn_fav);
        favoriteAdepter.FavoriteAdepter(new OnClick() {
            @Override
            public void OnClickItem(int pos) {
                App.boredStorage.deleteBoredAction(list.get(pos));
                list.remove(pos);
                favoriteAdepter.notifyDataSetChanged();
            }
        });
        toRemove();
        onLoad();
    }
    private void  toRemove(){
        if (likeButton != null){
            likeButton.setOnLikeListener(new OnLikeListener() {
                @Override
                public void liked(LikeButton likeButton) {

                }

                @Override
                public void unLiked(LikeButton likeButton) {
                    App.boredStorage.deleteBoredAction(boredAction);

                }
            });
        }
    }

    public void onLoad(){
        list.clear();
        list.addAll(App.boredStorage.getAllActions());
        favoriteAdepter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        onLoad();
    }

    @Override
    public void onPause() {
        super.onPause();
        onLoad();
    }
}