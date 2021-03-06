package com.example.fabian.mm.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fabian.mm.R;
import com.example.fabian.mm.helpers.SQLiteHelper;
import com.example.fabian.mm.model.Movie;
import com.example.fabian.mm.utils.ApiUtils;
import com.example.fabian.mm.utils.Constants;
import com.squareup.picasso.Picasso;

/**
 * Created by fabian on 7/27/17.
 */

public class DetailFragment extends Fragment {

    private int movieId;
    private ImageView imgCover;
    private TextView txtTitle;
    private TextView txtOverview;
    private TextView txtReleaseDate;
    Movie movie;

    public static DetailFragment newInstance(int movieId) {
        DetailFragment myFragment = new DetailFragment();

        Bundle args = new Bundle();
        args.putInt(Constants.MOVIE_ID, movieId);
        myFragment.setArguments(args);

        return myFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fragment, container, false);
        setRetainInstance(true);
        if (savedInstanceState == null) {
            movieId = getArguments().getInt(Constants.MOVIE_ID);
        }
        runQuery();
        initLayout(view);
        return view;
    }


    private void initLayout(View view) {
        imgCover = (ImageView) view.findViewById(R.id.cover_detail_fragment);
        txtTitle = (TextView) view.findViewById(R.id.title_detail_fragment);
        txtOverview = (TextView) view.findViewById(R.id.overview_detail_fragment);
        txtReleaseDate = (TextView) view.findViewById(R.id.release_date_detail_fragment);

        Picasso.with(getActivity()).load(ApiUtils.IMAGES_BASE_URL + movie.getPoster_path()).into(imgCover);
        txtTitle.setText(movie.getTitle());
        txtOverview.setText(movie.getOverview());
        txtReleaseDate.setText(movie.getRelease_date());
    }

    private void runQuery() {
        SQLiteHelper sqLiteHelper = new SQLiteHelper(getActivity());
        movie = sqLiteHelper.getHistory(movieId);
    }
}
