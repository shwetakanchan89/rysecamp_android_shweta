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
import com.rysecamp.adapters.FavouriteContributorsAdapter;
import com.rysecamp.adapters.RecentStreamsAdapter;
import com.rysecamp.adapters.SimilarStreamsAdapter;
import com.rysecamp.adapters.WhatsNewAdapter;
import com.rysecamp.dto.SpotlightDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class ActivitiesFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_activities, container, Boolean.FALSE);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView seenRecyclerVIew = view.findViewById(R.id.recycler_view_stream_seen);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        seenRecyclerVIew.setLayoutManager(layoutManager);
        seenRecyclerVIew.setHasFixedSize(true);
        WhatsNewAdapter whatsNewAdapter = new WhatsNewAdapter(this.getActivity(), new ArrayList<SpotlightDto>());
        seenRecyclerVIew.setAdapter(whatsNewAdapter);

        whatsNewAdapter.add(new LinkedList<SpotlightDto>(Arrays.asList(new SpotlightDto(), new SpotlightDto(), new SpotlightDto(), new SpotlightDto(), new SpotlightDto(), new SpotlightDto())));


        RecyclerView madeRecyclerVIew = view.findViewById(R.id.recycler_view_streams_made);
        RecyclerView.LayoutManager layoutManagerMade = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        madeRecyclerVIew.setLayoutManager(layoutManagerMade);
        madeRecyclerVIew.setHasFixedSize(true);
        SimilarStreamsAdapter similarStreamsAdapter = new SimilarStreamsAdapter(this.getActivity(), new ArrayList<SpotlightDto>());
        madeRecyclerVIew.setAdapter(similarStreamsAdapter);

        similarStreamsAdapter.add(new LinkedList<SpotlightDto>(Arrays.asList(new SpotlightDto(), new SpotlightDto(), new SpotlightDto(), new SpotlightDto(), new SpotlightDto(), new SpotlightDto())));

    }

    @Override
    public Bundle onSaveInstance(Bundle outState) {
        return null;
    }

    @Override
    public void onViewRestore(Bundle savedInstance) {

    }
}
