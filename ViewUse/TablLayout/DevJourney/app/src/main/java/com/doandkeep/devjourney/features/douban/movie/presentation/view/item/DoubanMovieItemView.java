package com.doandkeep.devjourney.features.douban.movie.presentation.view.item;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.doandkeep.devjourney.R;
import com.doandkeep.devjourney.features.douban.movie.presentation.model.MovieModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhangtao on 16/8/5.
 */
public class DoubanMovieItemView extends FrameLayout {

    @BindView(R.id.movie_name_tv)
    TextView mNameTV;

    private MovieModel movie;

    public DoubanMovieItemView(Context context) {
        super(context);
        init(context);
    }

    public DoubanMovieItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        View.inflate(context, R.layout.item_view_douban_movie, this);
        ButterKnife.bind(this, this);
    }

    public void setData(MovieModel movie) {
        this.movie = movie;

        mNameTV.setText(movie.getTitle());
    }
}