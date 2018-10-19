package com.zoran.dialogfragmenttest;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;

/**
 * Created by zqs on 2018/10/15.
 */
public class MyDialogFragment extends DialogFragment {
    FrameLayout fl_content;
    FragmentManager fragmentManager;

    MyFragment myFragment1 = new MyFragment();
    MyCancelFragment myFragment2 = new MyCancelFragment();
    Fragment curFragment = myFragment1;

    private View contentView;  //这个就是等于mView的地址

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("zqs", "MyDialogFragment onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("zqs", "MyDialogFragment onCreate");
        fragmentManager = getChildFragmentManager();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("zqs", "MyDialogFragment onCreateView");
        if(contentView == null) {
            contentView = inflater.inflate(R.layout.fragment_dialog, container, false);
            fl_content = contentView.findViewById(R.id.fl_content);
            contentView.findViewById(R.id.tv_tips).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    if(curFragment == null || curFragment == myFragment2) curFragment = myFragment1;
//                    else  curFragment = myFragment2;
                    //java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState
                    fragmentManager.beginTransaction().replace(R.id.fl_content,curFragment).commit();
//                    if (curFragment.isAdded()) fragmentManager.beginTransaction().remove(curFragment).commit();
//                    fragmentManager.beginTransaction().add(curFragment,"aa").commit();
                }
            });
        } else {
            //Solution: java.lang.IllegalStateException: DialogFragment can not be attached to a container view
            ViewGroup parent = (ViewGroup) contentView.getParent();
            if(parent != null) parent.removeView(contentView);
        }
        return contentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("zqs", "MyDialogFragment onActivityCreated");
    }

    @Override
    public void onPause() {
        Log.e("zqs", "MyDialogFragment onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.e("zqs", "MyDialogFragment onStop");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        Log.e("zqs", "MyDialogFragment onDestroy");
        super.onDestroy();
    }
}

