package com.bassem.lloydsPOC.view.topalbumslisting;

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
import com.bassem.lloydsPOC.controller.TopAlbumsAdapter;
import com.bassem.lloydsPOC.models.Album;
import com.bassem.lloydsPOC.view.BaseFragment;
import com.bassem.lloydsPOC.view.topalbumslisting.di.DaggerTopAlbumsComponent;
import com.bassem.lloydsPOC.view.topalbumslisting.di.TopAlbumsModule;
import com.bassem.lloydsPOC.utils.Constants;
import java.util.List;
import javax.inject.Inject;
import androidx.recyclerview.widget.RecyclerView;



public class TopAlbumsFragment extends BaseFragment implements TopAlbumsView {


    private RecyclerView albumsRecyclerView;
    private ProgressBar mainprogressBar;
    private View emptyLayout;


    @Inject
    TopAlbumsPresenter mPresenter;
    TopAlbumsAdapter mAdapter;
    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_albums, container, false);
        albumsRecyclerView=view.findViewById(R.id.rclr_albums);
        mainprogressBar=view.findViewById(R.id.prgrs_main);
        emptyLayout=view.findViewById(R.id.empty_layout);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerTopAlbumsComponent.builder().topAlbumsModule(new TopAlbumsModule(this)).build().inject(this);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.getTopAlbums(Constants.DEFAULT_LASTFM_USER, Constants.TOP_ITEMS_LIMIT, Constants.API_KEY);
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
    protected void searchUserName(String userName) {
        if (mAdapter != null) {
            mAdapter.clearDataset();
        }
        mPresenter.getTopAlbums(userName, Constants.TOP_ITEMS_LIMIT, Constants.API_KEY);

    }

    @Override
    public void showProgress() {
        mainprogressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mainprogressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        Toast.makeText(getContext(), R.string.general_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateData(List<Album> topAlbums) {
        if (mAdapter == null) {

            mAdapter = new TopAlbumsAdapter(topAlbums, getContext(), mOnAlbumClickedListener);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            albumsRecyclerView.setLayoutManager(layoutManager);
            albumsRecyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        } else {
            mAdapter.setDataset(topAlbums);
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

    View.OnClickListener mOnAlbumClickedListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (mListener != null) {
                int position = albumsRecyclerView.getChildAdapterPosition(view);
                mListener.onAlbumClicked(mAdapter.getItemByPosition(position));
            }
        }
    };

    public static TopAlbumsFragment newInstance() {
        return new TopAlbumsFragment();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFragmentInteractionListener {

        void onAlbumClicked(Album album);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }
}
