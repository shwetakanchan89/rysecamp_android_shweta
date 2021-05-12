package com.rysecamp.controller.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.rysecamp.R;
import com.rysecamp.adapters.SpotlightAdapter;
import com.rysecamp.adapters.WhatsNewAdapter;
import com.rysecamp.controller.activities.ContentDetailActivity;
import com.rysecamp.controller.activities.ContentPremiumActivity;
import com.rysecamp.dto.SpotlightDto;
import com.sahil.widget.ViewPagerIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class DashboardFragment extends BaseFragment implements WhatsNewAdapter.ItemClick, SpotlightAdapter.ItemClick{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_dashboard, container, Boolean.FALSE);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((TextView) getActivity().findViewById(R.id.app_title)).setText(R.string.app_name);

        RecyclerView spotLightRecyclerVIew = view.findViewById(R.id.recycler_view_spotlight);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        spotLightRecyclerVIew.setLayoutManager(layoutManager);
        spotLightRecyclerVIew.setHasFixedSize(true);
        SpotlightAdapter spotlightAdapter = new SpotlightAdapter(this.getActivity(), new ArrayList<SpotlightDto>(), this);
        spotLightRecyclerVIew.setAdapter(spotlightAdapter);

        spotlightAdapter.add(new LinkedList<SpotlightDto>(Arrays.asList(new SpotlightDto(), new SpotlightDto(), new SpotlightDto(), new SpotlightDto(), new SpotlightDto(), new SpotlightDto())));


        RecyclerView whatsNewRecyclerVIew = view.findViewById(R.id.recycler_view_whats_new);
        RecyclerView.LayoutManager layoutManagerWhatsNew = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        whatsNewRecyclerVIew.setLayoutManager(layoutManagerWhatsNew);
        whatsNewRecyclerVIew.setHasFixedSize(true);
        WhatsNewAdapter whatsNewAdapter = new WhatsNewAdapter(this.getActivity(), new ArrayList<SpotlightDto>(), this);
        whatsNewRecyclerVIew.setAdapter(whatsNewAdapter);

        whatsNewAdapter.add(new LinkedList<SpotlightDto>(Arrays.asList(new SpotlightDto(), new SpotlightDto(), new SpotlightDto(), new SpotlightDto(), new SpotlightDto(), new SpotlightDto())));


        RecyclerView popularRecyclerVIew = view.findViewById(R.id.recycler_view_popular_streams);
        RecyclerView.LayoutManager layoutManagerPopular = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        popularRecyclerVIew.setLayoutManager(layoutManagerPopular);
        popularRecyclerVIew.setHasFixedSize(true);
        WhatsNewAdapter popularAdapter = new WhatsNewAdapter(this.getActivity(), new ArrayList<SpotlightDto>());
        popularRecyclerVIew.setAdapter(popularAdapter);

        popularAdapter.add(new LinkedList<SpotlightDto>(Arrays.asList(new SpotlightDto(), new SpotlightDto(), new SpotlightDto(), new SpotlightDto(), new SpotlightDto(), new SpotlightDto())));

        final ViewPager viewPager =  view.findViewById(R.id.view_pager);
        viewPager.setAdapter(new PagerAdapter(getActivity().getSupportFragmentManager()));

        final ViewPager.LayoutParams layoutParams = new ViewPager.LayoutParams();
        layoutParams.width = ViewPager.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewPager.LayoutParams.WRAP_CONTENT;
        layoutParams.gravity = Gravity.BOTTOM;

        final ViewPagerIndicator indicator = new ViewPagerIndicator(this.getActivity());
//        indicator.setDotPadding(20);
        viewPager.addView(indicator, layoutParams);

    }

    @Override
    public void onItemClickListener(SpotlightDto data, View view) {
        startActivity(new Intent(DashboardFragment.this.getActivity(), ContentPremiumActivity.class));
    }

    @Override
    public void onSpotLightClick(SpotlightDto data, View view) {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new RatingsFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onLastView() {

    }

    private static class PagerAdapter extends FragmentPagerAdapter {

        private static final int NUM_PAGES = 5;

        private PagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        @Override
        public Fragment getItem(int position) {
            return PageFragment.newInstance("Page Number " + position);
        }
    }

    @Override
    public Bundle onSaveInstance(Bundle outState) {
        return null;
    }

    @Override
    public void onViewRestore(Bundle savedInstance) {

    }
}
