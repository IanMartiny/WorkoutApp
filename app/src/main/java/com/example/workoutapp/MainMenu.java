package com.example.workoutapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainMenu extends AppCompatActivity {
    public static String WORKOUT_DAY = "com.example.myfirstapp.MESSAGE";
    private DBHelper workoutDatabase;
    ArrayList<Exercise> exercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        workoutDatabase = new DBHelper(this);
    }

    public void startWorkout(View view) {
        switch(view.getId()){
            case R.id.Pull_Button:
                WORKOUT_DAY = "pull";
                exercises = new ArrayList<> (Arrays.asList(new Exercise(new String[] {getString(R.string.Pull1)}),
                        new Exercise(new String[] {getString(R.string.Pull2)}),
                        new Exercise(new String[] {getString(R.string.Pull3)}),
                        new Exercise(new String[] {getString(R.string.Pull4)}),
                        new Exercise(new String[] {getString(R.string.Pull5)}),
                        new Exercise(new String[] {getString(R.string.Pull6)}),
                        new Exercise(new String[] {getString(R.string.Pull7)}),
                        new Exercise(new String[] {getString(R.string.Pull8)})));
                break;
            case R.id.Push_Button:
                WORKOUT_DAY = "push";
                exercises = new ArrayList<>(Arrays.asList(new Exercise(new String[] {getString(R.string.Push1)}),
                        new Exercise(new String[] {getString(R.string.Push2)}),
                        new Exercise(new String[] {getString(R.string.Push3)}),
                        new Exercise(new String[] {getString(R.string.Push4),
                                getString(R.string.Push5)}),
                        new Exercise(new String[] {getString(R.string.Push6),
                                getString(R.string.Push7)}),
                        new Exercise(new String[] {getString(R.string.Push8)}),
                        new Exercise(new String[] {getString(R.string.Push9)})));
                break;
            case R.id.Legs_Button:
                WORKOUT_DAY = "legs";
                exercises = new ArrayList<>(Arrays.asList(new Exercise(new String[] {getString(R.string.Legs1)}),
                        new Exercise(new String[] {getString(R.string.Legs2)}),
                        new Exercise(new String[] {getString(R.string.Legs3)}),
                        new Exercise(new String[] {getString(R.string.Legs4)}),
                        new Exercise(new String[] {getString(R.string.Legs5)}),
                        new Exercise(new String[] {getString(R.string.Legs6)}),
                        new Exercise(new String[] {getString(R.string.Legs7)})));
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
