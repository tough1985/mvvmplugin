package com.fdd.mvvmplugin.templates

class TemplateKTViewModel extends TemplateViewModel {
    // Kotlin MVVM ViewModel模板
    def template = 
'''package ${applicaitionId}.${packageName}.viewmodel;

import android.databinding.ObservableField
import com.fangdd.mobile.mvvmcomponent.liveevent.SingleLiveEvent
import com.fangdd.mobile.mvvmcomponent.network.ApiExceptrion
import com.fangdd.mobile.mvvmcomponent.network.LoadingObserver
import com.fangdd.mobile.mvvmcomponent.viewmodel.BaseViewModel

/**
 * @Author: ${author}
 * @Date: ${date}
 * @Description:
 */
class ${functionName}VM : BaseViewModel() {

}
'''
}