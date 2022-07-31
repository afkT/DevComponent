
# About

Debug 编译辅助开发库 ( 提供切换环境、抓包数据可视化、调试按钮开关等辅助功能 )

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
    // 通用环境配置切换库
    compileOnly project(':core_lib_environment')
    // 性能优化、检测 lib
    compileOnly project(':core_lib_property')
    // 通用 UI 样式、资源、交互、控件 lib
    compileOnly project(':core_lib_ui')
}
```

# AndroidManifest

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="afkt_replace.core.lib.debug">

    <application>
        <activity
            android:name=".DebugMainContainerActivity"
            android:exported="false" />

        <provider
            android:name=".provider.InitProvider"
            android:authorities="${applicationId}.provider.InitProvider"
            android:exported="false" />
    </application>
</manifest>
```

# main/java 目录结构

```
- java                             
   - afkt_replace                  
      - core                       
         - lib                     
            - debug                
               - engine            
               - floating          
               - provider          
```


# main/res 目录结构

```
- res                    
   - drawable            
   - layout              
```
