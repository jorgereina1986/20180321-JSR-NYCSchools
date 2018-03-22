package com.jorgereina.jpmc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements SchoolAdapter.ItemClickListener {

    private static final String SCHOOL_URL = "https://data.cityofnewyork.us/";

    private List<School> schoolList = new ArrayList<>();
    private RecyclerView recyclerView;
    private LayoutManager layoutManager;
    private SchoolAdapter adapter;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.main_pb);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView = findViewById(R.id.school_rv);
        layoutManager = new LinearLayoutManager(this);
        adapter = new SchoolAdapter(getApplicationContext(), schoolList, this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        getSchoolsRequest();

    }

    private void getSchoolsRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SCHOOL_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SchoolService service = retrofit.create(SchoolService.class);
        Call<List<School>> schools = service.listSchools();
        schools.enqueue(new Callback<List<School>>() {
            @Override
            public void onResponse(Call<List<School>> call, Response<List<School>> response) {
                progressBar.setVisibility(View.GONE);
                schoolList.addAll(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<School>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onItemClick(int itemIndex) {
        School school = schoolList.get(itemIndex);
        openDetailsActivity(school, itemIndex);
    }

    private void openDetailsActivity(School school, int clickedItemIndex) {
        String schoolName = school.getSchoolName();
        String schoolEmail = school.getSchoolEmail();
        String schoolAddress = school.getAddress();
        String schoolCity = school.getCity();
        String schoolPhoneNumber = school.getPhoneNumber();
        String schoolDbn = school.getDbn();
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("SCHOOL_NAME", schoolName);
        intent.putExtra("SCHOOL_EMAIL", schoolEmail);
        intent.putExtra("SCHOOL_ADDRESS", schoolAddress);
        intent.putExtra("SCHOOL_CITY", schoolCity);
        intent.putExtra("SCHOOL_PHONE_NUMBER", schoolPhoneNumber);
        intent.putExtra("SCHOOL_DBN", schoolDbn);
        startActivity(intent);
    }
}
