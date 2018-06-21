package com.fdd.mvvmplugin.templates

class TemplateMvvmActivity {
    //Contract模板
    def template = 
'''package ${applicaitionId}.${packageName}.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import ${applicaitionId}.R;
import com.fangdd.mobile.mvvmcomponent.activity.BaseMvvmActivity;
import ${applicaitionId}.databinding.${upperModuleName}Activity${functionName}Binding;
import ${applicaitionId}.${packageName}.viewmodel.${functionName}VM;

/**
 * @Author: ${author}
 * @Date: ${date}
 * @Description:
 */
public class ${functionName}Activity extends BaseMvvmActivity<${functionName}VM> {

    public static final String TAG = ${functionName}Activity.class.getSimpleName();

    private ${upperModuleName}Activity${functionName}Binding mBinding;

    @Override
    public Class<${functionName}VM> getViewModelType() {
        return ${functionName}VM.class;
    }

    // ——————————————————————— ↓↓↓↓ <editor-fold desc="Lifecycle method"> ↓↓↓↓ ——————————————————————— //
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.${xmlName});
        mBinding.setViewmodel(getViewModel());

        initTitle();
        
        initLiveData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
   
    // ———————————————————————————————————————————— ↑↑↑↑ </editor-fold> ↑↑↑↑ ———————————————————————————————————————————— //
    
    // ——————————————————————————————— ↓↓↓↓ <editor-fold desc="init method"> ↓↓↓↓ ——————————————————————————————— //
    /**
     * 初始化标题栏
     */
    public void initTitle(){
        initTitleBar(mBinding.titleBar, false);
        String title = "";
        setTitleBarWithTitleAndDefaultLeft(title, null);
    }
    // ———————————————————————————————————————————— ↑↑↑↑ </editor-fold> ↑↑↑↑ ———————————————————————————————————————————— //
    
    // ——————————————————————— ↓↓↓↓ <editor-fold desc="initLiveData method"> ↓↓↓↓ ——————————————————————— //
    /**
     * 初始化LiveData
     */
    private void initLiveData(){
        
    }
    // ———————————————————————————————————————————— ↑↑↑↑ </editor-fold> ↑↑↑↑ ———————————————————————————————————————————— //
}
'''
}