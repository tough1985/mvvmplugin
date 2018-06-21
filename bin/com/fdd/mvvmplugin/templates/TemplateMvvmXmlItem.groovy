package com.fdd.mvvmplugin.templates

class TemplateMvvmXmlItem {
    //XML模板
    def template =
'''<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">

    <!--Author: ${author} -->
    <!--Date: ${date} -->
    <!--Description 请输入相关描述 -->
    
    <data>

        <import type="android.view.View" />

        <variable
            name="itemEvent"
            type="${applicaitionId}.${packageName}.event.${functionName}ItemEvent" />

        <variable
            name="viewModel"
            type="${applicaitionId}.${packageName}.viewmodel.${functionName}ItemVM" />
    </data>

    <FrameLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:onClick="@{(v)->itemEvent.onClick(v, viewModel)}">

    </FrameLayout>
</layout>
'''
}