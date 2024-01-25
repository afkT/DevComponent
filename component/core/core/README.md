
# About

核心基础整合库 ( 内部集成 core libs, 对外依赖该 module 即可 )

# 依赖信息

```groovy
dependencies {

    // ===================
    // = core - 核心开发库 =
    // ===================

    // 通用环境配置切换库
    api project(':core_lib_environment')

    // ================
    // = Dev 系列开发库 =
    // ================

    // https://github.com/afkT/DevUtils

    // DevApp - Android 工具类库
    api deps.dev.dev_app

    // DevAssist - 封装逻辑代码, 实现多个快捷功能辅助类、以及 Engine 兼容框架等
    api deps.dev.dev_assist

    // DevBase - Base ( Activity、Fragment )、MVP、ViewBinding、ContentLayout 基类库
    api deps.dev.dev_base

    // DevBaseMVVM - MVVM ( ViewDataBinding + ViewModel ) 基类库
    api deps.dev.dev_base_mvvm

    // DevMVVM - DataBinding 工具类库
    api deps.dev.dev_mvvm

    // DevEngine - 第三方框架解耦、一键替换第三方库、同类库多 Engine 组件化混合使用
    api deps.dev.dev_engine

    // DevWidget - 自定义 View UI 库
    api deps.dev.dev_widget

    // DevEnvironment - Android 环境配置切换库
    api deps.dev.dev_environment
//    if (isRelease) {
//        kapt deps.dev.dev_environment_compiler_release
//    } else {
//        kapt deps.dev.dev_environment_compiler
//    }

    // DevHttpCapture - OkHttp 抓包工具库
    api deps.dev.dev_http_capture
    // DevHttpCaptureCompiler - OkHttp 抓包工具库 ( 可视化功能 )
    if (isRelease) {
        api deps.dev.dev_http_capture_compiler_release
    } else {
        api deps.dev.dev_http_capture_compiler
    }

    // DevHttpManager - OkHttp 管理库 ( Retrofit 多 BaseUrl 管理、Progress 监听 )
    api deps.dev.dev_http_manager

    // DevRetrofit - Retrofit + Kotlin Coroutines 封装
    api deps.dev.dev_retrofit

    // ==========
    // = kotlin =
    // ==========

    api deps.kotlin.stdlib
    api deps.kotlin.core
    api deps.kotlin.coroutines
    api deps.kotlin.lifecycle_runtime
    api deps.kotlin.lifecycle_viewmodel
    api deps.kotlin.lifecycle_livedata
    api deps.kotlin.lifecycle_viewmodel_savedstate
    api deps.kotlin.lifecycle_common_java8
    api deps.kotlin.fragment
    api deps.kotlin.activity
    api deps.kotlin.navigation_fragment
    api deps.kotlin.navigation_ui

    // ============
    // = androidx =
    // ============

    api deps.androidx.design
    api deps.androidx.appcompat
    api deps.androidx.appcompat_resources
    api deps.androidx.cardview
    api deps.androidx.recyclerview
    api deps.androidx.multidex
    api deps.androidx.constraint_layout
    api deps.androidx.viewpager2
    api deps.androidx.work_runtime
    api deps.androidx.fragment
    api deps.androidx.activity
    api deps.androidx.palette
    api deps.androidx.flexbox
    api deps.androidx.startup

    // ====================
    // = libs - 第三方库依赖 =
    // ====================

    // okio https://github.com/square/okio
    api deps.lib.okio
    // OkHttp3 网络请求框架 https://github.com/square/okhttp
    api deps.lib.okhttp3
    // Retrofit 网络请求库  https://github.com/square/retrofit
    api deps.lib.retrofit
    // Retrofit Gson Converter  https://github.com/square/retrofit/tree/master/retrofit-converters/gson
    api deps.lib.retrofit_gson
    // LiveEventBus 消息总线 https://github.com/JeremyLiao/LiveEventBus
    api deps.lib.live_eventbus_fork
    // 基于 mmap 的高性能通用 key-value 组件 https://github.com/Tencent/MMKV/blob/master/readme_cn.md
    api deps.lib.mmkv
    // 今日头条屏幕适配方案终极版 https://github.com/JessYanCoding/AndroidAutoSize/blob/master/README-zh.md
    api deps.lib.autosize
    // 动画库 https://github.com/airbnb/lottie-android
    api deps.lib.lottie
    // MVVM Adapter Binding https://github.com/evant/binding-collection-adapter
    api deps.widget.binding_collection_adapter
    api deps.widget.binding_collection_adapter_recyclerview
    api deps.widget.binding_collection_adapter_viewpager2
    // 下拉刷新框架 https://github.com/scwang90/SmartRefreshLayout
    api deps.widget.smartrefreshlayout
    api deps.widget.smartrefresh_header_classics // 经典刷新头
//    api deps.widget.smartrefresh_header_radar // 雷达刷新头
//    api deps.widget.smartrefresh_header_falsify // 虚拟刷新头
//    api deps.widget.smartrefresh_header_material // 谷歌刷新头
//    api deps.widget.smartrefresh_header_two_level // 二级刷新头
//    api deps.widget.smartrefresh_footer_ball // 球脉冲加载
    api deps.widget.smartrefresh_footer_classics // 经典加载

    // ====================
    // = 性能检测、排查相关库 =
    // ====================

    // 是否开启优化检测、调试工具 ( 控制 UETool、LeakCanary、BlockCanary 等开关 )
    if (showDebugTools) {
//        // 内存检测工具 https://github.com/square/leakcanary
//        api deps_split.property.leakcanary
        // 饿了么 UETool https://github.com/eleme/UETool/blob/master/README_zh.md
        api deps_split.property.uetool
        api deps_split.property.uetool_base
    } else {
        // 饿了么 UETool https://github.com/eleme/UETool/blob/master/README_zh.md
        api deps_split.property.uetool_no_op
    }

    // Bugly https://bugly.qq.com/docs/
    api deps_split.property.bugly
    api deps_split.property.bugly_ndk

    // ==============
    // = deps_split =
    // ==============

    // LoadingDrawable https://github.com/dinuscxj/LoadingDrawable
    api deps_split.widget.loading_drawable
    // Activity 跳转动画 https://github.com/skydoves/TransformationLayout
    api deps_split.widget.transformation_layout
}
```

# AndroidManifest

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    package="afkt_replace.core">

    <!-- 读取手机状态 -->
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <!-- 网络权限 -->
    <uses-permission android:name="android.permission.INTERNET" />
    <!-- 读写权限 -->
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <!-- 摄像头权限 -->
    <uses-permission android:name="android.permission.CAMERA" />

    <application
        android:largeHeap="true"
        android:networkSecurityConfig="@xml/network_security_config"
        android:requestLegacyExternalStorage="false"
        android:supportsRtl="true"
        android:usesCleartextTraffic="true">

        <!-- 屏幕适配 -->

        <meta-data
            android:name="design_width_in_dp"
            android:value="360" />

        <meta-data
            android:name="design_height_in_dp"
            android:value="640" />

        <!-- App startup init -->

        <provider
            android:name="androidx.startup.InitializationProvider"
            android:authorities="${applicationId}.androidx-startup"
            android:exported="false"
            tools:node="merge">
            <meta-data
                android:name="afkt_replace.core.CoreInitializer"
                android:value="@string/androidx_startup" />
        </provider>
    </application>
</manifest>
```

# main/java 目录结构

```
- java                                          
   - afkt_replace                               
      - core                                    
         - app                                  
         - base                                 
            - app                               
               - base                           
                  - inter                       
                  - simple                      
                     - factory                  
               - extension                      
                  - loading                     
                  - mvvm                        
                  - theme                       
            - controller                        
               - inter                          
               - loading                        
               - transition                     
               - ui                             
                  - ext                         
                  - theme                       
               - viewmodel                      
            - core                              
            - repository                        
            - skin                              
            - split                             
               - controller                     
               - data                           
               - inter                          
         - channel                              
         - config                               
         - engine                               
            - debug                             
         - mvvm                                 
            - binding                           
         - network                              
            - common                            
         - property                             
         - receiver                             
            - feature                           
         - router                               
         - ui                                   
            - animation                         
            - assist                            
            - listener                          
            - skin                              
            - widget                            
               - extension                      
               - view_assist                    
                  - loading_skeleton            
         - web                                  
            - assist                            
```


# main/res 目录结构

```
- res                          
   - drawable                  
   - mipmap-xhdpi              
   - mipmap-xxhdpi             
   - mipmap-xxxhdpi            
   - values                    
   - values-v21                
   - xml                       
```


# main/res-base 目录结构

```
- res-base                      
   - drawable-anydpi            
   - drawable-hdpi              
   - drawable-mdpi              
   - drawable-xhdpi             
   - drawable-xxhdpi            
   - layout                     
```


# main/res-language 目录结构

```
- res-language         
   - values            
   - values-zh         
```


# main/res-tools 目录结构

```
- res-tools              
   - drawable            
   - values              
```


# main/res-view-assist 目录结构

```
- res-view-assist      
   - layout            
```