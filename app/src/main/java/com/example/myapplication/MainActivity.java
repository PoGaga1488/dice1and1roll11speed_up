package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button rzuc;
    private Button reset;
    private TextView d1, d2, d3, d4, d5;
    private TextView licznik;
    private TextView wynL;
    private TextView wynG;

    private int licznik1 = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);



        rzuc = findViewById(R.id.button);
        reset = findViewById(R.id.button2);
        licznik = findViewById(R.id.textView9);
        wynL = findViewById(R.id.textView);
        wynG = findViewById(R.id.textView8);
        d1 = findViewById(R.id.textView2);
        d2 = findViewById(R.id.textView3);
        d3 = findViewById(R.id.textView4);
        d4 = findViewById(R.id.textView5);
        d5 = findViewById(R.id.textView6);



        rzuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int newScore = diceandroll();
                licznik1++;
                updatelicz();
                updatescore(newScore);
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d1.setText(" ? ");
                d2.setText(" ? ");
                d3.setText(" ? ");
                d4.setText(" ? ");
                d5.setText(" ? ");
                wynL.setText("Wynik tego losowania: 0");
                wynG.setText("Wynik gry: 0");
                licznik.setText("Liczba rzutów: 0");
                score = 0;
                licznik1= 0;
            }
        });




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }



    private void updatelicz() {
        licznik.setText("Liczba rzutów: " + licznik1);

    }

    final Random myRandom = new Random();
    int[] dlist = new int[5];
    int[] dpowtor = new int[6];

    private int diceandroll(){
        int wynL1 = 0;
        for (int i = 0; i < dlist.length; i++) {
            dlist[i] = myRandom.nextInt(6) + 1;
        }

        d1.setText(String.valueOf(dlist[0]));
        d2.setText(String.valueOf(dlist[1]));
        d3.setText(String.valueOf(dlist[2]));
        d4.setText(String.valueOf(dlist[3]));
        d5.setText(String.valueOf(dlist[4]));

        for (int i = 0; i < dpowtor.length; i++) {
            dpowtor[i] = 0;
        }
        for (int value : dlist) {
            dpowtor[value - 1]++;
        }

        for (int j = 1; j <= dpowtor.length; j++) {
            if(dpowtor[j-1] > 1){
                wynL1 += dpowtor[j-1]*j;
            }
        }


        wynL.setText("Wynik tego losowania: " + wynL1);
        return wynL1;
    }
    private void updatescore(int newScore){
        score += newScore;
        wynG.setText("Wynik gry: " + score);
    }




}
