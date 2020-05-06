package com.moca.mechanicallife2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.moca.mechanicallife2.databinding.ActivityLoginBinding;
import com.moca.mechanicallife2.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final ActivityRegisterBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_register);




        binding.buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = binding.editTextName.getText().toString();
                String password = binding.editTextPassword.getText().toString();
                String password2 = binding.editTextPassword2.getText().toString();

                if (userName==null||"".equals(userName)){
                    Toast.makeText(RegisterActivity.this,"用户名不能为空",Toast.LENGTH_LONG).show();
                }
                else if (password==null||"".equals(password)){
                    Toast.makeText(RegisterActivity.this,"密码不能为空",Toast.LENGTH_LONG).show();
                }
                else if (userName.length()>7){
                    Toast.makeText(RegisterActivity.this,"用户名长度不能大于5",Toast.LENGTH_LONG).show();
                }
                else if (!password.equals(password2)){
                    Toast.makeText(RegisterActivity.this,"确认密码不相同，请重新检查",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_LONG).show();
                    finish();

                }

            }
        });


    }
}
