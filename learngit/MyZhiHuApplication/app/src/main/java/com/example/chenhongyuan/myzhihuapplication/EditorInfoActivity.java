package com.example.chenhongyuan.myzhihuapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.example.chenhongyuan.Fragment.EditorInfoFragment;
import com.example.chenhongyuan.Module.Editor;

/**
 * Created by chenhongyuan on 15/7/16.
 */
public class EditorInfoActivity extends ActionBarActivity {
    Toolbar toolbar;
    Editor editorInfo;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor_info_layout);
        toolbar = (Toolbar)findViewById(R.id.editor_info_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Bundle bundle = getIntent().getBundleExtra("EditorInfo");
        editorInfo = bundle.getParcelable("EditorInfo");
        EditorInfoFragment editorInfoFragment = new EditorInfoFragment();
        editorInfoFragment.setData(editorInfo);
        getSupportFragmentManager().beginTransaction().replace(R.id.edit_info_container, editorInfoFragment).commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
