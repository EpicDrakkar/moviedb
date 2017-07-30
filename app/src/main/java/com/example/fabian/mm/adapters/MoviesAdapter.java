package com.example.fabian.mm.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fabian.mm.R;
import com.example.fabian.mm.holders.MovieViewHolder;
import com.example.fabian.mm.interfaces.OnMovieClickListener;
import com.example.fabian.mm.model.Movie;
import com.example.fabian.mm.utils.ApiUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by fabian on 7/26/17.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private List<Movie> movies;
    private Context context;
    private OnMovieClickListener listener;

    public MoviesAdapter(Context context, List<Movie> movies) {
        this.movies = movies;
        this.context = context;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View movieView = inflater.inflate(R.layout.movie_view_holder, parent, false);

        return new MovieViewHolder(movieView);
    }

    @Override
    public void onBindViewHolder(final MovieViewHolder holder, int position) {

        Picasso.with(context).load(ApiUtils.IMAGES_BASE_URL + movies.get(position).getPoster_path()).into(holder.imgCover);
        holder.txtOverview.setText(movies.get(position).getOverview());
        holder.txtTitle.setText(movies.get(position).getTitle());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != movies ? movies.size() : 0);
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public void setListener(OnMovieClickListener listener) {
        this.listener = listener;
    }

    public Movie getMovie(int position) {
        return movies.get(position);
    }
}
