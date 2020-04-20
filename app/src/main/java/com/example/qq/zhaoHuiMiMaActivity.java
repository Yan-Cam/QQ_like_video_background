package com.example.qq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class zhaoHuiMiMaActivity extends AppCompatActivity {
    private ImageView fanHuiT;
    private TextView fanHuiZ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_zhu_ce);
        setContentView(R.layout.activity_zhao_hui_mi_ma);
        fanHuiT = (ImageView) findViewById(R.id.fht2);
        fanHuiZ = (TextView) findViewById(R.id.fhz2);
        fanHuiT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fh();
            }
        });
        fanHuiZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fh();
            }
        });
    }
    private void fh() {
        Intent intent = new Intent(zhaoHuiMiMaActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
