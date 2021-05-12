package com.rysecamp.controller.fragments;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.View;

/**
 * Created by Sahil Sharma 10/10/18.
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        try {
            if (outState == null)
                outState = new Bundle();
            if (onSaveInstance(outState) != null)
                outState.putAll(onSaveInstance(outState));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        try {
            onViewRestore(savedInstanceState);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract Bundle onSaveInstance(Bundle outState);

    public abstract void onViewRestore(Bundle savedInstance);

}
