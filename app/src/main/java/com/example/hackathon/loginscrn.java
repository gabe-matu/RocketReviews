package com.example.hackathon;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class loginscrn extends AppCompatActivity {
    Button createAccount;
    Button login;

    HashMap<Integer, Integer> upass = new HashMap<Integer, Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginscrn);
        login = findViewById(R.id.button2);

        try {
            BufferedReader in = new BufferedReader(new FileReader("data.txt"));
            String uname = in.readLine();
            String pword = in.readLine();

            while (uname != null) {

                upass.put(Integer.parseInt(uname), Integer.parseInt(pword));

                uname = in.readLine();
                pword = in.readLine();
            }

            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = ((EditText) findViewById(R.id.editTextTextEmailAddress)).getText().toString();
                String password = ((EditText) findViewById(R.id.editTextTextPassword)).getText().toString();

                if (upass.containsKey(username.hashCode()) && upass.get(password.hashCode()) != null){
                    Intent intent = new Intent(loginscrn.this, ReviewsPage.class);
                    startActivity(intent);
                }

                else {

                    Intent intent = new Intent(loginscrn.this, ReviewsPage.class);
                    startActivity(intent);

                    AlertDialog.Builder err = new AlertDialog.Builder(loginscrn.this);
                    err.setTitle("Login Error")
                            .setMessage("Invalid credentials. Please try again.")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .show();
                }

            }
        });


        createAccount = findViewById(R.id.createAccountB);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginscrn.this, createAccount.class);
                startActivity(intent);
            }
        });
    }
}
