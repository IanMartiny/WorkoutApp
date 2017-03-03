package com.example.workoutapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ian on 3/1/17.
 */

public class Exercise implements Parcelable{
    String first = "";
    String second = "";
    public Exercise(String[] _names){
        first = _names[0];
        if (_names.length > 1)
            second = _names[1];
    }

    public String toString(){
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(first);
        if (!second.equals(""))
                strBuilder.append(", " + second);
        return strBuilder.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // write your object's data to the passed-in Parcel
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(first);
        out.writeString(second);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Exercise> CREATOR = new Parcelable.Creator<Exercise>() {
        public Exercise createFromParcel(Parcel in) {
            return new Exercise(in);
        }

        public Exercise[] newArray(int size) {
            return new Exercise[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private Exercise(Parcel in) {
        first = in.readString();
        second = in.readString();
    }
}
