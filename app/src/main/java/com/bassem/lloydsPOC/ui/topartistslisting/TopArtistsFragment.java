package com.bassem.lloydsPOC.ui.topartistslisting;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bassem.lloydsPOC.R;
import com.bassem.lloydsPOC.adapters.TopArtistsAdapter;
import com.bassem.lloydsPOC.models.Artist;
import com.bassem.lloydsPOC.ui.BaseFragment;
import com.bassem.lloydsPOC.ui.topartistslisting.di.DaggerTopArtistsComponent;
import com.bassem.lloydsPOC.ui.topartistslisting.di.TopArtistsModule;
import com.bassem.lloydsPOC.utils.Constants;

import java.util.List;

import javax.inject.Inject;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TopArtistsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class TopArtistsFragment extends BaseFragment implements TopArtistsView {
    @BindView(R.id.rclr_artists)
    RecyclerView artistsRecyclerView;
    @BindView(R.id.prgrs_main)
    ProgressBar mainProgressBar;
    private OnFragmentInteractionListener mListener;
    @BindView(R.id.empty_layout)
    View emptyLayout;
    @Inject
    TopArtistsPresenter mPresenter;
    TopArtistsAdapter mAdapter;

    public TopArtistsFragment() {
        // Required empty public constructor
    }

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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_artists, container, false);
        ButterKnife.bind(this, view);
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
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFragmentInteractionListener {

        void onArtistClicked(Artist artist);

    }
}
