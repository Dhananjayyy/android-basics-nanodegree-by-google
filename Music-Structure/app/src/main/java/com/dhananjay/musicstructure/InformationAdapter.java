package com.dhananjay.musicstructure;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.ButterKnife;


public class InformationAdapter extends ArrayAdapter<Information> {

    private static final String LOG_TAG = InformationAdapter.class.getSimpleName();


    InformationAdapter(Activity context, ArrayList<Information> songInfo) {
        super(context, 0, songInfo);
    }

    static class ViewHolder {
        private TextView nameTextView;
        private TextView surnameTextView;
        private ImageView personImageView;


    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
            holder = new ViewHolder();
            ButterKnife.bind(this, convertView);
            holder.nameTextView = convertView.findViewById(R.id.song_name);
            holder.surnameTextView = convertView.findViewById(R.id.artist_name);
            holder.personImageView = convertView.findViewById(R.id.list_item_icon);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        Information mySongInfo = getItem(position);

        assert mySongInfo != null;
        holder.nameTextView.setText(mySongInfo.getSongName());
        holder.surnameTextView.setText(mySongInfo.getArtistName());
        holder.personImageView.setImageResource(mySongInfo.getImageResourceId());


        return convertView;
    }

}
