package com.example.anrdoid.newsone;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

@SuppressWarnings("deprecation")
public class NewsLoader extends AsyncTaskLoader<List<News>> {
    String mURL ;
    private static final String LOG_TAG = NewsLoader.class.getName();
    public NewsLoader(Context context, String url) {
        super(context);
        this.mURL = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        if (mURL == null) {
            return null;
        }

        return QueryUtils.fetchNewsData(mURL);
    }
}