package com.jorgereina.jpmc;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by jorgereina on 3/21/18.
 */

public interface SATService {
    //https://data.cityofnewyork.us/resource/734v-jeq5.json
    @GET("resource/734v-jeq5.json")
    Call<List<SATScore>> listScore();
}
