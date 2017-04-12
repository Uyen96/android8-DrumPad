package com.example.honguyen.myapplication.touches;

import android.view.View;

/**
 * Created by HongUyen on 12-Apr-17.
 */

public class Touch {
    private float x;
    private float y;
    private int id;
    private int action;

    public Touch(float x, float y, int id, int action) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.action = action;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "Touch{" +
                "x=" + x +
                ", y=" + y +
                ", id=" + id +
                ", action=" + action +
                '}';
    }
    public boolean isInside( View view){
        int [] location = new int [2];
        view.getLocationOnScreen(location);

        int left = location[0];
        int top = location[1];
        int right = left + view.getWidth();
        int bottom = top + view.getHeight();
        return x > left && x < right && y > top && y < bottom;
    }
}
