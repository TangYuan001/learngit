package com.example.chenhongyuan.myzhihuapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.example.chenhongyuan.Adapter.ExpandableAdapter;
import com.example.chenhongyuan.Module.AllService;
import com.example.chenhongyuan.Module.Comment;
import com.example.chenhongyuan.Module.Comments;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.UMImage;

import java.util.ArrayList;
import java.util.List;

import retrofit.RestAdapter;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by chenhongyuan on 15/7/20.
 */
public class CommentActivity extends ActionBarActivity{
    Toolbar toolbar;
    int commentNum, longCommentNum, shortCommentNum;
    int newsId;
    List<Comment> longComment;
    List<Comment> shortComment;
    AllService service;
    ExpandableListView expandableListView;
    ExpandableAdapter expandableAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_layout);

        toolbar = (Toolbar)findViewById(R.id.comment_toolbar);
        expandableListView = (ExpandableListView)findViewById(R.id.comment_expand_list);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.back);
        commentNum = getIntent().getIntExtra("commentNum", 0);
        longCommentNum = getIntent().getIntExtra("longCommentNum", 0);
        shortCommentNum = getIntent().getIntExtra("shortCommentNum", 0);
        newsId = getIntent().getIntExtra("newsId", 0);
        setTitle(commentNum + "条点评");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("http://news-at.zhihu.com").build();
        service = adapter.create(AllService.class);
        loadData();
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                CharSequence[] selectList = {"赞同", "举报", "复制", "回复"};
                AlertDialog.Builder builder = new AlertDialog.Builder(CommentActivity.this);
                builder.setItems(selectList, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //DO NOTHING
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
                return true;
            }
        });
    }

    public void loadData() {
        Observable.zip(service.shortComment(newsId), service.longComment(newsId), new Func2<Comments, Comments, List<Comments>>() {
            @Override
            public List<Comments> call(Comments comments, Comments comments2) {
                List<Comments> list = new ArrayList<Comments>();
                list.add(comments);
                list.add(comments2);
                return list;
            }
        }).observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Observer<List<Comments>>() {
            @Override
            public void onCompleted() {
            }
            @Override
            public void onError(Throwable e) {
                Log.i("log", e.getMessage());
            }

            @Override
            public void onNext(List<Comments> commentses) {
                List<String> titleList = new ArrayList<String>();
                titleList.add(longCommentNum + "条长评");
                titleList.add(shortCommentNum + "条短评");
                List<List<Comment>> childData = new ArrayList<List<Comment>>();
                childData.add(commentses.get(1).comments);
                childData.add(commentses.get(0).comments);

                expandableAdapter = new ExpandableAdapter(childData, titleList, CommentActivity.this);
                expandableListView.setAdapter(expandableAdapter);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.comment_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
