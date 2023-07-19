
# About

启动页 module 依赖 lib，用于校验是否存在广告需要展示

# 依赖信息

```groovy
dependencies {

    // 核心基础依赖库
    compileOnly project(':core')
}
```

# AndroidManifest

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest package="afkt_replace.lib.splash.ads" />
```

# main/java 目录结构

```
- java                       
   - afkt_replace            
      - lib                  
         - splash            
            - ads            
```


# main/res 目录结构

```
- res                  
   - layout            
```