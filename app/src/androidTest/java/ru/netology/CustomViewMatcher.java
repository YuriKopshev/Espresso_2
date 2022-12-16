package ru.netology;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.matcher.BoundedMatcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import java.util.Objects;

public class CustomViewMatcher {

    public static Matcher<View> recyclerViewSizeMatcher(final int matcherSize) {
        return new BoundedMatcher<>(RecyclerView.class) {

            @Override
            protected boolean matchesSafely(RecyclerView item) { //Проверка
                return matcherSize == Objects.requireNonNull(item.getAdapter()).getItemCount();
            }

            @Override
            public void describeTo(Description description) { // Доп. описание ошибки
                description.appendText("Item count: " + matcherSize);
            }

        };
    }
}

