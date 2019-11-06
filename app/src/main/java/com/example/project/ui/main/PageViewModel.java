package com.example.project.ui.main;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class PageViewModel extends ViewModel {

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private LiveData<String> mText = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            String temp="";
            switch(input){
                case 1:
                    temp = "대충 한식 먹고 싶다는 이야기";
                    break;
                case 2:
                    temp = "대충 분식 먹고 싶다는 이야기";
                    break;
                case 3:
                    temp = "대충 중식 먹고 싶다는 이야기";
                    break;
                case 4:
                    temp = "대충 치킨 먹고 싶다는 이야기";
                    break;
                case 5:
                    temp = "대충 양식 먹고 싶다는 이야기";
                    break;
                case 6:
                    temp = "대충 일식 먹고 싶다는 이야기";
                    break;
            }
            return temp;
        }
    });

    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<String> getText() {
        return mText;
    }
}