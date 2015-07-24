package com.example.chenhongyuan.Module;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by chenhongyuan on 15/7/14.
 */
public class Editor implements Parcelable {
    public String url;
    public String bio;
    public int id;
    public String avatar;
    public String name;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.bio);
        dest.writeInt(this.id);
        dest.writeString(this.avatar);
        dest.writeString(this.name);
    }

    public Editor() {
    }

    private Editor(Parcel in) {
        this.url = in.readString();
        this.bio = in.readString();
        this.id = in.readInt();
        this.avatar = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<Editor> CREATOR = new Parcelable.Creator<Editor>() {
        public Editor createFromParcel(Parcel source) {
            return new Editor(source);
        }

        public Editor[] newArray(int size) {
            return new Editor[size];
        }
    };
}
