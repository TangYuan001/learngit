package com.example.chenhongyuan.Adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.chenhongyuan.Module.Story;
import com.example.chenhongyuan.Fragment.ViewPagerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenhongyuan on 15/7/9.
 */
public class ViewPagerFragmentPagerAdapter extends FragmentPagerAdapter {

    List<Story> list = new ArrayList<>();

    public ViewPagerFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setData (List<Story> topStories) {
        list = topStories;
        notifyDataSetChanged();
    }
    @Override
    public Fragment getItem(int position) {
        ViewPagerFragment fragment = new ViewPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("ImageUri",list.get(position).image);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public int getCount() {
        return list.size();
    }
}
