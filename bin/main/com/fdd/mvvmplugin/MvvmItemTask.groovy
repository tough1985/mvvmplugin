package com.fdd.mvvmplugin

import com.fdd.mvvmplugin.templates.TemplateKTMvvmActivity
import com.fdd.mvvmplugin.templates.TemplateKTMvvmFragment
import com.fdd.mvvmplugin.templates.TemplateKTMvvmItemEvent
import com.fdd.mvvmplugin.templates.TemplateKTMvvmItemVM
import com.fdd.mvvmplugin.templates.TemplateKTMvvmListActivity
import com.fdd.mvvmplugin.templates.TemplateKTMvvmListFragment
import com.fdd.mvvmplugin.templates.TemplateKTViewModel
import com.fdd.mvvmplugin.templates.TemplateMvvmXmlItem
import com.fdd.mvvmplugin.templates.TemplateMvvmItemEvent
import com.fdd.mvvmplugin.templates.TemplateMvvmItemVM
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction


class MvvmItemTask extends DefaultTask {

    @TaskAction
    def generateMvvpItemFile() {

        def mvvmExtension = project.extensions.getByType(MvvmExtension)
        //应用ID
        def applicationId = mvvmExtension.applicationId
        //包名
        def packageName = mvvmExtension.packageName
        //功能名
        def functionName = mvvmExtension.functionName
        //作者
        def author = mvvmExtension.author
        //模块名
        def moduleName = mvvmExtension.moduleName

        def xmlName = PluginUtil.camel2Underline(functionName)

        def packageR = mvvmExtension.packageR

        def isKotlin = mvvmExtension.isKotlin

        def postfixName

        if (isKotlin) {
            postfixName = ".kt"
        } else {
            postfixName = ".java"
        }

        def templateMvvmItemVM
        def templateMvvmItemEvent
        TemplateMvvmXmlItem templateItemXml = new TemplateMvvmXmlItem()

        if (isKotlin) {
            templateMvvmItemVM = new TemplateKTMvvmItemVM()
            templateMvvmItemEvent = new TemplateKTMvvmItemEvent()
        } else {
            templateMvvmItemVM = new TemplateMvvmItemVM()
            templateMvvmItemEvent = new TemplateMvvmItemEvent()
        }

        println "generateMvvmFile : functionName=" + functionName
        println "generateMvvmFile : xmlName=" + xmlName


        def mvvmArray = [
                [
                        template : templateItemXml.template,
                        type : "xml",
                        fileName : ".xml"
                ],
                [
                        template : templateMvvmItemVM.template,
                        type : "viewmodel",
                        fileName : "ItemVM" + postfixName
                ],
                [
                        template : templateMvvmItemEvent.template,
                        type : "event",
                        fileName : "ItemEvent" + postfixName
                ]
        ]


        String dateString = PluginUtil.getFormatTime()

        if (moduleName != null && !moduleName.equals("")){
            moduleName = PluginUtil.camel2Underline(moduleName) + "_"
        }
        def listXmlName = moduleName + "item_" + xmlName

        def mBinding = [
                applicaitionId  : applicationId,
                packageName     : packageName,
                functionName    : functionName,
                date            : dateString,
                author          : author,
                xmlName         : xmlName,
                moduleName      : moduleName,
                listXmlName     : listXmlName,
                packageR        : packageR
        ];

        println "mvvmExtension.applicationId=" + mvvmExtension.applicationId
        def packageFilePath = mvvmExtension.applicationId.replace(".", "/");

        //代码文件根路径
        def fullPath = project.projectDir.toString() + "/src/main/java/" + packageFilePath

        //xmlPath文件路径
        def xmlPath = project.projectDir.toString() + "/src/main/res/layout"

        generateMvpFile(mvvmArray, mBinding, fullPath, xmlPath)

    }

    void generateMvpFile(def mvvmArray, def binding, def fullPath, def xmlPath){

        for(int i = 0; i < mvvmArray.size(); i++){
            preGenerateFile(mvvmArray[i], binding, fullPath, xmlPath)
        }
    }

    void preGenerateFile(def map, def binding, def fullPath, def xmlPath){
        // File mvpContractTemplateFile = new File("template/" + map.templateFileName)

//        println "preGenerateFile : map.templateName=" + map.templateName
//        println "preGenerateFile : map.type=" + map.type
//        println "preGenerateFile : map.fileName=" + map.fileName
        def template = PluginUtil.makeTemplate(map.template, binding)
        def path
        def fileName

        if(map.type.equals("xml")){ //如果是xml文件
            path = xmlPath

            fileName = path + "/" + binding.listXmlName + map.fileName

        } else {
            path = fullPath + "/" + binding.packageName + "/" + map.type
            fileName = path + "/" + binding.functionName + map.fileName
        }

        PluginUtil.generateFile(path, fileName, template)
    }

}