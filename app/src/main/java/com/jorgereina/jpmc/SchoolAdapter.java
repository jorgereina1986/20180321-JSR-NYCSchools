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
        public TextView email;
        public TextView address;
        public TextView city;
        public TextView phoneNumber;

        public SchoolViewHolder(View itemView) {
            super(itemView);

            schoolName = itemView.findViewById(R.id.main_school_name_tv);
            email = itemView.findViewById(R.id.main_email_tv);
            address = itemView.findViewById(R.id.main_address_tv);
            city = itemView.findViewById(R.id.main_city_tv);
            phoneNumber = itemView.findViewById(R.id.main_phone_number_tv);
            itemView.setOnClickListener(this);
        }

        void bind(School school){
            schoolName.setText(school.getSchoolName());
            email.setText(school.getSchoolEmail());
            address.setText(school.getAddress());
            city.setText(school.getCity());
            phoneNumber.setText(school.getPhoneNumber());
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            itemClickListener.onItemClick(position);
        }
    }

    // Interface to handle click events on RecyclerView items
    public interface ItemClickListener {
        void onItemClick(int itemIndex);
    }
}
