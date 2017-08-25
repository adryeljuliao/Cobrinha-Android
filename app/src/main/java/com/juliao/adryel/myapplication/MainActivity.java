package com.juliao.adryel.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

public class MainActivity extends AppCompatActivity {


    private static final String PREFS_NAME = "pres" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    public void iniciar(View v){
        Intent i = new Intent(this, Jogo.class);
        Bundle recuperaDados = getIntent().getExtras();
        if(recuperaDados == null){
            startActivity(i);
        }else{
            int n = recuperaDados.getInt("tam");
            i.putExtra("tamanho", n);
            startActivity(i);
        }





    }
    public void config(View v){
        Intent intent = new Intent(getApplication(), Config.class);
        startActivity(intent);

    }

}
