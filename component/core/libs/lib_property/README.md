
# About

性能优化、检测 lib

# 依赖信息

```groovy
dependencies {

    // 核心基础依赖库 ( 编译但不参与打包 )
    compileOnly project(':core_base_lib')

    // ====================
    // = 性能检测、排查相关库 =
    // ====================

    // 是否开启优化检测、调试工具 ( 控制 UETool、LeakCanary、BlockCanary 等开关 )
    if (showDebugTools) {
//        // 内存检测工具 https://github.com/square/leakcanary
//        api deps_split.property.leakcanary
        // 饿了么 UETool https://github.com/eleme/UETool/blob/master/README_zh.md
        api deps_split.property.uetool
        api deps_split.property.uetool_base
    } else {
        // 饿了么 UETool https://github.com/eleme/UETool/blob/master/README_zh.md
        api deps_split.property.uetool_no_op
    }

    // ====================
    // = libs - 第三方库依赖 =
    // ====================

    // Bugly https://bugly.qq.com/docs/
    api deps_split.property.bugly
    api deps_split.property.bugly_ndk
}
```

# AndroidManifest

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest package="afkt_replace.core.lib.property" />
```

# main/java 目录结构

```
- java                            
   - afkt_replace                 
      - core                      
         - lib                    
            - property            
```