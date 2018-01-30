package com.fdd.mvvmplugin

import org.gradle.api.Project
import org.gradle.api.Plugin

class MvvmPlugin implements Plugin<Project> {
    void apply(Project target){
        target.extensions.create("FddMvvm", MvvmExtension)

//        def mvvmExtension = project.extensions.getByType(MvvmExtension)
//        println "MvvmPlugin : applicationId=" + mvvmExtension.applicationId
        println "MvvmPlugin : target.name=" + target.name

        target.task(target.name + '_generateMvvm', type: MvvmTask){
            group "mvvmGenerator"
        }
    }
}