package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.AddFavoriteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.AddNeighbourEvent;
import com.openclassrooms.entrevoisins.events.DeleteFavoriteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ProfileActivity extends AppCompatActivity {
     ImageView mImageNeighbour ;
     TextView mPremierPrenom, mPrenomCard, mAdresse, mPhone, mInternet, mAProposDeMoi;
     ImageButton mFlecheRetourArriere;
     FloatingActionButton mFloatingActionButton;
     boolean favorite;
     NeighbourApiService mApiService;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mImageNeighbour = findViewById(R.id.imageProfile);
        mPremierPrenom = findViewById(R.id.premierPrenom);
        mAdresse = findViewById(R.id.adresse);
        mPrenomCard = findViewById(R.id.prenomVoisinCard);
        mPhone = findViewById(R.id.text_phone);
        mInternet  = findViewById(R.id.internet);
        mAProposDeMoi = findViewById(R.id.text_aproposdemoi);
        mFlecheRetourArriere  = findViewById(R.id.imageButtonArriere);
        mFloatingActionButton = findViewById(R.id.floatingActionButton3);




        Intent intent = getIntent();
        Neighbour neighbour = intent.getParcelableExtra("neighbour");
        favorite = neighbour.getIsFavorite() == 1;

        if (favorite) {
            mFloatingActionButton.setImageResource(R.drawable.ic_star_pleine_yellow);
        }
        else {
            mFloatingActionButton.setImageResource(R.drawable.ic_star_yellow);
        }

            Glide.with(mImageNeighbour.getContext())
                .load(neighbour.getAvatarUrl())
                .into(mImageNeighbour);


        mPremierPrenom.setText(neighbour.getName());
        mPrenomCard.setText(neighbour.getName());
        mAdresse.setText(neighbour.getAddress());
        mPhone.setText(neighbour.getPhoneNumber());
        mAProposDeMoi.setText(neighbour.getAboutMe());

        mApiService = DI.getNeighbourApiService();



      mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
                if (favorite) {
                    mApiService.deleteFavorite(neighbour);
                    mFloatingActionButton.setImageResource(R.drawable.ic_star_yellow);
                    Toast.makeText(ProfileActivity.this, "delete favorite", Toast.LENGTH_SHORT).show();

                } else {

                    mApiService.addFavorite(neighbour);
                    mFloatingActionButton.setImageResource(R.drawable.ic_star_pleine_yellow);

                    Toast.makeText(ProfileActivity.this, "add to favoris", Toast.LENGTH_SHORT).show();

                };
                favorite = !favorite;

          }
      });
        mFlecheRetourArriere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileActivity.this.finish();

            }
        });



    }


}