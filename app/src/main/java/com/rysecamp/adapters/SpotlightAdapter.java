package com.rysecamp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.rysecamp.R;
import com.rysecamp.dto.SpotlightDto;

import java.util.List;

/**
 * Created by sahil on 26/02/2021.
 */

public class SpotlightAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<SpotlightDto> spotlightDtos;

    private Context context;

    private ItemClick itemClickListener;

    public SpotlightAdapter(Context context, List<SpotlightDto> dataList) {
        this(context, dataList, null);
    }

    public SpotlightAdapter(Context context, List<SpotlightDto> dataList, ItemClick itemClickListener) {
        this.context = context;
        this.spotlightDtos = dataList;
        this.itemClickListener = itemClickListener;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RowViewHolder(LayoutInflater.from(context).inflate(R.layout.dashboard_spotlight_card_view, parent, Boolean.FALSE));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RowViewHolder)
            ((RowViewHolder) holder).onBindUi(this.spotlightDtos.get(position), position);
    }

    @Override
    public int getItemCount() {
        return spotlightDtos.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void replace(List<SpotlightDto> data) {
        this.spotlightDtos.clear();
        this.spotlightDtos.addAll(data);
        notifyDataSetChanged();
    }

    public void clearAll() {
        this.spotlightDtos.clear();
        notifyDataSetChanged();
    }

    public void add(SpotlightDto data) {
        this.spotlightDtos.add(data);
        notifyDataSetChanged();
    }

    public void add(List<SpotlightDto> data) {
        this.spotlightDtos.addAll(data);
        notifyDataSetChanged();
    }

    public void remove(SpotlightDto data) {
        if (spotlightDtos.size() > 0) {
            this.spotlightDtos.remove(data);
            notifyDataSetChanged();
        }
    }

    public void remove(int position) {
        try {
            if (spotlightDtos.size() > position) {
                this.spotlightDtos.remove(position);
                notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        this.spotlightDtos.clear();
    }

    public List<SpotlightDto> getList() {
        return spotlightDtos;
    }

    public void OnItemClickListener(ItemClick itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClick {
        void onSpotLightClick(SpotlightDto data, View view);

        void onLastView();
    }

    public class RowViewHolder extends RecyclerView.ViewHolder {

        private RowViewHolder(View view) {
            super(view);

        }

        private void onBindUi(final SpotlightDto data, final int position) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null)
                        itemClickListener.onSpotLightClick(data, v);
                }
            });

            if (itemClickListener != null && position == (getItemCount() - 1))
                itemClickListener.onLastView();
        }

    }

}
