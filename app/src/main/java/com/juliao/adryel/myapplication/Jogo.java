package com.juliao.adryel.myapplication;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class Jogo extends AppCompatActivity {
    MediaPlayer mp;
    int n;
    GridLayout grid;
    ImageView[][] table;
    ArrayList<int []> snake = new ArrayList<>();
    int[] orientation = new int[2];
    int[] position = new int[2];
    int x;
    int y;
    int[] fruit = new int[2];
    int score;
    boolean running;
    ImageButton botTop;
    ImageButton botBot;
    ImageButton botLe;
    ImageButton botRi;
    ImageView headSnake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);

        grid = (GridLayout) findViewById(R.id.idGrid);

        botBot = (ImageButton) findViewById(R.id.idButtonBot);
        botTop = (ImageButton) findViewById(R.id.idButtonTop);
        botLe = (ImageButton) findViewById(R.id.idButtonLe);
        botRi = (ImageButton) findViewById(R.id.idButtonRi);

        Bundle recuperaDados = getIntent().getExtras();
        if(recuperaDados == null){
            Log.i("ENTRA", "entrou no if");
            n = 30;
            grid.setColumnCount(n);
            grid.setRowCount(n);
            table = new ImageView[n][n];
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){



                    LayoutInflater inflar = LayoutInflater.from(this);
                    ImageView imageSquares = (ImageView) inflar.inflate(R.layout.square, grid, false);
                    table[i][j] = imageSquares;
                    grid.addView(imageSquares);

                }
            }

        }else{
            Log.i("ENTRA", "entrou no ELSE");
            int n = recuperaDados.getInt("tamanho");
            grid.setColumnCount(n);
            grid.setRowCount(n);
            table = new ImageView[n][n];
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){

                    LayoutInflater inflar = LayoutInflater.from(this);
                    ImageView imageSquares = (ImageView) inflar.inflate(R.layout.square, grid, false);
                    table[i][j] = imageSquares;
                    grid.addView(imageSquares);

                }
            }
        }
        mp = MediaPlayer.create(this, R.raw.spi);
        mp.setLooping(true);
        mp.start();



    createSnake();

    }

    public void squareRed(ImageView v){
        v.setImageResource(R.drawable.red_square);
    }

    public void createSnake(){
        squareRed(table[n/2][n/2]);
        orientation[0] = 0;
        orientation[1] = 1;
        int position[] = new int[2];
        position[0] = n/2;
        position[1] = n/2;
        snake.add(position);
        move(orientation);
    }
    public void head(int[] orient){
        position = snake.get(0);
        position[0] += orient[0];
        position[1] += orient[1];
        squareRed(table[position[0]][position[1]]);
        snake.set(0, position);
    }

    public void move(final int[] orient){
        final android.os.Handler handler = new android.os.Handler();



        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < snake.size(); i++) {
                            int [] position = snake.get(i);
                            squareRed(table[position[0]][position[1]]);
                        }
                       // head(orient);
                        move(orient);
                    }
                }, 200);
            }
        }).start();

    }



    public void clickLeft(View v){
        botRi.setEnabled(false);
        orientation[0] = 0;
        orientation[1] = -1;
        /*if(botBot.isPressed()){

        }*/
    }

    public void clickRigh(View v){
        botLe.setEnabled(false);
        orientation[0] = 0;
        orientation[1] = 1;
    }

    public void clickTop(View v){
        botBot.setEnabled(false);
        orientation[0] = -1;
        orientation[1] = 0;
    }
    public void clickBottom(View v){
        botTop.setEnabled(false);
        orientation[0] = 1;
        orientation[1] = 0;
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mp.stop();

    }
}
