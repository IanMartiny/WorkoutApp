package com.example.workoutapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class WorkoutActivity extends AppCompatActivity {
    ArrayList<Exercise> exercises;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        Intent intent = getIntent();
        exercises = intent.getParcelableArrayListExtra("exercises");

        ((TextView) findViewById(R.id.workout)).setText(exercises.get(0).toString());
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            String exer = (String) ((TextView) findViewById(R.id.workout)).getText();
            setContentView(R.layout.activity_workout_land);
            ((TextView) findViewById(R.id.workout)).setText(exer);
        }else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            String exer = (String) ((TextView) findViewById(R.id.workout)).getText();
            setContentView(R.layout.activity_workout);
            ((TextView) findViewById(R.id.workout)).setText(exer);
        }
    }
}
