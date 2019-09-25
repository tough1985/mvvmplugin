package com.fdd.mvvmplugin.templates

class TemplateKTMvvmItemEvent extends TemplateMvvmItemEvent {
    // Kotlin MVVM Item Event模板
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
class ${functionName}ItemEvent : BaseEvent<${functionName}ItemVM>() {

    override fun onClick(view: View?, itemVM: ${functionName}ItemVM) {
    }
}

'''
}