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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.detail_fragment, container, false);

        if (savedInstanceState == null) {
            movieId = getArguments().getInt(Constants.MOVIE_ID);
        }
        initLayout(view);

        return view;
    }

    private void initLayout(View view) {
        imgCover = (ImageView) view.findViewById(R.id.cover_detail_fragment);
        txtTitle = (TextView) view.findViewById(R.id.title_detail_fragment);
        txtOverview = (TextView) view.findViewById(R.id.overview_detail_fragment);
        txtReleaseDate = (TextView) view.findViewById(R.id.release_date_detail_fragment);

        SQLiteHelper sqLiteHelper = new SQLiteHelper(getActivity());
        Movie movie = sqLiteHelper.getHistory(movieId);

        Picasso.with(getActivity()).load(ApiUtils.IMAGES_BASE_URL + movie.getPoster_path()).into(imgCover);
        txtTitle.setText(movie.getTitle());
        txtOverview.setText(movie.getOverview());
        txtReleaseDate.setText(movie.getRelease_date());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        final Bundle movieState = new Bundle(outState);
        movieState.putInt(Constants.MOVIE_ID, movieId);
        outState.putBundle("movie_state_id", movieState);
        super.onSaveInstanceState(outState);
    }
}
