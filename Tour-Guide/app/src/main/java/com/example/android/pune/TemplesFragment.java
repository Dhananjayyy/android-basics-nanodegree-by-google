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

public class TemplesFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public TemplesFragment() {
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
        locations.add(new Location(getString(R.string.dagdusheth_name), getString(R.string.dagdusheth_description), getString(R.string.dagdusheth_address), R.drawable.dagdusheth));
        locations.add(new Location(getString(R.string.pataleshwar_name), getString(R.string.pataleshwar_description), getString(R.string.pataleshwar_address), R.drawable.pataleshwar));
        locations.add(new Location(getString(R.string.iskon_name), getString(R.string.iskon_description), getString(R.string.iskon_address), R.drawable.iskon));
        locations.add(new Location(getString(R.string.shirdi_name), getString(R.string.shirdi_description), getString(R.string.shirdi_address), R.drawable.shirdi));

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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that activity.
     */
    public interface OnFragmentInteractionListener {
        void onRestaurantsFragmentInteraction(Uri uri);
    }
}
