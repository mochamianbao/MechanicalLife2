package com.moca.mechanicallife2;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moca.mechanicallife2.databinding.FragmentUserInfoBinding;
import com.moca.mechanicallife2.myentity.MyUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserInfoFragment extends Fragment {

    FragmentUserInfoBinding binding;
    private MyUser myUser;

    public UserInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentUserInfoBinding.inflate(inflater);


        return  binding.getRoot();

//        return inflater.inflate(R.layout.fragment_user_info, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myUser = MyApplication.getThisUser();


        //绘制个人信息页面
        String sex = "";
        switch (myUser.getUserSex()){
            case 0:
                sex = "男";
                break;
            case 1:
                sex = "女";
                break;
                default:
                    break;
        }
        binding.UserInfoNameget.setText(myUser.getUserName());
        binding.UserInfoSexget.setText(sex);
        binding.UserInfoAgeget.setText(String.valueOf(myUser.getUserAge()));
        binding.UserInfoComplete.setText(String.valueOf(myUser.getThisUserCompletedNum()));

        //跳转到修改信息页面
        binding.userInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),UserInfoChangeActivity.class));
            }
        });
    }
}
