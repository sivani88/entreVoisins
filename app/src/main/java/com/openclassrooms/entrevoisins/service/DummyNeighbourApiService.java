package com.openclassrooms.entrevoisins.service;

import android.support.annotation.VisibleForTesting;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    @Override
    public int setFavs(int isFavorite) {
        return isFavorite;
    }

    public boolean boleanFavorite(int isFavorite) {
        if (isFavorite == 1){
            return true;
        } return false;
        }
    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    public List<Neighbour> getFavoriteNeighbours() {
        List<Neighbour> favs = new ArrayList<Neighbour>();
        for (int i = 0; i<neighbours.size(); i++) {
            if (neighbours.get(i).getIsFavorite()==1) {
                favs.add(neighbours.get(i));
            }
        }
        return favs;
    };

    public void addFavorite(Neighbour neighbour) {
        int pos = neighbours.indexOf(neighbour);
        neighbours.get(pos).setIsFavorite(1);
    }

    public void deleteFavorite(Neighbour neighbour) {
        for (int i = 0; i < neighbours.size(); i++) {
            if (neighbours.get(i).equals(neighbour)) {
                neighbours.get(i).setIsFavorite(0);
            }
        }
    }




}


