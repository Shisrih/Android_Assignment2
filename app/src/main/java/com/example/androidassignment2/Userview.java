package com.example.androidassignment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.androidassignment2.Model.User;

import java.util.ArrayList;
import java.util.List;

public class Userview extends AppCompatActivity implements userRvAdapter.onUserClickListener {
    RecyclerView recyclerView;
    List<User> users = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userview);

        recyclerView = findViewById(R.id.recylerusers);


        Intent intent = getIntent();
        final List<User> userList = (List<User>) intent.getSerializableExtra("allusers");
        users.addAll(userList);
        String[] userNames = new String[userList.size()];

        int userSize = userList.size();
        for(int i = 0; i<userSize; i++ ){
            Log.d("user", String.valueOf(userList.get(i)));
        }


        int i = 0;
        for(User user:userList){
            userNames[i] = user.getName();
            i++;
        }


        userRvAdapter adapter = new userRvAdapter(userList,this,this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onUserClickListener(int position) {
        Intent intent = new Intent(this, Userinfo.class);
        intent.putExtra("Name", users.get(position).getName());
        intent.putExtra("Gender", users.get(position).getGender());
        intent.putExtra("DoB", users.get(position).getDob());
        intent.putExtra("Country", users.get(position).getCountry());
        intent.putExtra("Phone", users.get(position).getPhone());
        intent.putExtra("Email", users.get(position).getEmail());
        intent.putExtra("image", users.get(position).getImg());
        startActivity(intent);

    }
}
