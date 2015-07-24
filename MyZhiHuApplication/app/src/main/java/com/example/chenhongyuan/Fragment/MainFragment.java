package com.example.chenhongyuan.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.chenhongyuan.Adapter.ViewPagerFragmentPagerAdapter;
import com.example.chenhongyuan.Module.Info;
import com.example.chenhongyuan.Module.AllService;
import com.example.chenhongyuan.Adapter.IndexAdapter;
import com.example.chenhongyuan.Module.StoryBody;
import com.example.chenhongyuan.myzhihuapplication.NewsActivity;
import com.example.chenhongyuan.myzhihuapplication.R;
import com.example.chenhongyuan.myzhihuapplication.SettingActivity;

import retrofit.RestAdapter;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by chenhongyuan on 15/7/14.
 */
public class MainFragment extends Fragment{
    public static final String KEY_STORY = "story";
    public static final String KEY_BODY = "body";
    public static final String KEY_VALUE = "main_value";
    public static final String KEY_NEWS = "newsId";
    ViewPagerFragmentPagerAdapter viewPagerFragmentPagerAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView myRecyclerView;
    IndexAdapter indexAdapter;
    Info mainInfo;
    StoryBody storybody;
    int newsId;
    private boolean isLoading = false;
    RestAdapter adapter = new RestAdapter.Builder()
            .setEndpoint("http://news-at.zhihu.com")
            .setLogLevel(RestAdapter.LogLevel.FULL)
            .build();
    AllService service = adapter.create(AllService.class);

    public void ServiceInfo (){
        service.info().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Info>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.i("log", e.getMessage());
                    }
                    @Override
                    public void onNext(Info info) {
                        mainInfo = info;
                        indexAdapter.setDatas(info);
                    }
                });
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mainfragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle("首页  ");
        swipeRefreshLayout = (SwipeRefreshLayout)getActivity().findViewById(R.id.swipe_container);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                ServiceInfo();
            }
        });
        ServiceInfo();
        viewPagerFragmentPagerAdapter = new ViewPagerFragmentPagerAdapter(getActivity().getSupportFragmentManager());
        myRecyclerView = (RecyclerView)getActivity().findViewById(R.id.main_recyclerView);
        LinearLayoutManager staggeredGridLayoutManager = new LinearLayoutManager(getActivity());
        myRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        myRecyclerView.setHasFixedSize(true);
        indexAdapter = new IndexAdapter(getActivity(),getActivity().getSupportFragmentManager());
        myRecyclerView.setAdapter(indexAdapter);
        setHasOptionsMenu(true);
        indexAdapter.setOnItemClickListener(new IndexAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void OnItemClick(View view) {
                final int position = myRecyclerView.getChildAdapterPosition(view);
                indexAdapter.setSelectedPosition(position);
                indexAdapter.setHasread(position);
                if (position > 0) {
                    newsId = mainInfo.stories.get(position - 1).id;
                    service.storyBody(newsId).subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<StoryBody>() {
                                @Override
                                public void onCompleted() {
                                }

                                @Override
                                public void onError(Throwable e) {
                                }

                                @Override
                                public void onNext(StoryBody body) {
                                    storybody = body;
                                    Intent intent = new Intent();
                                    intent.setClass(getActivity(), NewsActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putParcelable(KEY_STORY, mainInfo.stories.get(position - 1));
                                    bundle.putParcelable(KEY_BODY, storybody);
                                    intent.putExtra(KEY_VALUE, bundle);
                                    intent.putExtra(KEY_NEWS, newsId);
                                    startActivity(intent);
                                }
                            });
                }
            }
        });
        myRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int firstVisiblePosition = linearLayoutManager.findFirstVisibleItemPosition();
                if (firstVisiblePosition == 0) {
                    getActivity().setTitle("首页");
                } else {
                    getActivity().setTitle("今日热闻");
                }
                int lastVisiblePosition = linearLayoutManager.findLastVisibleItemPosition();
                if (lastVisiblePosition == mainInfo.stories.size()) {
                    if (!isLoading) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                service.info().subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(new Observer<Info>() {
                                            @Override
                                            public void onCompleted() {
                                            }

                                            @Override
                                            public void onError(Throwable e) {
                                            }

                                            @Override
                                            public void onNext(Info info) {
                                                indexAdapter.addDatas(info);
                                            }
                                        });
                                isLoading = false;
                            }
                        }, 3000);
                    }
                    isLoading = true;
                }
            }
        });

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.index_menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id) {
            case R.id.menu_settings:
                Intent intent = new Intent();
                intent.setClass(getActivity(), SettingActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
