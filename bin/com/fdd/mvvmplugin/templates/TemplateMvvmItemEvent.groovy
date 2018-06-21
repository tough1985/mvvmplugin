package com.fdd.mvvmplugin.templates

class TemplateMvvmItemEvent {
    //ViewModel模板
    def template =
'''package ${applicaitionId}.${packageName}.event;

import android.view.View;

import com.fangdd.mobile.mvvmcomponent.event.BaseEvent;
import ${applicaitionId}.${packageName}.viewmodel.${functionName}ItemVM;

/**
 * @Author: ${author}
 * @Date: ${date}
 * @Description:
 */
public class ${functionName}ItemEvent extends BaseEvent<${functionName}ItemVM> {

    @Override
    public void onClick(View view, ${functionName}ItemVM itemVM) {
    }
}

'''
}