package com.moca.mechanicallife2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.moca.mechanicallife2.dao.UserDao;
import com.moca.mechanicallife2.databinding.ActivityRegisterBinding;
import com.moca.mechanicallife2.myentity.MyUser;

public class RegisterActivity extends AppCompatActivity {
    String userName;
    String password;
    String password2;
    MyUser myUser = new MyUser();
    int age = 20;//默认值 20岁
    int sex = 0;//默认值 男
    UserDao eventDao = new UserDao(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final ActivityRegisterBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_register);




        initNumberPicker();

        //性别选择
        binding.radioButtonMale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    sex = 0;
                }
            }
        });
        binding.radioButtonFemale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    sex = 1;
                }
            }
        });



        //新建个人信息

        binding.buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = binding.editTextName.getText().toString();
                password = binding.editTextPassword.getText().toString();
                password2 = binding.editTextPassword2.getText().toString();
                System.out.println(age+"  "+sex);
                if (userName==null||"".equals(userName)){
                    Toast.makeText(RegisterActivity.this,"用户名不能为空",Toast.LENGTH_LONG).show();
                }
                else if (password==null||"".equals(password)){
                    Toast.makeText(RegisterActivity.this,"密码不能为空",Toast.LENGTH_LONG).show();
                }
                else if (userName.length()>7){
                    Toast.makeText(RegisterActivity.this,"用户名长度不能大于7",Toast.LENGTH_LONG).show();
                }
                else if (!password.equals(password2)){
                    Toast.makeText(RegisterActivity.this,"确认密码不相同，请重新检查",Toast.LENGTH_LONG).show();
                }
                else {


                    myUser.setUserName(userName);
                    myUser.setUserPassword(password);
                    myUser.setUserAge(age);
                    myUser.setUserSex(sex);


                    eventDao.insert(myUser);
                    Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_LONG).show();
                    finish();

                }

            }
        });



    }


    //年龄选择器
    private void initNumberPicker() {
        NumberPicker numberPicker = findViewById(R.id.UserInfoAgeget);
        //设置最大值
        numberPicker.setMaxValue(80);
        //设置最小值
        numberPicker.setMinValue(10);
        //设置当前值
        numberPicker.setValue(20);
        //设置不可输入
        numberPicker.setWrapSelectorWheel(false);
        //设置滑动监听
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            //当NunberPicker的值发生改变时，将会激发该方法
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                age = newVal;
            }
        });

    }

}
