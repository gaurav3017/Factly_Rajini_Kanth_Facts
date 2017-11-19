package com.singh.grv.factly;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Toasty.success(AboutActivity.this, "By Gaurav Singh", Toast.LENGTH_LONG, true).show();

    }
}
