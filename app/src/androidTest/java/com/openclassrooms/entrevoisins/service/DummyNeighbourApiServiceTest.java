package com.openclassrooms.entrevoisins.service;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DummyNeighbourApiServiceTest {


    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
        List<Neighbour> neighbours = service.getNeighbours();
    }

    @Test
    public void getFavoriteNeighbours() {
        List<Neighbour> neighboursFavorite = service.getFavoriteNeighbours();
        assertEquals(0,neighboursFavorite.size());
    }

    @Test
    public void addFavorite() {


    }

    @Test
    public void deleteFavorite() {
    }

    @Test
    public void getFavs() {
    }
}