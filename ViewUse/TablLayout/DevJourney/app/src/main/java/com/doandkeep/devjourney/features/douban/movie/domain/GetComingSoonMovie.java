package com.doandkeep.devjourney.features.douban.movie.domain;

import com.doandkeep.devjourney.base.domain.UseCase;
import com.doandkeep.devjourney.features.douban.movie.domain.repository.DoubanMovieRepo;
import com.doandkeep.devjourney.features.douban.movie.presentation.model.MovieListModel;

import rx.Observable;

/**
 * 获取即将上映电影用例
 * Created by zhangtao on 2016/11/24.
 */

public class GetComingSoonMovie extends UseCase<GetComingSoonMovie.RequestValues, GetComingSoonMovie.ResponseValues> {

    private DoubanMovieRepo mDoubanMovieRepo;

    public GetComingSoonMovie(DoubanMovieRepo doubanMovieRepo) {
        mDoubanMovieRepo = doubanMovieRepo;
    }

    @Override
    protected Observable buildUseCaseObservable(RequestValues requestValues) {
        return mDoubanMovieRepo.movieListForComingSoon(requestValues.getStart(), requestValues.getCount());
    }

    public static class RequestValues implements UseCase.RequestValues {
        private final int start;
        private final int count;

        public RequestValues(int start, int count) {
            this.start = start;
            this.count = count;
        }

        public int getStart() {
            return start;
        }

        public int getCount() {
            return count;
        }
    }

    public static class ResponseValues implements UseCase.ResponseValues {
        private final MovieListModel mMovieListModel;

        public ResponseValues(MovieListModel movieListModel) {
            mMovieListModel = movieListModel;
        }

        public MovieListModel getMovieListModel() {
            return mMovieListModel;
        }
    }
}
