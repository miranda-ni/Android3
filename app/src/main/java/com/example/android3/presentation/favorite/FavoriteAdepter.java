package com.example.android3.presentation.favorite;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android3.R;
import com.example.android3.model.BoredAction;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdepter extends RecyclerView.Adapter<FavoriteAdepter.ViewHolder> {

    List<BoredAction>models = new ArrayList<>();

   public OnClick onClick;
    public FavoriteAdepter(List<BoredAction> models){
        this.models = models;
    }

    @NonNull
    @Override
    public FavoriteAdepter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent
            , int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_list,parent,false);

        return new ViewHolder(view);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull FavoriteAdepter.ViewHolder holder, int position) {
    holder.bind(models.get(position));
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
    public void FavoriteAdepter(OnClick onClick){
        this.onClick = onClick;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView categotyText,emplText,priceText;
        ImageView participent1,participent2,participent3,participent4,participent;
        ProgressBar progressBar;
        LikeButton likeButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categotyText = itemView.findViewById(R.id.text_view_category_fav);
            emplText = itemView.findViewById(R.id.text_view_emp_fav);
            priceText = itemView.findViewById(R.id.text_view_price_fav);
            participent1 = itemView.findViewById(R.id.person_fav);
            progressBar = itemView.findViewById(R.id.progress_bar_fav);
            likeButton = itemView.findViewById(R.id.like_btn_fav);
            likeButton.setOnLikeListener(new OnLikeListener() {
                @Override
                public void liked(LikeButton likeButton) {

                }

                @Override
                public void unLiked(LikeButton likeButton) {

                }
            });
        }



        public void bind(BoredAction boredAction) {
            emplText.setText(boredAction.getActivity());
            priceText.setText(boredAction.getPrice().toString());
            switch (boredAction.getParticipants()){
                case 0:
                    participent1.setImageResource(R.drawable.ic_user_icon);
                    break;
                case 1:
                    participent1.setImageResource(R.drawable.ic_user_icon__);
                    break;
                case 2:
                    participent1.setImageResource(R.drawable.ic_user_icon_);
                    break;
                case 3:
                    participent1.setImageResource(R.drawable.ic_group_1);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                progressBar.setProgress((int)(boredAction.getAccessibility()*100),true);
            }
            categotyText.setText(boredAction.getType());
        }
    }
}
interface OnClick{
    void OnClickItem(int pos);
}


