package com.corbishley.intentbasic;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainCouseActivity extends Activity {

    private static final int ReturnData = 1 ;
    private static final int ReturnError = 2;

    private TextView mealA,mealB;
    private TextView textViewResult;
    private RadioGroup radioGroup;
    private RadioButton radioA,radioB;
    private boolean mealAFlag,mealBFlag;
    private CheckBox checkCola,checkSaladA,checkFrenchfries,checkApplepie;
    private CheckBox checkSoup,checkSaladB,checkIceCream,checkChicken;
    private boolean colaFlag,saladAFlag,frenchfriesFlag,applePieFlag;
    private boolean soupFlag,saladBFlag,iceCreamFlag,chickenFlag;
    private Button okButton,cancelButton;
    private String TAG = "main";
    private AlertDialog.Builder builder;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_couse);

        context = this;
        setTitle("請選擇你要的主餐");
        mealA = (TextView) findViewById(R.id.textView_mealA);
        mealB = (TextView) findViewById(R.id.textView_mealB);
        mealA.setText("漢堡餐");
        mealB.setText("炸雞餐");

        textViewResult = (TextView) findViewById(R.id.textView4_id);
        textViewResult.setText("");

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup_id);
        radioA = (RadioButton) findViewById(R.id.radioButton_A);
        radioB = (RadioButton) findViewById(R.id.radioButton_B);

        mealAFlag = false;
        mealBFlag = false;

        radioGroup.setOnCheckedChangeListener(new CheckedRadioChange());

        checkCola = (CheckBox) findViewById(R.id.checkBox_Cola);
        checkSaladA = (CheckBox) findViewById(R.id.checkBox_saladA);
        checkFrenchfries = (CheckBox) findViewById(R.id.checkBox_french);
        checkApplepie = (CheckBox) findViewById(R.id.checkBox_apple);

        checkSoup = (CheckBox) findViewById(R.id.checkBox_soup);
        checkSaladB = (CheckBox) findViewById(R.id.checkBox_saladB);
        checkIceCream = (CheckBox) findViewById(R.id.checkBox_iceCream);
        checkChicken = (CheckBox) findViewById(R.id.checkBox_chicken);

        checkCola.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mealAFlag){
                    colaFlag = isChecked;
                }else {
                    checkCola.setChecked(false);
                    colaFlag = false;
                }
            }
        });

        checkSaladA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mealAFlag){
                    saladAFlag = isChecked;
                }else {
                    checkSaladA.setChecked(false);
                    saladAFlag =false;
                }
            }
        });

        checkFrenchfries.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mealAFlag){
                    frenchfriesFlag = isChecked;
                }else {
                    checkFrenchfries.setChecked(false);
                    frenchfriesFlag =false;
                }
            }
        });

        checkApplepie.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mealAFlag){
                    applePieFlag = isChecked;
                }else {
                    checkApplepie.setChecked(false);
                    applePieFlag =false;
                }
            }
        });

        checkSoup.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mealBFlag){
                    soupFlag = isChecked;
                }else {
                    checkSoup.setChecked(false);
                    soupFlag =false;
                }
            }
        });

        checkSaladB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mealBFlag){
                    saladBFlag = isChecked;
                }else {
                    checkSaladB.setChecked(false);
                    saladBFlag =false;
                }
            }
        });

        checkIceCream.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mealBFlag){
                    iceCreamFlag = isChecked;
                }else {
                    checkIceCream.setChecked(false);
                    iceCreamFlag =false;
                }
            }
        });

        checkChicken.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mealBFlag){
                    chickenFlag = isChecked;
                }else {
                    checkChicken.setChecked(false);
                    chickenFlag =false;
                }
            }
        });

        okButton = (Button) findViewById(R.id.button_ok);
        cancelButton = (Button) findViewById(R.id.button_cancel);

        okButton.setOnClickListener(new buttonClick());
        cancelButton.setOnClickListener(new buttonClick());

    } // end of onCreat()

    private class CheckedRadioChange implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.radioButton_A:
                    mealAFlag = true;
                    mealBFlag = false;

                    checkSoup.setChecked(false);
                    checkSaladB.setChecked(false);
                    checkIceCream.setChecked(false);
                    checkChicken.setChecked(false);
                    break;

                case R.id.radioButton_B:
                    mealAFlag = false;
                    mealBFlag = true;

                    checkCola.setChecked(false);
                    checkSaladA.setChecked(false);
                    checkFrenchfries.setChecked(false);
                    checkApplepie.setChecked(false);
                    break;
            }
        }
    }
    private class buttonClick implements View.OnClickListener {
        int orderSum = 0;
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.button_ok:

                    if(mealAFlag){

                        textViewResult.setText("\n你點了漢堡餐,餐點如下:\n");
                        if(colaFlag) {
                            textViewResult.append("可口可樂 ");
                            orderSum += 50;
                        }
                        if(saladAFlag) {
                            textViewResult.append("凱薩沙拉 ");
                            orderSum += 100;
                        }
                        if(frenchfriesFlag) {
                            textViewResult.append("薯條 ");
                            orderSum += 60;
                        }
                        if(applePieFlag) {
                            textViewResult.append("蘋果派 ");
                            orderSum += 80;
                        }

                        if(orderSum == 0){
                            textViewResult.setText("你沒有點選主餐。");
                        }else{
                            textViewResult.append("\n主餐合計: $"+orderSum);
                        }

                    }else if(mealBFlag){

                        textViewResult.setText("\n你點了炸雞餐,餐點如下:\n");
                        if(soupFlag) {
                            textViewResult.append("玉米濃湯 ");
                            orderSum += 80;
                        }
                        if(saladBFlag) {
                            textViewResult.append("和風沙拉 ");
                            orderSum += 100;
                        }
                        if(iceCreamFlag) {
                            textViewResult.append("蛋捲冰淇淋 ");
                            orderSum += 90;
                        }
                        if(chickenFlag) {
                            textViewResult.append("六塊雞塊 ");
                            orderSum += 70;
                        }

                        if(orderSum == 0){
                            textViewResult.setText("你沒有點選主餐。");
                        }else{
                            textViewResult.append("\n主餐合計: $"+orderSum);
                        }
                    }

                    builder = new AlertDialog.Builder(context);
                    builder.setTitle("請確認您的餐點");
                    builder.setMessage(textViewResult.getText().toString());
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent();
                            String textResult = textViewResult.getText().toString();
                            intent.putExtra("order_list",textResult);
                            intent.putExtra("meal_total",orderSum);
                            setResult(ReturnData,intent);
                            dialog.dismiss();
                            finish();
                        }
                    });

                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            orderSum = 0;
                            dialog.dismiss();
                        }
                    });
                    builder.create().show();
                    break;

                case R.id.button_cancel:

                    textViewResult.setText("");
                    radioA.setChecked(true);
                    radioB.setChecked(false);
                    mealAFlag = true;
                    mealBFlag = false;

                    checkCola.setChecked(false);
                    colaFlag = false;

                    checkSaladA.setChecked(false);
                    saladAFlag = false;

                    checkFrenchfries.setChecked(false);
                    frenchfriesFlag = false;

                    checkApplepie.setChecked(false);
                    applePieFlag = false;

                    checkSoup.setChecked(false);
                    soupFlag = false;

                    checkSaladB.setChecked(false);
                    saladBFlag = false;

                    checkIceCream.setChecked(false);
                    iceCreamFlag = false;

                    checkChicken.setChecked(false);
                    chickenFlag = false;

                    Intent intertError = new Intent();
                    setResult(ReturnError,intertError);
                    break;


            }
        }
    }
}
