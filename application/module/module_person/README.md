
# About

人物 Module

# 依赖信息

```groovy
dependencies {

    // TMDB 通用 UI lib
    implementation project(':lib_tmdb_ui')
}
```

# AndroidManifest

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    package="afkt_replace.module.person">

    <application>
        <provider
            android:name="androidx.startup.InitializationProvider"
            android:authorities="${applicationId}.androidx-startup"
            android:exported="false"
            tools:node="merge">
            <meta-data
                android:name="afkt_replace.module.person.PersonInitializer"
                android:value="@string/androidx_startup" />
        </provider>

        <activity
            android:name=".MainContainerActivity"
            android:configChanges="orientation|keyboardHidden"
            android:exported="false"
            android:screenOrientation="portrait" />

        <!-- Person Details -->

        <activity
            android:name=".feature.details.PersonDetailsActivity"
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
         - person                   
            - base                  
            - data                  
               - api                
            - feature               
               - details            
               - popular            
```


# main/res 目录结构

```
- res                  
   - layout            
   - navigation        
   - values            
   - values-zh         
```