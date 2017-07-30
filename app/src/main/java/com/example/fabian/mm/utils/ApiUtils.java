package com.example.fabian.mm.utils;


import com.example.fabian.mm.remote.MService;
import com.example.fabian.mm.remote.RetrofitClient;

/**
 * Created by fabian on 7/26/17.
 */

public class ApiUtils {
    public static final String BASE_URL = "https://api.themoviedb.org/";
    public static final String IMAGES_BASE_URL = "https://image.tmdb.org/t/p/w500/";

    public static MService getSOService() {
        return RetrofitClient.getClient(BASE_URL).create(MService.class);
    }
}
