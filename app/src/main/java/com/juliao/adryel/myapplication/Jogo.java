package com.juliao.adryel.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

public class Jogo extends AppCompatActivity {
    Context c =this;
    private static final String PREF_SNAKE = "prefs";
    private static final String PREFS_SAVE = "pres";
    TextView score;
    int result;
    MediaPlayer mp;//musica do app
    int n;//tamanho do tabuleiro
    int dificult;//nivel de dificuldade
    boolean s;
    GridLayout grid;//tabuleiro grid para inflar
    ImageView[][] table;//tabuleiro auxiliar
    ArrayList<int []> snake = new ArrayList<>();//cobra, arrayList de vetor
    int[] orientation = new int[2];//diretção da cobra
    int[] position = new int[2];//posição
    int x;
    int y;
    int[] fruit = new int[2];
    int[] new_snake = new int[2];
//    int score;
    boolean running = true;//variavel axuliar para pausar ou despausar o jogo
    ImageButton botTop;//botao para cima
    ImageButton botBot;//botao para baixo
    ImageButton botLe;//botao para esquerda
    ImageButton botRi;//botao para direita
    ImageButton botPause;
    ImageButton botPlay;
//    ImageView headSnake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);

        score = (TextView) findViewById(R.id.idPontos);
        SharedPreferences settings = getSharedPreferences(PREFS_SAVE, MODE_PRIVATE );
//        SharedPreferences shared = getSharedPreferences(PREF_SNAKE, 0);
//        String stringSnake = shared.getString("tok", "");
//        String[] p = stringSnake.split("-");
//        Log.i("KKKK", ""+p.length + "   " + stringSnake);
//        for(int i = 0; i < p.length; i++){
//
//          String[] po_po = p[i].split(",");
//
//            new_snake[0] = Integer.parseInt(po_po[0]);
//            new_snake[1] = Integer.parseInt(po_po[1]);
//            snake.add(new_snake);
//
//        }






//        String teste = shared.getString("tamanho", "");
//        String[] str = teste.split(",");
//        List<String> item = new ArrayList<>();
//        for(int i = 0; i < str.length; i++){
//            item.add(str[i]);
//        }
//
//        for(int i = 0; i < item.size(); i++){
//            Log.i("AGORA", item.get(i));
//        }


        s = settings.getBoolean("salvo", false);
        n = settings.getInt("tamanho", 0);
        dificult = settings.getInt("dificuldade", 0);

        grid = (GridLayout) findViewById(R.id.idGrid);//pegando o grid pelo id
        //pegando os botoes
        botBot = (ImageButton) findViewById(R.id.idButtonBot);
        botTop = (ImageButton) findViewById(R.id.idButtonTop);
        botLe = (ImageButton) findViewById(R.id.idButtonLe);
        botRi = (ImageButton) findViewById(R.id.idButtonRi);
        botPause = (ImageButton) findViewById(R.id.idBotPause);
        botPlay = (ImageButton) findViewById(R.id.idBotPlay);


        //Bundle recuperaDados = getIntent().getExtras();//recuperando os dados do bandle da activity inicial
        if(!s){//verifica se o bundle é vazio, se for inicia com um valor padrão e dificuldade padrao

            Log.i("ENTRA", "entrou no if");
            n = 30;
            dificult = 300;
            grid.setColumnCount(n);
            grid.setRowCount(n);
            table = new ImageView[n][n];
            //for para inflar um layout, ou seja, o gridLayout
            inflatGrid(n);
            botPlay.setVisibility(View.INVISIBLE);

        }else{

//            Log.i("ENTRA", "entrou no ELSE");
//            n = recuperaDados.getInt("tamanho");
//            dificult = recuperaDados.getInt("dificuldade");
            grid.setColumnCount(n);
            grid.setRowCount(n);
            table = new ImageView[n][n];
            inflatGrid(n);
            botPlay.setVisibility(View.INVISIBLE);

        }
        //coloca a musica para tocar
        mp = MediaPlayer.create(this, R.raw.spi);
        mp.setLooping(true);
        mp.start();

        snake.add(new int[] {n/2,n/2});
        snake.add(new int[] {n/2,n/2});

  createSnake();

    }

    public void inflatGrid(int n){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){

                LayoutInflater inflar = LayoutInflater.from(this);
                ImageView imageSquares = (ImageView) inflar.inflate(R.layout.square, grid, false);
                table[i][j] = imageSquares;
                grid.addView(imageSquares);


            }
        }
    }


//metodo para setar um quadradinho vermelho
    public void squareRed(ImageView v){

        v.setImageResource(R.drawable.red_square);

    }
    //metodo para setar um quadradinho preto
    public void squareBlack(ImageView v){

        v.setImageResource(R.drawable.square_black);

    }
    public void squareWhite(ImageView v){

        v.setImageResource(R.drawable.quadrado);

    }
    //metodo que checa a posição da cabeça
     private void checkPosition(int x, int y) {
         if(x >= n){
             snake.get(0)[0] = 0;
         }
         if(y >= n){
             snake.get(0)[1] = 0;
         }
         if(x == -1){
             snake.get(0)[0] = n-1;
         }
         if(y == -1){
             snake.get(0)[1] = n-1;
         }
     }
    //cabeça da cobra
    public void head(){

        x = snake.get(0)[0];
        y = snake.get(0)[1];
        x += orientation[0];
        y += orientation[1];

        snake.get(0)[0] = x;
        snake.get(0)[1] = y;

        for (int i = 1; i < snake.size(); i++){

            if(snake.get(0)[0] == snake.get(i)[0] && snake.get(0)[1] == snake.get(i)[1]){
                Log.i("BATEB", "BATEU AQUI EM");
                running = false;
                Intent intent = new Intent(c, GameOver.class);
                intent.putExtra("score", ""+result);
                startActivity(intent);
                finish();

            }

        }

        checkPosition(x, y);

        eating();

    }

    public void eating(){
        if(x == fruit[0] && y == fruit[1]) {
            createFruit();
            result += 15;


            score.setText(result+"");

            snake.add(new int[] {x,y});
            Log.i("CRESC", "CRESCEU");
        }
    }
    //cria a cobra
    public void createSnake(){

        squareRed(table[n/2][n/2]);
        //cria uma orientação para direita
        orientation[0] = 0;
        orientation[1] = 1;
        //variavel para definir a posição

        position[0] = n/2;
        position[1] = n/2;
        //add a posição na cobra
        snake.add(position);
        //move ela
        move(orientation);
        //cria um rato
        createFruit();

    }
    //thread para mover a cobra
    public void move(final int[] orient){
        //precisa do handler para setar os ui
        final android.os.Handler handler = new android.os.Handler();


        //cria uma thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                //thread roda enquanto a variavel for verdadeira
                while(running){
                    try {
                        Thread.sleep(dificult);//tempo que a thread "dorme', aqui é setado a dificuldade
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                handler.post(new Runnable() {
                    @Override
                    public void run() {


                        //for que percorre a cobra
                        for (int i = 0; i < snake.size(); i++) {

                            int [] position = snake.get(i);
                            //limpa a posição que a cobra passou
                            squareBlack(table[position[0]][position[1]]);


                        }

                        eating();

                        for (int i = snake.size()-1; i > 0; i--) {

                            snake.get(i)[0] = snake.get(i-1)[0];
                            snake.get(i)[1] = snake.get(i-1)[1];


                        }
                        //cabeça da cobra
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

    public void createFruit(){
        fruit[0] = new Random().nextInt(n);
        fruit[1] = new Random().nextInt(n);
        Log.i("CRIA", "CIROU A FRUTA");
        squareWhite(table[fruit[0]][fruit[1]]);


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
        botPause.setVisibility(View.INVISIBLE);
        botPlay.setVisibility(View.VISIBLE);
    }

    public void play(View v){
        running = true;
        move(orientation);
        botPause.setVisibility(View.VISIBLE);
        botPlay.setVisibility(View.INVISIBLE);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onStop() {
        super.onStop();
        String vazia = "";
        for(int i = 0; i < snake.size(); i++){
            vazia += snake.get(i)[0] + ",";
            if(i == snake.size()-1){
                vazia += snake.get(i)[1];
            }else{
                vazia += snake.get(i)[1] + "-";
            }
        }

        SharedPreferences settings = getSharedPreferences(PREF_SNAKE, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("tok", vazia);

        Log.i("STRINGA", "" + vazia);


//        StringBuilder stringBuilder = new StringBuilder();
//        for(Integer[] i: snake){
//            stringBuilder.append(i);
//            stringBuilder.append(",");
//        }
//
//

//        editor.putString("tamanho", stringBuilder.toString());



//        editor.putInt("tam_array", snake.size());
//        for(int i = 0; i < snake.size(); i++){
//            editor.remove("tam_" + i);
//            editor.putInt("tam_" + i, snake.get(i)[i]);
//        }
        editor.commit();



        mp.stop();

        //editor.putInt("", table[position[0]][position[1]]);
    }


}
