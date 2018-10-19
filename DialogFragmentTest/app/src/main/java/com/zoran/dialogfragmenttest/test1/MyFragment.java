package com.zoran.dialogfragmenttest.test1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zoran.dialogfragmenttest.R;

/**
 * Created by zqs on 2018/10/15.
 */
public class MyFragment extends Fragment {
    public static int count = 0;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_content, container, false);
        ((TextView)view.findViewById(R.id.tv_title)).setText(++count + "");
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
