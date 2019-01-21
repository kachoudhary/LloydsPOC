package com.bassem.lloydsPOC.view.toptrackslisting;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.bassem.lloydsPOC.R;
import com.bassem.lloydsPOC.controller.TopTracksAdapter;
import com.bassem.lloydsPOC.models.Track;
import com.bassem.lloydsPOC.view.BaseFragment;
import com.bassem.lloydsPOC.view.toptrackslisting.di.DaggerTopTracksComponent;
import com.bassem.lloydsPOC.view.toptrackslisting.di.TopTracksModule;
import com.bassem.lloydsPOC.utils.Constants;
import java.util.List;
import javax.inject.Inject;
import androidx.recyclerview.widget.RecyclerView;

public class TopTracksFragment extends BaseFragment implements TopTracksView {

    private OnFragmentInteractionListener mListener;
    private RecyclerView tracksRecyclerView;
    private ProgressBar mainProgressBar;
    private View emptyLayout;

    @Inject
    TopTracksPresenter mPresenter;
    TopTracksAdapter mAdapter;

    public static TopTracksFragment newInstance() {
        TopTracksFragment fragment = new TopTracksFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_top_tracks, container, false);
        tracksRecyclerView=view.findViewById(R.id.rclr_tracks);
        mainProgressBar=view.findViewById(R.id.prgrs_main);
        emptyLayout =view.findViewById(R.id.empty_layout);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerTopTracksComponent.builder().topTracksModule(new TopTracksModule(this)).build().inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.getTopTracks(Constants.DEFAULT_LASTFM_USER, Constants.TOP_ITEMS_LIMIT, Constants.API_KEY);
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
    public void showError() {
        Toast.makeText(getContext(), R.string.general_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateData(List<Track> tracks) {
        if (mAdapter == null) {
            mAdapter = new TopTracksAdapter(tracks, getContext(), onTrackClickedListener);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            tracksRecyclerView.setLayoutManager(linearLayoutManager);
            tracksRecyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        } else {
            mAdapter.setDataset(tracks);
        }
    }

    @Override
    public void showEmpty() {
        emptyLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidEmpty() {
        emptyLayout.setVisibility(View.GONE);
    }

    @Override
    protected void searchUserName(String userName) {
        if (mAdapter != null) {
            mAdapter.clearDataset();
        }
        mPresenter.getTopTracks(userName, Constants.TOP_ITEMS_LIMIT, Constants.API_KEY);

    }

    public interface OnFragmentInteractionListener {
        void onTrackClicked(Track track);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    View.OnClickListener onTrackClickedListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (mListener != null) {
                int position = tracksRecyclerView.getChildAdapterPosition(view);
                Track track = mAdapter.getItemAt(position);
                mListener.onTrackClicked(track);
            }
        }
    };
}
