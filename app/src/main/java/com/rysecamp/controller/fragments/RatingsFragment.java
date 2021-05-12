package com.rysecamp.controller.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.rysecamp.R;

public class RatingsFragment extends BaseFragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       return LayoutInflater.from(getContext()).inflate(R.layout.fragment_ratings, container, Boolean.FALSE);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        ((TextView) getActivity().findViewById(R.id.app_title)).setText("My Content");

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
