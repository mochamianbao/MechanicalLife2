package com.moca.mechanicallife2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.moca.mechanicallife2.dao.UserDao;
import com.moca.mechanicallife2.databinding.ActivityLoginBinding;
import com.moca.mechanicallife2.myentity.MyUser;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {



    private MyUser myUser = new MyUser();//输入的用户信息
    private static MyUser findUser = new MyUser();
    private List<MyUser> tableUser = new ArrayList<>();//获取数据库的用户信息
    private EditText editTextName,editTextPassword;

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final ActivityLoginBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_login);

        Button btnLogin;
        TextView textViewRedister;
        btnLogin =  binding.buttonLogin;
        editTextName = binding.editTextName;
        editTextPassword = binding.editTextPassword;
        textViewRedister = binding.textViewRedister;


        //输入的用户名与信息



        //登录操作
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();

            }
        });





        //注册页面跳转
        textViewRedister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this,RegisterActivity.class );
                startActivity(intent);

            }


        });

    }


    public void checkLogin(){
        UserDao userDao = new UserDao(this);
        myUser.setUserName(editTextName.getText().toString());
        myUser.setUserPassword(editTextPassword.getText().toString());
        tableUser = userDao.checkUser(myUser.getUserName());
        for (int i = 0 ; i <tableUser.size();i++) {
            if (tableUser.get(i).getUserName().equals(myUser.getUserName())){
                findUser = tableUser.get(i);
                break;
            }
        }

        if (!myUser.getUserName().equals(findUser.getUserName())){
            Toast.makeText(LoginActivity.this,"登录失败,用户名不存在",Toast.LENGTH_LONG).show();
        }
        else if (!myUser.getUserPassword().equals(findUser.getUserPassword()))
        {
            Toast.makeText(LoginActivity.this,"登录失败,密码不正确",Toast.LENGTH_LONG).show();

        }else {

            //记录当前用户信息
            MyApplication.setThisUser(findUser);

            Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_LONG).show();
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            init(this);
            finish();
        }

        
        
        
    }

    public static void init (Context context){
        UserDao userDao = new UserDao(context);
        if (findUser.getLastLoginDay() != MyApplication.todayEventList.myDay){
            userDao.changelastLoginDay(findUser.getUid(),MyApplication.todayEventList.myDay);
            userDao.changethisDayCompletedNum(findUser.getUid(),0);
        }
        if (findUser.getLastLoginMonth() != MyApplication.todayEventList.myMonth){
            userDao.changelastLoginMonth(findUser.getUid(),MyApplication.todayEventList.myMonth);
            userDao.changethisMonthCompletedNum(findUser.getUid(),0);

        }
        System.out.println("初始化1"+findUser.getLastLoginDay()+"   "+MyApplication.todayEventList.myDay+ "   " +findUser.getUid());
        System.out.println("初始化2"+findUser.getLastLoginMonth()+"   "+MyApplication.todayEventList.myMonth+ "   " +findUser.getUid());


        userDao.changeEventprogress(findUser.getUid(),0);
    }



}
