package com.jorgereina.jpmc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailsActivity extends AppCompatActivity {

    private static final String SAT_SCORE_URL = "https://data.cityofnewyork.us/";

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        textView = findViewById(R.id.details_school_name);
        Intent getIntent = getIntent();
        textView.setText(getIntent.getStringExtra("SCHOOL_NAME"));

        getSatScores();
    }

    private void getSatScores() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SAT_SCORE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SATService service = retrofit.create(SATService.class);

        Call<List<SATScores>> scores = service.listScore();
        scores.enqueue(new Callback<List<SATScores>>() {
            @Override
            public void onResponse(Call<List<SATScores>> call, Response<List<SATScores>> response) {
                Log.d("lagarto", "onResponse: "+ response.body().get(0).getDbn());
            }

            @Override
            public void onFailure(Call<List<SATScores>> call, Throwable t) {

            }
        });
    }
}
