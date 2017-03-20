package com.egs.wogal.forsale_items_sat_18_3_2017_100;


import android.Manifest;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;


public class Sound_Play_Record_Helper_10000000 extends AppCompatActivity {

    private static final String LOG_TAG = "AudioRecordTest";
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private static String mFileName = null;

    private boolean mStartRecording = true;
    private boolean mStartPlaying = true;


    private MediaRecorder mRecorder = null;

    private MediaPlayer mPlayer = null;

    // Requesting permission to RECORD_AUDIO
    private boolean permissionToRecordAccepted = false;
    private String[] permissions = {Manifest.permission.RECORD_AUDIO};

    public Sound_Play_Record_Helper_10000000 () {
        mFileName = com.egs.wogal.forsale_items_sat_18_3_2017_100.Storage_Helper_Class.GetVoiceFilePath();        //
    }


    private void onRecord (boolean start) {
        if (start) {
            startRecording();
        } else {
            stopRecording();
        }
    }

    private void onPlay (boolean start) {
        if (start) {
            startPlaying();
        } else {
            stopPlaying();
        }
    }

    private void startPlaying () {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(mFileName);
            mPlayer.prepare();
            mPlayer.start();
            mPlayer.setOnCompletionListener((new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion (MediaPlayer mp) {
                    int aa;
                    aa = 123;
                }
            }));

        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    private void stopPlaying () {
        mPlayer.release();
        mPlayer = null;
    }

    private void startRecording () {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mRecorder.start();
    }

    private void stopRecording () {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }


    // will toggle stop / start
    public void RecordButton (boolean _recState) {
        mStartRecording = _recState;
        onRecord(mStartRecording);
    }

    // will toggle stop / start
    public void PlayButton (boolean _recState) {
        mStartRecording = _recState;
        onPlay(mStartPlaying);
    }


}