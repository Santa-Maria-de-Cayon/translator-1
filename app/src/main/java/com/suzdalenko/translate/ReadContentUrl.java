package com.suzdalenko.translate;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.EditText;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;


public class ReadContentUrl {
    private Context context;
    private String[] cats = {"trnsl.1.1.20191019T204406Z.c05a3880a7cd6f80.e76cd5e85a3bf462358c6d6d2cd2c25e8d8a4f8d", "trnsl.1.1.20191019T205552Z.e178742efd72ba33.1d7e5d9ba227c594bf3d6cdd60c75e9f52863a51", "trnsl.1.1.20191019T205552Z.e178742efd72ba33.1d7e5d9ba227c594bf3d6cdd60c75e9f52863a51", "trnsl.1.1.20191019T204705Z.e1776157e1c6e1ee.7433232b55598dc1c8f3afb7e5773b7403641a9b", "trnsl.1.1.20191019T205552Z.c562ff592ef53441.ec972d4bf4ef0b6901d9ca6c2e2d3b69419e42d1", "trnsl.1.1.20191019T205647Z.cb74e7f9b4cc20d8.1033302449f95336f5386e32e2f6043c3620fb62", "trnsl.1.1.20191019T205723Z.6ab5d58682bde824.7b25f5b6286bbb1ee957978559acc92cfa5065b1", "trnsl.1.1.20191019T205737Z.25d642ecac27bda9.19fa1b4d38cbfe690b3c89e084165deb31e55d93", "trnsl.1.1.20191019T205752Z.8f51298f40219c78.5ed7c352094b30e3214b6224988f14fa2762a9b7"};



    public ReadContentUrl(Context context) {
        this.context=context;
    }

    public String Read(final String text, final String language) {
        final String[] result = {""};

        @SuppressLint("HandlerLeak") final Handler h  = new Handler(){
            public void handleMessage(android.os.Message msg) {
                if (msg.what == 10) {


                    try {
                        JSONObject js = new JSONObject(result[0]);
                        JSONArray jsAr = js.getJSONArray("text");
                        String result = (String) jsAr.get(0);

                        EditText EdView =  ((Activity)context).findViewById(R.id.out);
                        EdView.setText( result );

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("tag", "Error to JSONObject or JSONArray");
                    }
                }
            }
        };


        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String lang = "en";
                    if(language.equalsIgnoreCase("en")) lang = "en";
                    if(language.equalsIgnoreCase("sp")) lang = "es";
                    if(language.equalsIgnoreCase("ru")) lang = "ru";

                    if(language.equalsIgnoreCase("pt")) lang = "pt";
                    if(language.equalsIgnoreCase("it")) lang = "it";
                    if(language.equalsIgnoreCase("de")) lang = "de";
                    if(language.equalsIgnoreCase("fr")) lang = "fr";

                    Random r = new Random();
                    int placeKey = r.nextInt(9);
                    Log.d("tag", "=======> " + placeKey);
                    String key = cats[placeKey];

                    String uri = "https://translate.yandex.net/api/v1.5/tr.json/translate?key="+key+"&text="+text+"&lang="+lang;
                    URL test = new URL(uri);
                    URLConnection uc = test.openConnection();
                    uc.addRequestProperty("User-Agent", "Mozilla/4.0");
                    BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
                    String inputLine;
                    StringBuilder sb = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        sb.append(inputLine);
                    }
                    result[0] = sb.toString();
                    in.close();
                    h.sendEmptyMessage(10);
                    } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("tag", "Error read url");
                }





            }
        });
        t.start();

        return result[0];

        }



}












// https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20191019T204705Z.e1776157e1c6e1ee.7433232b55598dc1c8f3afb7e5773b7403641a9b&text=hello&lang=ru
