
# About

模板 Module ( 便于 copy )

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
    package="afkt_replace.module.template">

    <application>
        <provider
            android:name="androidx.startup.InitializationProvider"
            android:authorities="${applicationId}.androidx-startup"
            android:exported="false"
            tools:node="merge">
            <meta-data
                android:name="afkt_replace.module.template.TemplateInitializer"
                android:value="@string/androidx_startup" />
        </provider>

        <activity
            android:name=".MainContainerActivity"
            android:configChanges="orientation|keyboardHidden"
            android:exported="false"
            android:screenOrientation="portrait" />

        <!-- End Page -->

        <activity
            android:name=".feature.end.EndActivity"
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
         - template           
            - base            
            - feature         
               - end          
               - start        
```


# main/res 目录结构

```
- res                  
   - layout            
   - navigation        
   - values            
   - values-zh         
```