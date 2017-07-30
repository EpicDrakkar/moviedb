package com.example.fabian.mm.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.fabian.mm.model.Movie;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by fabian on 7/27/17.
 */

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Movies";

    private static final String TABLE_HISTORY = "history";
    private static final String MOVIE_ID = "movie_id";
    private static final String DATA = "data";
    private static final String ID = "id";
    private static final String[] COLUMNS = {ID, MOVIE_ID, DATA};

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_MOVIE_TABLE = "CREATE TABLE " + TABLE_HISTORY + " ( " +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MOVIE_ID + " INTEGER, " +
                DATA + " TEXT )";

        sqLiteDatabase.execSQL(CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);
        this.onCreate(sqLiteDatabase);
    }

    public void addHistory(Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MOVIE_ID, movie.getId());
        values.put(DATA, serializeToJson(movie));

        db.insert(TABLE_HISTORY, null, values);
        db.close();
    }

    public Movie getHistory(int movieId) {
        Movie movie = null;
        SQLiteDatabase db = this.getReadableDatabase();


        String select = "SELECT " + DATA +
                " FROM " + TABLE_HISTORY +
                " WHERE " + MOVIE_ID + " ='" + movieId + "' LIMIT 1";
        Cursor cursor = db.rawQuery(select, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            movie = ParseCursor(cursor);
            cursor.close();
        }
        db.close();
        return movie;
    }

    public ArrayList<Movie> getAllHistory() {
        ArrayList<Movie> movies = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();


        String select = "SELECT " + DATA +
                " FROM " + TABLE_HISTORY +
                " ORDER BY " + ID + " ASC";
        Cursor cursor = db.rawQuery(select, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int index = cursor.getColumnIndex(DATA);
                if (index != -1) {
                    movies.add(ParseCursor(cursor));
                }
            }
            cursor.close();
        }
        db.close();
        return movies;
    }

    private Movie ParseCursor(Cursor cursor) {
        return deserializeFromJson(cursor.getString(0));
    }

    private String serializeToJson(Movie movie) {
        Gson gson = new Gson();
        return gson.toJson(movie);
    }

    private Movie deserializeFromJson(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, Movie.class);
    }
}
