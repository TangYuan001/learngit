package com.example.chenhongyuan.Module;

import android.view.View;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by chenhongyuan on 15/7/9.
 */
public interface AllService {
    @GET("/api/4/news/latest")
    Observable<Info> info();
    @GET("/api/4/themes")
    Observable<Themes> themes();
    @GET("/api/4/theme/{id}")
    Observable<ThemeContent> themeContent(@Path("id") int id);
    @GET("/api/4/story/{id}")
    Observable<StoryBody> storyBody(@Path("id") int id);
    @GET("/api/4/story/{id}/long-comments")
    Observable<Comments> longComment(@Path("id") int id);
    @GET("/api/4/story/{id}/short-comments")
    Observable<Comments> shortComment(@Path("id") int id);
    @GET("/api/4/story-extra/{id}")
    Observable<AdditionInfo> addtionInfo(@Path("id") int id);
}
