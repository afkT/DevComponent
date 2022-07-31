
# About

路由相关

# 依赖信息

```groovy
dependencies {

    // 核心基础依赖库 ( 编译但不参与打包 )
    compileOnly project(':core_base_lib')

    // ===================
    // = core - 核心开发库 =
    // ===================

    // 通用实体类 lib
    compileOnly project(':core_lib_bean')
    // 通用配置、常量 lib
    compileOnly project(':core_lib_config')
    // 通用 Engine ( 图片加载、日志、JSON、权限、资源选择 等 ) lib
    compileOnly project(':core_lib_engine')
    // 通用工具库
    compileOnly project(':core_lib_utils')

    // ====================
    // = libs - 第三方库依赖 =
    // ====================

    // ARouter 路由 https://github.com/alibaba/ARouter
    api deps.lib.arouter_api // https://github.com/alibaba/ARouter/blob/master/README_CN.md
    kapt deps.lib.arouter_compiler
}
```

# AndroidManifest

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest package="afkt_replace.core.lib.router">

</manifest>
```

# main/java 目录结构

```
- java                                     
   - afkt_replace                          
      - core                               
         - lib                             
            - router                       
               - module                    
                  - main                   
                  - splash                 
                  - template               
                  - user                   
                  - wan_android            
```
