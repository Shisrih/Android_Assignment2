package com.example.androidassignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Userinfo extends AppCompatActivity {
    TextView name, dob, gender, country, phone, email;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);

        name = findViewById(R.id.name);
        dob = findViewById(R.id.dob);
        gender = findViewById(R.id.gender);
        country = findViewById(R.id.country);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        image = findViewById(R.id.profileimagee);

        Bundle bundle = getIntent().getExtras();
        name.setText(bundle.getString("Name"));
        dob.setText(bundle.getString("DoB"));
        gender.setText(bundle.getString("Gender"));
        country.setText(bundle.getString("Country"));
        phone.setText(bundle.getString("Phone"));
        email.setText(bundle.getString("Email"));
        image.setImageResource(bundle.getInt("image"));
    }
}
