package com.scibots.aniket.mtickets.SearchSuggestion;

import android.annotation.SuppressLint;
import android.os.Parcel;

import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

/**
 * Created by aniket on 4/6/17.
 */

@SuppressLint("ParcelCreator")

public class movieSuggestion implements SearchSuggestion {
    private String mMovieName;

    public movieSuggestion(String mMovieName) {
        this.mMovieName = mMovieName;
    }

    @Override
    public String getBody() {
        return mMovieName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mMovieName);

    }
}
