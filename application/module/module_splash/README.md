
# About

启动页 ( 广告页、首次启动引导页 ) Module

# 依赖信息

```groovy
dependencies {

    // 启动页广告 lib
    implementation project(':lib_splash_ads')
}
```

# AndroidManifest

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="afkt_replace.module.splash">

    <application>

        <!-- LAUNCHER -->

        <activity
            android:name=".AppLauncherActivity"
            android:exported="true"
            android:theme="@style/AppTheme.Launcher">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>
</manifest>
```

# main/java 目录结构

```
- java                        
   - afkt_replace             
      - module                
         - splash             
            - base            
            - feature         
```


# main/res 目录结构

```
- res                  
   - layout            
   - navigation        
   - values            
   - values-zh         
```