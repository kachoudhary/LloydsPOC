package com.example.lloydsPOC.view.listing;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.lloydsPOC.R;
import com.example.lloydsPOC.controller.TopArtistsAdapter;
import com.example.lloydsPOC.models.Artist;
import com.example.lloydsPOC.view.BaseFragment;
import com.example.lloydsPOC.view.listing.artistsdi.DaggerTopArtistsComponent;
import com.example.lloydsPOC.view.listing.artistsdi.TopArtistsModule;
import com.example.lloydsPOC.utils.Constants;
import java.util.List;
import javax.inject.Inject;
import androidx.recyclerview.widget.RecyclerView;


public class TopArtistsFragment extends BaseFragment implements TopArtistsView {


    private RecyclerView artistsRecyclerView;
    private ProgressBar mainProgressBar;
    private OnFragmentInteractionListener mListener;
    private View emptyLayout;


    @Inject
    TopArtistsPresenter mPresenter;
    TopArtistsAdapter mAdapter;

    @Override
    protected void searchUserName(String userName) {
        if (mAdapter != null) {
            mAdapter.clearDataset();
        }
        mPresenter.getUserTopArtists(userName, Constants.TOP_ITEMS_LIMIT, Constants.API_KEY);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_artists, container, false);
        artistsRecyclerView=view.findViewById(R.id.rclr_artists);
        mainProgressBar   =view.findViewById(R.id.prgrs_main);
        emptyLayout=view.findViewById(R.id.empty_layout);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerTopArtistsComponent.builder().topArtistsModule(new TopArtistsModule(this)).build().inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.getUserTopArtists(Constants.DEFAULT_LASTFM_USER, Constants.TOP_ITEMS_LIMIT, Constants.API_KEY);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void showProgress() {
        mainProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mainProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void updateData(List<Artist> topArtists) {
        if (mAdapter == null) {
            mAdapter = new TopArtistsAdapter(topArtists, getContext(), onArtistclickedListener);
            RecyclerView.LayoutManager linearLayoutManager=new GridLayoutManager(getContext(),2);
            artistsRecyclerView.setLayoutManager(linearLayoutManager);
            artistsRecyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        } else {
            mAdapter.setDataset(topArtists);
        }
    }

    View.OnClickListener onArtistclickedListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int position = artistsRecyclerView.getChildLayoutPosition(view);
            Artist artist = mAdapter.getItemByPosition(position);
            if (mListener != null) {
                mListener.onArtistClicked(artist);
            }
        }
    };

    @Override
    public void showError() {
        Toast.makeText(getContext(), R.string.general_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEmpty() {
        emptyLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidEmpty() {
        emptyLayout.setVisibility(View.GONE);

    }

    public static TopArtistsFragment newInstance() {
        return new TopArtistsFragment();
    }

    public interface OnFragmentInteractionListener {

        void onArtistClicked(Artist artist);

    }
}
