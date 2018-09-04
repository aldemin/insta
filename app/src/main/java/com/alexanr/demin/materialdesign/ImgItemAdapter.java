package com.alexanr.demin.materialdesign;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.alexanr.demin.materialdesign.database.Database;
import com.alexanr.demin.materialdesign.database.Photo;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImgItemAdapter extends RecyclerView.Adapter<ImgItemAdapter.ViewHolder> {

    private List<Photo> photoList = new ArrayList<>();

    public void setPhotoList(List<Photo> list) {
        this.photoList.addAll(list);
        notifyDataSetChanged();
    }

    public void setImgItem(Photo item) {
        this.photoList.add(item);
        notifyDataSetChanged();
    }

    public void cleanImgItems() {
        this.photoList.clear();
        notifyDataSetChanged();
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
        private ImageButton button;

        ViewHolder(View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.image_item_container);
            button = itemView.findViewById(R.id.image_item_favorite_btn);
            button.setOnClickListener(this);
        }

        void bind(Photo item) {
            int width = (int) (0.8 * photo.getContext().getResources().getDisplayMetrics().widthPixels) / 2;
            Picasso.get()
                    .load(new File(item.getPath()))
                    .resize(width, 0)
                    .into(photo);
            if (item.getIsFavorite() == 0) {
                button.setBackground(button.getContext().getDrawable(R.drawable.ic_unfavorite));
            } else {
                button.setBackground(button.getContext().getDrawable(R.drawable.ic_favorite));
            }
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (photoList.get(position).getIsFavorite() == 0) {
                v.setBackground(v.getContext().getDrawable(R.drawable.ic_favorite));
                photoList.get(position).setIsFavorite(1);
                Database.get().getDatabase().photosDao().update(photoList.get(position));
            } else {
                v.setBackground(v.getContext().getDrawable(R.drawable.ic_unfavorite));
                photoList.get(position).setIsFavorite(0);
                Database.get().getDatabase().photosDao().update(photoList.get(position));
            }
        }
    }
}
