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

public class Jogo extends AppCompatActivity {
    MediaPlayer mp;//musica do app
    int tam;//tamanho do tabuleiro
    int dificult = 200;//nivel de dificuldade
    GridLayout grid;//tabuleiro grid para inflar
    ImageView[][] table;//tabuleiro auxiliar
    ArrayList<int []> snake = new ArrayList<>();//cobra, arrayList de vetor
    int[] orientation = new int[2];//diretção da cobra
    int[] position = new int[2];//posição
//    int x;
//    int y;
//    int[] fruit = new int[2];
//    int score;
    boolean running = true;//variavel axuliar para pausar ou despausar o jogo
    ImageButton botTop;//botao para cima
    ImageButton botBot;//botao para baixo
    ImageButton botLe;//botao para esquerda
    ImageButton botRi;//botao para direita
//    ImageView headSnake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);

        grid = (GridLayout) findViewById(R.id.idGrid);//pegando o grid pelo id
        //pegando os botoes
        botBot = (ImageButton) findViewById(R.id.idButtonBot);
        botTop = (ImageButton) findViewById(R.id.idButtonTop);
        botLe = (ImageButton) findViewById(R.id.idButtonLe);
        botRi = (ImageButton) findViewById(R.id.idButtonRi);

        Bundle recuperaDados = getIntent().getExtras();//recuperando os dados do bandle da activity inicial
        if(recuperaDados == null){//verifica se o bundle é vazio, se for inicia com um valor padrão e dificuldade padrao

            Log.i("ENTRA", "entrou no if");
            tam = 30;
            grid.setColumnCount(tam);
            grid.setRowCount(tam);
            table = new ImageView[tam][tam];
            //for para inflar um layout, ou seja, o gridLayout
            for(int i = 0; i < tam; i++){
                for(int j = 0; j < tam; j++){

                    LayoutInflater inflar = LayoutInflater.from(this);//obj que serve para inflar, pegando como parametro o contexto
                    ImageView imageSquares = (ImageView) inflar.inflate(R.layout.square, grid, false);//infla o layout
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

    public void squareBlack(ImageView v){
        v.setImageResource(R.drawable.square_black);
    }
    public void createSnake(){
        squareRed(table[tam /2][tam /2]);
        orientation[0] = 0;
        orientation[1] = 1;
        int position[] = new int[2];
        position[0] = tam /2;
        position[1] = tam /2;
        snake.add(position);
        move(orientation);
    }
    public void head(){
        position = snake.get(0);
        position[0] += orientation[0];
        position[1] += orientation[1];


        snake.get(0)[0] = position[0];
        snake.get(0)[1] = position[1];
    }

    public void move(final int[] orient){
        final android.os.Handler handler = new android.os.Handler();



        new Thread(new Runnable() {
            @Override
            public void run() {
                while(running){
                    try {
                        Thread.sleep(dificult);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < snake.size(); i++) {
                            int [] position = snake.get(i);

                            squareBlack(table[position[0]][position[1]]);


                        }
                        head();
                        for (int i = 0; i < snake.size(); i++){
                            int [] position = snake.get(i);
                            squareRed(table[position[0]][position[1]]);
                        }

                    }
                });
                }
            }
        }).start();

    }



    public void clickLeft(View v){
        botRi.setEnabled(false);
        botBot.setEnabled(true);
        botTop.setEnabled(true);

        orientation[0] = 0;
        orientation[1] = -1;
        /*if(botBot.isPressed()){

        }*/
    }

    public void clickRigh(View v){
        botLe.setEnabled(false);
        botTop.setEnabled(true);
        botBot.setEnabled(true);
        orientation[0] = 0;
        orientation[1] = 1;
    }

    public void clickTop(View v){
        botBot.setEnabled(false);
        botRi.setEnabled(true);
        botLe.setEnabled(true);
        orientation[0] = -1;
        orientation[1] = 0;
    }
    public void clickBottom(View v){
        botTop.setEnabled(false);
        botLe.setEnabled(true);
        botRi.setEnabled(true);
        orientation[0] = 1;
        orientation[1] = 0;
    }
    public void stop(View v){
       running = false;
    }
    public void continued(View v){
        running = true;
        move(orientation);
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
