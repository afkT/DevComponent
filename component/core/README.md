# About

该目录属于核心基础库代码，整个组件化项目基于该基础上进行开发

## 目录结构

```
- core                                 | 根目录
   - core                              | 核心基础整合库 ( 内部集成 core libs, 对外依赖该 module 即可 )
   - core_base_lib                     | 基础核心开发库依赖 ( libs 便捷依赖统一维护 )
   - libs                              | 具体功能拆分, 封装 lib
      - lib_base                       | 基类相关 ( Activity、Application 等 )
      - lib_base_split                 | 基础 ( 基类等 ) lib 拆包
      - lib_bean                       | 通用实体类 ( module 实体类下沉 )
      - lib_channel_flavors            | App 多渠道
      - lib_config                     | 通用配置、常量信息
      - lib_debug_assist               | Debug 编译辅助开发库 ( 提供切换环境、抓包数据可视化、调试按钮开关等辅助功能 )
      - lib_engine                     | 通用 Engine ( 图片加载、日志、JSON、权限、资源选择、缓存 ) lib
      - lib_environment                | 通用环境配置切换库
      - lib_language                   | 通用多语言 lib
      - lib_mvvm                       | MVVM 通用代码封装 ( 使用 MVVM module 必须 api 依赖如果使用 compileOnly 将会找不到 BindingAdapter 等 )
      - lib_network                    | 网络相关 lib ( 网络请求、上传下载 )
      - lib_property                   | 性能优化、检测 lib
      - lib_receiver                   | 广播监听 ( 如网络状态、电量、屏幕解锁 ) 相关
      - lib_router                     | 路由相关
      - lib_ui                         | 统一 style、widget、ui 相关组件
      - lib_ui_skin                    | APP 主题、换肤相关控制
      - lib_upload                     | 通用上传库
      - lib_utils                      | 通用工具库
      - lib_web                        | WebView 相关
```

# core/core

> 该 Module 依赖 core 核心开发库、核心第三方库等
>
> 统一维护核心库依赖，对外只需要依赖该 Module 便可使用整个核心模块 ( core 文件以及内部所有 libs )

# core/core_base_lib

> 该 Module 基于 Dev 系列开发库搭建，且不存在任何代码属于核心 lib 依赖 ( 全部开发基于该 module )
>
> 用于统一维护基础核心开发库依赖，如有必须依赖底层库在此添加