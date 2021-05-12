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
import com.rysecamp.adapters.NotificationAdapter;
import com.rysecamp.adapters.RecentBookmarksAdapter;
import com.rysecamp.dto.SpotlightDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class NotificationsFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_notifications, container, Boolean.FALSE);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView notificationRecyclerVIew = view.findViewById(R.id.recycler_view_popular_notifications);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        notificationRecyclerVIew.setLayoutManager(layoutManager);
        notificationRecyclerVIew.setHasFixedSize(true);
        NotificationAdapter notificationAdapter = new NotificationAdapter(this.getActivity(), new ArrayList<SpotlightDto>());
        notificationRecyclerVIew.setAdapter(notificationAdapter);

        notificationAdapter.add(new LinkedList<SpotlightDto>(Arrays.asList(new SpotlightDto(), new SpotlightDto(), new SpotlightDto(), new SpotlightDto(), new SpotlightDto(), new SpotlightDto())));

    }

    @Override
    public Bundle onSaveInstance(Bundle outState) {
        return null;
    }

    @Override
    public void onViewRestore(Bundle savedInstance) {

    }
}
