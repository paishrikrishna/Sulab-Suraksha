package com.example.baiscac;

public class DataPoint {
    int xValues, yValues;
    public DataPoint(int xValues,int yValues){
        this.xValues = xValues;
        this.yValues = yValues;
    }
    public DataPoint(){

    }
    public int getxValue(){
        return xValues;
    }
    public int getyValues(){
        return yValues;
    }
}
