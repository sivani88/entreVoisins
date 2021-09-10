package com.openclassrooms.entrevoisins.utils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;

public class MyDiffUtilCallbacks extends DiffUtil.Callback {
    ArrayList<Neighbour> oldNeighbourS = new ArrayList<>();
    ArrayList<Neighbour> newNeighbourS =  new ArrayList<>();
    Neighbour newNeighbour, oldNeighbour;

    public MyDiffUtilCallbacks(ArrayList<Neighbour> oldNeighbour, ArrayList<Neighbour> newNeighbour) {
        this.oldNeighbourS = oldNeighbourS;
        this.newNeighbourS = newNeighbourS;
    }

    @Override
    public int getOldListSize() {

        return oldNeighbourS != null ? oldNeighbourS.size(): 0;
    }

    @Override
    public int getNewListSize() {
        return newNeighbourS != null ? newNeighbourS.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int i, int i1) {
        return true;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldNeighbourS.get(oldItemPosition).equals(newNeighbourS.get(newItemPosition));


    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        newNeighbour = newNeighbourS.get(newItemPosition);
        oldNeighbour = oldNeighbourS.get(oldItemPosition);

        Bundle bundle = new Bundle();

        if (!newNeighbour.getName().equals(oldNeighbour.getName())){
            bundle.putString("name",newNeighbour.getName());
        }
        if (!newNeighbour.getAvatarUrl().equals(oldNeighbour.getAvatarUrl())){
            bundle.putString("phone", newNeighbour.getAvatarUrl());
        }
        if (bundle.size()==0)
            return null;

        return bundle;
    }
}
