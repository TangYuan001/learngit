package com.example.chenhongyuan.Adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chenhongyuan.Module.Info;
import com.example.chenhongyuan.Module.Story;
import com.example.chenhongyuan.myzhihuapplication.R;
import com.squareup.picasso.Picasso;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenhongyuan on 15/7/9.
 */
public class IndexAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    ViewPagerFragmentPagerAdapter myFragmentPagerAdapter;
    public List<Story> datas = new ArrayList();
    Context context;
    private static List<Integer> hasread = new ArrayList<Integer>();
    private int selectedPosition;
    public IndexAdapter (Context c, FragmentManager fragmentManager){
        myFragmentPagerAdapter = new ViewPagerFragmentPagerAdapter(fragmentManager);
        context = c;
    }

    public void setSelectedPosition (int selectedPosition) {
        this.selectedPosition = selectedPosition;
        notifyDataSetChanged();
    }
    public void setHasread (int position) {
        hasread.add(position);
        notifyDataSetChanged();
    }

    public void setDatas (Info info) {
        datas = info.stories;
        myFragmentPagerAdapter.setData(info.top_stories);
        notifyDataSetChanged();
    }
    public void addDatas (Info addInfo) {
        datas.addAll(addInfo.stories);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return 1;
        } else {
            return 2;
        }
    }
    public Story getItem(int position) {
        return datas.get(position-1);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        if(viewType == 2) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
            vh = new ViewHolder(view);
            view.setOnClickListener(this);
        }else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.index_topitem, parent, false);
            vh = new IndexTopViewHolder(view);
            view.setOnClickListener(this);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == 2){
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.textView.setText("" + getItem(position).title);
            Picasso.with(context)
                    .load(Uri.parse(getItem(position).images.get(0)))
                    .placeholder(R.mipmap.pic4)
                    .error(R.mipmap.pic4)
                    .into(viewHolder.imageView);
           if(hasread.contains(position)){
               viewHolder.textView.setTextColor(Color.parseColor("#A9A9A9"));
           } else {
               viewHolder.textView.setTextColor(Color.parseColor("#000000"));
           }
        } else {
            IndexTopViewHolder newViewHolder = (IndexTopViewHolder) holder;
            newViewHolder.viewPager.setAdapter(myFragmentPagerAdapter);
            newViewHolder.indicator.setViewPager(newViewHolder.viewPager);
        }

    }
    @Override
    public int getItemCount() {
        return datas.size() + 1;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public ImageView imageView;
        public ViewHolder(View view){
            super(view);
            textView = (TextView)view.findViewById(R.id.simpleText);
            imageView = (ImageView)view.findViewById(R.id.simpleImage);
        }
    }
    public static class IndexTopViewHolder extends RecyclerView.ViewHolder{
        public ViewPager viewPager;
        public TextView textView ;
        public CirclePageIndicator indicator;
        public IndexTopViewHolder(View view){
            super(view);
            viewPager = (ViewPager)view.findViewById(R.id.viewPager);
            indicator = (CirclePageIndicator)view.findViewById(R.id.titles);
            textView = (TextView)view.findViewById(R.id.main_text);
        }
    }

    public static interface OnRecyclerViewItemClickListener {
        void OnItemClick(View view);
    }
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener){
        this.mOnItemClickListener = listener;
    }

    public void onClick(View v) {
        if(mOnItemClickListener != null){
            ((TextView)v.findViewById(R.id.simpleText)).setTextColor(Color.parseColor("#A9A9A9"));
            mOnItemClickListener.OnItemClick(v);
        }
    }

}
