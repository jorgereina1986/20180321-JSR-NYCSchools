package com.jorgereina.jpmc;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jorgereina on 3/20/18.
 */

public class School {

    @SerializedName("school_name")
    private String schoolName;

    @SerializedName("school_email")
    private String schoolEmail;

    @SerializedName("phone_number")
    private String phoneNumber;

    @SerializedName("primary_address_line_1")
    private String address;

    @SerializedName("city")
    private String city;

    @SerializedName("dbn")
    private String dbn;


    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolEmail() {
        return schoolEmail;
    }

    public void setSchoolEmail(String schoolEmail) {
        this.schoolEmail = schoolEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDbn() {
        return dbn;
    }

    public void setDbn(String dbn) {
        this.dbn = dbn;
    }
}
