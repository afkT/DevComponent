
# About

核心基础整合库 ( 内部集成 core libs, 对外依赖该 module 即可 )

# 依赖信息

```groovy
dependencies {

    // 核心基础依赖库
    api project(':core_base_lib')

    // ===================
    // = core - 核心开发库 =
    // ===================

    // 基础 ( 基类等 ) lib
    api project(':core_lib_base')
    // 通用实体类 lib
    api project(':core_lib_bean')
    // App 多渠道 lib
    api project(':core_lib_channel_flavors')
    // 通用配置、常量 lib
    api project(':core_lib_config')
    // 通用 Engine ( 图片加载、日志、JSON、权限、资源选择 等 ) lib
    api project(':core_lib_engine')
    // 通用环境配置切换库
    api project(':core_lib_environment')
    // 网络相关 lib ( 网络请求、上传下载 )
    api project(':core_lib_network')
    // 性能优化、检测 lib
    api project(':core_lib_property')
    // 广播监听 ( 如网络状态、电量、屏幕解锁 ) 相关
    api project(':core_lib_receiver')
    // 路由相关
    api project(':core_lib_router')
    // 通用 UI 样式、资源、交互、控件 lib
    api project(':core_lib_ui')
    // 通用上传库
    api project(':core_lib_upload')
    // 通用工具库
    api project(':core_lib_utils')
    // WebView 相关
    api project(':core_lib_web')
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
         - property       
         - router         
```


# main/res 目录结构

```
- res               
   - xml            
```
