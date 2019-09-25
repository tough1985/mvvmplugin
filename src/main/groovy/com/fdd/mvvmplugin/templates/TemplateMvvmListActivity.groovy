package com.fdd.mvvmplugin.templates

class TemplateMvvmListActivity {

    def template =
'''package ${applicaitionId}.${packageName}.activity;

import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.fangdd.mobile.base.utils.refreshmanager.FddRefreshVLayoutManager;
import com.fangdd.mobile.base.widgets.refresh.FddRefreshLayout;
import com.fangdd.mobile.base.widgets.refresh.listener.OnFddRefreshLoadMoreListener;
import com.fangdd.mobile.mvvmcomponent.activity.BaseMvvmActivity;
import com.fangdd.mobile.mvvmcomponent.adapter.ReDataBindingSubAdapter;
import com.fangdd.mobile.mvvmcomponent.factory.SimpleViewModelFactory;

import ${packageR}.BR;
import ${packageR}.R;
import ${packageR}.databinding.${upperModuleName}Activity${functionName}Binding;
import ${applicaitionId}.${packageName}.viewmodel.${functionName}VM;
import ${applicaitionId}.${packageName}.viewmodel.${functionName}ItemVM;
import ${applicaitionId}.${packageName}.event.${functionName}ItemEvent;

/**
 * @Author: ${author}
 * @Date: ${date}
 * @Description:
 */
public class ${functionName}Activity extends BaseMvvmActivity<${functionName}VM> implements OnFddRefreshLoadMoreListener {

    public static final String TAG = ${functionName}Activity.class.getSimpleName();

    private ${upperModuleName}Activity${functionName}Binding mBinding;
    
    private RecyclerView mRecyclerView;

    private FddRefreshVLayoutManager vLayoutManager;

    //
    private ReDataBindingSubAdapter<Object, ${functionName}ItemVM> m${functionName}ItemAdapter;
    
    private int mPageIndex = 1;

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
        initRecyclerView();
        
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
    
    
    /**
     * 初始化列表
     */
    public void initRecyclerView(){
        init${functionName}ListAdapter();
        
        mRecyclerView = mBinding.refreshLayout.getRecyclerView();

        vLayoutManager = new FddRefreshVLayoutManager(mBinding.refreshLayout, mRecyclerView);
        vLayoutManager.setOnFddRefreshLoadmoreListener(this);
        vLayoutManager.addAdapter(m${functionName}ItemAdapter);
    }
    
    /**
     * 初始化item adapter
     */
    private void init${functionName}ListAdapter() {
        m${functionName}ItemAdapter = new ReDataBindingSubAdapter<Object, ${functionName}ItemVM>(
                new LinearLayoutHelper(),
                BR.viewModel,
                R.layout.${listXmlName},
                BR.itemEvent,
                new ${functionName}ItemEvent(),
                new SimpleViewModelFactory<${functionName}ItemVM>(${functionName}ItemVM.class));

    }
    // ———————————————————————————————————————————— ↑↑↑↑ </editor-fold> ↑↑↑↑ ———————————————————————————————————————————— //
    
    // ——————————————————————— ↓↓↓↓ <editor-fold desc="initLiveData method"> ↓↓↓↓ ——————————————————————— //
    /**
     * 初始化LiveData
     */
    private void initLiveData(){
        
        //加载状态变更
        getViewModel().getShowLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean isShow) {

                if (isShow) {

                    if (mPageIndex == 1 && !mBinding.refreshLayout.isRefreshing()) {
                        showLoadingDialog(false);
                    }

                } else {

                    dismissLoadingDialog();
                    if (mBinding.refreshLayout.isLoading()) {
                        mBinding.refreshLayout.finishLoadmore(true);
                    }
                    if (mBinding.refreshLayout.isRefreshing()) {
                        mBinding.refreshLayout.finishRefresh(true);
                    }
                }
            }
        });
    }
    // ———————————————————————————————————————————— ↑↑↑↑ </editor-fold> ↑↑↑↑ ———————————————————————————————————————————— //
    
    // ——————————————————————— ↓↓↓↓ <editor-fold desc="OnFddRefreshLoadMoreListener method"> ↓↓↓↓ ——————————————————————— //
    @Override
    public void onLoadMore(FddRefreshLayout refreshLayout) {
        
    }

    @Override
    public void onRefresh(FddRefreshLayout refreshLayout) {
        mPageIndex = 1;
    }
    // ———————————————————————————————————————————— ↑↑↑↑ </editor-fold> ↑↑↑↑ ———————————————————————————————————————————— //
}
'''
}