package com.juliao.adryel.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {

    TextView record;

    Context c = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        record = (TextView) findViewById(R.id.idRecord);
        String score = getIntent().getStringExtra("score");
        record.setText(score);

    }


    public void back(View v){
        Intent intent = new Intent(c, MainActivity.class);
        startActivity(intent);
    }
    public void newGame(View v){
        Intent intent = new Intent(c, Jogo.class);
        startActivity(intent);
    }
}
