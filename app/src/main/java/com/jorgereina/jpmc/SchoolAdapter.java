package com.jorgereina.jpmc;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jorgereina on 3/20/18.
 */

public class SchoolAdapter extends RecyclerView.Adapter<SchoolAdapter.SchoolViewHolder> {

    private Context context;
    private List<School> schoolList;
    ItemClickListener itemClickListener;


    public SchoolAdapter(Context context, List<School> schoolList, ItemClickListener itemClickListener) {
        this.context = context;
        this.schoolList = schoolList;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public SchoolViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.school_item, parent, false);
        return new SchoolViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SchoolViewHolder holder, int position) {
        School school = schoolList.get(position);
        holder.bind(school);
    }

    @Override
    public int getItemCount() {
        return schoolList.size();
    }

    //ViewHolder class
    class SchoolViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView schoolName;

        public SchoolViewHolder(View itemView) {
            super(itemView);

            schoolName = itemView.findViewById(R.id.school_name);
            itemView.setOnClickListener(this);
        }

        void bind(School school){
            schoolName.setText(school.getSchoolName());
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            itemClickListener.onItemClick(position);
        }
    }

    public interface ItemClickListener {
        void onItemClick(int itemIndex);
    }
}
