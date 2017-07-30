package com.example.fabian.mm.remote;

import com.example.fabian.mm.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MService {
    @GET("3/search/movie?api_key=9101601cde657ddff84db932cb019d8a")
    Call<MovieResponse> getMovies(@Query("query") String adId);
}
