package com.example.workoutapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ian on 3/1/17.
 */

public class Exercise implements Parcelable{
    String name = new String("");
    private int weight;
    private int increase;
    private int minRep;
    private int maxRep;
    private int numSets;

    public Exercise(String _name, int _weight, int _increase, int _minRep, int _maxRep,
                    int _numSets){
        name = _name;
        weight = _weight;
        increase = _increase;
        minRep = _minRep;
        maxRep = _maxRep;
        numSets = _numSets;
    }

    public Exercise(String _name){
        this(_name, 0, 0, 0, 0, 0);
    }

    public int getWeight(){return weight;}
    public void setWeight(int t){weight = t;}

    public String getName(){return name;}
    public void setName(String s){name = s;}

    public int getIncrease() {return increase;}
    public void setIncrease(int increase) {this.increase = increase;}

    public int getMinRep() {return minRep;}
    public void setMinRep(int minRep) {this.minRep = minRep;}

    public int getMaxRep() {return maxRep;}
    public void setMaxRep(int maxRep) {this.maxRep = maxRep;}

    public int getNumSets() {return numSets;}
    public void setNumSets(int numSets) {this.numSets = numSets;}

    public String toString(){
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // write your object's data to the passed-in Parcel
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeInt(weight);
        out.writeInt(increase);
        out.writeInt(minRep);
        out.writeInt(maxRep);
        out.writeInt(numSets);
    }
    // tnumSets;his is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
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
        name = in.readString();
        weight = in.readInt();
        increase = in.readInt();
        minRep = in.readInt();
        maxRep = in.readInt();
        numSets = in.readInt();
    }
}
