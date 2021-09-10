package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.espresso.util.EspressoOptional;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.openclassrooms.entrevoisins.R;

import junit.framework.TestCase;

import org.hamcrest.Matcher;
import org.junit.Rule;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withAlpha;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withResourceName;
import static com.openclassrooms.entrevoisins.R.id.floatingActionButton3;
import static java.util.EnumSet.allOf;
import static java.util.regex.Pattern.matches;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

public class ProfileActivityTest extends TestCase {

    @Rule
    public ActivityTestRule<ProfileActivity> mProfileActivityActivityTestRule = new ActivityTestRule<ProfileActivity>(ProfileActivity.class);


    public void chek()   throws Exception {

        //Espresso.onView(withId(floatingActionButton3)).perform(click());
        //Espresso.onView(allOf(withChild(floatingActionButton3).matches(R.drawable.star_pleine_yellow)))
               // .check()
       // onData(allOf(is(instanceOf(R.id.floatingActionButton3)), is(""))).perform(click());

       /* 1 - test vérifiant que lorsqu’on clique sur un élément de la liste, l’écran de
        détails est bien lancé ;
        2 - test vérifiant qu’au démarrage de ce nouvel écran, le TextView indiquant
        le nom de l’utilisateur en question est bien rempli ;
        3 - test vérifiant que l’onglet Favoris n’affiche que les voisins marqués comme
        favoris.*/








        super.setUp();
    }



    public void tearDown() throws Exception {
    }
}