package com.example.chenhongyuan.Module;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by chenhongyuan on 15/7/17.
 */
public class StoryBody implements Parcelable{
    public String body;
    StoryBody () {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.body);
    }
    private StoryBody (Parcel in) {
        this.body = in.readString();
    }
    public static final Parcelable.Creator<StoryBody> CREATOR = new Parcelable.Creator<StoryBody>() {
        @Override
        public StoryBody createFromParcel(Parcel source) {
            return new StoryBody(source);
        }

        @Override
        public StoryBody[] newArray(int size) {
            return new StoryBody[size];
        }
    };
}
