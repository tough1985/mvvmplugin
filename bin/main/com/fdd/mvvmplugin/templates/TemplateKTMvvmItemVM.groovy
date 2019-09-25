package com.fdd.mvvmplugin.templates

class TemplateKTMvvmItemVM extends TemplateMvvmItemVM {
    // Kotlin MVVM Item ViewModel模板
    def template =
'''package ${applicaitionId}.${packageName}.viewmodel

import android.databinding.ObservableField

import com.fangdd.mobile.mvvmcomponent.viewmodel.BaseAdapterViewModel

/**
 * @Author: ${author}
 * @Date: ${date}
 * @Description:
 */
class ${functionName}ItemVM : BaseAdapterViewModel<Object>() {

    val itemOf = ObservableField<>()
    
    //在列表中的索引
    var index: Int = 0
    
    override fun setData(index: Int, itemObject: Object) {
        this.index = index
        itemOf.set(itemObject)
    }
    
//    private lateinit var m${functionName}ItemAdapter: ReDataBindingSubAdapter<Object, ${functionName}ItemVM>
//    /**
//     * 初始化item adapter
//     */
//    private fun init${functionName}ListAdapter() {
//        m${functionName}ItemAdapter = ReDataBindingSubAdapter(
//                LinearLayoutHelper(),
//                BR.viewModel,
//                R.layout.${listXmlName},
//                BR.itemEvent,
//                ${functionName}ItemEvent(),
//                SimpleViewModelFactory<${functionName}ItemVM>(${functionName}ItemVM.class));
//        vLayoutManager.addAdapter(m${functionName}ItemAdapter)
//    }

}
'''
}

