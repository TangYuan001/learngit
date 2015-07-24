package com.example.chenhongyuan.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chenhongyuan.Module.Story;
import com.example.chenhongyuan.Module.StoryBody;
import com.example.chenhongyuan.myzhihuapplication.R;
import com.squareup.picasso.Picasso;

/**
 * Created by chenhongyuan on 15/7/17.
 */
public class MainNewsFragment extends Fragment {
    public ImageView headPicView;
    public TextView storyInfoView;
    public WebView bodyView;
    public String headPic;
    public String storyInfo;
    public String body;

    public void setData(Story story, StoryBody body) {
        headPic = story.images.get(0);
        storyInfo = story.title;
        this.body = body.body;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mainnewsfragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        headPicView = (ImageView)getActivity().findViewById(R.id.head_pic);
        storyInfoView = (TextView)getActivity().findViewById(R.id.story_info);
        bodyView = (WebView)getActivity().findViewById(R.id.body);
        Picasso.with(getActivity()).load(headPic).into(headPicView);
        storyInfoView.setText(storyInfo);
        bodyView.getSettings().setJavaScriptEnabled(true);
        bodyView.loadData(body, "text/html; charset=utf-8", "utf-8");
    }
}
