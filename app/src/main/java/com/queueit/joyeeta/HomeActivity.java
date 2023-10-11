package com.queueit.joyeeta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    int[] music = {
            R.raw.panel_music_1,
            R.raw.panel_music_2,
            R.raw.panel_music_3,
            R.raw.panel_music_4,
            R.raw.panel_music_5,
            R.raw.panel_music_6,
            R.raw.panel_music_7,
            R.raw.panel_music_8,
            R.raw.panel_music_9,
            R.raw.panel_music_10,
            R.raw.panel_music_11,
            R.raw.panel_music_12,
            R.raw.panel_music_13,
            R.raw.panel_music_14,
            R.raw.panel_music_15,
            R.raw.panel_music_16,
            R.raw.panel_music_17,
            R.raw.panel_music_18,
            R.raw.panel_music_19,
            R.raw.panel_music_20,
            R.raw.panel_music_21,
            R.raw.panel_music_22,
            R.raw.panel_music_23,
            R.raw.panel_music_24,
            R.raw.panel_music_25,
            R.raw.panel_music_26,
            R.raw.panel_music_27,
            R.raw.panel_music_28,
            R.raw.panel_music_29,
            R.raw.panel_music_30,
            R.raw.panel_music_31,
    };

    int[] panelNoImage = {
            R.drawable.panel_1,
            R.drawable.panel_2,
            R.drawable.panel_3,
            R.drawable.panel_4,
            R.drawable.panel_5,
            R.drawable.panel_6,
            R.drawable.panel_7,
            R.drawable.panel_8,
            R.drawable.panel_9,
            R.drawable.panel_10,
            R.drawable.panel_11,
            R.drawable.panel_12,
            R.drawable.panel_13,
            R.drawable.panel_14,
            R.drawable.panel_15,
            R.drawable.panel_16,
            R.drawable.panel_17,
            R.drawable.panel_18,
            R.drawable.panel_19,
            R.drawable.panel_20,
            R.drawable.panel_21,
            R.drawable.panel_22,
            R.drawable.panel_23,
            R.drawable.panel_24,
            R.drawable.panel_25,
            R.drawable.panel_26,
            R.drawable.panel_27,
            R.drawable.panel_28,
            R.drawable.panel_29,
            R.drawable.panel_30,
            R.drawable.panel_31,
    };


    String[] panelOneName,panelTwoName;
    GridLayoutManager gridLayoutManager, gridLayoutManager2;
    RecyclerView recyclerView;
    PanelOneAdapter panelOneAdapter;
    TextView showMore1,showLess1;

    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow(). addFlags (WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        panelOneName = getResources().getStringArray(R.array.panelOne);

        showMore1 = findViewById(R.id.showMore1);

        showLess1 = findViewById(R.id.showLess1);

        recyclerView = findViewById(R.id.rv_ImageList);

        recyclerView.setHasFixedSize(true);

        gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager2 = new GridLayoutManager(this, 2);
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(gridLayoutManager);

        panelOneAdapter = new PanelOneAdapter(this,panelNoImage,music,panelOneName);
        recyclerView.setAdapter(panelOneAdapter);

        panelOneAdapter.setOnItemClickListener(new PanelOneAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View view) {

                //Highlight Selected Item-----------------
                int previousItem = panelOneAdapter.selectedItem;
                panelOneAdapter.selectedItem = position;
                panelOneAdapter.notifyItemChanged(previousItem);
                panelOneAdapter.notifyItemChanged(position);
                if (previousItem==position){
                    mediaPlayer.pause();
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;
                    panelOneAdapter.selectedItem = -1;
                    panelOneAdapter.notifyItemChanged(previousItem);
                    panelOneAdapter.notifyItemChanged(position);
                }else {
                    if(mediaPlayer != null) {
                        //Fist stop the current playing raw file
                        mediaPlayer.pause();
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                        //Then Play the selected raw file.
                        mediaPlayer = MediaPlayer.create(HomeActivity.this, music[position]);
                        mediaPlayer.start();
                    } else {

                        mediaPlayer = MediaPlayer.create(HomeActivity.this, music[position]);
                        mediaPlayer.start();

                    }
                }


            }


        });


        showMore1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                panelOneAdapter.showAllItems();
                showMore1.setVisibility(View.GONE);
                showLess1.setVisibility(View.VISIBLE);
            }
        });
        showLess1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                panelOneAdapter.showItems();
                showMore1.setVisibility(View.VISIBLE);
                showLess1.setVisibility(View.GONE);
            }
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}