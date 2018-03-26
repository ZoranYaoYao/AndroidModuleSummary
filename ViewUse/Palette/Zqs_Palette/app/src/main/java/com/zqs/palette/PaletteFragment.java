package com.zqs.palette;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by zqs on 2018/3/26.
 */

public class PaletteFragment extends Fragment{
    private static final String ARG_POSITION = "position";

    private int position;
    private static final int[] drawables = {R.mipmap.one, R.mipmap.two, R.mipmap.four, R.mipmap
            .three, R.mipmap.five};

    //静态工厂方法
    public static PaletteFragment newInstance(int position) {
        PaletteFragment fragment = new PaletteFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_POSITION,position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt(ARG_POSITION);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        FrameLayout fl = new FrameLayout(getActivity());
        fl.setLayoutParams(params);
        fl.setBackgroundResource(drawables[position]);
        final int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,8,
                getResources().getDisplayMetrics());

        TextView textView = new TextView(getActivity());
        params.setMargins(margin, margin, margin, margin);
        textView.setLayoutParams(params);
        textView.setGravity(Gravity.BOTTOM);
        textView.setText("CARD " + (position + 1));

        fl.addView(textView);
        return  fl;
    }

    public static int getBackgroundBitmapPosition(int selectViewpagerItem) {
        return drawables[selectViewpagerItem];
    }
}
