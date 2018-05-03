package com.zqs.baidu.tts.synthesis;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zqs.baidu.tts.synthesis.bean.Voice;
import com.zqs.baidu.tts.synthesis.dida.AudioFocusHandler;
import com.zqs.baidu.tts.synthesis.dida.TtsSpeech;
import com.zqs.baidu.tts.synthesis.dida.VoiceAssistant;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private VoiceAssistant voiceAssistant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPermission(); // android 6.0以上动态权限申请
        checkVoiceAssistant();
    }

    @Override
    protected void onStop() {
        super.onStop();
        voiceAssistant.release();
    }

    /**
     * tts and media
     */
    public void checkVoiceAssistant() {
        if (voiceAssistant == null) {
            voiceAssistant = new VoiceAssistant(BaseApplication.getContext());
            voiceAssistant.setListeners(speechListener, onAudioFocusChangedCallback);
        }
    }

    AudioFocusHandler.OnAudioFocusChangedCallback onAudioFocusChangedCallback = new AudioFocusHandler.OnAudioFocusChangedCallback() {
        @Override
        public void onFocusLost() {
            //dismiss();
        }

        @Override
        public void onFocusGain() {
           // handler.sendEmptyMessageDelayed(MSG_FETCH_ORDER, FETCH_DELAYED_TIME);
        }
    };

    private TtsSpeech.SpeechListener speechListener = new TtsSpeech.SpeechListener() {
        @Override
        public void onSpeechStart() {

        }

        @Override
        public void onSpeechFinish() {
            handler.sendEmptyMessage(1);
        }
    };

    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case MSG_FETCH_ORDER:
//                    fetchAndPopOrder();
//                    break;
//                case MSG_SPEAK_FINISHED:
//                    if (monitorOrderUIStyle == MONITOR_ORDER_UI_DROPDOWN) {
//                        dismiss();
//                    } else if (monitorOrderUIStyle == MONITOR_ORDER_UI_POPUP && DeviceUtil.isOppo()) {
//                        //trigger when app from background to foreground.
//                        DidaBaseActivity topActivity = DidaBaseActivity.getTopActivity();
//                        if(topActivity != null && topActivity.isPaused() && !topActivity.isOrderShowing()) {
//                            fetchAndPopOrder();
//                        }
//                    }
//                    break;
//                case MSG_REQUEST_MONITOR_CONFIG:
//                    if (retryTimes < MAX_RETRY_TIMES) {
//                        requestMonitorConfig();
//                    }
//                    ++retryTimes;
//                    break;
//            }
        }
    };


    /**
     * android 6.0 以上需要动态申请权限
     */
    private void initPermission() {
        String[] permissions = {
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.MODIFY_AUDIO_SETTINGS,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_SETTINGS,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.CHANGE_WIFI_STATE
        };

        ArrayList<String> toApplyList = new ArrayList<String>();

        for (String perm : permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, perm)) {
                toApplyList.add(perm);
                // 进入到这里代表没有权限.
            }
        }
        String[] tmpList = new String[toApplyList.size()];
        if (!toApplyList.isEmpty()) {
            ActivityCompat.requestPermissions(this, toApplyList.toArray(tmpList), 123);
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // 此处为android 6.0以上动态授权的回调，用户自行实现。
    }

    public void onclick(View view) {
        checkVoiceAssistant();
        switch (view.getId()) {
            case R.id.normal:
                speak("Hello", true);
                break;
            case R.id.insert_order:
                Voice voice = new Voice("你播放的语音是20",20, Voice.Type.TEXT,false);
                if (!voice.canInterrupt()) {
                    voiceAssistant.stop();
                    voiceAssistant.playVoice(voice);
                }
                break;
            case R.id.insert_ring:
                Voice voice2 = new Voice("你播放的语音是30",30, Voice.Type.RING,false);
                voiceAssistant.addVoice(voice2);
                voiceAssistant.playVoice(voice2);
                break;
        }

    }

    public void speak(String speech, boolean interrupt) {
        if (voiceAssistant != null) {
            //voiceAssistant.speak(speech, interrupt);
            Voice voice = new Voice("你播放的语音是5.1",5, Voice.Type.TEXT);
            Voice voice2 = new Voice("你播放的语音是5.2",5, Voice.Type.FILE);
            Voice voice3 = new Voice("你播放的语音是5.3",5, Voice.Type.RING);
            Voice voice4 = new Voice("你播放的语音是8",8, Voice.Type.TEXT);
            Voice voice5 = new Voice("你播放的语音是9",9, Voice.Type.TEXT);
            voiceAssistant.addVoice(voice);
            voiceAssistant.addVoice(voice2);
            voiceAssistant.addVoice(voice3);
            voiceAssistant.addVoice(voice4);
            voiceAssistant.addVoice(voice5);
            voiceAssistant.playVoice(voice);
        }
    }


}
