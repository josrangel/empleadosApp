package com.inovacionaplicada.modelview;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NuevoElementoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public NuevoElementoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is send fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}