
# About

基类相关 ( Activity、Application 等 )

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
    // App 多渠道 lib
    compileOnly project(':core_lib_channel_flavors')
    // 通用配置、常量 lib
    compileOnly project(':core_lib_config')
    // 通用 Engine ( 图片加载、日志、JSON、权限、资源选择 等 ) lib
    compileOnly project(':core_lib_engine')
    // 通用环境配置切换库
    compileOnly project(':core_lib_environment')
//    // MVVM 通用代码封装 ( 使用 MVVM module 必须 api 依赖如果使用 compileOnly 将会找不到 BindingAdapter 等 )
//    api project(':core_lib_mvvm')
    // 路由相关
    compileOnly project(':core_lib_router')
    // 通用 UI 样式、资源、交互、控件 lib
    compileOnly project(':core_lib_ui')
    // APP 主题、换肤相关控制
    compileOnly project(':core_lib_ui_skin')
    // 通用工具库
    compileOnly project(':core_lib_utils')
}
```

# AndroidManifest

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest package="afkt_replace.core.lib.base" />
```

# main/java 目录结构

```
- java                                  
   - afkt_replace                       
      - core                            
         - lib                          
            - base                      
               - app                    
                  - base                
                     - inter            
                     - simple           
                        - factory       
                  - extension           
                     - loading          
                     - mvvm             
                     - theme            
               - controller             
                  - inter               
                  - loading             
                  - transition          
                  - ui                  
                     - ext              
                     - theme            
                  - viewmodel           
               - core                   
               - repository             
               - skin                   
```