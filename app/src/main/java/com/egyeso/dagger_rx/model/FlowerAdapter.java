package com.egyeso.dagger_rx.model;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.egyeso.dagger_rx.R;

import java.util.ArrayList;
import java.util.List;

public class FlowerAdapter extends RecyclerView.Adapter<FlowerAdapter.viewHolder> {

    private Context mContext;
    private List<FlowerResponse> mFlowerResponseList;
    private FlowerClick flowerClick;

    public FlowerAdapter(Context context, List<FlowerResponse> flowerResponseList) {
        mContext = context;
        mFlowerResponseList = flowerResponseList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flowers, parent, false);
        return new viewHolder(view,flowerClick);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.textViewName.setText(mFlowerResponseList.get(position).getName());
        holder.textViewPrice.setText(String.format("$"+mFlowerResponseList.get(position).getPrice()));
        Glide.with(mContext).load("http://services.hanselandpetal.com/photos/" + mFlowerResponseList.get(position).getPhoto()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                holder.progressBar.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                holder.progressBar.setVisibility(View.GONE);
                return false;
            }
        }).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mFlowerResponseList.size();
    }

    public void addFlowers(ArrayList<FlowerResponse> flowerResponses) {
        mFlowerResponseList.addAll(flowerResponses);
        notifyDataSetChanged();
    }

    class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        private TextView textViewName,textViewPrice;
        private ProgressBar progressBar;
        private FlowerClick flowerClick;
        viewHolder(@NonNull View itemView,FlowerClick click) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageView = itemView.findViewById(R.id.image);
            textViewName = itemView.findViewById(R.id.name);
            textViewPrice = itemView.findViewById(R.id.price);
            progressBar = itemView.findViewById(R.id.progress);
            flowerClick = click;
        }

        @Override
        public void onClick(View view) {
            flowerClick.onClick(getAdapterPosition());
        }
    }

    public interface FlowerClick{
        void onClick(int position);
    }

    public void setOnClick(FlowerClick click){
        flowerClick = click;
    }

}
