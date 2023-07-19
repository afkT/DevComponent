
# About

MVVM 通用代码封装 ( 使用 MVVM module 必须 api 依赖如果使用 compileOnly 将会找不到 BindingAdapter 等 )

# 依赖信息

```groovy
dependencies {

    // 核心基础依赖库 ( 编译但不参与打包 )
    compileOnly project(':core_base_lib')

    // ===================
    // = core - 核心开发库 =
    // ===================

    // 通用 UI 样式、资源、交互、控件 lib
    compileOnly project(':core_lib_ui')
}
```

# AndroidManifest

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest package="afkt_replace.core.lib.mvvm" />
```

# main/java 目录结构

```
- java                              
   - afkt_replace                   
      - core                        
         - lib                      
            - mvvm                  
               - binding            
```