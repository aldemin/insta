package com.alexanr.demin.materialdesign.Recycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ToggleButton;

import com.alexanr.demin.materialdesign.R;
import com.alexanr.demin.materialdesign.database.Database;
import com.alexanr.demin.materialdesign.database.Photo;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImgItemAdapter extends RecyclerView.Adapter<ImgItemAdapter.ViewHolder> {

    private int width;
    private List<Photo> photoList = new ArrayList<>();


    public ImgItemAdapter(Context context) {
        super();
        this.width = calculateWidth(context);
    }

    public void setPhotoList(List<Photo> list) {
        this.photoList.addAll(list);
        notifyDataSetChanged();
    }

    public void setImgItem(Photo item) {
        this.photoList.add(item);
        notifyItemInserted(this.photoList.size() - 1);
    }

    public void cleanImgItems() {
        this.photoList.clear();
        notifyDataSetChanged();
    }

    private int calculateWidth(Context context) {
        float marginCoefficient = 0.8f;
        int columnCount = 2;
        return (int) (marginCoefficient * context.getResources().getDisplayMetrics().widthPixels) / columnCount;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(photoList.get(position));
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView photo;
        private ToggleButton button;

        ViewHolder(View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.image_item_container);
            button = itemView.findViewById(R.id.image_item_favorite_btn);
            button.setOnClickListener(this);
        }

        void bind(Photo item) {
            Picasso.get()
                    .load(new File(item.getPath()))
                    .resize(width, 0)
                    .into(photo);
            if (item.getIsFavorite()) {
                button.setChecked(true);
                //button.setBackground(button.getContext().getDrawable(R.drawable.ic_unfavorite));
            } else {
                //button.setBackground(button.getContext().getDrawable(R.drawable.ic_favorite));
                button.setChecked(false);
            }
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            ToggleButton button = (ToggleButton) v;
            if (photoList.get(position).getIsFavorite()) {
                //v.setBackground(v.getContext().getDrawable(R.drawable.ic_favorite));
                button.setChecked(true);
                photoList.get(position).setIsFavorite(false);
                Database.get().getDatabase().photosDao().update(photoList.get(position));
            } else {
                button.setChecked(false);
                //v.setBackground(v.getContext().getDrawable(R.drawable.ic_unfavorite));
                photoList.get(position).setIsFavorite(true);
                Database.get().getDatabase().photosDao().update(photoList.get(position));
            }
            notifyItemChanged(position);
        }
    }
}
