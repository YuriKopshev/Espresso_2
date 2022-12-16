package ru.netology;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;

import android.content.Intent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.kkuzmichev.simpleappforespresso.MainActivity;
import ru.kkuzmichev.simpleappforespresso.R;


@RunWith(AndroidJUnit4.class)
public class MainActivitySettingsTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivitySettingsTest() {
        Intents.init();
        ViewInteraction view = onView(Matchers.allOf(
                withParent(withParent(ViewMatchers.withId(R.id.toolbar))), isDisplayed()));
        view.perform(click());


        ViewInteraction textView = onView(
                Matchers.allOf(withId(androidx.appcompat.R.id.title), withParent(withParent(withId(androidx.appcompat.R.id.content))), isDisplayed()));

        textView.check(matches(withParent(withParent(withId(androidx.appcompat.R.id.content)))));

        textView.perform(click());
        intended(hasData("https://google.com"));
        intended(hasAction(Intent.ACTION_VIEW));
        Intents.release();
    }
}
