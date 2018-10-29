package com.corbishley.intentbasic;

import android.app.Activity;
import android.os.Bundle;

public class RegisterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setTitle("炸牌店會員註冊");
    }
}
