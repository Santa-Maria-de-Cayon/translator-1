package com.suzdalenko.translate;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {
    EditText input, out;
    Button clear, translate;
    RadioButton english, spain, russian;
    String inputText, outText;
    String language = "en";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input     = findViewById(R.id.input);
        out       = findViewById(R.id.out);
        clear     = findViewById(R.id.clear);
        translate = findViewById(R.id.translate);
        english   = findViewById(R.id.english);
        spain     = findViewById(R.id.spain);
        russian   = findViewById(R.id.rus);

       // final ReadContentUrl rcu = new ReadContentUrl(this);

        translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText("");
                out.setText("");
                Log.d("tag", "Press clear button");
            }
        });

        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                english.setChecked(true);  language = "en";
                spain.setChecked(false);
                russian.setChecked(false); play();
            }
        });

        spain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                english.setChecked(false); language = "sp";
                spain.setChecked(true);
                russian.setChecked(false); play();
            }
        });

        russian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                english.setChecked(false); language = "ru";
                spain.setChecked(false);
                russian.setChecked(true);  play();
            }
        });
    }

    public void play(){
        inputText = input.getText().toString();
        if(inputText.equals("")) inputText = "hello,  write text";
        final ReadContentUrl rcu = new ReadContentUrl(this);
        rcu.Read(inputText, language);
    }
}

