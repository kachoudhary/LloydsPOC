package com.example.lloydsPOC.view;

import android.content.Context;
import com.google.android.material.tabs.TabLayout;

import androidx.browser.customtabs.CustomTabsIntent;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;
import com.example.lloydsPOC.R;
import com.example.lloydsPOC.controller.MainPagerAdapter;
import com.example.lloydsPOC.models.Album;
import com.example.lloydsPOC.models.Artist;
import com.example.lloydsPOC.view.listing.TopAlbumsFragment;
import com.example.lloydsPOC.view.listing.TopArtistsFragment;


public class MainActivity extends AppCompatActivity implements
        TopArtistsFragment.OnFragmentInteractionListener,
        TopAlbumsFragment.OnFragmentInteractionListener,
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
        mViewPager.setOffscreenPageLimit(2);
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

    void openUrl(String url) {
        if (!TextUtils.isEmpty(url)) {
            CustomTabsIntent.Builder builder=new CustomTabsIntent.Builder();
            builder.setStartAnimations(this, R.anim.fui_slide_in_right, R.anim.fui_slide_out_left);
            builder.setExitAnimations(this, R.anim.fui_slide_out_left, R.anim.fui_slide_in_right);
            CustomTabsIntent customTabsIntent=builder.build();
            customTabsIntent.launchUrl(this, Uri.parse(url));
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
