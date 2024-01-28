package com.example.hackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class createAccount extends AppCompatActivity {

    HashMap<Integer, Integer> upass = new HashMap<Integer, Integer>();
    Button complete;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        try {
            BufferedReader in = new BufferedReader(new FileReader("data.txt"));

            String uname = in.readLine().trim();
            String pword = in.readLine().trim();

            while (uname != null) {

                upass.put(Integer.parseInt(uname), Integer.parseInt(pword));

                uname = in.readLine().trim();
                pword = in.readLine().trim();
            }

            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        complete = (Button) findViewById(R.id.buttonC);
        complete.setOnClickListener(new View.OnClickListener() {
            private Object Log;

            @Override
            public void onClick(View v) {

                String username = ((EditText) findViewById(R.id.editTextTextEmailAddress3)).getText().toString();
                String password = ((EditText) findViewById(R.id.editTextNumberPassword)).getText().toString();



                if (upass.containsKey(username.hashCode()) && upass.get(password.hashCode()) != null) {

                    AlertDialog.Builder err = new AlertDialog.Builder(createAccount.this);
                    err.setTitle("Login Error")
                            .setMessage("Credentials already exist. Please try again.")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .show();

                } else {

                    try {
                        PrintWriter out = new PrintWriter(new FileWriter("data.txt", true));
                        out.println("\n" + username.hashCode() + "\n" + password.hashCode());
                        out.close();

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    Intent intent = new Intent(createAccount.this, loginscrn.class);
                    startActivity(intent);

                }

            }
        });

    }
 }