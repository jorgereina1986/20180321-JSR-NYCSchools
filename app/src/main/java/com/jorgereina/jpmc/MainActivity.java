package com.jorgereina.jpmc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements SchoolAdapter.ItemClickListener {

    private List<School> schoolList = new ArrayList<>();
    private RecyclerView recyclerView;
    private LayoutManager layoutManager;
    private SchoolAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.school_rv);
        layoutManager = new LinearLayoutManager(this);
        adapter = new SchoolAdapter(getApplicationContext(), schoolList, this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        getSchoolsRequest();

    }

    private void getSchoolsRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://data.cityofnewyork.us/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ChaseService service = retrofit.create(ChaseService.class);
        Call<List<School>> schools = service.listSchools();
        schools.enqueue(new Callback<List<School>>() {
            @Override
            public void onResponse(Call<List<School>> call, Response<List<School>> response) {
                Log.d("lagarto", "onResponse: " + response.body().get(0).getSchoolName());
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
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("SCHOOL_NAME", schoolName);
        startActivity(intent);
    }
}