package com.fdd.mvvmplugin.templates

class TemplateMvvmFragment {
    //Contract模板
    def template = 
'''package ${applicaitionId}.${packageName}.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fangdd.mobile.mvvmcomponent.fragment.BaseMvvmFragment;
import ${packageR}.databinding.Fragment${functionName}Binding;
import ${applicaitionId}.${packageName}.viewmodel.${functionName}VM;

/**
 * @Author: ${author}
 * @Date: ${date}
 * @Description:
 */
public class ${functionName}Fragment extends BaseMvvmFragment<${functionName}VM> {

    public static final String TAG = ${functionName}Fragment.class.getSimpleName();

    private Fragment${functionName}Binding mBinding;

    private ${functionName}VM mViewModel;

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = Fragment${functionName}Binding.inflate(inflater, container, false);
        mBinding.setViewModel(getViewModel());
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}'''
}