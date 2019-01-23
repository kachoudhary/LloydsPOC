package com.example.lloydsPOC.controller;

import android.content.Context;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.lloydsPOC.R;
import com.example.lloydsPOC.models.Artist;
import com.example.lloydsPOC.utils.ImageLoader;
import java.util.List;


public class TopArtistsAdapter extends RecyclerView.Adapter<TopArtistsAdapter.ViewHolder> {
    private List<Artist> mDataset;
    private View.OnClickListener mOnClickListener;
    private Context mContext;

    public TopArtistsAdapter(List<Artist> items, Context context, View.OnClickListener onItemClickListener) {
        this.mDataset = items;
        this.mOnClickListener = onItemClickListener;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Artist item = mDataset.get(position);
        ImageLoader.loadImage(mContext, item.getImageUrl(), R.drawable.default_artist, holder.artistImageView);
        holder.artistTextView.setText(item.getName());
        holder.numberOfPlaysTextView.setText(item.getPlaycount());
    }

    @Override
    public int getItemCount() {
        if (mDataset != null) {
            return mDataset.size();
        }
        return 0;
    }

    public void setDataset(List<Artist> items) {
        this.mDataset = items;
        notifyDataSetChanged();
    }

    public Artist getItemByPosition(int position) {
        return mDataset.get(position);
    }

    public void clearDataset() {
        if(mDataset!=null){
            mDataset.clear();
            notifyDataSetChanged();
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView artistImageView;
        private TextView artistTextView;
        private TextView numberOfPlaysTextView;
        private CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            artistImageView=itemView.findViewById(R.id.img_artist);
            artistTextView=itemView.findViewById(R.id.txt_artist_name);
            numberOfPlaysTextView=itemView.findViewById(R.id.txt_plays);
            cardView=itemView.findViewById(R.id.cv_artist_item);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnClickListener != null) {
                        mOnClickListener.onClick(v);
                    }
                }
            });
        }
    }

}
