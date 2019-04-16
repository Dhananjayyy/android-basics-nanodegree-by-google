package com.example.android.pune;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.tourguide.R;

import java.util.ArrayList;

class LocationAdapter extends ArrayAdapter<Location> {


    LocationAdapter(Activity context, ArrayList<Location> locations) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, locations);
    }


    private static class ViewHolder {
        private TextView nameTextView;
        private TextView descriptionTextView;
        private TextView locationTextView;
        private ImageView placeImageView;


    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
            holder = new ViewHolder();
            holder.nameTextView = convertView.findViewById(R.id.location_title_text_view);
            holder.descriptionTextView = convertView.findViewById(R.id.location_description_text_view);
            holder.locationTextView = convertView.findViewById(R.id.location_address_text_view);
            holder.placeImageView = convertView.findViewById(R.id.location_picture_image_view);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        Location allInfo = getItem(position);

        assert allInfo != null;
        holder.nameTextView.setText(allInfo.getTitle());
        holder.descriptionTextView.setText(allInfo.getDescription());
        holder.locationTextView.setText(allInfo.getAddress());
        holder.placeImageView.setImageResource(allInfo.getImageResourceId());


        holder.locationTextView.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the addressTextView is clicked on.
            @Override
            public void onClick(View view) {
                Intent geoIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                        "geo:0,0?q=" + Uri.encode(holder.locationTextView.getText().toString())));
                if (geoIntent.resolveActivity(view.getContext().getPackageManager()) != null)
                    view.getContext().startActivity(geoIntent);
            }
        });


        return convertView;
    }

}
