package com.example.workoutapp;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class UpdateDatabaseActivity extends AppCompatActivity {
    private Exercise ex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_database);

        Intent intent = getIntent();
        ex = intent.getParcelableExtra("exercise");
        ((TextView) findViewById(R.id.workout)).setText(ex.getName());

        if (ex.getWeight() > 0)
            ((EditText) findViewById(R.id.edit1)).setText(Integer.toString(ex.getWeight()));
        if (ex.getIncrease() > 0)
            ((EditText) findViewById(R.id.edit2)).setText(Integer.toString(ex.getIncrease()));
        if (ex.getMinRep() > 0)
            ((EditText) findViewById(R.id.edit3)).setText(Integer.toString(ex.getMinRep()));
        if (ex.getMaxRep() > 0)
            ((EditText) findViewById(R.id.edit4)).setText(Integer.toString(ex.getMaxRep()));
        if (ex.getNumSets() > 0)
            ((EditText) findViewById(R.id.edit5)).setText(Integer.toString(ex.getNumSets()));
    }

    public void onClick(View view){
        boolean haveWeight;
        boolean haveIncrease;
        boolean haveMinRep;
        boolean haveMaxRep;
        boolean haveSet;
        String weightStr = ((EditText) findViewById(R.id.edit1)).getText().toString();
        String increaseStr = ((EditText) findViewById(R.id.edit2)).getText().toString();
        String minRepStr = ((EditText) findViewById(R.id.edit3)).getText().toString();
        String maxRepStr = ((EditText) findViewById(R.id.edit4)).getText().toString();
        String setStr = ((EditText) findViewById(R.id.edit5)).getText().toString();
        if (weightStr.equals("")) {
            findViewById(R.id.star1).setVisibility(View.VISIBLE);
            haveWeight = false;
        }
        else{
            findViewById(R.id.star1).setVisibility(View.INVISIBLE);
            haveWeight = true;
        }

        if (increaseStr.equals("")) {
            findViewById(R.id.star2).setVisibility(View.VISIBLE);
            haveIncrease= false;
        }
        else{
            findViewById(R.id.star2).setVisibility(View.INVISIBLE);
            haveIncrease = true;
        }

        if (minRepStr.equals("")) {
            findViewById(R.id.star3).setVisibility(View.VISIBLE);
            haveMinRep = false;
        }
        else{
            findViewById(R.id.star3).setVisibility(View.INVISIBLE);
            haveMinRep = true;
        }

        if (maxRepStr.equals("")) {
            findViewById(R.id.star4).setVisibility(View.VISIBLE);
            haveMaxRep = false;
        }
        else{
            findViewById(R.id.star4).setVisibility(View.INVISIBLE);
            haveMaxRep = true;
        }

        if (setStr.equals("")) {
            findViewById(R.id.star5).setVisibility(View.VISIBLE);
            haveSet = false;
        }
        else{
            findViewById(R.id.star5).setVisibility(View.INVISIBLE);
            haveSet = true;
        }

        if (haveWeight && haveIncrease && haveMinRep && haveMaxRep && haveSet){
            ex.setWeight(Integer.parseInt(weightStr));
            ex.setIncrease(Integer.parseInt(increaseStr));
            ex.setMinRep(Integer.parseInt(minRepStr));
            ex.setMaxRep(Integer.parseInt(maxRepStr));
            ex.setNumSets(Integer.parseInt(setStr));
            Intent returnIntent = new Intent();
            returnIntent.putExtra("exercise", ex);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            String exer = ((TextView) findViewById(R.id.workout)).getText().toString();
            String weight = ((TextView) findViewById(R.id.edit1)).getText().toString();
            String increase = ((TextView) findViewById(R.id.edit2)).getText().toString();
            String minReps = ((TextView) findViewById(R.id.edit3)).getText().toString();
            String maxReps = ((TextView) findViewById(R.id.edit4)).getText().toString();
            String numSets = ((TextView) findViewById(R.id.edit5)).getText().toString();
            setContentView(R.layout.activity_update_database_land);
            ((TextView) findViewById(R.id.workout)).setText(exer);
            ((TextView) findViewById(R.id.edit1)).setText(weight);
            ((TextView) findViewById(R.id.edit2)).setText(increase);
            ((TextView) findViewById(R.id.edit3)).setText(minReps);
            ((TextView) findViewById(R.id.edit4)).setText(maxReps);
            ((TextView) findViewById(R.id.edit5)).setText(numSets);
        }else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            String exer = ((TextView) findViewById(R.id.workout)).getText().toString();
            String weight = ((TextView) findViewById(R.id.edit1)).getText().toString();
            String increase = ((TextView) findViewById(R.id.edit2)).getText().toString();
            String minReps = ((TextView) findViewById(R.id.edit3)).getText().toString();
            String maxReps = ((TextView) findViewById(R.id.edit4)).getText().toString();
            String numSets = ((TextView) findViewById(R.id.edit5)).getText().toString();
            setContentView(R.layout.activity_update_database);
            ((TextView) findViewById(R.id.workout)).setText(exer);
            ((TextView) findViewById(R.id.edit1)).setText(weight);
            ((TextView) findViewById(R.id.edit2)).setText(increase);
            ((TextView) findViewById(R.id.edit3)).setText(minReps);
            ((TextView) findViewById(R.id.edit4)).setText(maxReps);
            ((TextView) findViewById(R.id.edit5)).setText(numSets);
        }
    }
}
