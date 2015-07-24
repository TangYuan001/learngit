package com.example.chenhongyuan.Fragment;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chenhongyuan.Module.Editor;
import com.example.chenhongyuan.myzhihuapplication.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by chenhongyuan on 15/7/16.
 */
public class EditorInfoFragment extends Fragment {
    public CircleImageView editorInfoPic;
    public TextView editorInfoName;
    public TextView editorInfoOffice;
    public String sEditorInfoPic;
    public String sEditorInfoName;
    public String sEditorInfoOffice;
    public void setData (Editor editor) {
        sEditorInfoName = editor.name;
        sEditorInfoOffice = editor.bio;
        sEditorInfoPic = editor.avatar;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.editor_info_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        editorInfoOffice = (TextView)getActivity().findViewById(R.id.editor_info_bio);
        editorInfoName = (TextView)getActivity().findViewById(R.id.editor_info_name);
        editorInfoPic = (CircleImageView)getActivity().findViewById(R.id.editor_info_pic);
        Picasso.with(getActivity())
                .load(sEditorInfoPic).into(editorInfoPic);
        editorInfoName.setText(sEditorInfoName);
        editorInfoOffice.setText(sEditorInfoOffice);
    }

}
