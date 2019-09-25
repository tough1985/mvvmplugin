package com.fdd.mvvmplugin.templates

class TemplateKTMvvmListActivity{
    // Kotlin MVVM List Activity模板
    def template =
'''package ${applicaitionId}.${packageName}.activity

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v7.widget.RecyclerView

import com.alibaba.android.vlayout.layout.LinearLayoutHelper
import com.fangdd.mobile.base.utils.refreshmanager.FddRefreshVLayoutManager
import com.fangdd.mobile.base.widgets.refresh.FddRefreshLayout
import com.fangdd.mobile.base.widgets.refresh.listener.OnFddRefreshLoadMoreListener
import com.fangdd.mobile.mvvmcomponent.activity.BaseMvvmActivity
import com.fangdd.mobile.mvvmcomponent.adapter.ReDataBindingSubAdapter
import com.fangdd.mobile.mvvmcomponent.factory.SimpleViewModelFactory

import ${packageR}.BR
import ${packageR}.R
import ${packageR}.databinding.${upperModuleName}Activity${functionName}Binding
import ${applicaitionId}.${packageName}.viewmodel.${functionName}VM
import ${applicaitionId}.${packageName}.viewmodel.${functionName}ItemVM
import ${applicaitionId}.${packageName}.event.${functionName}ItemEvent

/**
 * @Author: ${author}
 * @Date: ${date}
 * @Description:
 */
class ${functionName}Activity : BaseMvvmActivity<${functionName}VM>(), OnFddRefreshLoadMoreListener {

    companion object {
        val TAG : String = ${functionName}Activity::class.java.simpleName
    }

    private lateinit var mBinding: ${upperModuleName}Activity${functionName}Binding
    
    private var mRecyclerView: RecyclerView? = null

    private lateinit var vLayoutManager: FddRefreshVLayoutManager

    private lateinit var m${functionName}ItemAdapter: ReDataBindingSubAdapter<Object, ${functionName}ItemVM>

    private var mPageIndex = 0

    override fun getViewModelType(): Class<${functionName}VM> {
        return ${functionName}VM::class.java
    }

    // ——————————————————————— ↓↓↓↓ <editor-fold desc="Lifecycle method"> ↓↓↓↓ ——————————————————————— //
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        mBinding = DataBindingUtil.setContentView(this, R.layout.${xmlName})
        mBinding.viewModel = viewModel

        initTitle()
        initRecyclerView()
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
    
    
    /**
     * 初始化列表
     */
    private fun initRecyclerView(){
        
        mRecyclerView = mBinding.refreshLayout.recyclerView

        vLayoutManager = FddRefreshVLayoutManager(mBinding.refreshLayout, mRecyclerView)
        vLayoutManager.setOnFddRefreshLoadmoreListener(this)

        // 初始化「 item adapter」
        init${functionName}ListAdapter()
    }
    
    /**
     * 初始化item adapter
     */
    private fun init${functionName}ListAdapter() {

        m${functionName}ItemAdapter = ReDataBindingSubAdapter(
                LinearLayoutHelper(),
                BR.viewModel,
                R.layout.${listXmlName},
                BR.itemEvent,
                ${functionName}ItemEvent(),
                SimpleViewModelFactory<${functionName}ItemVM>(${functionName}ItemVM::class.java)
        )
        vLayoutManager.addAdapter(m${functionName}ItemAdapter)
    }
    // ———————————————————————————————————————————— ↑↑↑↑ </editor-fold> ↑↑↑↑ ———————————————————————————————————————————— //
    
    // ——————————————————————— ↓↓↓↓ <editor-fold desc="initLiveData method"> ↓↓↓↓ ——————————————————————— //

    /**
     * 初始化LiveData
     */
    private fun initLiveData(){
        
        //加载状态变更
        viewModel.showLoading.observe(this, Observer { show ->
            show?.let {
                if (show) {
                    if (mPageIndex == 0 && !mBinding.refreshLayout.isRefreshing) {
                        showLoadingDialog(false)
                    }
                } else {
                    dismissLoadingDialog()
                    if (mBinding.refreshLayout.isLoading) {
                        mBinding.refreshLayout.finishLoadmore(true)
                    }
                    if (mBinding.refreshLayout.isRefreshing) {
                        mBinding.refreshLayout.finishRefresh(true)
                    }
                }
            }
        })
    }

    // ———————————————————————————————————————————— ↑↑↑↑ </editor-fold> ↑↑↑↑ ———————————————————————————————————————————— //
    
    // ——————————————————————— ↓↓↓↓ <editor-fold desc="OnFddRefreshLoadMoreListener method"> ↓↓↓↓ ——————————————————————— //

    override fun onRefresh(refreshLayout: FddRefreshLayout?) {
        mPageIndex = 0
        
    }

    override fun onLoadMore(refreshLayout: FddRefreshLayout?) {
        
    }

    // ———————————————————————————————————————————— ↑↑↑↑ </editor-fold> ↑↑↑↑ ———————————————————————————————————————————— //
}
'''
}