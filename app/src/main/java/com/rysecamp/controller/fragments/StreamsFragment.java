package com.rysecamp.controller.fragments;

import android.content.Intent;
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
import com.rysecamp.controller.activities.ContentDetailActivity;
import com.rysecamp.controller.activities.ContentDetailTextActivity;
import com.rysecamp.dto.SpotlightDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class StreamsFragment extends BaseFragment implements RecentStreamsAdapter.ItemClick, SimilarStreamsAdapter.ItemClick{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_streams, container, Boolean.FALSE);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView streamsRecyclerVIew = view.findViewById(R.id.recycler_view_recent_streams);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        streamsRecyclerVIew.setLayoutManager(layoutManager);
        streamsRecyclerVIew.setHasFixedSize(true);
        RecentStreamsAdapter recentStreamsAdapter = new RecentStreamsAdapter(this.getActivity(), new ArrayList<SpotlightDto>(), this);
        streamsRecyclerVIew.setAdapter(recentStreamsAdapter);

        recentStreamsAdapter.add(new LinkedList<SpotlightDto>(Arrays.asList(new SpotlightDto(), new SpotlightDto(), new SpotlightDto(), new SpotlightDto(), new SpotlightDto(), new SpotlightDto())));


        RecyclerView similarRecyclerVIew = view.findViewById(R.id.recycler_view_similar_streams);
        RecyclerView.LayoutManager layoutManagerSimilar = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        similarRecyclerVIew.setLayoutManager(layoutManagerSimilar);
        similarRecyclerVIew.setHasFixedSize(true);
        SimilarStreamsAdapter similarStreamsAdapter = new SimilarStreamsAdapter(this.getActivity(), new ArrayList<SpotlightDto>(), this);
        similarRecyclerVIew.setAdapter(similarStreamsAdapter);

        similarStreamsAdapter.add(new LinkedList<SpotlightDto>(Arrays.asList(new SpotlightDto(), new SpotlightDto(), new SpotlightDto(), new SpotlightDto(), new SpotlightDto(), new SpotlightDto())));


        RecyclerView contributorsRecyclerVIew = view.findViewById(R.id.recycler_view_favourite_contributors);
        RecyclerView.LayoutManager layoutManagerContributors = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        contributorsRecyclerVIew.setLayoutManager(layoutManagerContributors);
        contributorsRecyclerVIew.setHasFixedSize(true);
        FavouriteContributorsAdapter favouriteContributorsAdapter = new FavouriteContributorsAdapter(this.getActivity(), new ArrayList<SpotlightDto>());
        contributorsRecyclerVIew.setAdapter(favouriteContributorsAdapter);

        favouriteContributorsAdapter.add(new LinkedList<SpotlightDto>(Arrays.asList(new SpotlightDto(), new SpotlightDto(), new SpotlightDto(), new SpotlightDto(), new SpotlightDto(), new SpotlightDto())));

    }

    @Override
    public Bundle onSaveInstance(Bundle outState) {
        return null;
    }

    @Override
    public void onViewRestore(Bundle savedInstance) {

    }

    @Override
    public void onRecentStreamClick(SpotlightDto data, View view) {
        startActivity(new Intent(StreamsFragment.this.getActivity(), ContentDetailActivity.class));
    }

    @Override
    public void onSimilarStreamClick(SpotlightDto data, View view) {
        startActivity(new Intent(StreamsFragment.this.getActivity(), ContentDetailTextActivity.class));
    }

    @Override
    public void onLastView() {

    }
}
