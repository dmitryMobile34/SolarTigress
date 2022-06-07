package com.inspacegalaxyx.solartigress;

import static com.inspacegalaxyx.solartigress.MainActivity.CP_ONE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class STF extends AppCompatActivity {

    TextView shutka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stf);

        shutka = findViewById(R.id.inviteTxt);

        new asyncF().execute();
    }

    public class asyncF extends AsyncTask<Void, Void, Void> {


        String jsoup;

        String hawk = Hawk.get(CP_ONE);

        String flink = "https://solartigress.space/8YbgS?";


        String gonedone = "sub_id_1=";



        String solyara = flink + gonedone + hawk;


        @Override
        protected Void doInBackground(Void... voids) {
            try {

                Document doc = Jsoup.connect(solyara).get();


                jsoup = doc.text();
            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            shutka.setText(jsoup);

            Intent soxranisvoidengi = new Intent(getApplicationContext(), STS.class);

            Intent denginabochku = new Intent(getApplicationContext(), STW.class);
            if (jsoup.equals("2Jn8")) {
                startActivity(soxranisvoidengi);
            } else {
                startActivity(denginabochku);
            }
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }

    }
}