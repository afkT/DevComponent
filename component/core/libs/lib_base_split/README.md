
# About

基础 ( 基类等 ) lib 拆包

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
    // 通用多语言 lib
    compileOnly project(':core_lib_language')

    // ====================
    // = libs - 第三方库依赖 =
    // ====================

    // ==============
    // = deps_split =
    // ==============

    // Activity 跳转动画 https://github.com/skydoves/TransformationLayout
    api deps_split.widget.transformation_layout
}
```

# AndroidManifest

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest package="afkt_replace.core.lib.base.split" />
```

# main/java 目录结构

```
- java                                    
   - afkt_replace                         
      - core                              
         - lib                            
            - base                        
               - split                    
                  - controller            
                  - data                  
                  - inter                 
```