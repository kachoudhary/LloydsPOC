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
import com.bassem.lloydsPOC.models.Track;
import com.bassem.lloydsPOC.utils.DurationConverter;
import com.bassem.lloydsPOC.utils.ImageLoader;
import java.util.List;

public class TopTracksAdapter extends RecyclerView.Adapter<TopTracksAdapter.ViewHolder> {
    List<Track> mDataset;
    Context mContext;
    View.OnClickListener mOnItemClickListener;

    public TopTracksAdapter(List<Track> items, Context context, View.OnClickListener onClickListener) {
        this.mDataset = items;
        this.mContext = context;
        this.mOnItemClickListener = onClickListener;
    }

    @Override
    public TopTracksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.track_item, parent, false);
        return new TopTracksAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Track item = mDataset.get(position);
        ImageLoader.loadImage(mContext, item.getImageUrl(), R.drawable.default_track, holder.trackImageView);
        holder.nameTextView.setText(item.getName());
        holder.artistTextView.setText(item.getArtist().getName());
        holder.playCountTextView.setText(item.getPlaycount());
        holder.durationTextView.setText(DurationConverter.getDurationInMinutesText(Long.parseLong(item.getDuration())));
    }

    @Override
    public int getItemCount() {
        if (mDataset != null) {
            return mDataset.size();
        }
        return 0;
    }

    public void setDataset(List<Track> items) {
        this.mDataset = items;
        notifyDataSetChanged();
    }

    public Track getItemAt(int position) {
        return mDataset.get(position);
    }

    public void clearDataset() {
        if (mDataset != null) {
            mDataset.clear();
            notifyDataSetChanged();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView trackImageView;
        private TextView nameTextView;
        private TextView playCountTextView;
        private TextView artistTextView;
        private TextView durationTextView;
        private CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            trackImageView=itemView.findViewById(R.id.img_track);
            nameTextView=itemView.findViewById(R.id.txt_track_name);
            playCountTextView=itemView.findViewById(R.id.txt_plays);
            artistTextView=itemView.findViewById(R.id.txt_track_artist);
            durationTextView=itemView.findViewById(R.id.txt_duration);
            cardView=itemView.findViewById(R.id.cv_track_item);
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
