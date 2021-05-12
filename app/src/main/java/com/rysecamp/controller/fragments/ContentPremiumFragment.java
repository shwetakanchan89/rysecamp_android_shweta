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
public class ContentPremiumFragment extends Fragment {
    String text;
    private static final String TEXT = "text";
    private TextView ivTextView;

    public ContentPremiumFragment() {
        // Required empty public constructor
    }

    public static ContentPremiumFragment newInstance(String data) {
        ContentPremiumFragment fragment = new ContentPremiumFragment();
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
        View view = inflater.inflate(R.layout.fragment_content_premium_card, container, false);
//        ivTextView = view.findViewById(R.id.ivTextView);
//        ivTextView.setText(text);
        return view;
    }

}
