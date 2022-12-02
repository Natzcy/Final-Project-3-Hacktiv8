package com.hacktiv8.finpro3kalkulator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView textangka , texthasil;
    MaterialButton buttonC , buttonPersen , buttonDel , buttonTitik;
    MaterialButton buttonBagi , buttonKali , buttonKurang , buttonTambah , buttonHasil;
    MaterialButton no1 , no2 , no3 , no4 , no5 , no6 , no7 , no8 , no9 , no0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textangka = findViewById(R.id.TextAngka);
        texthasil = findViewById(R.id.TextHasil);

        assignId(buttonC,R.id.buttonC);
        assignId(buttonPersen,R.id.buttonPersen);
        assignId(buttonDel,R.id.buttonDel);
        assignId(buttonTitik,R.id.buttonTitik);
        assignId(buttonBagi,R.id.buttonBagi);
        assignId(buttonKali,R.id.buttonKali);
        assignId(buttonKurang,R.id.buttonKurang);
        assignId(buttonTambah,R.id.buttonTambah);
        assignId(buttonHasil,R.id.buttonHasil);
        assignId(no0,R.id.no0);
        assignId(no1,R.id.no1);
        assignId(no2,R.id.no2);
        assignId(no3,R.id.no3);
        assignId(no4,R.id.no4);
        assignId(no5,R.id.no5);
        assignId(no6,R.id.no6);
        assignId(no7,R.id.no7);
        assignId(no8,R.id.no8);
        assignId(no9,R.id.no9);
    }

    void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button =(MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = textangka.getText().toString();

        if(buttonText.equals("C")){
            textangka.setText("");
            texthasil.setText("0");
            return;
        }

        if(buttonText.equals("=")){
            textangka.setText(texthasil.getText());
            return;
        }

        if(buttonText.equals("⌫")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else {
            dataToCalculate = dataToCalculate+buttonText;
        }
        textangka.setText(dataToCalculate);

        String hasilFinal = gethasil(dataToCalculate);

        if(!hasilFinal.equals("Err")){
            texthasil.setText(hasilFinal);
        }
    }

    String gethasil(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String hasilFinal = context.evaluateString(scriptable,data,"Javascript",1,null).toString() ;
            if(hasilFinal.endsWith(".0")){
                hasilFinal = hasilFinal.replace(".0","");
            }
            return hasilFinal;
        }catch (Exception e){
            return "Err";
        }
    }
}