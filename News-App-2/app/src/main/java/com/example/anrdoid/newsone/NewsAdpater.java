package com.example.anrdoid.newsone;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

class NewsAdapter extends ArrayAdapter<News> {
    NewsAdapter(Context context, List<News> NewsItem) {
        super(context, 0, NewsItem);
    }
    private static class ViewHolder {
        private TextView type;
        private TextView location;
        private TextView author;
        private TextView date;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
            holder = new ViewHolder();
            holder.type = convertView.findViewById(R.id.articleType);
            holder.location = convertView.findViewById(R.id.primary_location);
            holder.author = convertView.findViewById(R.id.author);
            holder.date = convertView.findViewById(R.id.date);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        News newsInfo = getItem(position);
        assert newsInfo != null;
        holder.type.setText(newsInfo.getWebTitle());
        holder.location.setText(newsInfo.getName());
        holder.author.setText(newsInfo.getAuthor());
        holder.date.setText(newsInfo.getDate());
        return convertView;

    }

}