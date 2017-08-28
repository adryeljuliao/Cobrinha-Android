package com.juliao.adryel.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

public class Config extends AppCompatActivity {

    private static final String PREFS_SAVE = "pres";
    RadioGroup grupoRadio;
    RadioGroup grupoDif;
    Config c = this;
    int dificult;
    int tam;
    boolean salva;
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
            tam = 30;
            salva = true;
//            dados.putInt("n", tam);
//            dados.putInt("dif", dificult);
//            i.putExtras(dados);
            save();
            startActivity(i);
            Log.i("VER", "Verificou o tabuleiro");

        }else if (R.id.idVinteCinco == grupoRadio.getCheckedRadioButtonId()){
            verificDif(v);
            tam = 25;
            salva = true;
            save();

//            dados.putInt("n", tam);
//            dados.putInt("dif", dificult);
//            i.putExtras(dados);
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



    public void save(){
        Log.i("SALVOU", "ENTOUR E SALVOU");
        SharedPreferences settings = getSharedPreferences(PREFS_SAVE, MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("dificuldade", dificult);
        editor.putInt("tamanho", tam);
        editor.putBoolean("salvo", salva);
//        editor.putBoolean("salvando",salva);
        editor.commit();
    }
}
