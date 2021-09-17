
package com.openclassrooms.entrevoisins.neighbour_list;


import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.DrawableMatcher;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;


/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;

    private ListNeighbourActivity mActivity;

    private String mName = "Caroline";

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);
    private Object ProfileActivity;


    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        ViewMatchers.assertThat(mActivity, notNullValue());
    }

    @Before
    public void favoriteRecyclerviewFragment() {
        mActivityRule.getActivity().getFragmentManager().beginTransaction();
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test

    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(withId(R.id.list_neighbours))
                .check(matches(ViewMatchers.hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shownP
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT - 1));
    }

    @Test
    public void goodName_Is_controle() {
        onView(withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(1, ViewActions.click()));
        onView(withId(R.id.floatingActionButton3)).perform(ViewActions.click());
        onView(withId(R.id.imageButtonArriere)).perform(ViewActions.click());


        onView(withId(R.id.list_neighbours)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.favoris_list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteViewAction()));


        onView(ViewMatchers.withId(R.id.favoris_list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));
        onView(withId(R.id.floatingActionButton3)).perform(ViewActions.click());
        pressBack();


    }

    @Test
    public void add_1favorites_and_Check() {
        onView(withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(2, ViewActions.click()));
        onView(withId(R.id.floatingActionButton3)).perform(ViewActions.click());
        //onView(withId(R.id.imageButtonArriere)).perform(ViewActions.click());
        //onView(withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(5, ViewActions.click())) ;
        // onView(withId(R.id.floatingActionButton3)).perform(ViewActions.click());
        onView(withId(R.id.imageButtonArriere)).perform(ViewActions.click());
        onView(withId(R.id.list_neighbours)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.favoris_list_neighbours)).check(withItemCount(4));


    }

    @Test
    public void star_change_form() {

        onView(withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(5, ViewActions.click()));
        onView(withId(R.id.floatingActionButton3)).check(matches(new DrawableMatcher(R.drawable.ic_star_yellow)));
        onView(withId(R.id.floatingActionButton3)).perform(ViewActions.click());
        onView(withId(R.id.floatingActionButton3)).check(matches(new DrawableMatcher(R.drawable.ic_star_pleine_yellow)));

    }


}




