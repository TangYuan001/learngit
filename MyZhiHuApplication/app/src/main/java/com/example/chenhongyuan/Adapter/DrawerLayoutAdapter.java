package com.example.chenhongyuan.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chenhongyuan.Module.Others;
import com.example.chenhongyuan.Module.Themes;
import com.example.chenhongyuan.myzhihuapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenhongyuan on 15/7/10.
 */
public class DrawerLayoutAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{
    public List<Others> dataList = new ArrayList<Others>();
    private Context context;

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
        notifyDataSetChanged();
    }
    private int selectedPosition;
    public DrawerLayoutAdapter(Context context){
        this.context = context;
    }
    public void setData(Themes themes){
        dataList = themes.others;
        notifyDataSetChanged();
    }
    @Override
    public int getItemViewType(int position) {
        if(position == 0) {
            return 0;
        } else if(position == 1){
            return 1;
        } else {
            return 2;
        }
    }

    public Others getData(int position) {
        return dataList.get(position - 2);
    }

    protected Others getItem(int position) {
        return dataList.get(position - 2);
    }

    @Override
    public int getItemCount() {
        return dataList.size() +2;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == 0){
            DrawerLayoutTopHolder textViewHolder = (DrawerLayoutTopHolder) holder;
        } else if(getItemViewType(position) == 2){
            ListViewHolder listViewHolder = (ListViewHolder) holder;
            listViewHolder.imageView.setImageResource(android.R.drawable.ic_input_add);
            listViewHolder.textView.setText(getItem(position).name);
            if (position == selectedPosition) {
                listViewHolder.relativeLayout.setSelected(true);
            } else {
                listViewHolder.relativeLayout.setSelected(false);
            }
        } else {
            IndexViewHolder indexViewHolder = (IndexViewHolder)holder;
            if (position == selectedPosition) {
                indexViewHolder.indexItemLayout.setSelected(true);
            } else {
                indexViewHolder.indexItemLayout.setSelected(false);
            }
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        if (viewType == 0) {
            View view = LayoutInflater.from(context).inflate(R.layout.drawlayout_topitem, parent, false);
            vh = new DrawerLayoutTopHolder(view);
            view.setOnClickListener(this);
        } else if(viewType == 1){
            View view = LayoutInflater.from(context).inflate(R.layout.drawlayout_indexitem, parent, false);
            vh = new IndexViewHolder(view);
            view.setOnClickListener(this);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.drawer_listlayout, parent, false);
            vh = new ListViewHolder(view);
            view.setOnClickListener(this);
        }
        return vh;
    }
    public static class ListViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView textView;
        public RelativeLayout relativeLayout;
        public ListViewHolder(View view){
            super(view);
            imageView = (ImageView)view.findViewById(R.id.listItemImage);
            textView = (TextView)view.findViewById(R.id.listItemTextView);
            relativeLayout = (RelativeLayout) view.findViewById(R.id.rl_item_container);
        }
    }
    public static class DrawerLayoutTopHolder extends RecyclerView.ViewHolder{
        public LinearLayout linearLayout;
        public DrawerLayoutTopHolder(View view){
            super(view);
            linearLayout = (LinearLayout)view.findViewById(R.id.ll_layout);
        }
    }
    public static class IndexViewHolder extends RecyclerView.ViewHolder {
        public ImageView homeImage;
        public TextView indexText;
        public RelativeLayout indexItemLayout;
        public IndexViewHolder (View view) {
            super(view);
            homeImage = (ImageView)view.findViewById(R.id.home);
            indexText = (TextView)view.findViewById(R.id.textView_index);
            indexItemLayout = (RelativeLayout)view.findViewById(R.id.indexItemLayout);
        }
    }
    public static interface OnRecyclerViewItemClickListener {
        void OnItemClick(View view);
    }
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener){
        this.mOnItemClickListener = listener;
    }

    @Override
    public void onClick(View v) {
        if(mOnItemClickListener != null){
            mOnItemClickListener.OnItemClick(v);
        }
    }
}
