package com.example.honguyen.myapplication;

import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.honguyen.myapplication.touches.Touch;
import com.example.honguyen.myapplication.touches.TouchManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_POINTER_DOWN;
import static android.view.MotionEvent.ACTION_POINTER_UP;
import static android.view.MotionEvent.ACTION_UP;

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
        SoundManager.loadSoundIntoList(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        List<Touch> touches = TouchManager.toTouch(event);
        if (touches.size() > 0) {
            Touch firstTouch = touches.get(0);
            if (firstTouch.getAction() == ACTION_DOWN || firstTouch.getAction() == ACTION_POINTER_DOWN) {
                ImageView pressedKey = findPressedKey(firstTouch);
                if (pressedKey != null && !contantKeyInfoWith(pressedKey)) {
                    pressedKeyInfoList.add(new PressdeKeyInfo(pressedKey, firstTouch.getId()));
                    setPressed(pressedKey, true);
                    // TODO: Play a note
                    SoundManager.playSound(pressedKey.getTag().toString());
                }
            } else if (firstTouch.getAction() == ACTION_UP || firstTouch.getAction() == ACTION_POINTER_UP) {
                Iterator<PressdeKeyInfo> keyInfoIterator = pressedKeyInfoList.iterator();
                while (keyInfoIterator.hasNext()){
                    PressdeKeyInfo pressdeKeyInfo = keyInfoIterator.next();
                    if(firstTouch.getId() == pressdeKeyInfo.getPointerId()){
                        keyInfoIterator.remove();
                        setPressed(pressdeKeyInfo.getIvKey(), false);
                    }
                }

            } else if (firstTouch.getAction() == ACTION_MOVE) {
                for (Touch touch : touches) {
                    ImageView pressedKey = findPressedKey(touch);
                    if (pressedKey != null && !contantKeyInfoWith(pressedKey)) {
                        pressedKeyInfoList.add(new PressdeKeyInfo(pressedKey, touch.getId()));
                        setPressed(pressedKey, true);
                        // TODO: Play a note
                        SoundManager.playSound(pressedKey.getTag().toString());
                    }
                    Iterator<PressdeKeyInfo> infoIterator = pressedKeyInfoList.iterator();
                    while ( infoIterator.hasNext()){
                        PressdeKeyInfo pressdeKeyInfo = infoIterator.next();
                        ImageView ivKey = pressdeKeyInfo.getIvKey();
                        if (touch.getId() == pressdeKeyInfo.getPointerId() &&
                                !touch.isInside(ivKey)) {
                            infoIterator.remove();
                            setPressed(ivKey, false);

                        }
                    }

                }

            }


        }
        return super.onTouchEvent(event);
    }
    private boolean contantKeyInfoWith(ImageView ivKey) {
        for (PressdeKeyInfo pressdeKeyInfo : pressedKeyInfoList) {
            if(pressdeKeyInfo.getIvKey() == ivKey){
                return true;
            }
        }
        return false;
    }

    private ImageView findPressedKey( Touch touch){
        for(ImageView ivKey : ivKeys){
            if(touch.isInside(ivKey)){
                return ivKey;
            }
        }
        return null;
    }

    private void setPressed( ImageView vKey, boolean isPressed){
        if(isPressed){
            if(ivKeys.contains(vKey)) vKey.setImageResource(R.drawable.key_red);

        }else {
            if(ivKeys.contains(vKey)) vKey.setImageResource(R.drawable.default_key);

        }
    }
}
