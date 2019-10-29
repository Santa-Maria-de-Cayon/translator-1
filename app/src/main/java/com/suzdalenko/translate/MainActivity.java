package com.suzdalenko.translate;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;


public class MainActivity extends AppCompatActivity {
    int small = 1;
    int grand = 1;
    int real  = 0;
    EditText input, out;
    Button clear, translate;
    RadioButton english, spain, russian, doch, it, por, fr;
    String inputText, outText;
    String language = "en";
    ScrollView sv;
    private AdView mAdView, mAdView2;
    LinearLayout linAds;
    RelativeLayout.LayoutParams layoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView2 = findViewById(R.id.adView2);
        mAdView2.loadAd(adRequest);
        linAds = findViewById(R.id.linAds);


        sv = findViewById(R.id.sv);
        layoutParams = (RelativeLayout.LayoutParams) sv.getLayoutParams();

        input     = findViewById(R.id.input);
        out       = findViewById(R.id.out);
        clear     = findViewById(R.id.clear);
        translate = findViewById(R.id.translate);
        english   = findViewById(R.id.english);
        spain     = findViewById(R.id.spain);
        russian   = findViewById(R.id.rus);

        doch      =  findViewById(R.id.doch);
        it        =  findViewById(R.id.it);
        por       =  findViewById(R.id.por);
        fr        =  findViewById(R.id.fr);



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
                language = "en"; checkFalse();
                english.setChecked(true);
                 play();
            }
        });

        spain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                language = "sp"; checkFalse();
                spain.setChecked(true);
                 play();
            }
        });

        russian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                language = "ru"; checkFalse();
                russian.setChecked(true);  play();
            }
        });

        fr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                language = "fr"; checkFalse();
                fr.setChecked(true);  play();
            }
        });

        doch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                language = "de"; checkFalse();
                doch.setChecked(true);  play();
            }
        });

        it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 language = "it"; checkFalse();
                 it.setChecked(true);  play();
            }
        });

        por.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                language = "pt"; checkFalse();
                por.setChecked(true);  play();
            }
        });


        Log.d("tag", "start start start start start start start start start start ");
        final RelativeLayout rootView =  findViewById(R.id.rootView);
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout( ) {
                if( small == 1 )       { small = rootView.getRootView().getHeight() - rootView.getHeight(); }
                if( grand <=  small )  { grand = rootView.getRootView().getHeight() - rootView.getHeight(); }
                real = grand = rootView.getRootView().getHeight() - rootView.getHeight();

               if(real > small) {
                   linAds.setVisibility(View.GONE);
                   layoutParams.setMargins(0, 0, 0, 0);
                   sv.setLayoutParams(layoutParams);
               }
               else             {
                   linAds.setVisibility(View.VISIBLE);
                   layoutParams.setMargins(0, 0, 0, 400);
                   sv.setLayoutParams(layoutParams);
               }
            }
        });



    }

    public void play(){
        inputText = input.getText().toString();
        if(inputText.equals("")) inputText = "hello,  write text";
        final ReadContentUrl rcu = new ReadContentUrl(this);
        rcu.Read(inputText, language);
    }

    public void checkFalse(){
        spain.setChecked(false);
        english.setChecked(false);
        doch.setChecked(false);
        por.setChecked(false);
        it.setChecked(false);
        fr.setChecked(false);
        russian.setChecked(false);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Visit me");
        menu.add("Add you comment");
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        String title = item.getTitle().toString();
        if(title.equalsIgnoreCase("Visit me"))
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://suzdalenko.com/")));
        else startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.suzdalenko.translate")));
        return super.onOptionsItemSelected(item);
    }






}

