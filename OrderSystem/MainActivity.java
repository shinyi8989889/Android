package com.corbishley.intentbasic;

import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final int IntentRequestCode = 2;
    private static final int ReturnData = 1 ;
    private static final int ReturnError = 2;
    private static final int ReturnDrinkData = 3;
    private static final int ReturnDrinkError = 4;
    private static final String TAG = "menu";
    private ImageButton drinkButton,mainButton;
    private Intent intent;
    private TextView orderResult;
    private Context context;
    private String mealResult="",drinkResult="";
    private int mealTotal,drinkTotal,total;
    private final int registerItem = 10;
    private final int intoWebsite = 2;
    private final int groupID_1 = 1;
    private Button payButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("歡迎光臨炸牌店");
        context = this;

        drinkButton = (ImageButton) findViewById(R.id.imageButton_drink);
        mainButton = (ImageButton) findViewById(R.id.imageButton_main);
        payButton = (Button) findViewById(R.id.button_paybill);

        orderResult = (TextView) findViewById(R.id.textView_list);
        orderResult.setText("");


        drinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "請點選飲料",Toast.LENGTH_SHORT).show();
                intent = new Intent(context,DrinkActivity.class);
                startActivityForResult(intent, IntentRequestCode);
            }
        });

//        mainButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context,"order main course",Toast.LENGTH_SHORT).show();
//                intent = new Intent(context,MainCouseActivity.class);
//                startActivity(intent);
//            }
//        });

        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "請點選主餐",Toast.LENGTH_SHORT).show();
                intent = new Intent(context,MainCouseActivity.class);
                startActivityForResult(intent, IntentRequestCode);
            }
        });

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                total = mealTotal+drinkTotal;
                if(total == 0){
                    orderResult.setText("");
                    orderResult.append("你未選擇任何餐點！！！");
                }else{
                    orderResult.append("\n\n總計: "+total);
                    orderResult.append("\n\n請立刻付現，謝謝！");
                }
            }
        });

    } // end of onCreate()


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(groupID_1,intoWebsite,Menu.NONE,"Website");

        MenuItem item1 = menu.add(0,registerItem,Menu.NONE,"註冊");
        item1.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getGroupId() == groupID_1){
            String httpAddr = "uk.yahoo.com";
            String actionhttp = Intent.ACTION_VIEW;
            Uri uriHttp = Uri.parse("http:"+httpAddr);
            Log.d(TAG,"http uri = "+uriHttp);

            Intent intentHttp = new Intent(actionhttp, uriHttp);
            startActivity(intentHttp);
        }else if (item.getItemId() == registerItem){
            Intent intent = new Intent(context,RegisterActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == IntentRequestCode){
            switch(resultCode){
                case ReturnData:
                    mealResult = data.getStringExtra("order_list");
                    mealTotal = data.getIntExtra("meal_total",0);
                    break;

                case ReturnError:
                    orderResult.setText("");
                    orderResult.append("請選擇一個主餐 ! ");
                    break;

                case ReturnDrinkData:
                    drinkResult = data.getStringExtra("order_drinklist");
                    drinkTotal = data.getIntExtra("drink_total", 0);
                    break;

                case ReturnDrinkError:
                    orderResult.setText("");
                    orderResult.setText("請選擇一種飲料 ! ");
                    break;

                default:
                    Toast.makeText(context,"你不點餐來鬧的嗎！！！",Toast.LENGTH_SHORT).show();
                    break;
            }
        } // end of  if

        orderResult.setText(mealResult+drinkResult);

    }
}