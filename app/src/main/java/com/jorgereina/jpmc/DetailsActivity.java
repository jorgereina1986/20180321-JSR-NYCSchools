package com.jorgereina.jpmc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailsActivity extends AppCompatActivity {

    private static final String SAT_SCORE_URL = "https://data.cityofnewyork.us/";

    private TextView schoolName;
    private TextView testTakers;
    private TextView readingScores;
    private TextView mathScores;
    private TextView writingScores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        schoolName = findViewById(R.id.details_school_name_tv);
        testTakers = findViewById(R.id.details_test_takers_tv);
        readingScores = findViewById(R.id.details_average_reading_score_tv);
        mathScores = findViewById(R.id.details_average_math_score_tv);
        writingScores = findViewById(R.id.details_average_writing_score_tv);

        schoolName.setText(getSchoolInfo("SCHOOL_NAME"));
        getSatScoresRequest();
    }

    private void getSatScoresRequest() {
        final String schoolDbn = getSchoolInfo("SCHOOL_DBN");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SAT_SCORE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SATService service = retrofit.create(SATService.class);

        Call<List<SATScore>> scores = service.listScore();
        scores.enqueue(new Callback<List<SATScore>>() {
            @Override
            public void onResponse(Call<List<SATScore>> call, Response<List<SATScore>> response) {
                List<SATScore> scores = response.body();

                for (int i = 0; i < scores.size(); i++) {
                    if (scores.get(i).getDbn().equals(schoolDbn)) {

                        SATScore score = scores.get(i);
                        testTakers.setText(getStringResource(R.string.test_takers) + score.getTestTakers());
                        readingScores.setText(getStringResource(R.string.reading_scores) + score.getReadingScore());
                        mathScores.setText(getStringResource(R.string.math_scores) + score.getMathScore());
                        writingScores.setText(getStringResource(R.string.writing_scores) + score.getWritingScore());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<SATScore>> call, Throwable t) {

            }
        });
    }

    private String getSchoolInfo(String field) {
        return getIntent().getStringExtra(field);
    }

    private String getStringResource(int id) {
        return getResources().getString(id);
    }

}
