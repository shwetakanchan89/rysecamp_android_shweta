package com.rysecamp.controller.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.rysecamp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContentDetailFragment extends Fragment {
    String text;
    private static final String TEXT = "text";
    private TextView ivTextView;

    public ContentDetailFragment() {
        // Required empty public constructor
    }

    public static ContentDetailFragment newInstance(String data) {
        ContentDetailFragment fragment = new ContentDetailFragment();
        Bundle bundle = new Bundle(1);
        bundle.putString(TEXT, data);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.text = getArguments().getString(TEXT);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content_video_card, container, false);
//        ivTextView = view.findViewById(R.id.ivTextView);
//        ivTextView.setText(text);
        return view;
    }

}
