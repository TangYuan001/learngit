package com.example.chenhongyuan.myzhihuapplication;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.chenhongyuan.Adapter.DrawerLayoutAdapter;
import com.example.chenhongyuan.Fragment.ThemeFragment;
import com.example.chenhongyuan.Fragment.MainFragment;
import com.example.chenhongyuan.Module.AllService;
import com.example.chenhongyuan.Module.Others;
import com.example.chenhongyuan.Module.Themes;

import java.util.ArrayList;
import java.util.List;

import retrofit.RestAdapter;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends ActionBarActivity {
    DrawerLayout mDrawerLayout;
    DrawerLayoutAdapter drawerLayoutAdapter;
    ActionBarDrawerToggle actionBarDrawerToggle;
    RecyclerView drawerRecyclerView;
    List<Others> otherList = new ArrayList<Others>();
    Toolbar toolbar;

    RestAdapter adapter = new RestAdapter.Builder()
            .setEndpoint("http://news-at.zhihu.com")
            .setLogLevel(RestAdapter.LogLevel.FULL)
            .build();
    AllService service = adapter.create(AllService.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerRecyclerView = (RecyclerView)findViewById(R.id.drawer_recyclerView);
        drawerLayoutAdapter = new DrawerLayoutAdapter(this);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onDrawerClosed(View drawerView) {
                invalidateOptionsMenu();
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(actionBarDrawerToggle);
        toolbar.setNavigationIcon(R.mipmap.toolbaricon);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        service.themes().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Themes>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onNext(Themes themes) {
                        drawerLayoutAdapter.setData(themes);
                        otherList = themes.others;
                    }
                });
        drawerRecyclerView.setAdapter(drawerLayoutAdapter);
        drawerLayoutAdapter.setOnItemClickListener(new DrawerLayoutAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void OnItemClick(View view) {
                int position = drawerRecyclerView.getChildLayoutPosition(view);
                drawerLayoutAdapter.setSelectedPosition(position);
                if(position == 0){
                    //DO NOTHING
                } else if (position == 1) {
                    MainFragment mainFragment = new MainFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, mainFragment).commit();
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    ThemeFragment drawLayoutFragment = new ThemeFragment();
                    drawLayoutFragment.setLayout(otherList.get(position-2).id);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, drawLayoutFragment).commit();
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                }

            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        drawerRecyclerView.setLayoutManager(linearLayoutManager);
        drawerRecyclerView.setHasFixedSize(true);

        MainFragment mainFragment = new MainFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, mainFragment).commit();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }
}
