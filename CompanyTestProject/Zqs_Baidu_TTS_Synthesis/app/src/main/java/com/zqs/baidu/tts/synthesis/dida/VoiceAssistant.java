package com.zqs.baidu.tts.synthesis.dida;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.zqs.baidu.tts.synthesis.R;
import com.zqs.baidu.tts.synthesis.bean.Voice;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by herr.wang on 2018/2/25.
 * TODO cache the voice.
 */

public class VoiceAssistant {
    private Context context;
    private TtsSpeech ttsSpeech;
    private TtsSpeech.SpeechListener speechListener;
    private MediaPlayer voicePlayer, ringPlayer;
    private AudioFocusHandler audioFocusHandler;
    private AudioFocusHandler.OnAudioFocusChangedCallback onAudioFocusChangedCallback;

    public LinkedList<Voice> getVoiceQueue() {
        return voiceQueue;
    }

    private LinkedList<Voice> voiceQueue;
    private Voice mCurrentVoice;
    private TtsSpeech.SpeechListener speechCallback = new TtsSpeech.SpeechListener() {
        @Override
        public void onSpeechStart() {
            speechListener.onSpeechStart();
        }

        @Override
        public void onSpeechFinish() {
            if (!mCurrentVoice.canInterrupt()) {
                voiceQueue.clear();
            } else {
                playVoice();
            }

            speechListener.onSpeechFinish();
        }
    };

    private void playVoice(){
        playVoice(null);
    }

    public void playVoice(Voice voice) {
        addVoice(voice);
        mCurrentVoice = fetchVoice();
        if (null != mCurrentVoice) {
            printVoice(mCurrentVoice);
            switch (mCurrentVoice.getType()) {
                case TEXT:
                    playTextVoice(mCurrentVoice);
                    break;
                case RING:
                    playRing();
                    break;
                case FILE:
                    String path = Environment.getExternalStorageDirectory() + File.separator + "zqs" + File.separator +"OUT";
                    File file = new File(path);
                    speak(file);
                    break;
            }
        }
    }

    private void printVoice(Voice voice){
        if (null != voice) {
            Log.e("zqs1", "name= " + voice.getContent() +
                    "\t priority= " + voice.getPriority() +
                    "\t type= " + voice.getType());
        }

    }

    public boolean isPlaying() {
        if (ttsSpeech.isSpeaking() || ringPlayer.isPlaying() || voicePlayer.isPlaying()) {
            return true;
        }
        return false;
    }

    private void playRing() {
        ring(R.raw.dida_order_sound);
    }


    private void playTextVoice(Voice voice) {
        speak(voice.getContent(),voice.canInterrupt());
    }


    public VoiceAssistant(Context context) {
        this.context = context;
        voiceQueue = new LinkedList<>();
    }

    public void setListeners(TtsSpeech.SpeechListener speechListener, AudioFocusHandler.OnAudioFocusChangedCallback onAudioFocusChangedCallback) {
        this.speechListener = speechListener;
        this.onAudioFocusChangedCallback = onAudioFocusChangedCallback;
    }

    private void initTTS() {
        if (context == null) {
            return;
        }
        ttsSpeech = new TtsSpeech(context);
        ttsSpeech.setSpeechListener(speechCallback);
        ttsSpeech.initTts("11177826","qwl8Xb4GsMkzeTm36jEEbWFZ","dcyPnjWadD0Fg0u83ND0MCUMG4neU8SY");
    }

    private void checkTTS() {
        if (ttsSpeech == null) {
            initTTS();
        }
    }

    private void checkAudioFocus() {
        if (audioFocusHandler == null && context != null) {
            audioFocusHandler = new AudioFocusHandler(context, onAudioFocusChangedCallback);
        }
    }

    private void checkVoicePlayer() {
        if (voicePlayer == null) {
            voicePlayer = new MediaPlayer();
            voicePlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            voicePlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    abandonFocus();
                    speechCallback.onSpeechFinish();
                }
            });
        }

    }

    private void checkRingPlayer(int resId) {
        if (ringPlayer == null) {
            ringPlayer = MediaPlayer.create(context, resId);
            ringPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    abandonFocus();
                    Log.e("zqs","ring play over");
                    speechCallback.onSpeechFinish();
//                handler.sendEmptyMessageDelayed(MSG_FETCH_ORDER, FETCH_DELAYED_TIME);
                }
            });
        }
    }

    public void speak(String voiceContent, boolean interrupt) {
        checkAudioFocus();
        checkTTS();
        if (interrupt) {
            stop();
        }
        if (ttsSpeech != null) {
            ttsSpeech.speak(voiceContent);
        }
    }

    public void addVoice(Voice voice) {
        if (voice == null) return;

        if (!voice.canInterrupt()) {
            voiceQueue.clear();
            voiceQueue.add(voice);
        } else {
            if (voiceQueue.size() == 0) {
                voiceQueue.add(0, voice);
                return;
            }

            for (int i = voiceQueue.size()-1; i >= 0; i--) {
                if (voice.getPriority() <= voiceQueue.get(i).getPriority()) {
                    voiceQueue.add(i+1,voice);
                    break;
                }

                if (i == 0) {
                    voiceQueue.add(0, voice);
                }
            }
        }

    }

    public Voice fetchVoice() {
         return voiceQueue.poll();
    }

    public void speak(File voiceContent) {
        checkAudioFocus();
        checkVoicePlayer();
        stop();
        try {
            voicePlayer.reset();
            voicePlayer.setDataSource(context, Uri.fromFile(voiceContent));
            voicePlayer.prepareAsync();
            voicePlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    voicePlayer.start();
                }
            });
            voicePlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    speechCallback.onSpeechFinish();
                    Log.e("zqs","语音广告播放完成");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ring(int resId) {
        checkAudioFocus();
        checkRingPlayer(resId);

        try {
            if (audioFocusHandler != null && audioFocusHandler.isPlayAllowed()) {
                ringPlayer.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void stop() {
        if (ttsSpeech != null && ttsSpeech.isSpeaking()) {
            ttsSpeech.stop();
        }

        try {
            if (voicePlayer != null && voicePlayer.isPlaying()) {
                voicePlayer.stop();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void abandonFocus() {
        if (audioFocusHandler != null) {
            audioFocusHandler.abandonFocus();
        }
    }

    public void release() {
        stop();

        if (ttsSpeech != null) {
            ttsSpeech.release();
            ttsSpeech = null;
        }

        if (voicePlayer != null) {
            voicePlayer.release();
            voicePlayer = null;
        }

        if (ringPlayer != null) {
            ringPlayer.release();
            ringPlayer = null;
        }
    }

}
