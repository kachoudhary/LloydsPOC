package com.example.LloydsPOC;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;
import android.view.View;
import android.widget.TextView;
import org.hamcrest.Matcher;
import org.junit.Assert;
import static org.hamcrest.Matchers.is;


public class RecyclerViewItemStringDataAssertion implements ViewAssertion {

    private Matcher<String> mMatcher;
    private int mTextViewId;
    private int mPosition;

    public RecyclerViewItemStringDataAssertion(int textViewId, String expectedText, int position) {
        this.mMatcher = is(expectedText);
        this.mTextViewId = textViewId;
        this.mPosition = position;
    }



    @Override
    public void check(View view, NoMatchingViewException noViewFoundException) {
        if (noViewFoundException != null) {
            throw noViewFoundException;
        }

        RecyclerView recyclerView = (RecyclerView) view;
        View item = recyclerView.getLayoutManager().findViewByPosition(mPosition);
        TextView textView = (TextView) item.findViewById(mTextViewId);
        Assert.assertTrue(mMatcher.matches(textView.getText().toString()));

    }


}