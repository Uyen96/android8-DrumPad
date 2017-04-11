package com.example.honguyen.myapplication;

import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private List<ImageView> ivKeys;

    private List<PressdeKeyInfo> pressedKeyInfoList;
    class PressdeKeyInfo{
        private  ImageView ivKey;
        private int pointerId;

        public PressdeKeyInfo(ImageView ivKey, int pointerId) {
            this.ivKey = ivKey;
            this.pointerId = pointerId;
        }

        public ImageView getIvKey() {
            return ivKey;
        }

        public int getPointerId() {
            return pointerId;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivKeys = new ArrayList<>();

        ivKeys.add((ImageView) findViewById(R.id.iv_key_11));
        ivKeys.add((ImageView) findViewById(R.id.iv_key_12));
        ivKeys.add((ImageView) findViewById(R.id.iv_key_13));
        ivKeys.add((ImageView) findViewById(R.id.iv_key_14));
        ivKeys.add((ImageView) findViewById(R.id.iv_key_21));
        ivKeys.add((ImageView) findViewById(R.id.iv_key_22));
        ivKeys.add((ImageView) findViewById(R.id.iv_key_23));
        ivKeys.add((ImageView) findViewById(R.id.iv_key_24));
        ivKeys.add((ImageView) findViewById(R.id.iv_key_31));
        ivKeys.add((ImageView) findViewById(R.id.iv_key_32));
        ivKeys.add((ImageView) findViewById(R.id.iv_key_33));
        ivKeys.add((ImageView) findViewById(R.id.iv_key_34));

        pressedKeyInfoList = new ArrayList<>();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Log.d(TAG, String.format("onTouchEvent : %s, %s", event.getX(), event.getY()));


        int pointerindex = MotionEventCompat.getActionIndex(event);
        int pointerId = event.getPointerId(pointerindex);

        float pointerX = event.getX(pointerindex);
        float pointerY = event.getY(pointerindex);
        int pointerAction = event.getActionMasked();

        if(pointerAction == MotionEvent.ACTION_MOVE ){
            for(int i = 0; i < pressedKeyInfoList.size(); i++){
                PressdeKeyInfo  pressdeKeyInfo = pressedKeyInfoList.get(i);
                if(pressdeKeyInfo.getPointerId() == pointerId &&
                        !isInside(pointerX, pointerY, pressdeKeyInfo.getIvKey())){
                    pressedKeyInfoList.remove(i);
                    setPressed(pressdeKeyInfo.getIvKey(), false);

                }
            }
        }

        ImageView pressedKey = findPressedKey(pointerX, pointerY);
        if( pressedKey != null){
            if(pointerAction == MotionEvent.ACTION_DOWN || pointerAction == MotionEvent.ACTION_POINTER_DOWN ||
                    pointerAction == MotionEvent.ACTION_MOVE){
                pressedKeyInfoList.add(new PressdeKeyInfo(pressedKey, pointerId));
                setPressed(pressedKey, true);
            }
        }

        if( pressedKey != null){
            if(pointerAction == MotionEvent.ACTION_UP || pointerAction == MotionEvent.ACTION_POINTER_UP){
                for(int i = 0 ; i < pressedKeyInfoList.size(); i++){
                    PressdeKeyInfo pressdeKeyInfo = pressedKeyInfoList.get(i);
                    if(pressdeKeyInfo.getPointerId() == pointerId ){
                        pressedKeyInfoList.remove(i);
                    }
                    setPressed(pressedKey, false);
                }
            }
        }


        Log.d(TAG, String.format("onTouchEvent: %s", pointerindex));


//        if(pointerX > ivBlackKey1.getLeft() && pointerX < ivBlackKey1.getRight()&&
//                pointerY >ivBlackKey1.getTop() && pointerY  < ivBlackKey1.getBottom()){
//            Log.d(TAG, "onTounch : black 1");
//        }
        return super.onTouchEvent(event);
    }
    private boolean contantKeyInfoWith(ImageView ivKey) {
        for (PressdeKeyInfo pressdeKeyInfo : pressedKeyInfoList) {
            //    if(pressdeKeyInfo.getIvKey())
        }
        return true;
    }

    private ImageView findPressedKey( float pointerX, float pointerY){
        for(ImageView ivBlackKey : ivKeys){
            if(isInside(pointerX, pointerY,ivBlackKey)){
                return ivBlackKey;
            }
        }

        return null;
    }
    private boolean isInside( float x, float y , View v){
        int [] location = new int [2];
        v.getLocationOnScreen(location);

        int left = location[0];
        int top = location[1];
        int right = left + v.getWidth();
        int bottom = top + v.getHeight();
        return x > left && x < right && y > top && y < bottom;
    }
    private void setPressed( ImageView vKey, boolean isPressed){
        if(isPressed){
            if(ivKeys.contains(vKey)) vKey.setImageResource(R.drawable.key_red);

        }else {
            if(ivKeys.contains(vKey)) vKey.setImageResource(R.drawable.default_key);

        }
    }
}
