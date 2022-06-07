package com.inspacegalaxyx.solartigress;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;

public class STGP extends AppCompatActivity {

    private ImageView gladiolus, lilies, magnolia, orchid, rose;
    private TextView flowerTxt;
    private Button againBtn;
    private LottieAnimationView flowerEnd;
    ArrayList<ImageView> flowers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stgp);

        againBtn = findViewById(R.id.againBtn);
        flowerTxt = findViewById(R.id.flowerTxt);
        flowerEnd = findViewById(R.id.flowerEnd);
        gladiolus = findViewById(R.id.gladiolus);
        lilies = findViewById(R.id.lilies);
        magnolia = findViewById(R.id.magnolia);
        orchid = findViewById(R.id.orchid);
        rose = findViewById(R.id.rose);

        flowers.add(gladiolus);
        flowers.add(lilies);
        flowers.add(magnolia);
        flowers.add(orchid);
        flowers.add(rose);

        for (ImageView flower : flowers) {
            flower.setOnClickListener(v -> {
                flower.setVisibility(View.INVISIBLE);
                flowers.remove(flower);
                flower.setClickable(false);
                if (flowers.isEmpty()){
                    againBtn.setVisibility(View.VISIBLE);
                    flowerEnd.setVisibility(View.VISIBLE);
                    flowerTxt.setText("Beautiful, but you can do it even more beautiful");
                }
            });
        }
    }

    public void again(View view) {
        startActivity(new Intent(this, STGP.class));
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }
}