
# About

广播监听 ( 如网络状态、电量、屏幕解锁 ) 相关

# 依赖信息

```groovy
dependencies {

    // 核心基础依赖库 ( 编译但不参与打包 )
    compileOnly project(':core_base_lib')
}
```

# AndroidManifest

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest package="afkt_replace.core.lib.receiver" />
```

# main/java 目录结构

```
- java                              
   - afkt_replace                   
      - core                        
         - lib                      
            - receiver              
               - feature            
```