package com.example.demo1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zqs on 2018/3/14.
 */

public class PageFragment extends Fragment{
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;
    private RecyclerView lv;

    public static PageFragment newInstance(int page) {
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_PAGE,page);
        PageFragment pageFragment = new PageFragment();
        pageFragment.setArguments(bundle);
        return pageFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page,null);
        lv = view.findViewById(R.id.lv);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        lv.setLayoutManager(layoutManager);

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i + "");
        }
        lv.setAdapter(new MyAdapter(list));
        return view;
    }
}
