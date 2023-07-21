
# About

Debug 编译辅助开发库 ( 提供切换环境、抓包数据可视化、调试按钮开关等辅助功能 )

# 依赖信息

```groovy
dependencies {

    // ===================
    // = core - 核心开发库 =
    // ===================

    // 核心基础依赖库
    api project(':core')
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