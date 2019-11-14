package com.example.androidassignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.androidassignment2.Model.User;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,RadioGroup.OnCheckedChangeListener {
EditText name,dob,phone,email,img;
RadioGroup rg;
RadioButton male,female,other;
Spinner spinner_country;
Button submit, viewuser;
    String Name, DOB, Phn, Email, country, gender, Img;
    String[] countries = {"-CHOOSE ONE-", "Nepal", "India", "Srilanka", "Jap" +
            "an", "Korea", "Bhutan", "Pakistan", "Afganistan"};

    final Calendar mycalendar = Calendar.getInstance();
    List<User> userList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.Name);
        dob = findViewById(R.id.DoB);
        phone = findViewById(R.id.Phone);
        email = findViewById(R.id.Email);
        male = findViewById(R.id.RBMale);
        female = findViewById(R.id.RBFemale);
        other = findViewById(R.id.RBOther);
        spinner_country = findViewById(R.id.Spinner);
        img = findViewById(R.id.img);
        submit = findViewById(R.id.btnSubmit);
        viewuser = findViewById(R.id.btnView);
        rg = findViewById(R.id.radiogroup);


        ArrayAdapter adapter =new ArrayAdapter(this,R.layout.spinnervalues, countries);
        spinner_country.setAdapter(adapter);

        rg.setOnCheckedChangeListener(this);
        setSpinnerValues();
        submit.setOnClickListener(this);
        dob.setOnClickListener(this);
        viewuser.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Name = name.getText().toString();
        DOB = dob.getText().toString();
        Phn = phone.getText().toString();
        Email = email.getText().toString();
        Img = img.getText().toString();

        String image ="@drawable/"+ Img;

        int resID =getResources().getIdentifier(image,null,getPackageName());


        if (view.getId() == R.id.btnSubmit){
            if(validate()){
                userList.add(new User(Name,gender,DOB, country, Phn, Email, resID));
                Toast.makeText(this, "All selected", Toast.LENGTH_SHORT).show();


            }
        }

        if (view.getId() == R.id.DoB){
            new DatePickerDialog(MainActivity.this,date,mycalendar
                    .get(Calendar.YEAR),mycalendar.get(Calendar.MONTH),mycalendar.get(Calendar.DAY_OF_MONTH))
                    .show();
        }

        if (view.getId() == R.id.btnView){
            Intent intent = new Intent(this,Userview.class);
            intent.putExtra("allusers", (Serializable) userList);
            startActivity(intent);

        }




    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (i == R.id.RBMale){
            gender= "Male";
        }
        if (i == R.id.RBFemale){
            gender= "Female";
        }
        if (i == R.id.RBOther){
            gender= "Other";
        }

    }


    private void setSpinnerValues(){
        spinner_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
           if (i>0){
               country = parent.getSelectedItem().toString();
           }else {
               Toast.makeText(MainActivity.this, "Select a country", Toast.LENGTH_SHORT).show();
           }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(MainActivity.this, "Nothing Selected Here", Toast.LENGTH_SHORT).show();

            }
        });

    }



    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            mycalendar.set(Calendar.YEAR, i);
            mycalendar.set(Calendar.MONTH, i1);
            mycalendar.set(Calendar.DAY_OF_MONTH, i2);
            updateLabel();


        }
    };


    private void updateLabel(){
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dob.setText(sdf.format(mycalendar.getTime()));

    }


    private boolean validate(){
        if(TextUtils.isEmpty(Name)){
            name.setError("Enter Name");
            name.requestFocus();
            return false;
        }
        if(TextUtils.isEmpty(DOB)){
            dob.setError("Enter dob");
            dob.requestFocus();
            return false;
        }
        if(TextUtils.isEmpty(gender)){
            Toast.makeText(this, "Please select a gender", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (country.equals("-CHOOSE ONE-")) {
            Toast.makeText(this, "Select Country", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(country)) {
            Toast.makeText(this, "Select Country", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(Phn)){
            phone.setError("Enter Number");
            phone.requestFocus();
            return false;
        }
        if(!TextUtils.isDigitsOnly(Phn)){
            phone.setError("Enter Valid number");
            phone.requestFocus();
            return false;
        }
        if(TextUtils.isEmpty(Email)){
            email.setError("Enter Email");
            email.requestFocus();
            return false;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            email.setError("Invalid Email");
            email.requestFocus();
            return false;
        }
        if(TextUtils.isEmpty(Img)){
            email.setError("Provide Image");
            email.requestFocus();
            return false;
        }
        return true;

    }






}
