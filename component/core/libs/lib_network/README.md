
# About

网络相关 lib ( 网络请求、上传下载 )

# 依赖信息

```groovy
dependencies {

    // 核心基础依赖库 ( 编译但不参与打包 )
    compileOnly project(':core_base_lib')

    // ===================
    // = core - 核心开发库 =
    // ===================

    // 通用 Engine ( 图片加载、日志、JSON、权限、资源选择 等 ) lib
    compileOnly project(':core_lib_engine')
    // 通用工具库
    compileOnly project(':core_lib_utils')

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
}
```

# AndroidManifest

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="afkt_replace.core.lib.network">

    <!-- 网络权限 -->
    <uses-permission android:name="android.permission.INTERNET" />
    <!-- 读写权限 -->
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
</manifest>
```

# main/java 目录结构

```
- java                             
   - afkt_replace                  
      - core                       
         - lib                     
            - network              
               - common            
```
