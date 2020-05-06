package com.moca.mechanicallife2.dao;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.moca.mechanicallife2.myentity.MyEvent;

public class EViewModel extends ViewModel {



    public MutableLiveData<Integer> LiveDataNumber;

    public MutableLiveData<Integer> getLiveDataNumber() {
        if (LiveDataNumber == null){
            LiveDataNumber = new MutableLiveData<>();
            LiveDataNumber.setValue(0);
        }
        return LiveDataNumber;
    }

    public void setLiveDataNumber(MutableLiveData<Integer> liveDataNumber) {
        LiveDataNumber = liveDataNumber;
    }
}
