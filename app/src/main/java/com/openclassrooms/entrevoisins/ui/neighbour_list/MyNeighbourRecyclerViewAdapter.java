package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.DeleteFavoriteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyNeighbourRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private final List<Neighbour> mNeighbours;
    private ItemClickListener mItemClickListener;
    private Boolean isFavorite;


    public MyNeighbourRecyclerViewAdapter(Context context, List<Neighbour> items, NeighbourFragment mItemClickListener, Boolean isFavorite) {
        super();
        mNeighbours = items;
        this.context = context;
        this.mItemClickListener = mItemClickListener;
        this.isFavorite = isFavorite;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_neighbour, parent, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Neighbour neighbour = mNeighbours.get(position);
        holder.mNeighbourName.setText(neighbour.getName());
        Glide.with(holder.mNeighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mNeighbourAvatar);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mItemClickListener != null) {
                    int position = holder.getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        mItemClickListener.onItemClick(position);
                    }
                }
            }
        });


        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isFavorite) {
                    EventBus.getDefault().post(new DeleteFavoriteNeighbourEvent(neighbour));
                } else {
                    EventBus.getDefault().post(new DeleteNeighbourEvent(neighbour));
                }


            }
        });


    }


    @Override
    public int getItemCount() {
        if (this.mNeighbours == null) {
            return 0;
        } else {
            return this.mNeighbours.size();
        }

    }

    public interface ItemClickListener {
        public void onItemClick(int position);
    }

    public Neighbour getUser(int position) {
        return this.mNeighbours.get(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_avatar)
        public ImageView mNeighbourAvatar;
        @BindView(R.id.item_list_name)
        public TextView mNeighbourName;
        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
