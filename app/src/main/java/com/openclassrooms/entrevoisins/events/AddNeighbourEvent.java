package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class AddNeighbourEvent {

    public Neighbour mNeighbour;

    public AddNeighbourEvent(Neighbour neighbour) {
        this.mNeighbour = neighbour;
    }
}
