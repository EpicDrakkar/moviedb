package com.example.fabian.mm.fragments;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fabian.mm.R;
import com.example.fabian.mm.adapters.MoviesAdapter;
import com.example.fabian.mm.helpers.SQLiteHelper;
import com.example.fabian.mm.interfaces.OnMovieClickListener;
import com.example.fabian.mm.model.Movie;

import java.util.ArrayList;

/**
 * Created by fabian on 7/27/17.
 */

public class HistoryFragment extends BaseFragment {

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.history_fragment, container, false);
        movieListRecycler = (RecyclerView) view.findViewById(R.id.recycler_view_history);
        setupAdapter();
        setupRecycler();
        getMoviesResult();
        return view;
    }

    public void getMoviesResult() {
        SQLiteHelper sqLiteHelper = new SQLiteHelper(getActivity());
        moviesAdapter.setMovies(sqLiteHelper.getAllHistory());
        moviesAdapter.notifyDataSetChanged();
    }

    @Override
    public void setupAdapter() {
        if (moviesAdapter == null) {
            moviesAdapter = new MoviesAdapter(getActivity(), new ArrayList<Movie>(0));
            moviesAdapter.setListener(new OnMovieClickListener() {
                @Override
                public void onItemClick(int position) {
                    Movie movie = moviesAdapter.getMovie(position);
                    Toast.makeText(getActivity(), movie.getTitle(), Toast.LENGTH_LONG).show();
                    context.changeFragment(DetailFragment.newInstance(movie.getId()));
                }
            });
        }

    }
}
