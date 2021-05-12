package com.rysecamp.controller.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.rysecamp.R;
import com.rysecamp.controller.fragments.ActivitiesFragment;
import com.rysecamp.controller.fragments.CommentsFragment;
import com.rysecamp.databinding.ActivityProfileBinding;

public class ProfileActivity extends BaseActivity {

    private View activitiesBackground, commentsBackground;

   @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityProfileBinding activityProfileBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        activityProfileBinding.setHandlers(this);

        activitiesBackground = findViewById(R.id.activities_background);
        commentsBackground = findViewById(R.id.comments_backgrounds);

        activitiesBackground.setBackgroundResource(R.drawable.selected_left_layout);
        commentsBackground.setBackgroundResource(R.drawable.right_layout);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_content, new ActivitiesFragment())
                .commit();

        ((Button) findViewById(R.id.edit_profile_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, EditProfileActivity.class));
            }
        });
    }



    public void onActivitiesClick(View view) {
        activitiesBackground.setBackgroundResource(R.drawable.selected_left_layout);
        commentsBackground.setBackgroundResource(R.drawable.right_layout);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_content, new ActivitiesFragment())
                .commit();
    }

    public void onCommentsClick(View view) {
        activitiesBackground.setBackgroundResource(R.drawable.left_layout);
        commentsBackground.setBackgroundResource(R.drawable.selected_right_layout);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_content, new CommentsFragment())
                .commit();


    }
}
