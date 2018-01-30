package com.fdd.mvvmplugin

class TemplateMvvmActivity {
    //Contract模板
    def template = 
'''package ${applicaitionId}.${packageName}.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import ${applicaitionId}.R;
import com.fangdd.mobile.mvvmcomponent.activity.BaseMvvmActivity;
import ${applicaitionId}.databinding.Activity${functionName}Binding;
import ${applicaitionId}.${packageName}.viewmodel.${functionName}VM;

/**
 * @Author: ${author}
 * @Date: ${date}
 * @Description:
 */
public class ${functionName}Activity extends BaseMvvmActivity<${functionName}VM> {

    public static final String TAG = ${functionName}Activity.class.getSimpleName();

    @Override
    public Class<${functionName}VM> getViewModelType() {
        return ${functionName}VM.class;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Activity${functionName}Binding binding = DataBindingUtil.setContentView(this, R.layout.${xmlName});
        binding.setViewmodel(getViewModel());

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
'''
}