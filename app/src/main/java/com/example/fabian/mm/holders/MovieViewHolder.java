package com.example.fabian.mm.holders;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fabian.mm.R;

/**
 * Created by fabian on 7/26/17.
 */


public class MovieViewHolder extends RecyclerView.ViewHolder {

    public CardView cardView;
    public TextView txtTitle;
    public ImageView imgCover;
    public TextView txtOverview;

    public MovieViewHolder(View itemView) {
        super(itemView);
        cardView = (CardView) itemView.findViewById(R.id.movie_holder_cardview);
        txtTitle = (TextView) itemView.findViewById(R.id.title_holder);
        imgCover = (ImageView) itemView.findViewById(R.id.cover_image_holder);
        txtOverview = (TextView) itemView.findViewById(R.id.title_overview_holder);
    }
}
