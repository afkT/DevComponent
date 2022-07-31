
# About

通用工具库

# 依赖信息

```groovy
dependencies {

    // 核心基础依赖库 ( 编译但不参与打包 )
    compileOnly project(':core_base_lib')

    // ===================
    // = core - 核心开发库 =
    // ===================

    // 通用 Engine ( 图片加载、日志、JSON、权限、资源选择 等 ) lib
    compileOnly project(':core_lib_engine')
}
```

# AndroidManifest

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest package="afkt_replace.core.lib.utils" />
```

# main/java 目录结构

```
- java                         
   - afkt_replace              
      - core                   
         - lib                 
            - utils            
   - dev                       
      - kotlin                 
         - engine              
            - analytics        
            - barcode          
            - cache            
            - compress         
            - image            
            - json             
            - keyvalue         
            - log              
            - media            
            - permission       
            - push             
            - share            
            - storage          
         - utils               
            - image            
            - price            
            - size             
```


# main/res-tools 目录结构

```
- res-tools            
   - values            
```
