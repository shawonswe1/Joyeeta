package com.queueit.joyeeta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity {
    PrefManager prefManager;
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

    int[] music_en = {
            R.raw.panel_music_en_1,
            R.raw.panel_music_en_2,
            R.raw.panel_music_en_3,
            R.raw.panel_music_en_4,
            R.raw.panel_music_en_5,
            R.raw.panel_music_en_6,
            R.raw.panel_music_en_7,
            R.raw.panel_music_en_8,
            R.raw.panel_music_en_9,
            R.raw.panel_music_en_10,
            R.raw.panel_music_en_11,
            R.raw.panel_music_en_12,
            R.raw.panel_music_en_13,
            R.raw.panel_music_en_14,
            R.raw.panel_music_en_15,
            R.raw.panel_music_en_16,
            R.raw.panel_music_en_17,
            R.raw.panel_music_en_18,
            R.raw.panel_music_en_19,
            R.raw.panel_music_en_20,
            R.raw.panel_music_en_21,
            R.raw.panel_music_en_22,
            R.raw.panel_music_en_23,
            R.raw.panel_music_en_24,
            R.raw.panel_music_en_25,
            R.raw.panel_music_en_26,
            R.raw.panel_music_en_27,
            R.raw.panel_music_en_28,
            R.raw.panel_music_en_29,
            R.raw.panel_music_en_30,
            R.raw.panel_music_en_31,
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
    String language;
    String[] panelOneName,panelOneNameEn,panelNumber,panelNumberEn;
    GridLayoutManager gridLayoutManager, gridLayoutManager2;
    RecyclerView recyclerView;
    PanelOneAdapter panelOneAdapter;
    TextView showMore1,showLess1,lenguageText,textView,jamdaniGallery,jayeetaTower;
    MediaPlayer mediaPlayer;
    Handler handler;
    Runnable r;
    AlertDialog alertDialog;
    ImageView menuLogo;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow(). addFlags (WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        prefManager = new PrefManager(this);
        language = prefManager.getLanguage();
        lenguageText = findViewById(R.id.lenguageText);
        jamdaniGallery = findViewById(R.id.jamdaniGallery);
        jayeetaTower = findViewById(R.id.jayeetaTower);
        textView = findViewById(R.id.textView);
        panelOneName = getResources().getStringArray(R.array.panelOne);
        panelOneNameEn = getResources().getStringArray(R.array.panelOneEn);
        panelNumber = getResources().getStringArray(R.array.panelNumber);
        panelNumberEn = getResources().getStringArray(R.array.panelNumberEn);

        menuLogo = findViewById(R.id.menu_Logo);
        lenguageText.setOnClickListener(v -> {
            // Initializing the popup menu and giving the reference as current context
            PopupMenu popupMenu = new PopupMenu(HomeActivity.this, lenguageText);

            // Inflating popup menu from popup_menu.xml file
            popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                // Toast message on menu item clicked
                if (Objects.equals(menuItem.getTitle(), "English")){
                    language = "English";
                    panelOneAdapter = new PanelOneAdapter(HomeActivity.this,panelNoImage,music_en,panelOneNameEn,panelNumberEn);
                    recyclerView.setAdapter(panelOneAdapter);
                    prefManager.saveLanguage("English");
                    jamdaniGallery.setText("Jamdani Gallery");
                    jayeetaTower.setText("Jayeeta Tower");
                    lenguageText.setText(menuItem.getTitle());
                    textView.setText("Audio Tour");
                    showMore1.setText("See More ");
                    showLess1.setText("Minimize");
                }else {
                    language = "Bangla";
                    panelOneAdapter = new PanelOneAdapter(HomeActivity.this,panelNoImage,music,panelOneName,panelNumber);
                    recyclerView.setAdapter(panelOneAdapter);
                    prefManager.saveLanguage("Bangla");
                    jamdaniGallery.setText("জামদানি গ্যালারি");
                    jayeetaTower.setText("জয়িতা টাওয়ার");
                    lenguageText.setText(menuItem.getTitle());
                    textView.setText("অডিও ট্যুর");
                    showMore1.setText("আরও দেখুন ");
                    showLess1.setText("সংক্ষিপ্ত করুন");
                }

//                        Toast.makeText(HomeActivity.this, "You Clicked " + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            });
            // Showing the popup menu
            popupMenu.show();
        });
        showMore1 = findViewById(R.id.showMore1);

        showLess1 = findViewById(R.id.showLess1);

        recyclerView = findViewById(R.id.rv_ImageList);

        recyclerView.setHasFixedSize(true);

        gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager2 = new GridLayoutManager(this, 2);
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(gridLayoutManager);

        if (language.equals("Bangla")){
            panelOneAdapter = new PanelOneAdapter(this,panelNoImage,music,panelOneName,panelNumber);
            recyclerView.setAdapter(panelOneAdapter);
            lenguageText.setText("বাংলা");
            jamdaniGallery.setText("জামদানি গ্যালারি");
            jayeetaTower.setText("জয়িতা টাওয়ার");
            textView.setText("অডিও ট্যুর");
            showMore1.setText("আরও দেখুন ");
            showLess1.setText("সংক্ষিপ্ত করুন");
        }else {
            panelOneAdapter = new PanelOneAdapter(this,panelNoImage,music_en,panelOneNameEn,panelNumberEn);
            recyclerView.setAdapter(panelOneAdapter);
            lenguageText.setText("English");
            jamdaniGallery.setText("Jamdani Gallery");
            jayeetaTower.setText("Jayeeta Tower");
            textView.setText("Audio Tour");
            showMore1.setText("See More ");
            showLess1.setText("Minimize");
        }


        panelOneAdapter.setOnItemClickListener((position, view) -> {

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
                    if (language.equals("Bangla")){
                        mediaPlayer = MediaPlayer.create(HomeActivity.this, music[position]);
                    }else {
                        mediaPlayer = MediaPlayer.create(HomeActivity.this, music_en[position]);
                    }

                    mediaPlayer.start();
                } else {
                    if (language.equals("Bangla")){
                        mediaPlayer = MediaPlayer.create(HomeActivity.this, music[position]);
                    }else {
                        mediaPlayer = MediaPlayer.create(HomeActivity.this, music_en[position]);
                    }
                    mediaPlayer.start();

                }
            }


        });


        showMore1.setOnClickListener(view -> {
            panelOneAdapter.showAllItems();
            showMore1.setVisibility(View.GONE);
            showLess1.setVisibility(View.VISIBLE);
        });
        showLess1.setOnClickListener(view -> {
            panelOneAdapter.showItems();
            showMore1.setVisibility(View.VISIBLE);
            showLess1.setVisibility(View.GONE);
        });

        handler = new Handler();
        r = () -> {
            // TODO Auto-generated method stub
//                Toast.makeText(HomeActivity.this, "user is inactive from last 5 minutes",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(HomeActivity.this,StartActivity.class));
            finish();
        };
        startHandler();
    }


    @Override
    public void onBackPressed() {
//        AlertDialogBox();
        super.onBackPressed();
    }

    @Override
    public void onUserInteraction() {
        // TODO Auto-generated method stub
        super.onUserInteraction();
        stopHandler();//stop first and then start
        startHandler();
    }
    public void stopHandler() {
        handler.removeCallbacks(r);
    }
    public void startHandler() {
        handler.postDelayed(r, 3*60*1000); //for 3 minutes
//        handler.postDelayed(r, 3000); //for 5 minutes
    }
    @SuppressLint("MissingInflatedId")
    private void AlertDialogBox()
    {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.sample_dialog_box,viewGroup,false);

       EditText editText = view.findViewById(R.id.et_Code);
       TextView Exit = view.findViewById(R.id.tv_Exit);
       Exit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               String code = editText.getText().toString();
               if (code.equals("5807"))
               {
                   finish();
               }else {
                   Toast.makeText(HomeActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
               }

           }
       });
        TextView cancel = view.findViewById(R.id.tv_Cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        builder.setCancelable(true);
        builder.setView(view);

        alertDialog = builder.create();
        alertDialog.show();
    }

//    @Override
//    public void onAttachedToWindow() {
//        this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG);
//        super.onAttachedToWindow();
//    }

}