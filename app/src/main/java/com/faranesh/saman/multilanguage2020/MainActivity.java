package com.faranesh.saman.multilanguage2020;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoadLocale();
        setContentView(R.layout.activity_main);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));


        btn=(Button)findViewById(R.id.btn_chang_language);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeLanguageState();
            }
        });

    }


    public void ChangeLanguageState(){


        final String[] listItem=new String[]{"English","فارسی"};

        AlertDialog.Builder dialog=new AlertDialog.Builder(this);

        dialog.setTitle("Chosse one Language....");
        dialog.setSingleChoiceItems(listItem, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                if (which==0){

                    setLocale("en");
                    recreate();
                }else if (which==1){
                    setLocale("fa");
                    recreate();
                }

                dialog.dismiss();
            }
        });

        AlertDialog builder=dialog.create();
        builder.show();

    }

    public  void setLocale(String lang){

        Locale locale=new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration=new Configuration();
        configuration.locale=locale;
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor=getSharedPreferences("settings",MODE_PRIVATE).edit();
        editor.putString("MY_LANG",lang);
        editor.apply();


    }

    public void LoadLocale(){

        SharedPreferences sharepref=getSharedPreferences("settings",Activity.MODE_PRIVATE);
                String language=sharepref.getString("MY_LANG","");
        setLocale(language);
    }
}
