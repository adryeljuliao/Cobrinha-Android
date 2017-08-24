package com.juliao.adryel.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;

public class Jogo extends AppCompatActivity {
    int t = 30;
    GridLayout grid;
    ImageView[][] table;
    ArrayList<int []> snake = new ArrayList<>();
    int[] orientation = new int[2];
    int[] snakeTile = new int[2];
    int x;
    int y;
    int[] fruit = new int[2];
    int score;
    boolean running;
    ImageButton botTop;
    ImageButton botBot;
    ImageButton botLe;
    ImageButton botRi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);
        grid = (GridLayout) findViewById(R.id.idGrid);
        grid.setColumnCount(t);
        grid.setRowCount(t);
        table = new ImageView[t][t];
        botBot = (ImageButton) findViewById(R.id.idButtonBot);
        botTop = (ImageButton) findViewById(R.id.idButtonTop);
        botLe = (ImageButton) findViewById(R.id.idButtonLe);
        botRi = (ImageButton) findViewById(R.id.idButtonRi);

        Bundle args = getIntent().getExtras();
        int n = args.getInt("tam");
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){

                LayoutInflater inflar = LayoutInflater.from(this);
                ImageView imageSquares = (ImageView) inflar.inflate(R.layout.square, grid, false);
                table[i][j] = imageSquares;
                grid.addView(imageSquares);

            }

        }

    }

    public void click1(View v){
        botLe.setEnabled(false);
        botRi.setEnabled(false);
        botBot.setEnabled(true);
        botTop.setEnabled(true);
    }
    public void click2(View v){
        botLe.setEnabled(true);
        botRi.setEnabled(true);
        botBot.setEnabled(false);
        botTop.setEnabled(false);
    }
}
