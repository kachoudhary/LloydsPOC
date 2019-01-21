package com.bassem.lloydsPOC.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import com.bassem.lloydsPOC.R;
import com.bassem.lloydsPOC.controller.MainPagerAdapter;
import com.bassem.lloydsPOC.models.Album;
import com.bassem.lloydsPOC.models.Artist;
import com.bassem.lloydsPOC.models.Track;
import com.bassem.lloydsPOC.view.topalbumslisting.TopAlbumsFragment;
import com.bassem.lloydsPOC.view.topartistslisting.TopArtistsFragment;
import com.bassem.lloydsPOC.view.toptrackslisting.TopTracksFragment;


public class MainActivity extends AppCompatActivity implements
        TopArtistsFragment.OnFragmentInteractionListener,
        TopAlbumsFragment.OnFragmentInteractionListener,
        TopTracksFragment.OnFragmentInteractionListener,
        View.OnClickListener{


    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private MainPagerAdapter mAdapter;
    private EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabLayout=findViewById(R.id.tl_main);
        mViewPager=findViewById(R.id.vp_main);
        searchEditText=findViewById(R.id.edt_search);
        searchEditText.setOnClickListener(this::onClick);
        looseSearchEditTextFocus();
        initializeFragments();
    }


    private void initializeFragments() {
        mAdapter = new MainPagerAdapter(getSupportFragmentManager(), this);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(3);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void showEnterValidUserNameToast() {
        Toast.makeText(this, R.string.please_enter_a_user_name, Toast.LENGTH_SHORT).show();
    }

    private boolean isValidSearch(String search) {
        if (TextUtils.isEmpty(search))
            return false;
        return true;
    }

    private void searchUser(String userName) {
        for (Fragment fr : mAdapter.getFragments()
                ) {
            if (fr instanceof BaseFragment) {
                ((BaseFragment) fr).searchUserName(userName);
            }
        }
    }


    @Override
    public void onArtistClicked(Artist artist) {
        // open artist url
        openUrl(artist.getUrl());
    }

    @Override
    public void onAlbumClicked(Album album) {
        openUrl(album.getUrl());
    }

    @Override
    public void onTrackClicked(Track track) {
        openUrl(track.getUrl());
    }

    void openUrl(String url) {
        if (!TextUtils.isEmpty(url)) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
    }

    private void looseSearchEditTextFocus() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        searchEditText.clearFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

        }
    }

    @Override
    public void onClick(View v) {
       if (v.getId()==R.id.edt_search) {
           if (isValidSearch(searchEditText.getText().toString())) {
               searchUser(searchEditText.getText().toString());
           } else {
               showEnterValidUserNameToast();
           }
           looseSearchEditTextFocus();
       }
    }

}
