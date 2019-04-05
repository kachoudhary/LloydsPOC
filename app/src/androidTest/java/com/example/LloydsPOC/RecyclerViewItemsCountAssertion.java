package com.example.LloydsPOC;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;
import android.view.View;
import org.hamcrest.Matcher;
import org.junit.Assert;
import static org.hamcrest.Matchers.is;


public class RecyclerViewItemsCountAssertion implements ViewAssertion {
    private Matcher<Integer> mMatcher;

    public RecyclerViewItemsCountAssertion(int expectedCount) {
        this.mMatcher = is(expectedCount);
    }

    @Override
    public void check(View view, NoMatchingViewException noViewFoundException) {
        if (noViewFoundException != null) {
            throw noViewFoundException;
        }
        RecyclerView recyclerView = (RecyclerView) view;
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        Assert.assertTrue(mMatcher.matches(adapter.getItemCount()));
    }
}
