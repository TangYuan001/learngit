package com.example.chenhongyuan.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.chenhongyuan.Module.Editor;
import com.example.chenhongyuan.Module.ThemeContent;
import com.example.chenhongyuan.myzhihuapplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by chenhongyuan on 15/7/15.
 */
public class EditorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public Context context;
    public List<Editor> editorList;
    EditorAdapter (Context c){
        context = c;
    }
    public void setData (ThemeContent content) {
        editorList = content.editors;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return editorList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.editor_pic, parent, false);
        vh = new ImageViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ImageViewHolder imageViewHolder = (ImageViewHolder)holder;
        Picasso.with(context)
                .load(editorList.get(position).avatar)
                .placeholder(R.mipmap.pic4)
                .into(imageViewHolder.image);
    }
    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView image;
        public ImageViewHolder (View view) {
            super(view);
            image = (CircleImageView)view.findViewById(R.id.editor_pic);
        }
    }
}
