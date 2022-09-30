package com.nogravity.learnit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {

    Context context;
    String[] introNames;
    int[] images;
    private final OnRecycleViewClick onRecycleViewClick;
    public RecycleViewAdapter(Context context, String[] introNames,int[] images,OnRecycleViewClick onRecycleViewClick){

        this.onRecycleViewClick = onRecycleViewClick;
        this.context = context;
        this.introNames = introNames;
        this.images = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view =  layoutInflater.inflate(R.layout.recycleview_item,parent,false);

        return new ViewHolder(view, onRecycleViewClick);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    holder.title.setText(introNames[position]);
    holder.image.setImageResource(images[position]);

    }

    @Override
    public int getItemCount() {
        return introNames.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        ImageView image;
        OnRecycleViewClick onRecycleViewClick;
        public ViewHolder(@NonNull View itemView,OnRecycleViewClick onRecycleViewClick) {
            super(itemView);
            title = itemView.findViewById(R.id.introName);
            image = itemView.findViewById(R.id.introIMG);
            this.onRecycleViewClick = onRecycleViewClick;
            itemView.setOnClickListener(this);
        }



        @Override
        public void onClick(View v) {
            onRecycleViewClick.OnRecycleClick(getAdapterPosition());
        }
    }
    public interface OnRecycleViewClick{
        void OnRecycleClick(int position);
    }
}
