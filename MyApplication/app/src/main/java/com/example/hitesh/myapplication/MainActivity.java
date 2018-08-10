package com.example.hitesh.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.hitesh.myapplication.DatabaseHelper.COL_2;
import static com.example.hitesh.myapplication.DatabaseHelper.COL_3;
import static com.example.hitesh.myapplication.DatabaseHelper.COL_4;
import static com.example.hitesh.myapplication.DatabaseHelper.COL_5;
import static com.example.hitesh.myapplication.DatabaseHelper.COL_6;
import static com.example.hitesh.myapplication.DatabaseHelper.TABLE_NAME;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper mydb;
    EditText txtname,txtsurname,txtemail,txtmobile,txtpassword;
    Button register,signin;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DatabaseHelper(this);
        db = mydb.getReadableDatabase();

        txtname = findViewById(R.id.name);
        txtsurname = findViewById(R.id.surname);
        txtemail = findViewById(R.id.email);
        txtmobile = findViewById(R.id.mobile);
        txtpassword = findViewById(R.id.password);
        register = findViewById(R.id.btnreg);
        signin = findViewById(R.id.btnsignin);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Signin.class);
                startActivity(i);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                db = mydb.getWritableDatabase();


                String name = txtname.getText().toString();
                String surname = txtsurname.getText().toString();
                String email = txtemail.getText().toString();
                String password = txtpassword.getText().toString();
                String mobile = txtmobile.getText().toString();

                if (name.trim().isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Please Enter Name", Toast.LENGTH_SHORT).show();
                }
                else if (surname.trim().isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Please Enter Surname", Toast.LENGTH_SHORT).show();
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    Toast.makeText(MainActivity.this, "Please Enter Valid Email", Toast.LENGTH_SHORT).show();
                }
                else if (password.trim().isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                }
                else if (mobile.trim().isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Please Enter Mobile Number", Toast.LENGTH_SHORT).show();
                }
                else{
                insertData(name,surname,email,password,mobile);
                Toast.makeText(MainActivity.this, "Register Successfully", Toast.LENGTH_SHORT).show();
            }
            }
        });
    }

    public boolean insertData(String name,String surname,String email,String password ,String mobile){

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,email);
        contentValues.put(COL_5,password);
        contentValues.put(COL_6,mobile);
        long result = db.insert(TABLE_NAME,null , contentValues);
        if (result == -1)
            return false;
        else
            return true;


    }
}
