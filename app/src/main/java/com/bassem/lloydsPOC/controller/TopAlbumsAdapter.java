package com.bassem.lloydsPOC.controller;

import android.content.Context;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bassem.lloydsPOC.R;
import com.bassem.lloydsPOC.models.Album;
import com.bassem.lloydsPOC.utils.ImageLoader;
import java.util.List;

public class TopAlbumsAdapter extends RecyclerView.Adapter<TopAlbumsAdapter.ViewHolder> {
    List<Album> mDataset;
    Context mContext;
    View.OnClickListener mOnItemClickListener;

    public TopAlbumsAdapter(List<Album> items, Context context, View.OnClickListener onClickListener) {
        this.mDataset = items;
        this.mContext = context;
        this.mOnItemClickListener = onClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Album item = mDataset.get(position);
        ImageLoader.loadImage(mContext, item.getImageUrl(), R.drawable.default_album, holder.albumImageView);
        holder.nameTextView.setText(item.getName());
        holder.playCountTextView.setText(item.getPlaycount());
        holder.artistTextView.setText(item.getArtist().getName());
    }

    @Override
    public int getItemCount() {
        if (mDataset != null) {
            return mDataset.size();
        }
        return 0;
    }

    public void setDataset(List<Album> items) {
        this.mDataset = items;
        notifyDataSetChanged();
    }

    public Album getItemByPosition(int position) {
        return mDataset.get(position);
    }

    public void clearDataset() {
        if (mDataset != null) {
            mDataset.clear();
            notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView albumImageView;
        private TextView artistTextView;
        private TextView nameTextView;
        private TextView playCountTextView;
        private CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            albumImageView=itemView.findViewById(R.id.img_album);
            artistTextView=itemView.findViewById(R.id.txt_album_artist);
            nameTextView=itemView.findViewById(R.id.txt_album_name);
            playCountTextView=itemView.findViewById(R.id.txt_plays);
            cardView=itemView.findViewById(R.id.cv_album_item);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onClick(v);
                    }
                }
            });
        }
    }
}
