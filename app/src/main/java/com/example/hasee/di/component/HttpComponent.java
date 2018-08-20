package com.example.hasee.di.component;

import com.example.hasee.ui.book.BookFragment;
import com.example.hasee.ui.main.MainActivity;
import com.example.hasee.ui.movie.MovieDetailFragment;
import com.example.hasee.ui.movie.MovieFragment;
import com.example.hasee.ui.mycenter.MyFragment;
import com.example.hasee.ui.news.DetailFragment;
import com.example.hasee.ui.news.ImageBrowseActivity;
import com.example.hasee.ui.news.NewsFragment;
import com.example.hasee.ui.news.ReadTextActivity;
import com.example.hasee.ui.person.LoginActivity;

import dagger.Component;

/**
 * Demo ${CLASS}
 *
 * @author 廖望
 * @date 2018/8/20 13:33
 */
@Component(dependencies = ApplicationComponent.class)
public interface HttpComponent {

    void inject(NewsFragment newsFragment);

    void inject(BookFragment bookFragment);

    void inject(MovieFragment movieFragment);

    void inject(MyFragment myFragment);

    void inject(DetailFragment detailFragment);

    void inject(MovieDetailFragment movieDetailFragment);

    void inject(ImageBrowseActivity imageBrowseActivity);

    void inject(LoginActivity loginActivity);

    void inject(MainActivity mainActivity);

    void inject(ReadTextActivity readTextActivity);
}
