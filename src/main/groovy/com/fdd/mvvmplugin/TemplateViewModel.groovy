package com.fdd.mvvmplugin

class TemplateViewModel {
    //ViewModel模板
    def template = 
'''package ${applicaitionId}.${packageName}.viewmodel;

import android.databinding.ObservableField;

import com.fangdd.mobile.mvvmcomponent.viewmodel.BaseViewModel;

/**
 * @Author: ${author}
 * @Date: ${date}
 * @Description:
 */
public class ${functionName}VM extends BaseViewModel {

    public static final String TAG = ${functionName}VM.class.getSimpleName();


    public ${functionName}VM() {

    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
'''
}