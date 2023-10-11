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
            R.raw.panelone_music1,
            R.raw.panelone_music2,
            R.raw.panelone_music3,
            R.raw.panelone_music4,
            R.raw.panelone_music5,
            R.raw.panelone_music6,
            R.raw.panelone_music7,
            R.raw.panelone_music8,
            R.raw.panelone_music9,
            R.raw.panelone_music10,
            R.raw.panelone_music11,
            R.raw.panelone_music12,
            R.raw.panelone_music13,
    };
    int [] music2 = {
            R.raw.paneltwo_music1,
            R.raw.paneltwo_music2,
            R.raw.paneltwo_music3,
            R.raw.paneltwo_music4,
            R.raw.paneltwo_music5,
            R.raw.paneltwo_music6,
            R.raw.paneltwo_music7,
            R.raw.paneltwo_music8,
            R.raw.paneltwo_music9,
            R.raw.paneltwo_music10,
            R.raw.paneltwo_music11,
            R.raw.paneltwo_music12,
            R.raw.paneltwo_music13,
            R.raw.paneltwo_music14,
            R.raw.paneltwo_music15,
            R.raw.paneltwo_music16,
            R.raw.paneltwo_music17,
            R.raw.paneltwo_music18,
    };
    int[] panelNoImage = {
            R.drawable.panel_no1,
            R.drawable.panel_no2,
            R.drawable.panel_no3,
            R.drawable.panel_no4,
            R.drawable.panel_no5,
            R.drawable.panel_no6,
            R.drawable.panel_no7,
            R.drawable.panel_no8,
            R.drawable.panel_no9,
            R.drawable.panel_no10,
            R.drawable.panel_no11,
            R.drawable.panel_no12,
            R.drawable.panel_no13,
    };

    int[] panelTwoImage = {
            R.drawable.panel_two1,
            R.drawable.panel_two2,
            R.drawable.panel_two3,
            R.drawable.panel_two4,
            R.drawable.panel_two5,
            R.drawable.panel_two6,
            R.drawable.panel_two7,
            R.drawable.panel_two8,
            R.drawable.panel_two9,
            R.drawable.panel_two10,
            R.drawable.panel_two11,
            R.drawable.panel_two12,
            R.drawable.panel_two13,
            R.drawable.panel_two18,
            R.drawable.panel_two18,
            R.drawable.panel_two16,
            R.drawable.panel_two17,
            R.drawable.panel_two18,
    };

    String[] panelOneName,panelTwoName;
    GridLayoutManager gridLayoutManager, gridLayoutManager2;
    RecyclerView recyclerView,recyclerView2;
    PanelOneAdapter panelOneAdapter;
    PanelTwoAdapter panelTwoAdapter;
    TextView showMore1,showLess1,ShowMore2,ShowLess2;

    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow(). addFlags (WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        panelOneName = getResources().getStringArray(R.array.panelOne);
        panelTwoName = getResources().getStringArray(R.array.panelTwo);

        showMore1 = findViewById(R.id.showMore1);
        ShowMore2 = findViewById(R.id.showMore2);
        showLess1 = findViewById(R.id.showLess1);
        ShowLess2 = findViewById(R.id.showLess2);
        recyclerView = findViewById(R.id.rv_ImageList);
        recyclerView2 = findViewById(R.id.rv_ImageList2);
        recyclerView.setHasFixedSize(true);
        recyclerView2.setHasFixedSize(true);
        gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager2 = new GridLayoutManager(this, 2);
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView2.setLayoutManager(gridLayoutManager2);
        panelOneAdapter = new PanelOneAdapter(this,panelNoImage,music,panelOneName);
        recyclerView.setAdapter(panelOneAdapter);
        panelTwoAdapter = new PanelTwoAdapter(this,panelTwoImage,music2,panelTwoName);
        recyclerView2.setAdapter(panelTwoAdapter);
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

                //For Adapter Two
                panelTwoAdapter.selectedItem = -1;
                panelTwoAdapter.notifyItemChanged(previousItem);
                panelTwoAdapter.notifyItemChanged(position);
            }


        });

        panelTwoAdapter.setOnItemClickListener(new PanelTwoAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                //Highlight Selected Item-----------------
                int previousItem = panelTwoAdapter.selectedItem;
                panelTwoAdapter.selectedItem = position;
                panelTwoAdapter.notifyItemChanged(previousItem);
                panelTwoAdapter.notifyItemChanged(position);
                if (previousItem==position){
                    mediaPlayer.pause();
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;
                    panelTwoAdapter.selectedItem = -1;
                    panelTwoAdapter.notifyItemChanged(previousItem);
                    panelTwoAdapter.notifyItemChanged(position);

                }else {
                    if (mediaPlayer != null) {
                        //Fist stop the current playing raw file
                        mediaPlayer.pause();
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                        //Then Play the selected raw file.
                        mediaPlayer = MediaPlayer.create(HomeActivity.this, music2[position]);
                        mediaPlayer.start();

                    } else {

                        mediaPlayer = MediaPlayer.create(HomeActivity.this, music2[position]);

                        mediaPlayer.start();

                    }
                }

                //For Adapter One
                panelOneAdapter.selectedItem = -1;
                panelOneAdapter.notifyItemChanged(previousItem);
                panelOneAdapter.notifyItemChanged(position);
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

        ShowMore2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                panelTwoAdapter.showAllItems();
                ShowMore2.setVisibility(View.GONE);
                ShowLess2.setVisibility(View.VISIBLE);
            }
        });
        ShowLess2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                panelTwoAdapter.showItems();
                ShowMore2.setVisibility(View.VISIBLE);
                ShowLess2.setVisibility(View.GONE);
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}