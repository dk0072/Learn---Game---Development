package com.nogravity.learnit;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.security.Signature;

public class ChatAdapter extends FirebaseRecyclerAdapter<Chat, ChatAdapter.ChatViewHolder> {

    StorageReference storageReference;
    SharedPreferences sharedPreferences;
    Context context;
    boolean retry;

    public ChatAdapter(@NonNull FirebaseRecyclerOptions<Chat> options,Context context) {
        super(options);

        sharedPreferences = context.getSharedPreferences("userLogs",Context.MODE_PRIVATE);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ChatAdapter.ChatViewHolder holder, int position, @NonNull Chat model) {


        holder.userName.setText(model.getUserName());
        holder.userComment.setText(model.getMessage());
        holder.userTimestamp.setText(model.getTimeStamp());
        holder.Date.setText(model.getDate());


    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item,parent,false);
        return new ChatViewHolder(view);
    }

    public static class ChatViewHolder extends RecyclerView.ViewHolder {
        TextView userName,userComment,userTimestamp,Date;
        //ImageView profile;
        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.userNameText);
            userComment = itemView.findViewById(R.id.userComment);
            userTimestamp = itemView.findViewById(R.id.userTimestamp);
            Date = itemView.findViewById(R.id.date);
        }
    }
}
