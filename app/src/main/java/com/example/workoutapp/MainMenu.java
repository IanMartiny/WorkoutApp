package com.example.workoutapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainMenu extends AppCompatActivity {
    public static String WORKOUT_DAY = "com.example.myfirstapp.MESSAGE";
    ArrayList<Exercise> exercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void startWorkout(View view) {
        Exercise tmp;
        Exercise[] arr;
        exercises = new ArrayList<>();
        switch(view.getId()){
            case R.id.Pull_Button:
                WORKOUT_DAY = "pull";
                exercises.add(new Exercise(getString(R.string.Pull1)));
                exercises.add(new Exercise(getString(R.string.Pull2)));
                exercises.add(new Exercise(getString(R.string.Pull3)));
                exercises.add(new Exercise(getString(R.string.Pull4)));
                exercises.add(new Exercise(getString(R.string.Pull5)));
                exercises.add(new Exercise(getString(R.string.Pull6)));
                exercises.add(new Exercise(getString(R.string.Pull7)));
                exercises.add(new Exercise(getString(R.string.Pull8)));
                break;
            case R.id.Push_Button:
                WORKOUT_DAY = "push";
                exercises.add(new Exercise(getString(R.string.Push1)));
                exercises.add(new Exercise(getString(R.string.Push2)));
                exercises.add(new Exercise(getString(R.string.Push3)));
                exercises.add(new Exercise(getString(R.string.Push4)));
                exercises.add(new Exercise(getString(R.string.Push5)));
                exercises.add(new Exercise(getString(R.string.Push6)));
                exercises.add(new Exercise(getString(R.string.Push7)));
                exercises.add(new Exercise(getString(R.string.Push8)));
                exercises.add(new Exercise(getString(R.string.Push9)));
                break;
            case R.id.Legs_Button:
                WORKOUT_DAY = "legs";
                exercises.add(new Exercise(getString(R.string.Legs1)));
                exercises.add(new Exercise(getString(R.string.Legs2)));
                exercises.add(new Exercise(getString(R.string.Legs3)));
                exercises.add(new Exercise(getString(R.string.Legs4)));
                exercises.add(new Exercise(getString(R.string.Legs5)));
                exercises.add(new Exercise(getString(R.string.Legs6)));
                exercises.add(new Exercise(getString(R.string.Legs7)));
                break;
        }
        /*try {
            Cursor rs = workoutDatabase.getExcercise(WORKOUT_DAY);
        }
        catch (IllegalStateException e){
            workoutDatabase.insertWorkout(WORKOUT_DAY, exercises);
        }*/
        //Toast.makeText(getApplicationContext(), , Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, WorkoutActivity.class);
        intent.putParcelableArrayListExtra("exercises",exercises);
        startActivity(intent);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_main_menu_land);
        }else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            setContentView(R.layout.activity_main_menu);
        }
    }
}
