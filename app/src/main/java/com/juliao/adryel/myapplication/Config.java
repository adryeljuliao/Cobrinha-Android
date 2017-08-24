package com.juliao.adryel.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

public class Config extends AppCompatActivity {

    RadioGroup grupoRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        grupoRadio = (RadioGroup) findViewById(R.id.idGroup);


    }

    public void clik(View v){
        if (R.id.idCinquenta == grupoRadio.getCheckedRadioButtonId()){
            Intent i = new Intent(getApplicationContext(), Jogo.class);
            Bundle dados = new Bundle();
            dados.putInt("tam", 30);
            i.putExtras(dados);
            startActivity(i);
            finish();
        }else if (R.id.idVinteCinco == grupoRadio.getCheckedRadioButtonId()){
            Intent i = new Intent(getApplicationContext(), Jogo.class);
            Bundle dados = new Bundle();
            dados.putInt("tam", 25);
            i.putExtras(dados);
            startActivity(i);
            finish();
        }
    }
}
