package com.example.chenhongyuan.Fragment;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chenhongyuan.Module.Story;
import com.example.chenhongyuan.Module.StoryBody;
import com.example.chenhongyuan.myzhihuapplication.R;
import com.squareup.picasso.Picasso;

/**
 * Created by chenhongyuan on 15/7/20.
 */
public class ThemeNewsFragment extends Fragment {
    public WebView bodyView;
    public String body;
    public void setData(StoryBody body) {
        this.body = body.body;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.themenewsfragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bodyView = (WebView)getActivity().findViewById(R.id.theme_news_body);
        bodyView.getSettings().setJavaScriptEnabled(true);
        bodyView.loadData(body, "text/html; charset=utf-8", "utf-8");
    }
}
