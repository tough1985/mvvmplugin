package com.fdd.mvvmplugin

import java.text.SimpleDateFormat
import java.util.regex.Matcher
import java.util.regex.Pattern


class PluginUtil {

    /**
     * 加载模板
     */
    static def makeTemplate(def template, def binding){

        // File f = new File("./buildSrc/mvvmplugin/template/" + templateName)

        def engine = new groovy.text.GStringTemplateEngine()

        return engine.createTemplate(template).make(binding)
    }

    /**
     * 生成文件
     * @param path
     * @param fileName
     * @param template
     */
    static void generateFile(def path, def fileName, def template){
        //验证文件路径，没有则创建
        validatePath(path)

        File mvvmFile = new File(fileName)

        //如果文件已经存在，直接返回
        if(!mvvmFile.exists()){
            mvvmFile.createNewFile()
        } else {
            return
        }

        FileOutputStream out = new FileOutputStream(mvvmFile, false)
        out.write(template.toString().getBytes("utf-8"))
        out.close()
    }

    /**
     * 验证文件路径，没有则创建
     * @param path
     */
    static void validatePath(def path){
        File mvvmFileDir = new File(path)

        if(!mvvmFileDir.exists()){
            mvvmFileDir.mkdirs()
        }
    }

    /**
     * 格式化当前时间
     * @return
     */
    static def getFormatTime(){
        Date date = new Date()

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return formatter.format(date)
    }

    /**
     * 驼峰法转下划线
     * @param line 源字符串
     * @return 转换后的字符串
     */
    static String camel2Underline(String line){
        if(line==null||"".equals(line)){
            return ""
        }
        line=String.valueOf(line.charAt(0)).toUpperCase().concat(line.substring(1))
        StringBuffer sb=new StringBuffer()
        Pattern pattern=Pattern.compile("[A-Z]([a-z\\d]+)?")
        Matcher matcher=pattern.matcher(line)
        while(matcher.find()){
            String word=matcher.group()
            sb.append(word.toLowerCase())
            sb.append(matcher.end()==line.length()?"":"_")
        }
        return sb.toString()
    }
}