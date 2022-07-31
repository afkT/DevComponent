
# About

统一 style、widget、ui 相关组件

# 依赖信息

```groovy
dependencies {

    // 核心基础依赖库 ( 编译但不参与打包 )
    compileOnly project(':core_base_lib')

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
               - assist            
               - widget            
```


# main/res 目录结构

```
- res                          
   - drawable                  
   - mipmap-xxxhdpi            
   - values                    
   - values-zh                 
```


# main/res-base 目录结构

```
- res-base                     
   - layout                    
   - mipmap-xxxhdpi            
```
