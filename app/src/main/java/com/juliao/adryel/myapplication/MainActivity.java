package com.juliao.adryel.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void iniciar(View v){
        Intent intent = new Intent(getApplication(), Jogo.class);
        startActivity(intent);

    }
    public void config(View v){
        Intent intent = new Intent(getApplication(), Config.class);
        startActivity(intent);

    }

}
