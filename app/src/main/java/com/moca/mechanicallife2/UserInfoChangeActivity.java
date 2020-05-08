package com.moca.mechanicallife2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.moca.mechanicallife2.dao.EventDao;
import com.moca.mechanicallife2.dao.UserDao;
import com.moca.mechanicallife2.databinding.ActivityUserInfoChangeBinding;
import com.moca.mechanicallife2.myentity.MyUser;

public class UserInfoChangeActivity extends AppCompatActivity {

    String userNameChange;
    int userAgeChange =20;//默认值
    int userSexChangeInt  = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_change);

        final UserDao userDao = new UserDao(this);

        ActivityUserInfoChangeBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_user_info_change);
//性别选择
        binding.radioButtonMale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    userSexChangeInt = 0;
                }
            }
        });
        binding.radioButtonFemale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    userSexChangeInt = 1;
                }
            }
        });


        initNumberPicker();
        binding.userInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText editText1  =findViewById(R.id.UserInfoNameget);
                userNameChange = editText1.getText().toString();

                if (userNameChange==null||"".equals(userNameChange)){
                    Toast.makeText(UserInfoChangeActivity.this,"用户名不能为空",Toast.LENGTH_LONG).show();
                }
                else if (userNameChange.length()>7){
                    Toast.makeText(UserInfoChangeActivity.this,"用户名长度不能大于7",Toast.LENGTH_LONG).show();
                }else {
                    System.out.println(userNameChange+ "    "+userAgeChange+ "   "+userSexChangeInt);

                    int id = MyApplication.getThisUser().getUid();
                    userDao.changeUserInfo(userNameChange,userSexChangeInt,userAgeChange,id);
                    finish();
                }


            }
        });


        //返回
        binding.userInfoBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
                userAgeChange = newVal;
            }
        });

    }
}
