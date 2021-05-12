package com.rysecamp.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import com.rysecamp.R;
import com.rysecamp.controller.activities.SignupActivity;
import com.rysecamp.dto.NavBarListDto;
import com.rysecamp.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeActivityNavBarViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<NavBarListDto> data;
    private Context context;
    private ItemClick clickListener;

    public HomeActivityNavBarViewAdapter(Context context, List<NavBarListDto> data, ItemClick clickListener) {
        this.context = context;
        this.data = data;
        this.clickListener = clickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1:
                return new Header(LayoutInflater.from(context).inflate(R.layout.nav_header_home, parent, Boolean.FALSE));
            case 2:
                return new ListItem(LayoutInflater.from(context).inflate(R.layout.nav_menu_list_item, parent, Boolean.FALSE));
            case 3:
                return new Footer(LayoutInflater.from(context).inflate(R.layout.nav_bar_footer, parent, Boolean.FALSE));
            default:
                return new Footer(LayoutInflater.from(context).inflate(R.layout.nav_bar_footer, parent, Boolean.FALSE));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof Header)
            ((Header) holder).onBindUi(this.data.get(position), position);
        if (holder instanceof ListItem)
            ((ListItem) holder).onBindUi(this.data.get(position), position);
        if (holder instanceof Footer)
            ((Footer) holder).onBindUi(this.data.get(position), position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemViewType(int position) {
        if (data != null) {
            if (data.get(position).getViewType().equals(NavBarListDto.ViewType.HEADER))
                return 1;
            if (data.get(position).getViewType().equals(NavBarListDto.ViewType.MENU_ITEM))
                return 2;
            if (data.get(position).getViewType().equals(NavBarListDto.ViewType.FOOTER))
                return 3;
        }
        return 0;
    }

    public void replace(List<NavBarListDto> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void add(NavBarListDto data) {
        this.data.add(data);
        notifyItemInserted(getItemCount() - 1);
    }

    public void add(List<NavBarListDto> data) {
        this.data.addAll(data);
        notifyItemInserted(getItemCount() - 1);
    }

    private void remove(int position) {
        if (data.size() >= position)
            this.data.remove(position);
        notifyItemRemoved(position);
    }

    public void setOnItemClickListener(ItemClick clickListener) {
        this.clickListener = clickListener;
    }

    public List<NavBarListDto> getList() {
        return data;
    }

    public interface ItemClick {
        void onItemClickListener(NavBarListDto data, int position);

        void onCloseNavClick();
    }

    private class Header extends RecyclerView.ViewHolder {

        private TextView userName;

        private TextView userEmail;

        private ImageView profile, close;

        private Drawable headerImg;

        private Header(View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.nav_username);
            profile = itemView.findViewById(R.id.imageView);
            close = itemView.findViewById(R.id.close_nav);

            // Username to be updated here.

                //userName.setText(SignupActivity.getData1());

            if (headerImg != null)
                profile.setImageDrawable(headerImg);
        }

        private void onBindUi(final NavBarListDto data, final int position) {

            Picasso.with(context).load(R.drawable.ic_dummy_profile_photo).transform(new CircleTransform()).into(profile);

            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null)
                        clickListener.onCloseNavClick();
                }
            });
        }
    }

    private class ListItem extends RecyclerView.ViewHolder {

        private ImageView menuIcon;

        private TextView menuName;

        private View divider;

        private ListItem(View itemView) {
            super(itemView);
            //menuIconDrawable = VectorDrawableCompat.create(context.getResources(), R.drawable.ic_back, null);
            menuIcon = itemView.findViewById(R.id.nav_menu_icon);
            menuName = itemView.findViewById(R.id.nav_menu_list);
            divider = itemView.findViewById(R.id.divider);
            //menuIcon.setImageDrawable(menuIconDrawable);
        }

        private void onBindUi(final NavBarListDto data, final int position) {
            menuName.setText(data.getMenuName());
            //Drawable img = context.getResources().getDrawable(data.getMenuIcon());
            Drawable img = VectorDrawableCompat.create(context.getResources(), data.getMenuIcon(), null);
            menuIcon.setImageDrawable(img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null)
                        clickListener.onItemClickListener(data, position);
                }
            });


            if (position == (getItemCount() - 1))
                divider.setVisibility(View.GONE);
            else
                divider.setVisibility(View.VISIBLE);
        }
    }

    private class Footer extends RecyclerView.ViewHolder {

        private ImageView footerIcon;


        private Footer(View itemView) {
            super(itemView);
            footerIcon = itemView.findViewById(R.id.footer_icon);
        }

        private void onBindUi(final NavBarListDto data, final int position) {
//            Toast.makeText(context, "Footer", Toast.LENGTH_LONG).show();
        }
    }
}
