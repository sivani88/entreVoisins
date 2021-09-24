
package com.openclassrooms.entrevoisins.neighbour_list;


import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.DrawableMatcher;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;


/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    private NeighbourApiService service;

    // This is fixed
    private static int ITEMS_COUNT = 12;

    private ListNeighbourActivity mActivity;


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

    public void myNeighboursListShouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(withId(R.id.list_neighbours))
                .check(matches(ViewMatchers.hasMinimumChildCount(1)));
    }


    @Test
    public void myNeighboursListDeleteActionShouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT - 1));
    }


    @Test
    public void starChangeForm() {

        onView(withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(6, ViewActions.click()));
        onView(withId(R.id.floatingActionButton3)).check(matches(new DrawableMatcher(R.drawable.ic_star_yellow)));
        onView(withId(R.id.floatingActionButton3)).perform(ViewActions.click());
        onView(withId(R.id.floatingActionButton3)).check(matches(new DrawableMatcher(R.drawable.ic_star_pleine_yellow)));

    }

    @Test
    public void infoActivity() {
        onView(withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(3, ViewActions.click()));
        onView(withId(R.id.prenomVoisinCard)).check(matches(withText("Elodie")));

    }

    @Test
    public void deleteFavoriteAndCheck() {
        service = DI.getNeighbourApiService();
        List<Neighbour> favoriteNeighbours = service.getFavoriteNeighbours();
        int nbrFavorite = favoriteNeighbours.size();


        onView(withId(R.id.list_neighbours)).perform(ViewActions.swipeLeft());

        onView(withId(R.id.favoris_list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        onView(withId(R.id.favoris_list_neighbours)).check(withItemCount(nbrFavorite - 1));
    }

    @Test
    public void addFavoritesAndCheck() {
        service = DI.getNeighbourApiService();
        List<Neighbour> favoriteNeighbours = service.getFavoriteNeighbours();
        int nbrFavorite = favoriteNeighbours.size();

        onView(withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(5, ViewActions.click()));
        onView(withId(R.id.floatingActionButton3)).perform(ViewActions.click());
        onView(withId(R.id.imageButtonArriere)).perform(ViewActions.click());


        onView(withId(R.id.list_neighbours)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.favoris_list_neighbours)).check(withItemCount(nbrFavorite + 1));

    }


}





