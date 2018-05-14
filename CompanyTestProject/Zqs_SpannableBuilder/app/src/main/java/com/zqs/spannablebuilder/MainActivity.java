package com.zqs.spannablebuilder;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv_one = findViewById(R.id.tv_one);
        tv_one.setText(new SpanUtils().append("共击败了")
                                      .append("146").setForegroundColor(ContextCompat.getColor(this,R.color.color_e79c1e))
                                      .append("位司机").create());

        TextView tv_two = findViewById(R.id.tv_two);
        SpannableStringBuilder ssb = new SpannableStringBuilder("共击败了146位司机");
        ssb.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this,R.color.color_e79c1e)), 4, 7, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        tv_two.setText(ssb);

        //testFlag(tv_two);

    }

    /**
     * 测试 flag Spanned.SPAN_INCLUSIVE_INCLUSIVE的用意
     * @param tv_two
     */
    private void testFlag(TextView tv_two) {
        String str = "Click abcd";
        SpannableStringBuilder ssb = new SpannableStringBuilder(str);
        ssb.setSpan(new ForegroundColorSpan(Color.BLUE),0, 7,Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        ssb.insert(0,"avbbb"); ssb.append(" abbb");
        tv_two.setText(ssb);
    }

    @Override
    public void onBackPressed() {
        Log.e("zqs", "onBackPressed()");
        super.onBackPressed();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.e("zqs", "onKeyDown() event = " +  event.getAction());
        return true;
        // return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.e("zqs", "dispatchKeyEvent() event =" + event.getAction());
        return super.dispatchKeyEvent(event);
    }

}

