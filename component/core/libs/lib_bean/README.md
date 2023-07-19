
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

    // 基础 ( 基类等 ) lib 拆包
    compileOnly project(':core_lib_base_split')
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
               - base            
               - movie           
               - person          
               - splash          
               - tv              
```