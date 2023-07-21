
# About

核心基础库依赖描述

# 依赖信息

```groovy
dependencies {

    // ===================
    // = core - 核心开发库 =
    // ===================

    // 核心基础依赖库
    api project(':core')
    // 项目相关代码库
    api project(':core_project')

    // =====================
    // = Debug 编译辅助开发库 =
    // =====================

    if (showDebugTools) {
        // Debug 编译辅助开发库 ( 提供切换环境、抓包数据可视化、调试按钮开关等辅助功能 )
        api project(':core_lib_debug_assist')
    }
}
```

# AndroidManifest

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest package="afkt_replace.core.desc" />
```