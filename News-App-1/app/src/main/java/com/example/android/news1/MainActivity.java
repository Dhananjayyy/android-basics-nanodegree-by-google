    package com.example.android.news1;
    import android.app.LoaderManager;
    import android.app.LoaderManager.LoaderCallbacks;
    import android.content.Context;
    import android.content.Intent;
    import android.content.Loader;
    import android.net.ConnectivityManager;
    import android.net.NetworkInfo;
    import android.net.Uri;
    import android.os.Bundle;
    import android.support.v7.app.AppCompatActivity;
    import android.view.View;
    import android.widget.AdapterView;
    import android.widget.ListView;
    import android.widget.TextView;

    import java.util.ArrayList;
    import java.util.List;
    import java.util.Objects;

    public class MainActivity extends AppCompatActivity
            implements LoaderCallbacks<List<News>> {


        private static final String LOG_TAG = MainActivity.class.getName();
        public static final String JASON =
                "https://content.guardianapis.com/search?&show-tags=contributor&api-key=1205bd93-b6c3-4f90-9eac-eba3ff2c2b02";


        private static final int FETCH = 1;
        private NewsAdapter textAdapter;
        private TextView EmptyView;

        @Override
        public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
            return new NewsLoader(this);
        }

        @Override
        public void onLoadFinished(Loader<List<News>> loader, List<News> NewsItems) {
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            EmptyView.setText(R.string.no_news);
            textAdapter.clear();

            if (NewsItems != null && !NewsItems.isEmpty()) {
                textAdapter.addAll(NewsItems);
            }
        }

        @Override
        public void onLoaderReset(Loader<List<News>> loader) {
            textAdapter.clear();
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            ListView newsListView = findViewById(R.id.main_list);

            EmptyView = findViewById(R.id.empty_text);
            newsListView.setEmptyView(EmptyView);

            textAdapter = new NewsAdapter(this, new ArrayList<News>());
            newsListView.setAdapter(textAdapter);

            newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    News currentNewsArticle = textAdapter.getItem(position);
                    Uri newsUri = Uri.parse(Objects.requireNonNull(currentNewsArticle).getUrl());
                    Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUri);
                    startActivity(websiteIntent);
                }
            });

            ConnectivityManager connMgr = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.isConnected()) {
                LoaderManager loaderManager = getLoaderManager();
                loaderManager.initLoader(FETCH, null, this);
            } else {
                View loadingIndicator = findViewById(R.id.loading_indicator);
                loadingIndicator.setVisibility(View.GONE);

                EmptyView.setText(R.string.no_internet_connection);
            }
        }


    }