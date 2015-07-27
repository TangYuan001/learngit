package com.example.chenhongyuan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.chenhongyuan.Module.Editor;
import com.example.chenhongyuan.myzhihuapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenhongyuan on 15/7/16.
 */
public class EditorListAdapter extends BaseAdapter {

    public List<Editor> editorList = new ArrayList<Editor>();
    public Context context;
    public EditorListAdapter(List<Editor> list, Context c) {
        editorList = list;
        context = c;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return editorList.size();
    }

    @Override
    public Object getItem(int position) {
        return editorList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EditorViewHolder editorViewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.editorinfolistitem, parent, false);
            editorViewHolder = new EditorViewHolder();
            editorViewHolder.editorPic = (ImageView)convertView.findViewById(R.id.editorPic);
            editorViewHolder.editorName = (TextView)convertView.findViewById(R.id.editorName);
            editorViewHolder.editorOffice = (TextView)convertView.findViewById(R.id.editorOffice);
            convertView.setTag(editorViewHolder);
        } else {
            editorViewHolder = (EditorViewHolder)convertView.getTag();
        }
        Picasso.with(context).load(editorList.get(position).avatar).into(editorViewHolder.editorPic);
        editorViewHolder.editorName.setText(editorList.get(position).name);
        editorViewHolder.editorOffice.setText(editorList.get(position).bio);
        return convertView;
    }

    public class EditorViewHolder {
        ImageView editorPic;
        TextView editorName;
        TextView editorOffice;

    }
}
