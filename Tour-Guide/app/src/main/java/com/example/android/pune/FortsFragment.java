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
 * {@link FortsFragment.OnFragmentInteractionListener} interface to handle interaction events.
 */
public class FortsFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public FortsFragment() {


        // Required empty public constructor
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
        locations.add(new Location(getString(R.string.shivneri_name),getString(R.string.shivneri_description),getString(R.string.shivneri_address),R.drawable.shivneri));
        locations.add(new Location(getString(R.string.pratapgad_name),getString(R.string.pratapgad_description),getString(R.string.pratapgad_address),R.drawable.pratapgad));
        locations.add(new Location(getString(R.string.rajgad_name),getString(R.string.rajgad_description),getString(R.string.rajgad_address),R.drawable.rajgad));
        locations.add(new Location(getString(R.string.shaniwarwada_name),getString(R.string.shaniwarwada_description),getString(R.string.shaniwarwada_address),R.drawable.shaniwarwada));
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
        void onHotelsFragmentInteraction(Uri uri);
    }
}