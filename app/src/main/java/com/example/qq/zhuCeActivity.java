package com.example.qq;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class zhuCeActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button zc;
    private String Username;
    private String Password;
    private ImageView fanHuiT;
    private TextView fanHuiZ;
    private boolean flag;
    CreateSqlite sql;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_zhu_ce);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        zc = (Button) findViewById(R.id.zc);
        fanHuiT = (ImageView) findViewById(R.id.fht);
        fanHuiZ = (TextView) findViewById(R.id.fhz);
        sql = new CreateSqlite(zhuCeActivity.this, "Mysql",null,1);
        db = sql.getReadableDatabase();
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
        zc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Username = username.getText().toString();
                Password = password.getText().toString();
                if (Username.equals("") || Password.equals(""))
                {
                    nullTip();
                }
                else
                {
                    inspectRepeat(Username);
                    if (flag == true)
                        repeatTip();
                    else
                        putSqlite(Username, Password);
                }
            }
        });

    }
    private void repeatTip() {
        new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("该账号已被注册！")
                .setPositiveButton("确定",null)
                .show();
        username.setText("");
        password.setText("");
    }
    private void nullTip() {
        new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("请把密码或账号填写完整哦")
                .setPositiveButton("确定",null)
                .show();
        username.setText("");
        password.setText("");
    }
    private void zcSuccess() {
        new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("注册成功！")
                .setPositiveButton("确定",null)
                .show();
        username.setText("");
        password.setText("");
    }


    private void putSqlite(String username, String password) {
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);
        db.insert("info_user", null, values);
        zcSuccess();
    }
    private void fh() {
        Intent intent = new Intent(zhuCeActivity.this, MainActivity.class);
        startActivity(intent);
    }
    private void inspectRepeat(String username) {
        Cursor cursor = db.query("info_user", new String[]{"username", "password"}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            //遍历查找所有用户名
            String userquery = cursor.getString(cursor.getColumnIndex("username"));
            if (username.equals(userquery))
            {
                flag = true;
            }
            else
            {
                flag = false;
            }
        }
    }
}
