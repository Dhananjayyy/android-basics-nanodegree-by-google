package com.example.android.pune;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.tourguide.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CafesFragment.OnFragmentInteractionListener} interface to handle interaction events.
 */
public class CafesFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public CafesFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_view, container, false);


        final ArrayList<Location> locations = new ArrayList<Location>();
        locations.add(new Location(getString(R.string.vaishali_name),getString(R.string.vaishali_description),getString(R.string.vaishali_address),R.drawable.vaishali));
        locations.add(new Location(getString(R.string.taj_name),getString(R.string.taj_description),getString(R.string.taj_address),R.drawable.taj));
        LocationAdapter adapter = new LocationAdapter(getActivity(), locations);

        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (OnFragmentInteractionListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        void onCafesFragmentInteraction(Uri uri);
    }
}