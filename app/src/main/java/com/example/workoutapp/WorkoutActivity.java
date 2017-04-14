package com.example.workoutapp;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class WorkoutActivity extends AppCompatActivity {
    ArrayList<Exercise> exercises;
    private int currInd = 0;
    private DBHelper workoutDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        workoutDatabase = new DBHelper(this);
        Intent intent = getIntent();
        exercises = intent.getParcelableArrayListExtra("exercises");

        ((TextView) findViewById(R.id.workout)).setText(exercises.get(0).getName());
        try {
            Cursor c = workoutDatabase.getExercise(exercises.get(currInd).getName());
            c.moveToFirst();

            exercises.set(currInd,new Exercise(c.getString(c.getColumnIndex("exercise")),
                    c.getInt(c.getColumnIndex("weight")),
                    c.getInt(c.getColumnIndex("increase")),
                    c.getInt(c.getColumnIndex("minrep")),
                    c.getInt(c.getColumnIndex("maxrep")),
                    c.getInt(c.getColumnIndex("numsets"))));
            updateDisplay(exercises.get(currInd));
        }
        catch(RuntimeException e){
            e.printStackTrace();
            Intent i = new Intent(this, UpdateDatabaseActivity.class);
            i.putExtra("exercise",exercises.get(currInd));
            startActivityForResult(i,1);
        }
    }

    private int getSet(int buttonId){
        switch (buttonId) {
            case R.id.buttonset1:
                return 1;
            case R.id.buttonset2:
                return 2;
            case R.id.buttonset3:
                return 3;
            case R.id.buttonset4:
                return 4;
            case R.id.buttonset5:
                return 5;
            case R.id.buttonset6:
                return 6;
        }

        return -1;
    }

    private void showNextSet(int buttonId){
        switch(buttonId){
            case R.id.buttonset1:
                (findViewById(R.id.set2)).setVisibility(View.VISIBLE);
                break;
            case R.id.buttonset2:
                (findViewById(R.id.set3)).setVisibility(View.VISIBLE);
                break;
            case R.id.buttonset3:
                (findViewById(R.id.set4)).setVisibility(View.VISIBLE);
                break;
            case R.id.buttonset4:
                (findViewById(R.id.set5)).setVisibility(View.VISIBLE);
                break;
            case R.id.buttonset5:
                (findViewById(R.id.set6)).setVisibility(View.VISIBLE);
                break;
        }
    }

    private String getEditText(int buttonId){
        String s;
        switch(buttonId){
            case R.id.buttonset1:
                try {
                    s = ((EditText) findViewById(R.id.edit1)).getText().toString();
                    return s;
                }
                catch(Exception e){
                    e.printStackTrace();
                    return "";
                }
            case R.id.buttonset2:
                try {
                    s = ((EditText) findViewById(R.id.edit2)).getText().toString();
                    return s;
                }
                catch(Exception e){
                    e.printStackTrace();
                    return "";
                }
            case R.id.buttonset3:
                try {
                    s = ((EditText) findViewById(R.id.edit3)).getText().toString();
                    return s;
                }
                catch(Exception e){
                    e.printStackTrace();
                    return "";
                }
            case R.id.buttonset4:
                try {
                    s = ((EditText) findViewById(R.id.edit4)).getText().toString();
                    return s;
                }
                catch(Exception e){
                    e.printStackTrace();
                    return "";
                }
            case R.id.buttonset5:
                try {
                    s = ((EditText) findViewById(R.id.edit5)).getText().toString();
                    return s;
                }
                catch(Exception e){
                    e.printStackTrace();
                    return "";
                }
            case R.id.buttonset6:
                try {
                    s = ((EditText) findViewById(R.id.edit6)).getText().toString();
                    return s;
                }
                catch(Exception e){
                    e.printStackTrace();
                    return "";
                }
        }

        return "";
    }

    private void updateDisplay(Exercise e){
        ((TextView) findViewById(R.id.workout)).setText(e.getName());
        ((TextView) findViewById(R.id.weight)).setText(Integer.toString(e.getWeight()));
        String reps = new String("/"+e.getMinRep());
        if (e.getMinRep() != e.getMaxRep())
            reps += "-" + e.getMaxRep();

        ((TextView) findViewById(R.id.reps1)).setText(reps);
        ((TextView) findViewById(R.id.reps2)).setText(reps);
        ((TextView) findViewById(R.id.reps3)).setText(reps);
        ((TextView) findViewById(R.id.reps4)).setText(reps);
        ((TextView) findViewById(R.id.reps5)).setText(reps);
    }

    public void onClick(View view) {
        int id = view.getId();
        String s = getEditText(id);
        if(!s.equals("")){
            if (getSet(id) < exercises.get(currInd).getNumSets())
                showNextSet(id);
        }
    }

    private void setReps(String reps){
        ((TextView) findViewById(R.id.reps1)).setText(reps);
        ((TextView) findViewById(R.id.reps2)).setText(reps);
        ((TextView) findViewById(R.id.reps3)).setText(reps);
        ((TextView) findViewById(R.id.reps4)).setText(reps);
        ((TextView) findViewById(R.id.reps5)).setText(reps);
        ((TextView) findViewById(R.id.reps6)).setText(reps);
    }

    private ArrayList<String> getEdits(){
        ArrayList<String> al = new ArrayList<>();
        al.add(((TextView) findViewById(R.id.edit1)).getText().toString());
        al.add(((TextView) findViewById(R.id.edit2)).getText().toString());
        al.add(((TextView) findViewById(R.id.edit3)).getText().toString());
        al.add(((TextView) findViewById(R.id.edit4)).getText().toString());
        al.add(((TextView) findViewById(R.id.edit5)).getText().toString());
        al.add(((TextView) findViewById(R.id.edit6)).getText().toString());

        return al;
    }

    private void setEdits(ArrayList<String> al){
        ((TextView) findViewById(R.id.edit1)).setText(al.get(0));
        ((TextView) findViewById(R.id.edit2)).setText(al.get(1));
        ((TextView) findViewById(R.id.edit3)).setText(al.get(2));
        ((TextView) findViewById(R.id.edit4)).setText(al.get(3));
        ((TextView) findViewById(R.id.edit5)).setText(al.get(4));
        ((TextView) findViewById(R.id.edit6)).setText(al.get(5));
    }

    private ArrayList<View> getVis(){
        ArrayList<View> vis = new ArrayList<>();

        vis.add(findViewById(R.id.set1));
        vis.add(findViewById(R.id.set2));
        vis.add(findViewById(R.id.set3));
        vis.add(findViewById(R.id.set4));
        vis.add(findViewById(R.id.set5));
        vis.add(findViewById(R.id.set6));

        return vis;
    }

    private void setVis(ArrayList<View> vis){
        findViewById(R.id.set1).setVisibility(vis.get(0).getVisibility());
        findViewById(R.id.set2).setVisibility(vis.get(1).getVisibility());
        findViewById(R.id.set3).setVisibility(vis.get(2).getVisibility());
        findViewById(R.id.set4).setVisibility(vis.get(3).getVisibility());
        findViewById(R.id.set5).setVisibility(vis.get(4).getVisibility());
        findViewById(R.id.set6).setVisibility(vis.get(5).getVisibility());
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            String exer = ((TextView) findViewById(R.id.workout)).getText().toString();
            String weight = ((TextView) findViewById(R.id.weight)).getText().toString();
            String reps = ((TextView) findViewById(R.id.reps1)).getText().toString();
            ArrayList<String> al = getEdits();
            ArrayList<View> vis = getVis();
            setContentView(R.layout.activity_workout_land);
            ((TextView) findViewById(R.id.workout)).setText(exer);
            ((TextView) findViewById(R.id.weight)).setText(weight);
            setReps(reps);
            setEdits(al);
            setVis(vis);
        }else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            String exer = ((TextView) findViewById(R.id.workout)).getText().toString();
            String weight = ((TextView) findViewById(R.id.weight)).getText().toString();
            String reps = ((TextView) findViewById(R.id.reps1)).getText().toString();
            ArrayList<String> al = getEdits();
            ArrayList<View> vis = getVis();
            setContentView(R.layout.activity_workout);
            ((TextView) findViewById(R.id.workout)).setText(exer);
            ((TextView) findViewById(R.id.weight)).setText(weight);
            setReps(reps);
            setEdits(al);
            setVis(vis);
        }
    }

    public void changeWorkout(View view) {
        Intent i = new Intent(this, UpdateDatabaseActivity.class);
        i.putExtra("exercise",exercises.get(currInd));
        startActivityForResult(i,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                Exercise curr = data.getParcelableExtra("exercise");
                exercises.set(currInd, curr);
                int id = workoutDatabase.getId(curr.getName());
                if (id == -1) {
                    workoutDatabase.insertWorkout(curr.getName(), curr.getWeight(),
                            curr.getIncrease(), curr.getMinRep(), curr.getMaxRep(),
                            curr.getNumSets());
                }
                else{
                    workoutDatabase.updateWorkout(id, curr.getName(), curr.getWeight(),
                            curr.getIncrease(), curr.getMinRep(), curr.getMaxRep(),
                            curr.getNumSets());
                }
                updateDisplay(curr);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult
}
