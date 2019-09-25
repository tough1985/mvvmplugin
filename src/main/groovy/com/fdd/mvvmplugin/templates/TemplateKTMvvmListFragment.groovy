package com.fdd.mvvmplugin.templates

class TemplateKTMvvmListFragment{
    // Kotlin MVVM List Fragment模板
    def template =
'''package ${applicaitionId}.${packageName}.fragment

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v7.widget.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.alibaba.android.vlayout.layout.LinearLayoutHelper
import com.fangdd.mobile.base.utils.refreshmanager.FddRefreshVLayoutManager
import com.fangdd.mobile.base.widgets.refresh.FddRefreshLayout
import com.fangdd.mobile.base.widgets.refresh.listener.OnFddRefreshLoadMoreListener
import com.fangdd.mobile.mvvmcomponent.fragment.BaseMvvmFragment
import com.fangdd.mobile.mvvmcomponent.adapter.ReDataBindingSubAdapter
import com.fangdd.mobile.mvvmcomponent.factory.SimpleViewModelFactory

import ${packageR}.BR
import ${packageR}.R
import ${packageR}.databinding.${upperModuleName}Fragment${functionName}Binding
import ${applicaitionId}.${packageName}.viewmodel.${functionName}VM
import ${applicaitionId}.${packageName}.viewmodel.${functionName}ItemVM
import ${applicaitionId}.${packageName}.event.${functionName}ItemEvent

/**
 * @Author: ${author}
 * @Date: ${date}
 * @Description:
 */
class ${functionName}Fragment : BaseMvvmFragment<${functionName}VM>(), OnFddRefreshLoadMoreListener {

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
    
    private var mRecyclerView: RecyclerView? = null

    private lateinit var vLayoutManager: FddRefreshVLayoutManager
    
    private lateinit var m${functionName}ItemAdapter: ReDataBindingSubAdapter<Object, ${functionName}ItemVM>
    
    private var mPageIndex = 0

    override fun getFragmentTag(): String {
        return TAG
    }

    override fun getViewModelType(): Class<${functionName}VM> {
        return ${functionName}VM::class.java
    }

    // ——————————————————————— ↓↓↓↓ <editor-fold desc="Lifecycle method"> ↓↓↓↓ ——————————————————————— //
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.${xmlName}, container, false)
        mBinding.viewModel = viewModel
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initRecyclerView()
        initLiveData()
    }
    
    // ———————————————————————————————————————————— ↑↑↑↑ </editor-fold> ↑↑↑↑ ———————————————————————————————————————————— //
    
    // ——————————————————————————————— ↓↓↓↓ <editor-fold desc="init method"> ↓↓↓↓ ——————————————————————————————— //
    
    /**
     * 初始化UI
     */
    private fun initView(){

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
                        baseActivity.showLoadingDialog(false)
                    }
                } else {
                    baseActivity.dismissLoadingDialog()
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
}'''
}