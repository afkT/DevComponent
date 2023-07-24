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

> 该 Module 属于 core 核心开发、第三方库封装、框架代码封装等核心代码实现

# core/core_desc

> 该 Module 属于项目依赖描述库，不存在任何代码属于核心依赖，统一维护核心库依赖，对外只需要依赖该 Module 便可使用整个核心模块 ( core 文件以及内部所有 libs )

# core/core_project

> 该 Module 属于项目独有的相关代码模块，便于拆分存储业务相关逻辑代码