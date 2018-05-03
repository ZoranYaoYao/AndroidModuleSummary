package com.zqs.baidu.tts.synthesis.dida;

import android.content.Context;
import android.media.AudioManager;

import java.lang.ref.WeakReference;

/**
 * Created by herr.wang on 2017/5/10.
 */

public class AudioFocusHandler {
    private WeakReference<Context> mContext;
    private AudioManager mAudioManager;
    private AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener;
    private OnAudioFocusChangedCallback callback;
    public AudioFocusHandler(Context context, OnAudioFocusChangedCallback callback) {
        mContext = new WeakReference<>(context);
        this.callback = callback;
        mAudioManager = (AudioManager) mContext.get().getSystemService(Context.AUDIO_SERVICE);

    }

    public boolean isPlayAllowed() {
        if (onAudioFocusChangeListener == null) {
            onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    switch (focusChange) {
                        case AudioManager.AUDIOFOCUS_LOSS:
                        case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                        case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                            if(callback != null){
                                callback.onFocusLost();
                            }
                            break;
                        case AudioManager.AUDIOFOCUS_GAIN:
                            if(callback != null){
                                callback.onFocusGain();
                            }
                            break;
                    }
                }
            };
        }
        int result = mAudioManager.requestAudioFocus(onAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK);
        return AudioManager.AUDIOFOCUS_REQUEST_GRANTED == result;
    }

    public void abandonFocus(){
        if(mAudioManager != null && onAudioFocusChangeListener != null){
            mAudioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }

    public interface OnAudioFocusChangedCallback{
        void onFocusLost();
        void onFocusGain();
    }
}
