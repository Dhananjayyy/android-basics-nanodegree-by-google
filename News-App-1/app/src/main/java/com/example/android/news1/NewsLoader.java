    package com.example.android.news1;

    import android.content.AsyncTaskLoader;
    import android.content.Context;

    import java.util.List;

    class NewsLoader extends AsyncTaskLoader<List<News>> {
        private static final String LOG_TAG = NewsLoader.class.getName();
        private final String mUrl;
        NewsLoader(Context context) {
            super(context);
            mUrl = MainActivity.JASON;
        }
        @Override
        protected void onStartLoading() {
            forceLoad();
        }
        @Override
        public List<News> loadInBackground() {
            if (mUrl == null) {
                return null;
            }
            return QueryUtils.fetchNewsData(mUrl);
        }
    }