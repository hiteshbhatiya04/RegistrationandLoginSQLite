package com.example.hitesh.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signin extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;
    EditText textemail,textpassword;
    Button signin;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        databaseHelper = new DatabaseHelper(this);
        sqLiteDatabase = databaseHelper.getReadableDatabase();
        sqLiteDatabase = databaseHelper.getWritableDatabase();

        textemail = findViewById(R.id.textemail);
        textpassword = findViewById(R.id.textpassword);
        signin = findViewById(R.id.btnsignin);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = textemail.getText().toString();
                String password = textpassword.getText().toString();
                cursor = sqLiteDatabase.rawQuery("SELECT *FROM " + databaseHelper.TABLE_NAME + " WHERE " + databaseHelper.COL_4 + "=? AND " + databaseHelper.COL_5 + "=?", new String[]{email,password} );
                if (cursor!=null){
                    if (cursor.getCount() > 0){
                        //cursor.moveToFirst();
                        Toast.makeText(Signin.this, " Login Successful", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(Signin.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
    }
}
