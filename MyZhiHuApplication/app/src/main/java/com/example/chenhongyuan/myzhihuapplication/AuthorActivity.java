package com.example.chenhongyuan.myzhihuapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.example.chenhongyuan.Fragment.EditorListFragment;
import com.example.chenhongyuan.Module.Editor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenhongyuan on 15/7/16.
 */
public class AuthorActivity extends ActionBarActivity {
    Toolbar toolbar;
    Intent intent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor_main_layout);
        toolbar = (Toolbar)findViewById(R.id.editor_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        intent = getIntent();
        List<Editor> list = new ArrayList<Editor>();
        Bundle bundle = new Bundle();
        bundle= intent.getBundleExtra("EditorList");
        list = bundle.getParcelableArrayList("EditorList");
        EditorListFragment editorListFragment = new EditorListFragment();
        editorListFragment.setData(list);
        getSupportFragmentManager().beginTransaction().replace(R.id.editor_fragment_container, editorListFragment).commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
