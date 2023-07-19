
# About

统一 style、widget、ui 相关组件

# 依赖信息

```groovy
dependencies {

    // 核心基础依赖库 ( 编译但不参与打包 )
    compileOnly project(':core_base_lib')

    // ===================
    // = core - 核心开发库 =
    // ===================

    // 通用配置、常量 lib
    compileOnly project(':core_lib_config')
    // 通用多语言 lib
    compileOnly project(':core_lib_language')
    // APP 主题、换肤相关控制
    compileOnly project(':core_lib_ui_skin')

    // ====================
    // = libs - 第三方库依赖 =
    // ====================

    // 下拉刷新框架 https://github.com/scwang90/SmartRefreshLayout
    api deps.widget.smartrefreshlayout
    api deps.widget.smartrefresh_header_classics // 经典刷新头
//    api deps.widget.smartrefresh_header_radar // 雷达刷新头
//    api deps.widget.smartrefresh_header_falsify // 虚拟刷新头
//    api deps.widget.smartrefresh_header_material // 谷歌刷新头
//    api deps.widget.smartrefresh_header_two_level // 二级刷新头
//    api deps.widget.smartrefresh_footer_ball // 球脉冲加载
    api deps.widget.smartrefresh_footer_classics // 经典加载

    // ==============
    // = deps_split =
    // ==============

    // LoadingDrawable https://github.com/dinuscxj/LoadingDrawable
    api deps_split.widget.loading_drawable
}
```

# AndroidManifest

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest package="afkt_replace.core.lib.ui" />
```

# main/java 目录结构

```
- java                                             
   - afkt_replace                                  
      - core                                       
         - lib                                     
            - ui                                   
               - animation                         
               - assist                            
               - listener                          
               - widget                            
                  - extension                      
                  - view_assist                    
                     - loading_skeleton            
```


# main/res 目录结构

```
- res                          
   - drawable                  
   - mipmap-xhdpi              
   - mipmap-xxhdpi             
   - mipmap-xxxhdpi            
   - values                    
   - values-v21                
```


# main/res-base 目录结构

```
- res-base                      
   - drawable-anydpi            
   - drawable-hdpi              
   - drawable-mdpi              
   - drawable-xhdpi             
   - drawable-xxhdpi            
   - layout                     
```


# main/res-tools 目录结构

```
- res-tools              
   - drawable            
   - values              
```


# main/res-view-assist 目录结构

```
- res-view-assist      
   - layout            
```