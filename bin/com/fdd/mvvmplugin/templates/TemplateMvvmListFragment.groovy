package com.fdd.mvvmplugin.templates

class TemplateMvvmListFragment{
    //Contract模板
    def template =
'''package ${applicaitionId}.${packageName}.fragment;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.fangdd.mobile.base.utils.refreshmanager.FddRefreshVLayoutManager;
import com.fangdd.mobile.base.widgets.refresh.FddRefreshLayout;
import com.fangdd.mobile.base.widgets.refresh.listener.OnFddRefreshLoadMoreListener;
import com.fangdd.mobile.mvvmcomponent.fragment.BaseMvvmFragment;
import com.fangdd.mobile.mvvmcomponent.adapter.ReDataBindingSubAdapter;
import com.fangdd.mobile.mvvmcomponent.factory.SimpleViewModelFactory;

import ${applicaitionId}.BR;
import ${applicaitionId}.R;
import ${applicaitionId}.databinding.${upperModuleName}Fragment${functionName}Binding;
import ${applicaitionId}.${packageName}.viewmodel.${functionName}VM;
import ${applicaitionId}.${packageName}.viewmodel.${functionName}ItemVM;
import ${applicaitionId}.${packageName}.event.${functionName}ItemEvent;

/**
 * @Author: ${author}
 * @Date: ${date}
 * @Description:
 */
public class ${functionName}Fragment extends BaseMvvmFragment<${functionName}VM> implements OnFddRefreshLoadMoreListener {

    public static final String TAG = ${functionName}Fragment.class.getSimpleName();

    private ${upperModuleName}Fragment${functionName}Binding mBinding;

    private ${functionName}VM mViewModel;
    
    private RecyclerView mRecyclerView;

    private FddRefreshVLayoutManager vLayoutManager;
    
    //
    private ReDataBindingSubAdapter<Object, ${functionName}ItemVM> m${functionName}ItemAdapter;
    
    private int mPageIndex = 1;

    public static ${functionName}Fragment newInstance(){
        ${functionName}Fragment fragment = new ${functionName}Fragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public String getFragmentTag() {
        return TAG;
    }

    @Override
    public Class<${functionName}VM> getViewModelType() {
        return ${functionName}VM.class;
    }

    // ——————————————————————— ↓↓↓↓ <editor-fold desc="Lifecycle method"> ↓↓↓↓ ——————————————————————— //
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = ${upperModuleName}Fragment${functionName}Binding.inflate(inflater, container, false);
        mBinding.setViewmodel(getViewModel());
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initRecyclerView();
        initLiveData();
    }
    
    // ———————————————————————————————————————————— ↑↑↑↑ </editor-fold> ↑↑↑↑ ———————————————————————————————————————————— //
    
    // ——————————————————————————————— ↓↓↓↓ <editor-fold desc="init method"> ↓↓↓↓ ——————————————————————————————— //
    
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
                        getBaseActivity().showLoadingDialog(false);
                    }

                } else {
                    getBaseActivity().dismissLoadingDialog();
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
}'''
}