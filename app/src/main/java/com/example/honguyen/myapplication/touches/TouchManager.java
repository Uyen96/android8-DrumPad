package com.example.honguyen.myapplication.touches;

import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

import static android.view.MotionEvent.*;


/**
 * Created by HongUyen on 12-Apr-17.
 */

public class TouchManager {

    public static List<Touch> toTouch(MotionEvent event) {
        int action = event.getActionMasked();
        ArrayList<Touch> touches = new ArrayList<>();

        if (action == ACTION_DOWN || action == ACTION_POINTER_DOWN || action == ACTION_UP || action == ACTION_POINTER_UP) {
            int pointerIndex = event.getActionIndex();
            float pointerX = event.getX(pointerIndex);
            float pointerY = event.getY(pointerIndex);
            int pointerId = event.getPointerId(pointerIndex);
            touches.add(new Touch(pointerX, pointerY,pointerId, action));

        }
        else if(action == ACTION_MOVE){
            for(int pointerIndex = 0; pointerIndex < event.getPointerCount(); pointerIndex++){
                float pointerX = event.getX(pointerIndex);
                float pointerY = event.getY(pointerIndex);
                int pointerId = event.getPointerId(pointerIndex);
                touches.add(new Touch(pointerX, pointerY, pointerId, action));

            }
        }
        return touches;

    }
//    int action = event.getActionMasked();
//    ArrayList<Touch> touches = new ArrayList<>();
//
//        if( action == ACTION_DOWN || action == ACTION_POINTER_DOWN || action == ACTION_UP || action == ACTION_POINTER_UP ){
//        int pointerIndex = event.getActionIndex();
//        float pointerX = event.getX(pointerIndex);
//        float pointerY = event.getY(pointerIndex);
//        int pointerId = event.getPointerId(pointerIndex);
//        touches.add(new Touch(pointerX, pointerY, pointerId,action));
//    }else if(action == ACTION_MOVE){
//        for( int pointerIndex = 0; pointerIndex < event.getPointerCount(); pointerIndex ++){
//            float pointerX = event.getX(pointerIndex);
//            float pointerY = event.getY(pointerIndex);
//            int pointerId = event.getPointerId(pointerIndex);
//            touches.add(new Touch(pointerX, pointerY, pointerId, action));
//        }
//    }
//
//
//        return  touches;
}
