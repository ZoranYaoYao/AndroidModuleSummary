package com.zqs.baidu.tts.synthesis.dida;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.baidu.tts.client.SpeechError;
import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.SpeechSynthesizerListener;
import com.baidu.tts.client.TtsMode;

import java.io.UnsupportedEncodingException;

/**
 * Created by herr.wang on 2017/3/7.
 */

public class TtsSpeech {

    private Object tag;
    private SpeechSynthesizer mSpeechSynthesizer;
    private Context mContext;
    private SpeechListener speechListener;
    private boolean isSpeaking;

    public TtsSpeech(Context context) {
        mContext = context;
    }

    public void setSpeechListener(SpeechListener speechListener){
        this.speechListener = speechListener;
    }

    /**
     * @param appId
     * @param apiKey
     * @param secretKey
     */
    public void initTts(String appId, String apiKey, String secretKey) {
        this.mSpeechSynthesizer = SpeechSynthesizer.getInstance();
        this.mSpeechSynthesizer.setContext(mContext);
        this.mSpeechSynthesizer.setSpeechSynthesizerListener(speechSynthesizerListener);
        // 请替换为语音开发者平台上注册应用得到的App ID (离线授权)
        this.mSpeechSynthesizer.setAppId(appId);
        // 请替换为语音开发者平台注册应用得到的apikey和secretkey (在线授权)
        this.mSpeechSynthesizer.setApiKey(apiKey, secretKey);
        // 发音人（在线引擎），可用参数为0,1,2,3。。。（服务器端会动态增加，各值含义参考文档，以文档说明为准。0--普通女声，1--普通男声，2--特别男声，3--情感男声。。。）
        this.mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER, "0");
        // 设置Mix模式的合成策略
        this.mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_MIX_MODE, SpeechSynthesizer.MIX_MODE_HIGH_SPEED_NETWORK);
        // 设置声音大小
        this.mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_VOLUME, "7");
//        this.mSpeechSynthesizer.setStereoVolume(10, 10);
        // 授权检测接口(只是通过AuthInfo进行检验授权是否成功。)
        // AuthInfo接口用于测试开发者是否成功申请了在线或者离线授权，如果测试授权成功了，可以删除AuthInfo部分的代码（该接口首次验证时比较耗时），不会影响正常使用（合成使用时SDK内部会自动验证授权）
        this.mSpeechSynthesizer.auth(TtsMode.ONLINE);
        // 初始化tts
        mSpeechSynthesizer.initTts(TtsMode.ONLINE);

    }

    /**
     * @param speech
     */
    public void speak(String speech) {
        try {
            if (TextUtils.isEmpty(speech) || speech.getBytes("GBK").length > 1024) {
                return;
            }
            if (mSpeechSynthesizer == null) {
                return;
            }

            int result = mSpeechSynthesizer.speak(speech);
            if (result < 0) {
                Log.e("zqs","speak failed. error code is " + result);
            } else {
                Log.e("zqs","result = " + result + "\t speak:" + speech);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        mSpeechSynthesizer.pause();
    }

    public void resume() {
        mSpeechSynthesizer.resume();
    }

    public void stop() {
        if (mSpeechSynthesizer != null) {
            mSpeechSynthesizer.stop();
        }
    }

    public void release() {
        if (mSpeechSynthesizer != null) {
            mSpeechSynthesizer.release();
            mSpeechSynthesizer = null;
        }
    }

    //not implemented now.
    private SpeechSynthesizerListener speechSynthesizerListener = new SpeechSynthesizerListener() {
        @Override
        public void onSynthesizeStart(String s) {
        }

        @Override
        public void onSynthesizeDataArrived(String s, byte[] bytes, int i) {

        }

        @Override
        public void onSynthesizeFinish(String s) {
        }

        @Override
        public void onSpeechStart(String s) {
            isSpeaking = true;
            if(speechListener != null){
                speechListener.onSpeechStart();
            }
        }

        @Override
        public void onSpeechProgressChanged(String s, int i) {

        }

        @Override
        public void onSpeechFinish(String s) {
            isSpeaking = false;
            if(speechListener != null){
                speechListener.onSpeechFinish();
            }
        }

        @Override
        public void onError(String s, SpeechError speechError) {
            isSpeaking = false;
            Log.e("zqs","onError..." + speechError.description + "\t" + speechError.code);
            if(speechListener != null){
                speechListener.onSpeechFinish();
            }
        }
    };


    public void setTag(Object tag) {
        this.tag = tag;
    }

    public boolean isSpeaking(){
        return isSpeaking;
    }



    public Object getTag() {
        return tag;
    }

    public interface SpeechListener{
        void onSpeechStart();
        void onSpeechFinish();
    }

}
