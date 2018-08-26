package com.alexanr.demin.materialdesign;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainFragment extends Fragment {
    private FloatingActionButton FAB;
    private View.OnClickListener FABListener;
    private View root;
    private static final int REQUEST_TAKE_PHOTO = 1;
    private String mCurrentPhotoPath;

    RecyclerView recyclerView;
    ImgItemAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_main, container, false);
        initListeners();
        FAB = root.findViewById(R.id.floating_action_button);
        FAB.setOnClickListener(FABListener);
        initRecycler();
        return root;
    }

    @Override
    public void onDestroy() {
        FAB.setOnClickListener(null);
        super.onDestroy();
    }

    private void initRecycler() {
        recyclerView = root.findViewById(R.id.main_img_list);
        recyclerView.setLayoutManager(new GridLayoutManager(root.getContext(), 2));
        adapter = new ImgItemAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void initListeners() {
        FABListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    File photoFile = null;
                    try {
                        StringBuilder fileName = new StringBuilder();
                        fileName.append("JPAG_");
                        fileName.append(new SimpleDateFormat("ddMMyyyy_HHmm", Locale.ENGLISH).format(new Date()));
                        fileName.append("_");
                        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                        photoFile = File.createTempFile(fileName.toString(), ".jpg", storageDir);
                        mCurrentPhotoPath = photoFile.getAbsolutePath();
                    } catch (IOException ex) {
                        Snackbar.make(v, getString(R.string.error), Snackbar.LENGTH_SHORT).show();
                    }
                    if (photoFile != null) {
                        Uri photoURI = FileProvider.getUriForFile(getActivity(),
                                "com.example.android.provider",
                                photoFile);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                        startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                    }
                }
            }
        };
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_PHOTO) {
            Snackbar.make(root, getString(R.string.photo_added), Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDetach() {
        root = null;
        super.onDetach();
    }
}
