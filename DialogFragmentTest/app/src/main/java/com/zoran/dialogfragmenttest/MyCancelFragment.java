package com.zoran.dialogfragmenttest;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by zqs on 2018/10/15.
 */
public class MyCancelFragment extends Fragment {
    public static int count = 0;
    View view;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("zqs", "MyCancelFragment onAttach()");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("zqs", "MyCancelFragment onCreate()");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_content, container, false);
        ((TextView)view.findViewById(R.id.tv_title)).setText("取消模块");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("zqs", "MyCancelFragment onActivityCreated()");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("zqs", "MyCancelFragment onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("zqs", "MyCancelFragment onResume()");
    }


    @Override
    public void onPause() {
        super.onPause();
        Log.e("zqs", "MyCancelFragment onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("zqs", "MyCancelFragment onStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("zqs", "MyCancelFragment onDestroyView()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("zqs", "MyCancelFragment onDetach()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("zqs", "MyCancelFragment onDestroy()");
    }
}
