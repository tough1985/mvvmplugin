package com.fdd.mvvmplugin.templates

class TemplateMvvmXmlActivity {
    //XML模板
    def template = 
'''<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">
    
    <!--Author: ${author} -->
    <!--Date: ${date} -->
    <!--Description 请输入相关描述 -->
    
    <data>
        <variable
            name="viewmodel"
            type="${applicaitionId}.${packageName}.viewmodel.${functionName}VM"/>
    </data>
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">
        
        <!--顶部标题-->
        <com.fangdd.mobile.basecore.widget.BaseTitleBar
            android:id="@+id/title_bar"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"/>
            
    </LinearLayout>
</layout>'''
}