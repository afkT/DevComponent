
# About

通用实体类 ( module 实体类下沉 )

# 依赖信息

```groovy
dependencies {

    // 核心基础依赖库 ( 编译但不参与打包 )
    compileOnly project(':core_base_lib')

    // ===================
    // = core - 核心开发库 =
    // ===================

    // 通用配置、常量 lib
    compileOnly project(':core_lib_config')
    // 通用 Engine ( 图片加载、日志、JSON、权限、资源选择 等 ) lib
    compileOnly project(':core_lib_engine')
}
```

# AndroidManifest

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest package="afkt_replace.core.lib.bean" />
```

# main/java 目录结构

```
- java                        
   - afkt_replace             
      - core                  
         - lib                
            - bean            
```
