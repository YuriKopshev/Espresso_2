package ru.netology;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.ViewInteraction;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import ru.kkuzmichev.simpleappforespresso.MainActivity;
import ru.kkuzmichev.simpleappforespresso.R;

public class EspressoIdlingResourcesTest {

    @Before // Выполняется перед тестами
    public void registerIdlingResources() { //Подключаемся к “счетчику”
        IdlingRegistry.getInstance().register(EspressoIdlingResources.idlingResource);
    }

    @After // Выполняется после тестов
    public void unregisterIdlingResources() { //Отключаемся от “счетчика”
        IdlingRegistry.getInstance().unregister(EspressoIdlingResources.idlingResource);
    }

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void openGalleryTest() {
        ViewInteraction menu = onView(isAssignableFrom(AppCompatImageButton.class));
        menu.check(matches(isDisplayed()));
        menu.perform(click());

        ViewInteraction gallery = onView(withId(R.id.nav_gallery));
        gallery.perform(click());


        ViewInteraction recycleView = onView(withId(R.id.recycle_view));
        recycleView.check(CustomViewAssertions.isRecyclerView());

        recycleView.check(matches(CustomViewMatcher.recyclerViewSizeMatcher(10)));

        ViewInteraction sevenItem = onView(allOf(withId(R.id.item_number), withText("7")));
        sevenItem.check(matches(withText("7")));
    }
}
