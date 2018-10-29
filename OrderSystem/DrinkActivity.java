package com.corbishley.intentbasic;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

public class DrinkActivity extends Activity {

    private static final int ReturnDrinkData = 3;
    private static final int ReturnDrinkError = 4;
    private static final String TAG = "drink";
    private CheckBox checkCola,checkBubbleTea,checkChocolate,checkKiwi;
    private boolean colaFlag,bubbleTeaFlag,chocolateFlag,kiwiFlag;
    private Button okButton,cancelButton;
    private TextView textResult;
    private AlertDialog.Builder builder;
    private Context context;
    private EditText editCoke,editBubble,editChocolate,editKiwi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        context = this;
        setTitle("請不客氣的點選飲料");

        checkCola = (CheckBox) findViewById(R.id.checkBox_cola);
        checkBubbleTea = (CheckBox) findViewById(R.id.checkBox_bubbletea);
        checkChocolate = (CheckBox) findViewById(R.id.checkBox_chocolate);
        checkKiwi = (CheckBox) findViewById(R.id.checkBox_kiwi);

        checkCola.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (colaFlag = isChecked){
                    editCoke.setText("1");
                }else {
                    editCoke.setText("");
                }
            }
        });

        checkBubbleTea.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (bubbleTeaFlag = isChecked){
                    editBubble.setText("1");
                }else{
                    editBubble.setText("");
                }
            }
        });

        checkChocolate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (chocolateFlag = isChecked){
                    editChocolate.setText("1");
                }else {
                    editChocolate.setText("");
                }
            }
        });

        checkKiwi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (kiwiFlag = isChecked){
                    editKiwi.setText("1");
                }else{
                    editKiwi.setText("");
                }
            }
        });

        okButton = (Button) findViewById(R.id.button_ok);
        cancelButton = (Button) findViewById(R.id.button_cancel);

        okButton.setOnClickListener(new myButtonClick());
        cancelButton.setOnClickListener(new myButtonClick());

        textResult = (TextView) findViewById(R.id.textView_result);
        textResult.setText("");

        editCoke = (EditText) findViewById(R.id.editText1);
        editBubble = (EditText) findViewById(R.id.editText2);
        editChocolate = (EditText) findViewById(R.id.editText3);
        editKiwi = (EditText) findViewById(R.id.editText4);



    } // end of onCreate()

    private class myButtonClick implements View.OnClickListener {
        int orderSum = 0;
        int coke,tea,chocolate,kiwi;

        @Override
        public void onClick(View v) {

            switch(v.getId()){

                case R.id.button_ok:

                    textResult.setText("\n\n你點的飲料如下:\n");
                    if(colaFlag){
                        coke = Integer.parseInt(editCoke.getText().toString());
                        textResult.append("大瓶可樂($200) x "+coke);
                        orderSum +=(200*coke);
                    }

                    if(bubbleTeaFlag){
                        tea = Integer.parseInt(editBubble.getText().toString());
                        textResult.append(" 珍珠奶茶($100) x "+tea);
                        orderSum +=(100*tea);
                    }

                    if(chocolateFlag){
                        chocolate = Integer.parseInt(editChocolate.getText().toString());
                        textResult.append(" 巧克力奶($150) x "+chocolate);
                        orderSum +=(150*chocolate);
                    }

                    if(kiwiFlag){
                        kiwi = Integer.parseInt(editKiwi.getText().toString());
                        textResult.append(" 奇異果汁($300) x "+kiwi);
                        orderSum +=(300*kiwi);
                    }

                    if(orderSum == 0){
                        textResult.setText("\n\n你沒有點選飲料。");
                    }else{
                        textResult.append("\n飲料合計: $"+orderSum);
                    }

                    builder = new AlertDialog.Builder(context);
                    builder.setTitle("請確認您的餐點");
                    builder.setMessage(textResult.getText().toString());
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent();
                            String textViewResult = textResult.getText().toString();
                            intent.putExtra("order_drinklist",textViewResult);
                            intent.putExtra("drink_total", orderSum);
                            setResult(ReturnDrinkData,intent);
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

                    checkCola.setChecked(false);
                    checkBubbleTea.setChecked(false);
                    checkChocolate.setChecked(false);
                    checkKiwi.setChecked(false);
                    textResult.setText("");
                    editCoke.setText("");
                    editBubble.setText("");
                    editChocolate.setText("");
                    editKiwi.setText("");
                    Intent intentError = new Intent();
                    setResult(ReturnDrinkError,intentError);
                    break;
            }
        }
    }
}
