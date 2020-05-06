package com.moca.mechanicallife2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.moca.mechanicallife2.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final ActivityLoginBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_login);

        Button btnLogin;
        final EditText editTextName,editTextPassword;
        TextView textViewRedister;
        btnLogin =  binding.buttonLogin;
        editTextName = binding.editTextName;
        editTextPassword = binding.editTextPassword;
        textViewRedister = binding.textViewRedister;

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String password = editTextPassword.getText().toString();
                if (name.equals("admit") && password.equals("admit"))
                {
                    Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));

                }else {
                    Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_LONG).show();
                }
            }
        });

        textViewRedister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this,RegisterActivity.class );
                startActivity(intent);

            }


        });

    }





}
