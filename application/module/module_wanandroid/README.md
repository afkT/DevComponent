
# About

DevHttpManager 演示 - 玩 Android 文章 Module

# 依赖信息

```groovy
dependencies {
}
```

# AndroidManifest

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    package="afkt_replace.module.wan_android">

    <application>
        <provider
            android:name="androidx.startup.InitializationProvider"
            android:authorities="${applicationId}.androidx-startup"
            android:exported="false"
            tools:node="merge">
            <meta-data
                android:name="afkt_replace.module.wan_android.WanAndroidInitializer"
                android:value="@string/androidx_startup" />
        </provider>

        <activity
            android:name=".MainContainerActivity"
            android:configChanges="orientation|keyboardHidden"
            android:exported="false"
            android:screenOrientation="portrait" />
    </application>
</manifest>
```

# main/java 目录结构

```
- java                              
   - afkt_replace                   
      - module                      
         - wan_android              
            - base                  
            - data                  
               - api                
               - model              
            - feature               
               - article            
```


# main/res 目录结构

```
- res                          
   - drawable                  
   - layout                    
   - mipmap-xhdpi              
   - mipmap-xxhdpi             
   - mipmap-xxxhdpi            
   - values                    
   - values-zh                 
```
