package com.queueit.joyeeta;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;

public class PanelOneAdapter extends RecyclerView.Adapter<PanelOneAdapter.MyViewHolder> {
    Context context;
    int[] image;
    int[] music;
    String[] panelName;
    String[] panelNumber;
    private int visibleItemCount = 4;
    private boolean showAllItems = false;

    private static ClickListener clickListener;

    public int selectedItem;

    public PanelOneAdapter(Context context, int[] image, int[] music, String[] panelName, String[] panelNumber) {
        this.context = context;
        this.image = image;
        this.music = music;
        this.panelName = panelName;
        this.panelNumber = panelNumber;
        selectedItem = -1;
    }

    @NonNull
    @Override
    public PanelOneAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_panel_one,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PanelOneAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {


        holder.roundedImageView.setImageResource(image[position]);
        holder.panelName.setText(panelName[position]);
        holder.panelNumber.setText(panelNumber[position]);

        if (selectedItem == position){
            Log.e("s+p",""+selectedItem+"+"+position);
            holder.puseButton.setVisibility(View.VISIBLE);
            holder.playButton.setVisibility(View.GONE);
        }else {
            holder.puseButton.setVisibility(View.GONE);
            holder.playButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
//        return image.length;
        return showAllItems ? image.length : Math.min(visibleItemCount, image.length);
    }

    public void showAllItems() {
        showAllItems = true;
        notifyDataSetChanged();
    }
    public void showItems() {
        showAllItems = false;
        notifyDataSetChanged();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        RoundedImageView roundedImageView;
        ImageView playButton,puseButton;
        TextView panelName,panelNumber;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            roundedImageView = itemView.findViewById(R.id.randomLiveUserImage);
            playButton = itemView.findViewById(R.id.playButton);
            puseButton = itemView.findViewById(R.id.puseButton);
            panelName = itemView.findViewById(R.id.panelName);
            panelNumber = itemView.findViewById(R.id.panelNumber);
            playButton.setOnClickListener(this);
            puseButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition(),view);
        }
    }

    public interface ClickListener{
        void onItemClick(int position,View view);
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        PanelOneAdapter.clickListener = clickListener;
    }
}
