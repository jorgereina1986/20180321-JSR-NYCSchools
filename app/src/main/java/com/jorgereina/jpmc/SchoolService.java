package com.jorgereina.jpmc;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by jorgereina on 3/20/18.
 */

public interface SchoolService {
    //https://data.cityofnewyork.us/resource/97mf-9njv.json
    @GET("resource/97mf-9njv.json")
    Call<List<School>> listSchools();
}
