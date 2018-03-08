package com.example.zoran.recycleview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

/**
 * Created by zqs on 2018/3/6.
 */

public class RecyclerViewFragment extends Fragment {

    public static final String TAG = "RecyclerViewFragment";
    private final int DATA_COUNT = 60;
    private final int SPAN_COUNT = 2;
    private String[] datas;

    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    protected LayoutManagerType mCurrentlayouyMangerType;

    protected RadioButton mLinearLayoutRadioButton;
    protected RadioButton mGridLayoutRadioButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        datas = new String[DATA_COUNT];
        for (int i = 0 ; i < 60; i++) {
            datas[i] = "This is Element #" + i;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recycler_view_frag,container,false);
        rootView.setTag(TAG);
        mRecyclerView = rootView.findViewById(R.id.recyclerView);

//        mLayoutManager = new LinearLayoutManager(getActivity());
//        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        mRecyclerView.setLayoutManager(mLayoutManager);
        setRecycleViewLayoutManager(LayoutManagerType.LINEAR_LAYOUT_MANAGER);

        CustomAdapter customAdapter = new CustomAdapter(datas);
        mRecyclerView.setAdapter(customAdapter);

        mLinearLayoutRadioButton = rootView.findViewById(R.id.linear_layout_rb);
        mLinearLayoutRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecycleViewLayoutManager(LayoutManagerType.LINEAR_LAYOUT_MANAGER);
            }
        });

        mGridLayoutRadioButton = rootView.findViewById(R.id.grid_layout_rb);
        mGridLayoutRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecycleViewLayoutManager(LayoutManagerType.GRID_LAYOUT_MANGER);
            }
        });
        return rootView;
    }

    public void setRecycleViewLayoutManager(LayoutManagerType type) {

        switch (type) {
            case GRID_LAYOUT_MANGER:
                mLayoutManager = new GridLayoutManager(getActivity(),SPAN_COUNT);
                mCurrentlayouyMangerType = LayoutManagerType.GRID_LAYOUT_MANGER;
                break;
            case LINEAR_LAYOUT_MANAGER:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentlayouyMangerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                break;
            default:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentlayouyMangerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        }

        //源码可以看见setLayoutManager会进行测量,布局,绘制
        mRecyclerView.setLayoutManager(mLayoutManager);

    }

    //枚举展示数据类型
    private enum LayoutManagerType {
        GRID_LAYOUT_MANGER,
        LINEAR_LAYOUT_MANAGER
    }
}
