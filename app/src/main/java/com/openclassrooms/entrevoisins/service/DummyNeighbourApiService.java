package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements NeighbourApiService {

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
    public int setFavorites(int isFavorite) {

        return isFavorite;
    }


    /**
     * {@inheritDoc}
     *
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    @Override
    public List<Neighbour> getFavoriteNeighbours() {
        List<Neighbour> favorites = new ArrayList<Neighbour>();
        for (int i = 0; i < neighbours.size(); i++) {
            if (neighbours.get(i).getIsFavorite() == 1) {
                favorites.add(neighbours.get(i));
            }
        }
        return favorites;
    }
    //On fait une boucle for dans la liste des voisins pour recuperer la liste des favoris les voisins en 1

    @Override
    public void addFavorite(Neighbour neighbour) {
        int pos = neighbours.indexOf(neighbour);
        neighbours.get(pos).setIsFavorite(1);
        // dans la liste de voisin se voissin a ette position sera retourner en tant que favoris
    }

    @Override
    public void deleteFavorite(Neighbour neighbour) {
        for (int i = 0; i < neighbours.size(); i++) {
            if (neighbours.get(i).equals(neighbour)) {
                neighbours.get(i).setIsFavorite(0);
            }
        }
    }


}


