package com.fdd.mvvmplugin

import com.fdd.mvvmplugin.templates.TemplateMvvmActivity
import com.fdd.mvvmplugin.templates.TemplateMvvmFragment
import com.fdd.mvvmplugin.templates.TemplateMvvmListActivity
import com.fdd.mvvmplugin.templates.TemplateMvvmListFragment
import com.fdd.mvvmplugin.templates.TemplateMvvmXmlFragment
import com.fdd.mvvmplugin.templates.TemplateMvvmXmlListActivity
import com.fdd.mvvmplugin.templates.TemplateMvvmXmlActivity
import com.fdd.mvvmplugin.templates.TemplateViewModel
import com.fdd.mvvmplugin.templates.TemplateMvvmXmlItem
import com.fdd.mvvmplugin.templates.TemplateMvvmItemEvent
import com.fdd.mvvmplugin.templates.TemplateMvvmItemVM
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction


class MvvmTask extends DefaultTask {

    @TaskAction
    def generateMvvpFile() {

        def mvvmExtension = project.extensions.getByType(MvvmExtension)
        //应用ID
        def applicationId = mvvmExtension.applicationId;
        //包名
        def packageName = mvvmExtension.packageName
        //功能名
        def functionName = mvvmExtension.functionName
        //作者
        def author = mvvmExtension.author
        //view是activity 还是 fragment
        def isViewActivity = mvvmExtension.isViewActivity
        //是否需要添加listItem
        def isList = mvvmExtension.isList
        //模块名
        def moduleName = mvvmExtension.moduleName

        def xmlName = PluginUtil.camel2Underline(functionName)

        TemplateMvvmActivity templateMvvmActivity = new TemplateMvvmActivity()
        TemplateMvvmFragment templateMvvmFragment = new TemplateMvvmFragment()

        TemplateMvvmXmlActivity templateMvvmXml = new TemplateMvvmXmlActivity()
        TemplateMvvmXmlFragment templateMvvmXmlFragment = new TemplateMvvmXmlFragment()

        TemplateViewModel templateViewModel = new TemplateViewModel()

        TemplateMvvmItemVM templateMvvmItemVM = new TemplateMvvmItemVM()
        TemplateMvvmXmlItem templateItemXml = new TemplateMvvmXmlItem()
        TemplateMvvmItemEvent templateMvvmItemEvent = new TemplateMvvmItemEvent()

        TemplateMvvmListActivity templateMvvmListActivity = new TemplateMvvmListActivity()
        TemplateMvvmXmlListActivity templateMvvmListActivityXml = new TemplateMvvmXmlListActivity()
        TemplateMvvmListFragment templateMvvmListFragment = new TemplateMvvmListFragment();

        println "generateMvvmFile : functionName=" + functionName
        println "generateMvvmFile : xmlName=" + xmlName

        if (isViewActivity){
            xmlName = "activity_" + xmlName
        } else {
            xmlName = "fragment_" + xmlName
        }

        def mvvmArray = [

                [
                        template : templateViewModel.template,
                        type : "viewmodel",
                        fileName : "VM.java"
                ]
        ]


        if (isViewActivity){    // 如果是Activity

            if (isList){    //如果是列表
                mvvmArray.add([
                        template : templateMvvmListActivity.template,
                        type : "activity",
                        fileName : "Activity.java",
                ])

                mvvmArray.add([
                        template : templateMvvmListActivityXml.template,
                        type : "xml",
                        fileName : ".xml"
                ])

            } else {
                mvvmArray.add([
                        template : templateMvvmActivity.template,
                        type : "activity",
                        fileName : "Activity.java",
                ])

                mvvmArray.add([
                        template : templateMvvmXml.template,
                        type : "xml",
                        fileName : ".xml"
                ])
            }

        } else {    // 如果是Fragment

            if (isList) {    //如果是列表
                mvvmArray.add([
                        template : templateMvvmListFragment.template,
                        type : "fragment",
                        fileName : "Fragment.java",
                ])
                mvvmArray.add([
                        template : templateMvvmListActivityXml.template,
                        type : "xml",
                        fileName : ".xml"
                ])
            } else {
                mvvmArray.add([
                        template : templateMvvmFragment.template,
                        type : "fragment",
                        fileName : "Fragment.java",
                ])
                mvvmArray.add([
                        template : templateMvvmXmlFragment.template,
                        type : "xml",
                        fileName : ".xml"
                ])
            }


        }

        if (isList){
            mvvmArray.add([
                    template : templateItemXml.template,
                    type : "itemxml",
                    fileName : ".xml"
            ])
            mvvmArray.add([
                    template : templateMvvmItemVM.template,
                    type : "viewmodel",
                    fileName : "ItemVM.java"
            ])
            mvvmArray.add([
                    template : templateMvvmItemEvent.template,
                    type : "event",
                    fileName : "ItemEvent.java"
            ])
        }

        String dateString = PluginUtil.getFormatTime()

        //listItem xml文件名称
        def listXmlName = generateItemXmlName(moduleName, functionName)

        //xml文件名称
        if (moduleName != null && !moduleName.equals("")){
            xmlName = PluginUtil.camel2Underline(moduleName) + "_" + xmlName
        }

        def upperModuleName = upperFirst(moduleName)

        def mBinding = [
                applicaitionId  : applicationId,
                packageName     : packageName,
                functionName    : functionName,
                date            : dateString,
                author          : author,
                xmlName         : xmlName,
                moduleName      : moduleName,
                listXmlName     : listXmlName,
                upperModuleName : upperModuleName
        ]

        println "mvvmExtension.applicationId=" + mvvmExtension.applicationId
        def packageFilePath = mvvmExtension.applicationId.replace(".", "/")

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

        def template = PluginUtil.makeTemplate(map.template, binding)
        def path
        def fileName

        if(map.type.equals("xml")){ //如果是xml文件
            path = xmlPath

            fileName = path + "/" + binding.xmlName + map.fileName
        } else if (map.type.equals("itemxml")){
            path = xmlPath

            def listXmlName = generateItemXmlName(binding.moduleName, binding.functionName)

            fileName = path + "/" + listXmlName + map.fileName
        } else {
            path = fullPath + "/" + binding.packageName + "/" + map.type
            fileName = path + "/" + binding.functionName + map.fileName
        }

        PluginUtil.generateFile(path, fileName, template)
    }



    /**
     * 生成item xml文件名称
     * @param moduleName
     * @param functionName
     * @return
     */
    String generateItemXmlName(def moduleName, def functionName){
        def xmlModuleName
        if (moduleName != null && !moduleName.equals("")){
            xmlModuleName = PluginUtil.camel2Underline(moduleName) + "_"
        }
        def listXmlName = xmlModuleName + "item_" +PluginUtil.camel2Underline(functionName)
        return listXmlName
    }

    String upperFirst(String s){
        if (s == null || s.equals("")){
            return ""
        }
        String newStr = s.substring(0, 1).toUpperCase() + s.substring(1)
        return newStr
    }

}