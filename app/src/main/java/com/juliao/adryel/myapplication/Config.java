package com.juliao.adryel.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

public class Config extends AppCompatActivity {

    RadioGroup grupoRadio;
    RadioGroup grupoDif;
    Config c = this;
    int dificult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        grupoRadio = (RadioGroup) findViewById(R.id.idGroup);
        grupoDif = (RadioGroup) findViewById(R.id.idGroupDificult);

    }

   public void clikConfirm(View v){

        Intent i = new Intent(c, MainActivity.class);
        Bundle dados = new Bundle();

        if (R.id.idCinquenta == grupoRadio.getCheckedRadioButtonId()){
            verificDif(v);
            dados.putInt("n", 30);
            dados.putInt("dif", dificult);
            i.putExtras(dados);
            startActivity(i);
            Log.i("VER", "Verificou o tabuleiro");

        }else if (R.id.idVinteCinco == grupoRadio.getCheckedRadioButtonId()){
            verificDif(v);
            dados.putInt("n", 25);
            dados.putInt("dif", dificult);
            i.putExtras(dados);
            startActivity(i);
            Log.i("VER", "Verificou o tabuleiro");

        }


    }

    public void verificDif(View v){
        if(R.id.idHard == grupoDif.getCheckedRadioButtonId()){
           dificult = 100;
            Log.i("TENTOU", "verificou a dificultade");
        }else if(R.id.idMed == grupoDif.getCheckedRadioButtonId()){
            dificult = 250;

            Log.i("TENTOU", "verificou a dificultade");
        }else if(R.id.idIzi == grupoDif.getCheckedRadioButtonId()){
            dificult = 300;
            Log.i("TENTOU", "verificou a dificultade");
        }
    }
}
