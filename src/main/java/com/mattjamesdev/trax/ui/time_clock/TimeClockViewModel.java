package com.mattjamesdev.trax.ui.time_clock;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TimeClockViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TimeClockViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}