package com.example.fabian.mm.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.fabian.mm.adapters.MoviesAdapter;
import com.example.fabian.mm.interfaces.ActionsListener;
import com.example.fabian.mm.remote.MService;
import com.example.fabian.mm.utils.ApiUtils;

/**
 * Created by fabian on 7/27/17.
 */

public abstract class BaseFragment extends Fragment {

    public MService mService;
    public MoviesAdapter moviesAdapter;
    public RecyclerView movieListRecycler;
    public ActionsListener context;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeApiService();
    }

    public void initializeApiService() {
        mService = ApiUtils.getSOService();
    }

    public void setupRecycler() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        movieListRecycler.setLayoutManager(layoutManager);
        movieListRecycler.setAdapter(moviesAdapter);
        movieListRecycler.setHasFixedSize(true);
    }

    public void showErrorMessage() {
        Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = (ActionsListener) context;
    }

    public abstract void setupAdapter();
}
