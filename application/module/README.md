# About

该目录下的 Module 在 `isModular=true` 的情况下，都属于独立的应用可单独运行，为 `false` 则都属于功能模块，被主体应用 ( 壳 ) 所依赖使用

## 目录结构

```
- module                          | 根目录
   - module_main                  | 首页 Module
   - module_splash                | 启动页 ( 广告页、首次启动引导页 ) Module
   - module_template              | 模板 Module ( 便于 copy )
   - module_user                  | 用户 Module
   - module_wanandroid            | 玩 Android Module
```