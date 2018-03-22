package com.jorgereina.jpmc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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

    private TextView schoolNameTv;
    private TextView emailTv;
    private TextView phoneTv;
    private TextView addressTv;
    private TextView cityTv;
    private TextView testTakersTv;
    private TextView readingScoresTv;
    private TextView mathScoresTv;
    private TextView writingScoresTv;
    private ProgressBar progressBar;
    private LinearLayout scoresLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        progressBar = findViewById(R.id.details_pb);
        progressBar.setVisibility(View.VISIBLE);
        scoresLayout = findViewById(R.id.details_sat_layout);
        scoresLayout.setVisibility(View.INVISIBLE);
        schoolNameTv = findViewById(R.id.details_school_name_tv);
        emailTv = findViewById(R.id.details_email_tv);
        phoneTv = findViewById(R.id.details_phone_number_tv);
        addressTv= findViewById(R.id.details_address_tv);
        cityTv= findViewById(R.id.details_city_tv);
        testTakersTv = findViewById(R.id.details_test_takers_tv);
        readingScoresTv = findViewById(R.id.details_average_reading_score_tv);
        mathScoresTv = findViewById(R.id.details_average_math_score_tv);
        writingScoresTv = findViewById(R.id.details_average_writing_score_tv);

        schoolNameTv.setText(getSchoolInfoFromIntent("SCHOOL_NAME"));
        emailTv.setText(getSchoolInfoFromIntent("SCHOOL_EMAIL"));
        phoneTv.setText(getSchoolInfoFromIntent("SCHOOL_PHONE_NUMBER"));
        addressTv.setText(getSchoolInfoFromIntent("SCHOOL_ADDRESS"));
        cityTv.setText(getSchoolInfoFromIntent("SCHOOL_CITY"));
        getSatScoresRequest();
    }

    private void getSatScoresRequest() {
        final String schoolDbn = getSchoolInfoFromIntent("SCHOOL_DBN");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SAT_SCORE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SATService service = retrofit.create(SATService.class);

        Call<List<SATScore>> scores = service.listScore();
        scores.enqueue(new Callback<List<SATScore>>() {
            @Override
            public void onResponse(Call<List<SATScore>> call, Response<List<SATScore>> response) {
                progressBar.setVisibility(View.GONE);
                scoresLayout.setVisibility(View.VISIBLE);
                List<SATScore> scores = response.body();
                for (int i = 0; i < scores.size(); i++) {
                    if (scores.get(i).getDbn().equals(schoolDbn)) {
                        testTakersTv.setText(getSchoolInfo(R.string.test_takers, scores.get(i).getTestTakers()));
                        readingScoresTv.setText(getSchoolInfo(R.string.reading_scores, scores.get(i).getReadingScore()));
                        mathScoresTv.setText(getSchoolInfo(R.string.math_scores, scores.get(i).getMathScore()));
                        writingScoresTv.setText(getSchoolInfo(R.string.writing_scores, scores.get(i).getWritingScore()));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<SATScore>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private String getStringResource(int id) {
        return getResources().getString(id);
    }

    private String getSchoolInfoFromIntent(String field) {
        return getIntent().getStringExtra(field);
    }

    private String getSchoolInfo(int id, String schoolInfo) {
        return getStringResource(id) + schoolInfo;
    }
}
