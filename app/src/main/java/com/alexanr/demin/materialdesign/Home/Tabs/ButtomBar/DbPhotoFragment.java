package com.alexanr.demin.materialdesign.Home.Tabs.ButtomBar;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alexanr.demin.materialdesign.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DbPhotoFragment extends Fragment {


    public static DbPhotoFragment newInstance(Bundle bundle) {
        DbPhotoFragment fragment = new DbPhotoFragment();
        Bundle args = new Bundle();
        args.putBundle("gettedArgs", bundle);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_db_photo, container, false);
    }

}
