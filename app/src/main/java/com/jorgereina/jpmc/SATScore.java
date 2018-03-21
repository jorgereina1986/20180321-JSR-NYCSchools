package com.jorgereina.jpmc;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jorgereina on 3/21/18.
 */

public class SATScore {

    @SerializedName("dbn")
    private String dbn;

    @SerializedName("num_of_sat_test_takers")
    private String testTakers;

    @SerializedName("sat_critical_reading_avg_score")
    private String readingScore;

    @SerializedName("sat_math_avg_score")
    private String mathScore;

    @SerializedName("sat_writing_avg_score")
    private String writingScore;

    public String getDbn() {
        return dbn;
    }

    public void setDbn(String dbn) {
        this.dbn = dbn;
    }

    public String getTestTakers() {
        return testTakers;
    }

    public void setTestTakers(String testTakers) {
        this.testTakers = testTakers;
    }

    public String getReadingScore() {
        return readingScore;
    }

    public void setReadingScore(String readingScore) {
        this.readingScore = readingScore;
    }

    public String getMathScore() {
        return mathScore;
    }

    public void setMathScore(String mathScore) {
        this.mathScore = mathScore;
    }

    public String getWritingScore() {
        return writingScore;
    }

    public void setWritingScore(String writingScore) {
        this.writingScore = writingScore;
    }
}
