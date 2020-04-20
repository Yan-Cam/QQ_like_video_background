package com.example.qq;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class jianKangMaActivity extends AppCompatActivity {
private Button qrtj;
private ImageView fanHuiT;
private TextView fanHuiZ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_jian_kang_ma);

        qrtj = (Button) findViewById(R.id.btn_qrtj);
        fanHuiT = (ImageView) findViewById(R.id.fht3);
        fanHuiZ = (TextView) findViewById(R.id.fhz3);

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
        qrtj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(jianKangMaActivity.this)
                        .setTitle("温馨提示")
                        .setMessage("提交成功")
                        .setPositiveButton("确定",null)
                        .show();
            }
        });
    }
    private void fh() {
        Intent intent = new Intent(jianKangMaActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
