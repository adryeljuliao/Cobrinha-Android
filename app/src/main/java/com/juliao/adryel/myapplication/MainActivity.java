package com.juliao.adryel.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

public class MainActivity extends AppCompatActivity {

    Button botContinue;
    int n;
    int d;
    boolean s;
    private static final String PREFS_SAVE = "pres" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences settings = getSharedPreferences(PREFS_SAVE, MODE_PRIVATE );

        s = settings.getBoolean("salvo", false);
        n = settings.getInt("tamanho", 0);
        d = settings.getInt("dificuldade", 0);

        Log.i("CRIAA", ""+ s);
        Log.i("CRIAA", ""+ d);
        botContinue = (Button) findViewById(R.id.idContinua);
        botContinue.setVisibility(View.GONE);

    }


    public void iniciar(View v){
        Intent i = new Intent(this, Jogo.class);
//        Bundle b = new Bundle();
//        if(s){
            startActivity(i);
//        }else{

//            b.putInt("tamanho", n);
//            b.putInt("dificuldade", d);
//            i.putExtras(b);
//            startActivity(i);
//        }





    }
    public void exit(View v){
        finish();
    }
    public void continuar(){
        Intent i = new Intent(this, Jogo.class);
        startActivity(i);
    }
    public void config(View v){
        Intent intent = new Intent(getApplication(), Config.class);
        startActivity(intent);

    }

    @Override
    protected void onStop() {
        super.onStop();
        botContinue.setVisibility(View.VISIBLE);
    }
}
