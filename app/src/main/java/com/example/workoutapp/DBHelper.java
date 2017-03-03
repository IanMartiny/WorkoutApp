package com.example.workoutapp;

/**
 * Created by ian on 3/1/17.
 */

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "workoutDatabase.db";
    public static final String EXERCISES_TABLE_NAME = "exercises";
    public static final String EXERCISES_COLUMN_ID = "id";
    public static final String EXERCISES_COLUMN_EXERCISE = "exercise";
    public static final String EXERCISES_COLUMN_WEIGHT = "weight";
    public static final String EXERCISES_COLUMN_INCREASE = "increase";
    public static final String EXERCISES_COLUMN_MIN_REP = "minrep";
    public static final String EXERCISES_COLUMN_MAX_REP = "maxrep";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table " + EXERCISES_TABLE_NAME +
                        "(id integer primary key, " + EXERCISES_COLUMN_EXERCISE + " text," +
                        EXERCISES_COLUMN_WEIGHT + " integer," +
                        EXERCISES_COLUMN_INCREASE + " integer," +
                        EXERCISES_COLUMN_MIN_REP + " integer," +
                        EXERCISES_COLUMN_MAX_REP + " integer)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + EXERCISES_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertWorkout (String exercise, int weight, int increase,
                                  int minRep, int maxRep) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EXERCISES_COLUMN_EXERCISE, exercise);
        contentValues.put(EXERCISES_COLUMN_WEIGHT, weight);
        contentValues.put(EXERCISES_COLUMN_INCREASE, increase);
        contentValues.put(EXERCISES_COLUMN_MIN_REP, minRep);
        contentValues.put(EXERCISES_COLUMN_MAX_REP, maxRep);
        db.insert(EXERCISES_TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + EXERCISES_TABLE_NAME + " where id="+id+"", null );
        return res;
    }

    public Cursor getExcercise(String exercise) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + EXERCISES_TABLE_NAME + " where " +
                EXERCISES_COLUMN_EXERCISE + "="+exercise+"", null );
        return res;
    }


    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, EXERCISES_TABLE_NAME);
        return numRows;
    }

    public boolean updateWorkout (Integer id, String exercise, int weight, int increase,
                                  int minRep, int maxRep) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EXERCISES_COLUMN_EXERCISE, exercise);
        contentValues.put(EXERCISES_COLUMN_WEIGHT, weight);
        contentValues.put(EXERCISES_COLUMN_INCREASE, increase);
        contentValues.put(EXERCISES_COLUMN_MIN_REP, minRep);
        contentValues.put(EXERCISES_COLUMN_MAX_REP, maxRep);
        db.update(EXERCISES_TABLE_NAME, contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteWorkout (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(EXERCISES_TABLE_NAME,
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllExercises() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + EXERCISES_TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(EXERCISES_TABLE_NAME)));
            res.moveToNext();
        }
        return array_list;
    }
}
