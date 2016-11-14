package com.example.apolcz.mysong.listeners;

import android.graphics.Color;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.util.Log;

/**
 * Created by apolcz on 12.11.2016.
 */
public class Listener {
    boolean startTuningBool = true;
    //        int sampleRate = 44100;
//        int sampleSizeInBits = 16;
//        int channels = 1;
//        boolean signed = true;
//        boolean bigEndian = false;
    AudioRecord tuner;
    int audioSource = MediaRecorder.AudioSource.MIC;
    int sampleRateInHz = AudioTrack.getNativeOutputSampleRate(AudioManager.STREAM_SYSTEM);
    int channelConfig = AudioFormat.CHANNEL_CONFIGURATION_MONO;
    int audioFormat = AudioFormat.ENCODING_PCM_16BIT;
    int  bufferSizeInBytes = 4096;
    short[] audioData;
    int samples;

    public void startRecording()
    {
        tuner = new AudioRecord(audioSource, sampleRateInHz, channelConfig, audioFormat, bufferSizeInBytes);
        audioData = new short[bufferSizeInBytes];
        try {
            tuner.startRecording();
            samples = tuner.read(audioData, 0, bufferSizeInBytes);
        }
        catch (Throwable t){
            String a = "z";

        }
        for(int i = 2; i < samples; i++){
            String value = ( String.valueOf(audioData[i]));
            Log.d("AUDIO VALUES: " ,value);
        }
    }

}
