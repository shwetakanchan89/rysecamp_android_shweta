package com.rysecamp.controller.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rysecamp.R;
import com.rysecamp.adapters.HomeActivityNavBarViewAdapter;
import com.rysecamp.api.ApiClient;
import com.rysecamp.api.EgApi;
import com.rysecamp.controller.fragments.ContentFragment;
import com.rysecamp.controller.fragments.DashboardFragment;
import com.rysecamp.controller.fragments.NotificationsFragment;
import com.rysecamp.controller.fragments.SubscriptionFragment;
import com.rysecamp.dto.NavBarListDto;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends BaseActivity implements View.OnClickListener, HomeActivityNavBarViewAdapter.ItemClick {

    private FrameLayout homeLayout, streamLayout, notificationLayout;

    private RecyclerView recyclerView;

    private HomeActivityNavBarViewAdapter adapter;

    String data1 = SignupActivity.getData1();

    String userId,token,nameStr;
    EgApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");
        token = intent.getStringExtra("token");
        nameStr = intent.getStringExtra("nameStr");

        Log.d("newtoken",token);
        //Toast.makeText(this, token, Toast.LENGTH_SHORT).show();

        api = ApiClient.getClient().create(EgApi.class);

        api.homeAuth("Bearer "+token).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //Toast.makeText(HomeActivity.this, response.body(), Toast.LENGTH_SHORT).show();
                Toast.makeText(HomeActivity.this, "Welcome "+ nameStr+" ", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "failed", Toast.LENGTH_SHORT).show();

            }
        });






        homeLayout = findViewById(R.id.frame_layout_home);
        streamLayout = findViewById(R.id.frame_layout_stream);
        notificationLayout = findViewById(R.id.frame_layout_notification);

        homeLayout.setOnClickListener(this);
        streamLayout.setOnClickListener(this);
        notificationLayout.setOnClickListener(this);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        recyclerView = findViewById(R.id.nav_list_recycler_home_activity);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);
        adapter = new HomeActivityNavBarViewAdapter(getApplicationContext(), setMenus(), this);
        recyclerView.setAdapter(adapter);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new DashboardFragment()).commit();
    }

    private List<NavBarListDto> setMenus() {
        List<NavBarListDto> menuList = new LinkedList<>();
        menuList.add(new NavBarListDto(NavBarListDto.ViewType.HEADER, "", R.drawable.ic_bottom_bar_stream_icon));

        menuList.add(new NavBarListDto(NavBarListDto.ViewType.MENU_ITEM, "My Profile", R.drawable.ic_bottom_bar_stream_icon));

        menuList.add(new NavBarListDto(NavBarListDto.ViewType.MENU_ITEM, "Categories", R.drawable.ic_nav_categories_icon));

        menuList.add(new NavBarListDto(NavBarListDto.ViewType.MENU_ITEM, "My Content", R.drawable.ic_nav_my_content_icon));

        menuList.add(new NavBarListDto(NavBarListDto.ViewType.MENU_ITEM, "Subscription", R.drawable.ic_nav_subscription_icon));

        menuList.add(new NavBarListDto(NavBarListDto.ViewType.MENU_ITEM, "Settings", R.drawable.ic_nav_setting_icon));

        menuList.add(new NavBarListDto(NavBarListDto.ViewType.MENU_ITEM, "Help", R.drawable.ic_nav_help_icon));

        menuList.add(new NavBarListDto(NavBarListDto.ViewType.FOOTER, "Help", R.drawable.ic_nav_help_icon));

        return menuList;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.frame_layout_home:
                homeLayout.setBackground(HomeActivity.this.getDrawable(R.drawable.bottom_menu_circle_selected));
                streamLayout.setBackground(HomeActivity.this.getDrawable(R.drawable.bottom_menu_circle));
                notificationLayout.setBackground(HomeActivity.this.getDrawable(R.drawable.bottom_menu_circle));
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new DashboardFragment())
                        .commit();
                break;
            case R.id.frame_layout_stream:
                homeLayout.setBackground(HomeActivity.this.getDrawable(R.drawable.bottom_menu_circle));
                streamLayout.setBackground(HomeActivity.this.getDrawable(R.drawable.bottom_menu_circle_selected));
                notificationLayout.setBackground(HomeActivity.this.getDrawable(R.drawable.bottom_menu_circle));
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new ContentFragment())
                        .commit();
                break;
            case R.id.frame_layout_notification:
                homeLayout.setBackground(HomeActivity.this.getDrawable(R.drawable.bottom_menu_circle));
                streamLayout.setBackground(HomeActivity.this.getDrawable(R.drawable.bottom_menu_circle));
                notificationLayout.setBackground(HomeActivity.this.getDrawable(R.drawable.bottom_menu_circle_selected));
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new NotificationsFragment())
                        .commit();
                break;
        }
    }

    @Override
    public void onItemClickListener(NavBarListDto data, int position) {

        switch (data.getMenuName()) {
            case "My Profile":
                startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
                break;
            case "Categories":
                break;
            case "My Content":
                break;
            case "Subscription":
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new SubscriptionFragment())
                        .addToBackStack(null)
                        .commit();
                break;
            case "Settings":
                break;
            case "Help":
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onCloseNavClick() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
}
