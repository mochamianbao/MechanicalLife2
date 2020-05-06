package com.moca.mechanicallife2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class BottomFragmentAdapter extends FragmentPagerAdapter {


    //定义属性 Fragment列表
    private List<Fragment> fragmentList;


    //构造方法
    public BottomFragmentAdapter(FragmentManager fm){
        super(fm);
    }


    public BottomFragmentAdapter(@NonNull FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }


    //显示那个页面
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    //页面的个数
    @Override
    public int getCount() {
        return fragmentList.size();
    }

}
