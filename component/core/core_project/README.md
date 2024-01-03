
# About

项目相关代码库

# 依赖信息

```groovy
dependencies {

    // ===================
    // = core - 核心开发库 =
    // ===================

    // 核心基础依赖库
    api project(':core')

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
<manifest package="afkt_replace.core.project" />
```

# main/java 目录结构

```
- java                                  
   - afkt_replace                       
      - core                            
         - project                      
            - bean                      
               - base                   
               - movie                  
               - person                 
               - splash                 
               - tv                     
            - network                   
               - tmdb                   
            - router                    
               - module                 
                  - main                
                  - movie               
                  - person              
                  - splash              
                  - template            
                  - tv                  
            - utils                     
               - image                  
               - tmdb                   
```


# main/res-language 目录结构

```
- res-language         
   - values            
   - values-zh         
```