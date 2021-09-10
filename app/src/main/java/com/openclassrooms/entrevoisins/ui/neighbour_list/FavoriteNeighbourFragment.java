package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v4.app.Fragment;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.AddFavoriteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.DeleteFavoriteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourApiService;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class FavoriteNeighbourFragment extends NeighbourFragment implements MyNeighbourRecyclerViewAdapter.ItemClickListener {


    private List<Neighbour> mNeighbours;
    private NeighbourApiService mApiService;
    private RecyclerView mRecyclerView;



        @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fav_fragment_neighbour_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        Log.e("Recylerview: ", view.toString());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        return view;
    }

    private void initList() {
        mNeighbours = mApiService.getFavoriteNeighbours();
        mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(getContext(), mNeighbours, this, true));

    }

    public static FavoriteNeighbourFragment newInstance() {
        FavoriteNeighbourFragment fragment = new FavoriteNeighbourFragment();
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("onResume : favorite", "");

        initList();
    }
    @Override
    public void onStart() {
        super.onStart();
        Log.e("Onstart : favorite", "");
        EventBus.getDefault().register(this);

    }

    @Override
    public void onStop() {
            super.onStop();
            EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onDeleteFavoriteNeighbour(DeleteFavoriteNeighbourEvent event) {
        mApiService.deleteFavorite(event.neighbour);
        initList();
    }



    public void onItemClick(int position) {
        Log.e("tag fav","position : " + position);
        Intent intent = new Intent(mRecyclerView.getContext(), ProfileActivity.class);
        intent.putExtra("neighbour", (Parcelable) mNeighbours.get(position));
        intent.putExtra("favorite", true );
        startActivity(intent);
    }



}
