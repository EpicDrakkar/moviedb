package com.example.fabian.mm.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DAO {
    private static final int VERSION = 8;
    public static final String DB_NAME = "delorean_base.db";

    public SQLiteDatabase db;
    private static DAO mInstance;
    public static final String TABLE_NAME = "movies";

    protected DAO(Context context) {
        this.db = new SearchSQLiteHelper(context).getWritableDatabase();
    }

    public static DAO getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DAO(context);
        }
        return mInstance;
    }

    private void createMovie(SQLiteDatabase db) {
        db.execSQL(getCreateExecSql());
    }


    public static String getCreateExecSql() {
        return "CREATE TABLE " + TABLE_NAME + " (" +
                MovieEntry.ID + " TEXT PRIMARY KEY, " +
                MovieEntry.NAME + " TEXT, " +
                MovieEntry.PICTURE + " TEXT";
    }

    private class MovieEntry {
        private static final String ID = "id";
        private static final String NAME = "name";
        private static final String PICTURE = "picture";
    }

    private class SearchSQLiteHelper extends SQLiteOpenHelper {
        private SearchSQLiteHelper(Context context) {
            super(context, DB_NAME, null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(getCreateExecSql());
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            if (oldVersion <= 1) {

            }
        }
    }
}
