
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
                  - movie           
                  - person          
                  - splash          
                  - template        
                  - tv              
```