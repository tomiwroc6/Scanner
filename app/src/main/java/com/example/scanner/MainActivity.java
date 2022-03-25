package com.example.scanner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    public static TextView text;

    public static TextView nazwa;

    public static MainActivity instance;
    public static String ServerIP;
    public static SharedPreferences sharedPreferences;

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);

        return true;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getPreferences(MODE_PRIVATE);

        instance = this;

        text = findViewById(R.id.textView);

        nazwa = findViewById(R.id.textView3);

        registerReceiver(new BroadcastReceiver()
        {
            @Override
            public void onReceive(Context context, Intent intent)
            {
        String code = intent.getExtras().getString("com.motorolasolutions.emdk.datawedge.data_string");

                // do something
                text.setText(code);
                setTitle("Pobieram...");
                new HttpGet().execute("http://sprawdzarka.astra?action=get&barcode=" + text.getText() );
            }
        }
        , new IntentFilter("my.barcode"));

        ServerIP = sharedPreferences.getString("ip","");
    }

    public void scan(android.view.View view) {
        Intent intent = new Intent();
        intent.setAction("com.motorolasolutions.emdk.datawedge.api.ACTION_SOFTSCANTRIGGER");
        intent.putExtra("com.motorolasolutions.emdk.datawedge.api.EXTRA_PARAMETER", "TOGGLE_SCANNING");
        sendBroadcast(intent);
    }


    public void check(android.view.View view)
    {
        setTitle("Pobieram...");
        new HttpGet().execute("http://sprawdzarka.astra?action=get&barcode=" + text.getText() );
    }
}