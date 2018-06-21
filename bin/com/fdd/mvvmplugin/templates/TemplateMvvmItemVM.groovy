package com.fdd.mvvmplugin.templates

class TemplateMvvmItemVM {
    //ViewModel模板
    def template =
'''package ${applicaitionId}.${packageName}.viewmodel;

import android.databinding.ObservableField;

import com.fangdd.mobile.mvvmcomponent.viewmodel.BaseAdapterViewModel;

/**
 * @Author: ${author}
 * @Date: ${date}
 * @Description:
 */
public class ${functionName}ItemVM extends BaseAdapterViewModel<Object> {

    public ObservableField<Object> itemOf = new ObservableField<>();
    
    //在列表中的索引
    private int index;
    
    @Override
    public void setData(int index, Object itemObject) {
        this.index = index;
        itemOf.set(itemObject);
    }
    
    public int getIndex() {
        return index;
    }
    
    
//    private ReDataBindingSubAdapter<Object, ${functionName}ItemVM> m${functionName}ItemAdapter;
//    /**
//     * 初始化item adapter
//     */
//    private void init${functionName}ListAdapter() {
//        m${functionName}ItemAdapter = new ReDataBindingSubAdapter<Object, ${functionName}ItemVM>(
//                new LinearLayoutHelper(),
//                BR.viewModel,
//                R.layout.${listXmlName},
//                BR.itemEvent,
//                new ${functionName}ItemEvent(),
//                new SimpleViewModelFactory<${functionName}ItemVM>(${functionName}ItemVM.class));
//
//    }

}

'''
}

