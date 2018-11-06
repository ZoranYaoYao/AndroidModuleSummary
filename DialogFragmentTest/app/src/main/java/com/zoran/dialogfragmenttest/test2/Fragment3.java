package com.zoran.dialogfragmenttest.test2;

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

import com.zoran.dialogfragmenttest.R;

/**
 * Created by zqs on 2018/10/15.
 */
public class Fragment3 extends Fragment {
    public static int count = 0;
    View view;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("zqs", "Fragment3 onAttach()");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("zqs", "Fragment3 onCreate()");
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("zqs", "Fragment3 onCreateView()");
        view = inflater.inflate(R.layout.fragment_content, container, false);
        ((TextView)view.findViewById(R.id.tv_title)).setText("Fragment3");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("zqs", "Fragment3 onActivityCreated()");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("zqs", "Fragment3 onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("zqs", "Fragment3 onResume()");
    }


    @Override
    public void onPause() {
        super.onPause();
        Log.e("zqs", "Fragment3 onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("zqs", "Fragment3 onStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("zqs", "Fragment3 onDestroyView()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("zqs", "Fragment3 onDetach()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("zqs", "Fragment3 onDestroy()");
    }
}
