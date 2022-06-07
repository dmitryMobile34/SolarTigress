package com.inspacegalaxyx.solartigress;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerLib;
import com.facebook.applinks.AppLinkData;
import com.orhanobut.hawk.Hawk;

import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {



    private static final String AF_DEV_KEY = "nzoyjQ5cQnEYXMQn8fNTrX";

    public static final String DP_ONE = "dpl1";
    public static final String DP_TWO = "dpl1";



    String stringInc;


    public static final String CP_ONE = "cp1";

    public static final String CP_TWO = "cp2";

    public static final String CP_THREE = "cp3";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dlMe();

        SharedPreferences prefs = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
        if(prefs.getBoolean("activity_exec", false)){
            Intent intent = new Intent(this, STF.class);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            startActivity(intent);
            finish();
        } else {
            SharedPreferences.Editor exec = prefs.edit();
            exec.putBoolean("activity_exec", true);
            exec.commit();
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        appsyFi();
    }


    public void appsyFi() {
        AppsFlyerConversionListener conversionListener = new AppsFlyerConversionListener() {



            @Override
            public void onConversionDataSuccess(Map<String, Object> conversionData) {



                Log.d("TESTING_ZONE", "af stat is " + conversionData.get("af_status"));

                stringInc = (String) conversionData.get("campaign");


                Log.d("NAMING TEST", "campaign attributed: " + stringInc);


                StringTokenizer tokenizer = new StringTokenizer(stringInc, "_");


                String one = tokenizer.nextToken();
                String two = tokenizer.nextToken();
                String three = tokenizer.nextToken();



                Hawk.put(CP_ONE, one);
                Hawk.put(CP_TWO, two);
                Hawk.put(CP_THREE, three);

                eventHor();

                finish();


            }




            @Override
            public void onConversionDataFail(String errorMessage) {
                Log.d("LOG_TAG", "error getting conversion data: " + errorMessage);

                eventHor();



            }

            @Override
            public void onAppOpenAttribution(Map<String, String> attributionData) {

                for (String attrName : attributionData.keySet()) {
                    Log.d("LOG_TAG", "attribute: " + attrName + " = " + attributionData.get(attrName));
                }

            }

            @Override
            public void onAttributionFailure(String errorMessage) {
                Log.d("LOG_TAG", "error onAttributionFailure : " + errorMessage);
            }

        };


        AppsFlyerLib.getInstance().init(AF_DEV_KEY, conversionListener, this);
        AppsFlyerLib.getInstance().start(this);
        AppsFlyerLib.getInstance().setDebugLog(true);


    }


    public void eventHor(){

        Intent EH = new Intent(MainActivity.this, STF.class);
        startActivity(EH);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();

    }

    public void dlMe() {


        AppLinkData.fetchDeferredAppLinkData(this,
                appLinkData -> {

                    if (appLinkData != null) {

                        List<String> params = appLinkData.getTargetUri().getPathSegments();
                        String id = params.get(params.size() - 1);
                        String conjoined = TextUtils.join("/", params);

                        StringTokenizer tokenizer = new StringTokenizer(conjoined, "/");

                        String firstLink = tokenizer.nextToken();
                        String secondLink = tokenizer.nextToken();


                        Hawk.put(DP_ONE, firstLink);
                        Hawk.put(DP_TWO, secondLink);


                    } else {
                        Log.d("FB", "Error Code:");

                    }

                }
        );
    }

}