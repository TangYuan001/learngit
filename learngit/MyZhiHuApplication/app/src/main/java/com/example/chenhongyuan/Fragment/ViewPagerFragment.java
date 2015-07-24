package com.example.chenhongyuan.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.chenhongyuan.myzhihuapplication.R;
import com.squareup.picasso.Picasso;

/**
 * Created by chenhongyuan on 15/7/9.
 */
public class ViewPagerFragment extends Fragment {
    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout1, container, false);
        imageView = (ImageView)view.findViewById(R.id.image1);
        Picasso.with(getActivity())
                .load(getArguments().getString("ImageUri"))
                .into(imageView);
        return view;
    }
}
