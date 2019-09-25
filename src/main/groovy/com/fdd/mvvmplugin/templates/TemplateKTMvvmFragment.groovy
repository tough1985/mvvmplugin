package com.fdd.mvvmplugin.templates

class TemplateKTMvvmFragment extends TemplateMvvmFragment {
    // Kotlin MVVM Fragment模板
    def template = 
'''package ${applicaitionId}.${packageName}.fragment

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.Nullable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import ${packageR}.R
import ${packageR}.databinding.${upperModuleName}Fragment${functionName}Binding
import com.fangdd.mobile.mvvmcomponent.fragment.BaseMvvmFragment
import ${applicaitionId}.${packageName}.viewmodel.${functionName}VM

/**
 * @Author: ${author}
 * @Date: ${date}
 * @Description:
 */
class ${functionName}Fragment : BaseMvvmFragment<${functionName}VM> {

    companion object {
        val TAG : String = ${functionName}Fragment::class.java.simpleName

        @JvmStatic
        fun newInstance() =
                ${functionName}Fragment().apply {
                    arguments = Bundle().apply {
                        
                    }
                }
    }

    private lateinit var mBinding: ${upperModuleName}Fragment${functionName}Binding

    override fun getFragmentTag(): String {
        return TAG
    }

    override fun getViewModelType(): Class<${functionName}VM> {
        return ${functionName}VM.class
    }

    // ——————————————————————— ↓↓↓↓ <editor-fold desc="Lifecycle method"> ↓↓↓↓ ——————————————————————— //

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.${xmlName}, container, false)
        mBinding.viewmodel = viewModel
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        initLiveData()
    }

    // ———————————————————————————————————————————— ↑↑↑↑ </editor-fold> ↑↑↑↑ ———————————————————————————————————————————— //

    // ——————————————————————————————— ↓↓↓↓ <editor-fold desc="init method"> ↓↓↓↓ ——————————————————————————————— //
    /**
     * 初始化UI
     */
    private fun initView(){

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
                    baseActivity.showLoadingDialog(false)
                } else {
                    baseActivity.dismissLoadingDialog()
                }
            }
        })

    }
    // ———————————————————————————————————————————— ↑↑↑↑ </editor-fold> ↑↑↑↑ ———————————————————————————————————————————— //

}'''
}