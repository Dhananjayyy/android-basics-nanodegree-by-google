package com.dhananjay.musicstructure;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.dhananjay.musicstructure.R.id.listview_songs;

public class MainActivity extends AppCompatActivity {


    private ListView songList;
    private TextView textArtist;
    private TextView textMusic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);

        songList = findViewById(listview_songs);
        textArtist = findViewById(R.id.text_artist);
        textMusic = findViewById(R.id.text_music);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_songs:
                                item.setChecked(true);
                                songList.setVisibility(View.VISIBLE);
                                textArtist.setVisibility(View.GONE);
                                textMusic.setVisibility(View.GONE);
                                break;
                            case R.id.action_artists:
                                item.setChecked(true);
                                songList.setVisibility(View.GONE);
                                textArtist.setVisibility(View.VISIBLE);
                                textMusic.setVisibility(View.GONE);
                                break;
                            case R.id.action_albums:
                                item.setChecked(true);
                                songList.setVisibility(View.GONE);
                                textArtist.setVisibility(View.GONE);
                                textMusic.setVisibility(View.VISIBLE);
                                break;
                        }
                        return false;
                    }
                });


        ArrayList<Information> songInformation = new ArrayList<Information>();
        songInformation.add(new Information("Smooth", "Santana", R.drawable.santana));
        songInformation.add(new Information("Mack The Knife", "Bobby Darlin", R.drawable.bobby));
        songInformation.add(new Information("How Do I Live", "LeAnn Rimes", R.drawable.leann));
        songInformation.add(new Information("Party Rock Anthem", "LMFAO", R.drawable.lmfao));
        songInformation.add(new Information("I Gotta Feeling", "The Black Eyed Peas", R.drawable.theblackeyedpeas));
        songInformation.add(new Information("Macarena", "Los Del Rio", R.drawable.los));
        songInformation.add(new Information("Physical", "Olivia Newton-John", R.drawable.olivia_newton));
        songInformation.add(new Information("You Light Up My Life", "Debby Boone", R.drawable.debby));
        songInformation.add(new Information("Hey Jude", "The Beatles", R.drawable.the_beatles));
        songInformation.add(new Information("Uptown Funk", "Mark Ronson", R.drawable.mark_ronson));


        InformationAdapter songAdapter = new InformationAdapter(this, songInformation);


        ListView listView = findViewById(listview_songs);
        listView.setAdapter(songAdapter);
        ViewCompat.setNestedScrollingEnabled(listView, true);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(MainActivity.this, NowPlayingActivity.class);
                startActivity(intent);


            }

        });


    }


}

