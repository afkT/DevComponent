# About

该目录属于核心基础库代码，整个组件化项目基于该基础上进行开发

## 目录结构

```
- core                              | 根目录
   - core                           | 核心基础整合库 ( 内部集成 core libs, 对外依赖该 module 即可 )
   - core_desc                      | 核心基础库依赖描述
   - core_project                   | 项目相关代码库
   - libs                           | 具体功能拆分, 封装 lib
      - lib_debug_assist            | Debug 编译辅助开发库 ( 提供切换环境、抓包数据可视化、调试按钮开关等辅助功能 )
      - lib_environment             | 通用环境配置切换库
```

# core/core

> 该 Module 依赖 core 核心开发库、核心第三方库等
>
> 统一维护核心库依赖，对外只需要依赖该 Module 便可使用整个核心模块 ( core 文件以及内部所有 libs )

# core/core_base_lib

> 该 Module 基于 Dev 系列开发库搭建，且不存在任何代码属于核心 lib 依赖 ( 全部开发基于该 module )
>
> 用于统一维护基础核心开发库依赖，如有必须依赖底层库在此添加