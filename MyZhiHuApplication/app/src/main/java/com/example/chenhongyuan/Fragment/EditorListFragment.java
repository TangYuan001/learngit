package com.example.chenhongyuan.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.chenhongyuan.Adapter.EditorListAdapter;
import com.example.chenhongyuan.Module.Editor;
import com.example.chenhongyuan.myzhihuapplication.EditorInfoActivity;
import com.example.chenhongyuan.myzhihuapplication.R;

import java.util.List;

/**
 * Created by chenhongyuan on 15/7/16.
 */
public class EditorListFragment extends Fragment {
    ListView listView;
    EditorListAdapter editorListAdapter;
    List<Editor> list;
    public void setData(List<Editor> list) {
        this.list = list;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.editorlistfragment, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView = (ListView)getActivity().findViewById(R.id.editorlist);
        editorListAdapter = new EditorListAdapter(list, getActivity());
        listView.setAdapter(editorListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), EditorInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("EditorInfo", list.get(position));
                intent.putExtra("EditorInfo", bundle);
                startActivity(intent);

            }
        });
    }
}
