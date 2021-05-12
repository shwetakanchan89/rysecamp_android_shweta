package com.rysecamp.controller.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.rysecamp.R;
import com.rysecamp.databinding.FragmentContentBinding;

public class ContentFragment extends BaseFragment {

    private View streamsBackground, bookmarksBackground;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentContentBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_content, container, Boolean.FALSE);
        binding.setHandlers(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        ((TextView) getActivity().findViewById(R.id.app_title)).setText("My Content");

        streamsBackground = view.findViewById(R.id.streams_background);
        bookmarksBackground = view.findViewById(R.id.bookmarks_backgrounds);

        streamsBackground.setBackgroundResource(R.drawable.selected_left_layout);
        bookmarksBackground.setBackgroundResource(R.drawable.right_layout);
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_content, new StreamsFragment())
                .commit();
    }

    public void onStreamsClick(View view) {
        streamsBackground.setBackgroundResource(R.drawable.selected_left_layout);
        bookmarksBackground.setBackgroundResource(R.drawable.right_layout);
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_content, new StreamsFragment())
                .commit();
    }

    public void onBookmarksClick(View view) {
        streamsBackground.setBackgroundResource(R.drawable.left_layout);
        bookmarksBackground.setBackgroundResource(R.drawable.selected_right_layout);
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_content, new BookmarksFragment())
                .commit();
    }

    @Override
    public Bundle onSaveInstance(Bundle outState) {
        return null;
    }

    @Override
    public void onViewRestore(Bundle savedInstance) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((TextView) getActivity().findViewById(R.id.app_title)).setText(R.string.app_name);
    }
}
