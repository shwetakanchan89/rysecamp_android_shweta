package com.rysecamp.controller.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rysecamp.R;
import com.rysecamp.adapters.GoogleAnalyticsAdapter;
import com.rysecamp.adapters.RecentBookmarksAdapter;
import com.rysecamp.dto.SpotlightDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class BookmarksFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_bookmarks, container, Boolean.FALSE);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView bookmarkRecyclerVIew = view.findViewById(R.id.recycler_view_recent_bookmarks);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        bookmarkRecyclerVIew.setLayoutManager(layoutManager);
        bookmarkRecyclerVIew.setHasFixedSize(true);
        RecentBookmarksAdapter recentBookmarksAdapter = new RecentBookmarksAdapter(this.getActivity(), new ArrayList<SpotlightDto>());
        bookmarkRecyclerVIew.setAdapter(recentBookmarksAdapter);

        recentBookmarksAdapter.add(new LinkedList<SpotlightDto>(Arrays.asList(new SpotlightDto(), new SpotlightDto(), new SpotlightDto(), new SpotlightDto(), new SpotlightDto(), new SpotlightDto())));


        RecyclerView googleAnalyticsRecyclerVIew = view.findViewById(R.id.recycler_view_google_analytics);
        RecyclerView.LayoutManager layoutManagerAnalytics = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        googleAnalyticsRecyclerVIew.setLayoutManager(layoutManagerAnalytics);
        googleAnalyticsRecyclerVIew.setHasFixedSize(true);
        GoogleAnalyticsAdapter googleAnalyticsAdapter = new GoogleAnalyticsAdapter(this.getActivity(), new ArrayList<SpotlightDto>());
        googleAnalyticsRecyclerVIew.setAdapter(googleAnalyticsAdapter);

        googleAnalyticsAdapter.add(new LinkedList<SpotlightDto>(Arrays.asList(new SpotlightDto(), new SpotlightDto(), new SpotlightDto(), new SpotlightDto(), new SpotlightDto(), new SpotlightDto())));

    }

    @Override
    public Bundle onSaveInstance(Bundle outState) {
        return null;
    }

    @Override
    public void onViewRestore(Bundle savedInstance) {

    }
}
