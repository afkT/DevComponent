
# About

基类相关 ( Activity、Application 等 )

# 依赖信息

```groovy
dependencies {

    // 核心基础依赖库 ( 编译但不参与打包 )
    compileOnly project(':core_base_lib')

    // 依赖 UI ( 编译但不参与打包 )
    compileOnly project(':core_lib_ui')
}
```

# AndroidManifest

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest package="dev.core_lib_base">

</manifest>
```

# main/java 目录结构

```
- java                           
   - dev                         
      - core                     
         - lib                   
            - base               
               - app             
               - controller      
```
