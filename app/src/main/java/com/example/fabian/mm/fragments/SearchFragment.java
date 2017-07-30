package com.example.fabian.mm.fragments;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fabian.mm.R;
import com.example.fabian.mm.adapters.MoviesAdapter;
import com.example.fabian.mm.helpers.SQLiteHelper;
import com.example.fabian.mm.interfaces.OnMovieClickListener;
import com.example.fabian.mm.model.Movie;
import com.example.fabian.mm.model.MovieResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by fabian on 7/27/17.
 */

public class SearchFragment extends BaseFragment {

    private TextView txtNoResultsFound;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment, container, false);
        initializateViews(view);
        setRetainInstance(true);
        if (savedInstanceState == null) {
            setupAdapter();
            setupRecycler();
        }

        return view;
    }

    private void initializateViews(View view) {
        movieListRecycler = (RecyclerView) view.findViewById(R.id.recycler_view_search);
        EditText edtSearchInput = (EditText) view.findViewById(R.id.search_input_search_fragment);
        txtNoResultsFound = (TextView) view.findViewById(R.id.no_results_search_fragment);

        edtSearchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 3) {
                    getMoviesResult(editable.toString());
                }
            }
        });
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
                    SQLiteHelper sqLiteHelper = new SQLiteHelper(getActivity());
                    sqLiteHelper.addHistory(movie);
                    context.changeFragment(DetailFragment.newInstance(movie.getId()));
                }
            });
        }
    }

    public void getMoviesResult(String query) {
        mService.getMovies(query).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Movie> results = response.body().getResults();
                    if (results != null && moviesAdapter != null) {
                        moviesAdapter.setMovies(results);
                        moviesAdapter.notifyDataSetChanged();
                        hasResults(results.size() > 0);
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                hasResults(false);
            }
        });
    }

    private void hasResults(boolean toggle) {
        if (txtNoResultsFound != null && movieListRecycler != null) {
            movieListRecycler.setVisibility(toggle ? View.VISIBLE : View.GONE);
            txtNoResultsFound.setVisibility(toggle ? View.GONE : View.VISIBLE);
        }
    }
}
