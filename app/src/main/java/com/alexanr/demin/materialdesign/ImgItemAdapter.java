package com.alexanr.demin.materialdesign;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ImgItemAdapter extends RecyclerView.Adapter<ImgItemAdapter.ViewHolder> {

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.image_item_container);
        }

        void bind(Img item) {
            this.img.setImageBitmap(item.getImg());
        }
    }

    private List<Img> imgList = new ArrayList<>();

    public void setImgList(List<Img> list) {
        this.imgList.addAll(list);
        notifyDataSetChanged();
    }

    public void setImgItem(Img item) {
        this.imgList.add(item);
        notifyDataSetChanged();
    }

    public void cleanImgItems() {
        this.imgList.clear();
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
        holder.bind(imgList.get(position));
    }

    @Override
    public int getItemCount() {
        return imgList.size();
    }
}
