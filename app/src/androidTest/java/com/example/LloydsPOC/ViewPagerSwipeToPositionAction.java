package com.example.LloydsPOC;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewAssertion;
import androidx.viewpager.widget.ViewPager;

import android.view.View;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsAnything;
import static org.hamcrest.Matchers.anything;

public class ViewPagerSwipeToPositionAction implements ViewAction {
    private int mPosition;

    public ViewPagerSwipeToPositionAction(int position) {
        mPosition = position;
    }

    @Override
    public Matcher<View> getConstraints() {
        return new Matcher<View>() {
            @Override
            public boolean matches(Object item) {
                return (item instanceof ViewPager);
            }

            @Override
            public void describeMismatch(Object item, Description mismatchDescription) {

            }

            @Override
            public void _dont_implement_Matcher___instead_extend_BaseMatcher_() {

            }

            @Override
            public void describeTo(Description description) {

            }
        };
    }

    @Override
    public String getDescription() {
        return "set current item for a view pager";
    }

    @Override
    public void perform(UiController uiController, View view) {
        if (view instanceof ViewPager) {
            ViewPager viewPager = (ViewPager) view;
            viewPager.setCurrentItem(mPosition);
        }
    }
}
