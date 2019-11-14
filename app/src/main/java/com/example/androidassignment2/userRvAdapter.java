package com.example.androidassignment2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidassignment2.Model.User;

import java.util.ArrayList;
import java.util.List;

public class userRvAdapter extends RecyclerView.Adapter<userRvAdapter.userHolder> {
    List<User> users= new ArrayList<>();
    private Context context;
    private onUserClickListener onUserClickListener;


    public userRvAdapter(List<User> users,Context context,onUserClickListener onUserClickListener){
        this.users = users;
        this.context = context;
        this.onUserClickListener = onUserClickListener;


    }







    @NonNull
    @Override
    public userRvAdapter.userHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.userdisplay,parent,false);
        userHolder userHolder = new userHolder(view, onUserClickListener);



        return userHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull userRvAdapter.userHolder holder, int position) {
        final User user = users.get(position);
        holder.tvName.setText(user.getName());
        holder.imageView.setImageResource(user.getImg());


        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });






    }

    @Override
    public int getItemCount() {
        return users.size();
    }


    public class userHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView;
        TextView tvName;
        onUserClickListener onUserClickListener;


        public userHolder(@NonNull View itemView, onUserClickListener onUserClickListener) {
            super(itemView);

            imageView = itemView.findViewById(R.id.userImg);
            tvName = itemView.findViewById(R.id.userName);

            this.onUserClickListener = onUserClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onUserClickListener.onUserClickListener(getAdapterPosition());

        }
    }


    public interface onUserClickListener{
        void onUserClickListener(int position);

    }


}
