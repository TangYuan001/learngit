package com.example.chenhongyuan.Module;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenhongyuan on 15/7/9.
 */
public class Story implements Parcelable{
    public String title;
    public String ga_prefix;
    public List<String> images = new ArrayList();
    public String image;
    public int type;
    public int id;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.ga_prefix);
        dest.writeStringList(this.images);
        dest.writeString(this.image);
        dest.writeInt(this.type);
        dest.writeInt(this.id);
    }

    public Story (){

    }

    private Story (Parcel in){
        this.title = in.readString();
        this.ga_prefix = in.readString();
        in.readStringList(this.images);
        this.image = in.readString();
        this.type = in.readInt();
        this.id = in.readInt();
    }
    public static final Parcelable.Creator<Story> CREATOR = new Parcelable.Creator<Story>() {
        public Story createFromParcel(Parcel source) {
            return new Story(source);
        }

        public Story[] newArray(int size) {
            return new Story[size];
        }
    };

}
