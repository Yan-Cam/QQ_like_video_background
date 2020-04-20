package com.example.qq;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
private VideoView myVideo;
private String videoPach;
private EditText zhanghao;
private EditText mima;
private Button login;
private TextView wjmm;
private TextView zhzc;
private TextView fwtg;
private String username;
private String password;
private ImageView shanchu;
private ImageView yan;
private int openeye;
private int closeeye;
private boolean isHideFirst = true;// 输入框密码是否是隐藏的，默认为true
CreateSqlite sql;
SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zhanghao = (EditText) findViewById(R.id.zhanghao);
        mima = (EditText) findViewById(R.id.mima);
        login = (Button) findViewById(R.id.btn_login);
        wjmm = (TextView) findViewById(R.id.wjmm);
        zhzc = (TextView) findViewById(R.id.zhzc);
        fwtg = (TextView) findViewById(R.id.fwtk);
        shanchu = (ImageView) findViewById(R.id.qingkong);
        yan = (ImageView) findViewById(R.id.yanjing);
        myVideo = (VideoView) findViewById(R.id.video);
        closeeye = R.drawable.close_eye;
        openeye = R.drawable.open_eye;
        videoPach = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.ic).toString();
        myVideo.setVideoPath(videoPach);
        myVideo.start();
        myVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                mp.setLooping(true);
            }
        });
        myVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                myVideo.setVideoPath(videoPach);
                myVideo.start();
            }
        });

        sql = new CreateSqlite(MainActivity.this, "Mysql",null,1);
        db = sql.getReadableDatabase();
        yan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isHideFirst == true)
                {
                    //密文显示
                    HideReturnsTransformationMethod method1 = HideReturnsTransformationMethod.getInstance();
                    mima.setTransformationMethod(method1);
                    isHideFirst = false;
                    // 光标的位置
                    int index = mima.getText().toString().length();
                    mima.setSelection(index);
                    yan.setImageResource(openeye);
                }
                else
                {
                    //明文显示
                    TransformationMethod method = PasswordTransformationMethod.getInstance();
                    mima.setTransformationMethod(method);
                    isHideFirst = true;
                    // 光标的位置
                    int index = mima.getText().toString().length();
                    mima.setSelection(index);
                    yan.setImageResource(closeeye);
                }

            }
        });
        shanchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mima.setText("");
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = zhanghao.getText().toString();
                password = mima.getText().toString();
                if (username.equals("") || password.equals(""))
                {
                    nullTip();
                }
                else
                {
                    queryUser(username, password);
                }
            }
        });

        zhzc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,zhuCeActivity.class);
                startActivity(intent);
                zhanghao.setText("");
                mima.setText("");
            }
        });

        wjmm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,zhaoHuiMiMaActivity.class);
                startActivity(intent);
                zhanghao.setText("");
                mima.setText("");
            }
        });

    }
    private void nullTip() {
        new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("请把密码或账号填写完整哦")
                .setPositiveButton("确定",null)
                .show();
        zhanghao.setText("");
        mima.setText("");
    }
    private void dislikeTip() {
        new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("请把密码或账号填写正确哦。")
                .setPositiveButton("确定",null)
                .show();
        zhanghao.setText("");
        mima.setText("");
    }
    private void Loginsuccess() {
        Intent intent = new Intent(MainActivity.this,jianKangMaActivity.class);
        startActivity(intent);
    }

    private void queryUser(String username, String password) {
        Cursor cursor = db.query("info_user", new String[]{"username", "password"}, null, null, null, null, null);
        //遍历cursor游标集
        while (cursor.moveToNext()){
            //取出用户名所对应的密码
            String userquery = cursor.getString(cursor.getColumnIndex("username"));
            String psdquery = cursor.getString(cursor.getColumnIndex("password"));
            //判断用户输入的密码和库中该用户名对应的密码是否一致
            if (username.equals(userquery) && password.equals(psdquery)){
                //如果一致，跳转进入到主页面
                Loginsuccess();
            }else {
                //否则调用dislikeTip方法，给出错误提示
                dislikeTip();
            }
        }
    }
}
