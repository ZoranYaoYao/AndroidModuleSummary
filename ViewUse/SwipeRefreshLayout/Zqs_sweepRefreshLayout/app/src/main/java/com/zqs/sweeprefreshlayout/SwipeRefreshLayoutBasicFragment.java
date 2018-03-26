package com.zqs.sweeprefreshlayout;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * Created by zqs on 2018/3/23.
 */

public class SwipeRefreshLayoutBasicFragment extends Fragment{

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private ListView mListView;

    private ArrayAdapter<String> mListAdpter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sample, container, false);
        mSwipeRefreshLayout = view.findViewById(R.id.swiperefresh);

        mSwipeRefreshLayout.setColorScheme(
                R.color.swipe_color_1,R.color.swipe_color_2,
                R.color.swipe_color_3,R.color.swipe_color_4);

        mListView = view.findViewById(android.R.id.list);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mListAdpter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,
                android.R.id.text1,
                Cheeses.randomList(20));
        mListView.setAdapter(mListAdpter);

        /**
         * Implement {@link SwipeRefreshLayout.OnRefreshListener}. When users do the "swipe to
         * refresh" gesture, SwipeRefreshLayout invokes
         * {@link SwipeRefreshLayout.OnRefreshListener#onRefresh onRefresh()}. In
         * {@link SwipeRefreshLayout.OnRefreshListener#onRefresh onRefresh()}, call a method that
         * refreshes the content. Call the same method in response to the Refresh action from the
         * action bar.
         */
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                inititateRefresh();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_refresh:
                if (!mSwipeRefreshLayout.isRefreshing()) {
                    mSwipeRefreshLayout.setRefreshing(true);
                }
                inititateRefresh();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void inititateRefresh() {
        new DumpBackgroundTask().execute();
    }

    private class DumpBackgroundTask extends AsyncTask<Void,Void, List<String>>{
        @Override
        protected List<String> doInBackground(Void... voids) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return Cheeses.randomList(20);
        }

        @Override
        protected void onPostExecute(List<String> result) {
            super.onPostExecute(result);
            onRefreshComplete(result);
        }
    }

    private void onRefreshComplete(List<String> result) {
        mListAdpter.clear();

        for (String cheese : result) {
            mListAdpter.add(cheese);
        }

        //Stop the refreshing indicator
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
