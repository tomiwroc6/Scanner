package com.example.scanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        setTitle("Settings");

        TextView text = findViewById(R.id.editIP);

        text.setText(MainActivity.ServerIP);
        text.setSelected(true);
    }

    public void closeSettings(android.view.View view)
    {
        TextView text = findViewById(R.id.editIP);

        MainActivity.ServerIP = text.getText().toString();
        MainActivity.sharedPreferences.edit().putString("ip",MainActivity.ServerIP).commit();

        this.finish();
        //startActivity(new Intent(MainActivity2.this, MainActivity.class));
    }

}