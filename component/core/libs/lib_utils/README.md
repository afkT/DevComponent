
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

    // 通用 UI 样式、资源、交互、控件 lib
    compileOnly project(':core_lib_ui')

    // ====================
    // = libs - 第三方库依赖 =
    // ====================

    // Android 平台下的图片选择器 https://github.com/LuckSiege/PictureSelector
    api deps_split.lib.pictureSelector
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
         - utils               
            - image            
```


# main/res-tools 目录结构

```
- res-tools            
   - values            
```