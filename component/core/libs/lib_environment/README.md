
# About

通用环境配置切换库

# 依赖信息

```groovy
dependencies {

    // ================
    // = Dev 系列开发库 =
    // ================

    // https://github.com/afkT/DevUtils

    // DevApp - Android 工具类库
    api deps.dev.dev_app

    // DevEnvironment - Android 环境配置切换库
    api deps.dev.dev_environment
    if (isRelease) {
        kapt deps.dev.dev_environment_compiler_release
    } else {
        kapt deps.dev.dev_environment_compiler
    }
}
```

# AndroidManifest

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest package="afkt_replace.core.lib.environment" />
```

# main/java 目录结构

```
- java                               
   - afkt_replace                    
      - core                         
         - lib                       
            - environment            
```