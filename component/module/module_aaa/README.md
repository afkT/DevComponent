
# About

演示 AAA Module

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
    package="afkt.module.aaa">

    <application>
        <provider
            android:name="androidx.startup.InitializationProvider"
            android:authorities="${applicationId}.androidx-startup"
            android:exported="false"
            tools:node="merge">
            <meta-data
                android:name="afkt.module.aaa.AAAInitializer"
                android:value="@string/androidx_startup" />
        </provider>

        <activity
            android:name=".activity.MainContainerActivity"
            android:configChanges="orientation|keyboardHidden"
            android:screenOrientation="portrait" />
    </application>
</manifest>
```

# main/java 目录结构

```
- java                              
   - afkt                           
      - module                      
         - aaa                      
            - activity              
            - base                  
            - fragment              
```


# main/res 目录结构

```
- res                               
   - layout                         
   - mipmap-xxxhdpi                 
   - values                         
   - values-zh                      
```
