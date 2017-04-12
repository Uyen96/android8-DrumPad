package com.example.honguyen.myapplication;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by HongUyen on 12-Apr-17.
 */

public class SoundManager {
    private static int NUMBER_OF_NOTES = 12;
    public static SoundPool soundPool = new SoundPool(NUMBER_OF_NOTES, AudioManager.STREAM_MUSIC, 0);
    public static ArrayList<Integer> soundIDList = new ArrayList<>();

    public static void loadSoundIntoList(Context context) {
        for (int i = 1; i <= NUMBER_OF_NOTES; i++) {
            int resIDSound = context.getResources().getIdentifier("sound_" + i, "raw", context.getPackageName());
            int soundPoolID = soundPool.load(context, resIDSound, 1);
            soundIDList.add(soundPoolID);
        }
    }

    static HashMap<String, Integer> listSoundId = new HashMap<>();

    static {
        listSoundId.put("A", 0);
        listSoundId.put("B", 1);
        listSoundId.put("C", 2);
        listSoundId.put("D", 3);
        listSoundId.put("E", 4);
        listSoundId.put("F", 5);
        listSoundId.put("G", 6);
        listSoundId.put("H", 7);
        listSoundId.put("I", 8);
        listSoundId.put("K", 9);
        listSoundId.put("L", 10);
        listSoundId.put("M", 11);
    }

    public static void playSound(String string) {
        soundPool.play(soundIDList.get(listSoundId.get(string)), 1.0f, 1.0f, 1, 0, 1.0f);
    }

}
