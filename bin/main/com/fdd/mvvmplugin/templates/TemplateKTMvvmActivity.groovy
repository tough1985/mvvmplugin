package com.fdd.mvvmplugin.templates

class TemplateKTMvvmActivity extends TemplateMvvmActivity {
    // Kotlin MVVM Activity模板
    def template = 
'''package ${applicaitionId}.${packageName}.activity

import android.databinding.DataBindingUtil
import android.os.Bundle

import ${packageR}.R
import ${packageR}.databinding.${upperModuleName}Activity${functionName}Binding
import com.fangdd.mobile.mvvmcomponent.activity.BaseMvvmActivity
import ${applicaitionId}.${packageName}.viewmodel.${functionName}VM

/**
 * @Author: ${author}
 * @Date: ${date}
 * @Description:
 */
class ${functionName}Activity : BaseMvvmActivity<${functionName}VM> {

    companion object {
        val TAG : String = ${functionName}Activity::class.java.simpleName
    }

    private lateinit var mBinding: ${upperModuleName}Activity${functionName}Binding

    override fun getViewModelType(): Class<${functionName}VM> {
        return ${functionName}VM.class
    }

    // ——————————————————————— ↓↓↓↓ <editor-fold desc="Lifecycle method"> ↓↓↓↓ ——————————————————————— //
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        mBinding = DataBindingUtil.setContentView(this, R.layout.${xmlName})
        mBinding.viewmodel = viewModel

        initTitle()
        
        initLiveData()
    }

    override fun onResume() {
        super.onResume()
    }
   
    // ———————————————————————————————————————————— ↑↑↑↑ </editor-fold> ↑↑↑↑ ———————————————————————————————————————————— //
    
    // ——————————————————————————————— ↓↓↓↓ <editor-fold desc="init method"> ↓↓↓↓ ——————————————————————————————— //
    /**
     * 初始化标题栏
     */
    private fun initTitle(){
        initTitleBar(mBinding.titleBar, false)
        val title = ""
        setTitleBarWithTitleAndDefaultLeft(title, null)
    }
    // ———————————————————————————————————————————— ↑↑↑↑ </editor-fold> ↑↑↑↑ ———————————————————————————————————————————— //
    
    // ——————————————————————— ↓↓↓↓ <editor-fold desc="initLiveData method"> ↓↓↓↓ ——————————————————————— //
    /**
     * 初始化LiveData
     */
    private fun initLiveData(){

        viewModel.showLoading.observe(this, Observer { show ->
            show?.let {
                if (show) {
                    showLoadingDialog(false)
                } else {
                    dismissLoadingDialog()
                }
            }
        })
    }
    // ———————————————————————————————————————————— ↑↑↑↑ </editor-fold> ↑↑↑↑ ———————————————————————————————————————————— //
}
'''
}