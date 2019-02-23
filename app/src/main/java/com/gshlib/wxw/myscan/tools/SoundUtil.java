package com.gshlib.wxw.myscan.tools;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Vibrator;

import com.gshlib.wxw.myscan.R;

import java.io.IOException;

public class SoundUtil {
    Activity activity;
    boolean playBeep = true;
    MediaPlayer mediaPlayer = null;
    private static final float BEEP_VOLUME = 0.10f;
    private static final long VIBRATE_DURATION = 200L;

    public SoundUtil(Activity activity) {
        this.activity = activity;
        AudioManager audioService = (AudioManager) activity
                .getSystemService(Context.AUDIO_SERVICE);
        // 如果手机是震动模式就震动
        if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
            playBeep = false;
            return;
        }
        playBeep = true;
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setOnCompletionListener(beepListener);
        AssetFileDescriptor file = activity.getResources()
                .openRawResourceFd(R.raw.beep);
        try {
            mediaPlayer.setDataSource(file.getFileDescriptor(),
                    file.getStartOffset(), file.getLength());
            file.close();
            mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
            mediaPlayer.prepare();
        } catch (IOException e) {
            mediaPlayer = null;
        }
    }

    private final MediaPlayer.OnCompletionListener beepListener = new MediaPlayer.OnCompletionListener() {
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.seekTo(0);
        }
    };

    public void playBeep() {
        if (playBeep && mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    public void playVibrator() {
        Vibrator vibrator = (Vibrator) activity
                .getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(VIBRATE_DURATION);
    }

    public void playPrompt() {
        playBeep();
        playVibrator();
    }
}