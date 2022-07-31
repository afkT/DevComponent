
# About

通用 Engine ( 图片加载、日志、JSON、权限、资源选择、缓存 ) lib

# 依赖信息

```groovy
dependencies {

    // 核心基础依赖库 ( 编译但不参与打包 )
    compileOnly project(':core_base_lib')
}
```

# AndroidManifest

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="afkt_replace.core.lib.engine">

    <!-- 网络权限 -->
    <uses-permission android:name="android.permission.INTERNET" />
    <!-- 读写权限 -->
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <!-- 摄像头权限 -->
    <uses-permission android:name="android.permission.CAMERA" />
</manifest>
```

# main/java 目录结构

```
- java                            
   - afkt_replace                 
      - core                      
         - lib                    
            - engine              
               - debug            
```
