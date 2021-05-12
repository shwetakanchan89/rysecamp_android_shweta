package com.rysecamp.controller.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import com.rysecamp.R;
import com.rysecamp.adapters.ContentPremiumAdapter;
import com.rysecamp.adapters.ContentVideoAdapter;
import com.rysecamp.dto.SpotlightDto;
import com.rysecamp.utils.VerticalViewPager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ContentDetailActivity extends AppCompatActivity {
    private VerticalViewPager viewPager;
    private Context context;
    private List<String> stringList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_details);
        context = this;
//        viewPager = findViewById(R.id.viewPager);
//        addData();
//        viewPager.setAdapter(new ContentVideoAdapter(getSupportFragmentManager(), context, stringList));

        RecyclerView recyclerView = findViewById(R.id.recycler_view_content_details);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        ContentVideoAdapter contentVideoAdapter = new ContentVideoAdapter(getApplicationContext(), new ArrayList<SpotlightDto>());
        recyclerView.setAdapter(contentVideoAdapter);
        contentVideoAdapter.add(new LinkedList<SpotlightDto>(Arrays.asList(new SpotlightDto(), new SpotlightDto(), new SpotlightDto(), new SpotlightDto(), new SpotlightDto())));


        ImageView backIcon = findViewById(R.id.toolbar_icon);
        backIcon.setVisibility(View.VISIBLE);
        backIcon.setImageDrawable(VectorDrawableCompat.create(this.getResources(), R.drawable.ic_toolbar_back_icon, null));
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addData() {
        for (int i = 0; i < 8; i++) {
            stringList.add(String.valueOf(i));
        }
    }
}
