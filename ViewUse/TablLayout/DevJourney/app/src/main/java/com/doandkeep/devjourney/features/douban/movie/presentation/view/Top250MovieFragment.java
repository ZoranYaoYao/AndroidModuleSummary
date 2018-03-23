package com.doandkeep.devjourney.features.douban.movie.presentation.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.doandkeep.devjourney.R;
import com.doandkeep.devjourney.features.douban.movie.presentation.DoubanMovieAdapter;
import com.doandkeep.devjourney.features.douban.movie.presentation.contract.Top250MovieContract;
import com.doandkeep.devjourney.features.douban.movie.presentation.model.MovieListModel;
import com.doandkeep.devjourney.util.ToastUtils;
import com.doandkeep.devjourney.view.cyclerview.DividerItemDecoration;
import com.doandkeep.devjourney.view.cyclerview.EndlessRecyclerViewScrollListener;

import butterknife.BindView;
import butterknife.OnClick;
import timber.log.Timber;

/**
 * Created by zhangtao on 16/8/3.
 */
public class Top250MovieFragment extends DoubanMovieFragment implements Top250MovieContract.View {

    private static final int COUNT_PER_REQ = 20;

    @BindView(R.id.movie_srl)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.movie_rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.progress_view)
    View mProgressView;
    @BindView(R.id.retry_view)
    View mRetryView;

    private DoubanMovieAdapter mAdapter;

    protected Top250MovieContract.Presenter mPresenter;

    public static Top250MovieFragment newInstance() {
        return new Top250MovieFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_douban_movie;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                loadMoreMovies(page * COUNT_PER_REQ);
            }
        });

        mAdapter = new DoubanMovieAdapter();
        mRecyclerView.setAdapter(mAdapter);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST);
        itemDecoration.setDivider(getContext().getResources().getDrawable(R.drawable.divider_douban_movie));
        mRecyclerView.addItemDecoration(itemDecoration);

        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshMovies();
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            loadMovies();
        }
    }

    @Override
    public void showMoives(MovieListModel movieListModel) {
        if (movieListModel != null) {
            mAdapter.setData(movieListModel.getSubjects());
        }
    }

    @Override
    public void showMoreMovies(MovieListModel movieListModel) {
        if (movieListModel != null) {
            mAdapter.addData(movieListModel.getSubjects());
        }
    }

    @Override
    public void setPresenter(Top250MovieContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showLoading() {
        mProgressView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressView.setVisibility(View.GONE);
    }

    @Override
    public void showRetry() {
        mRetryView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        mRetryView.setVisibility(View.GONE);
    }

    @Override
    public void showLoadingMore() {

    }

    @Override
    public void hideLoadingMore() {

    }

    @Override
    public void showError(String msg) {
        ToastUtils.showErrorToase(context(), msg);
    }

    @Override
    public Context context() {
        return context().getApplicationContext();
    }

    @Override
    public void showRefresh() {
        // do nothing
    }

    @Override
    public void hideRefresh() {
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public String getLaber() {
        return "TOP";
    }

    @OnClick(R.id.retry_btn)
    void onRetryBtnClicked() {
        loadMovies();
    }

    private void loadMovies() {
        mPresenter.loadMovies(COUNT_PER_REQ);
    }

    private void refreshMovies() {
        mPresenter.refreshMovies(COUNT_PER_REQ);
    }

    private void loadMoreMovies(int start) {
        Timber.i("loadMoreMovies start:" + start);
        mPresenter.loadMoreMovies(start, COUNT_PER_REQ);
    }

}
