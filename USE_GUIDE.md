# 如何使用该项目进行开发?

## 直接使用该项目代码会遇到的问题

* 最直接的就是 `包名` 每个应用包名都是唯一且有意义的

## 解决方案

针对 `包名` 替换问题，已编写 [DevReplace][DevReplace] 项目进行快捷替换

便于直接使用该模板进行组件化开发，减少使用者包名替换、重新搭建组件化流程。

> 核心实现代码 [DevReplace.Code][DevReplace.Code]，代码执行入口 [ReplaceMain][ReplaceMain]

### DevReplace 使用步骤

1. Clone `https://github.com/afkT/DevComponent.git` 项目到本地
2. 打开项目根目录，进入 `/interesting/DevReplace` 文件目录，定位并打开 `Code.kt` 文件
3. 搜索 `const val REPLACE_PACKNAME = ""` 并修改字符串值为待变更包名，例：com.afkt.shop
4. 同目录打开 `ReplaceMain.kt` 文件，执行 `main` 方法，完成替换包名第一步
5. 进行全局搜索 **afkt_replace** 并替换为刚才 `REPLACE_PACKNAME` 字符串设置的包名，并结束替换流程

**至此整个流程结束，成功将该项目包名替换为指定包名**

* 最后只需 clean project 并进行 rebuild 即可使用该组件化模板进行个人、公司项目开发
* 项目文件名 ( DevComponent ) 直接手动修改即可
* 执行该操作结束后记得把 .git 文件夹删除，再 push 到 git 服务器上

### 替换包名结果目录展示

### [core][DevComponent.Core]

```
- core                        | 根目录
   - core                     | com.afkt.shop.core
   - core_base_lib            | com.afkt.shop.core.base.lib
   - libs                     | 
      - lib_base              | com.afkt.shop.core.lib.base
      - lib_bean              | com.afkt.shop.core.lib.bean
      - lib_config            | com.afkt.shop.core.lib.config
      - lib_debug_assist      | com.afkt.shop.core.lib.debug
      - lib_engine            | com.afkt.shop.core.lib.engine
      - lib_environment       | com.afkt.shop.core.lib.environment
      - lib_network           | com.afkt.shop.core.lib.network
      - lib_property          | com.afkt.shop.core.lib.property
      - lib_receiver          | com.afkt.shop.core.lib.receiver
      - lib_router            | com.afkt.shop.core.lib.router
      - lib_ui                | com.afkt.shop.core.lib.ui
      - lib_upload            | com.afkt.shop.core.lib.upload
      - lib_utils             | com.afkt.shop.core.lib.utils
      - lib_web               | com.afkt.shop.core.lib.web
```

### [application、module][DevComponent.Application]

```
- application                 | 根目录
   - app                      | com.afkt.shop.component
   - module                   | 
      - module_commodity      | com.afkt.shop.module.commodity
      - module_main           | com.afkt.shop.module.main
      - module_splash         | com.afkt.shop.module.splash
      - module_template       | com.afkt.shop.module.template
      - module_user           | com.afkt.shop.module.user
      - module_wanandroid     | com.afkt.shop.module.wan_android
```

如上结果展示可得出规律

* 主体应用包名为 com.afkt.shop.component
* 各个 module 包名为 com.afkt.shop.module.xxxx
* core/libs 为 com.afkt.shop.core.lib.xxx

如果对该包名结构不满意，可自行在`执行替换操作之后`进行修改，最大程度减少替换包名的复杂性。

并同步更新 [modular.gradle][modular.gradle] 中 modularVersion 的各个 module applicationId 包名配置即可





[DevReplace]: https://github.com/afkT/DevComponent/tree/main/interesting
[ReplaceMain]: https://github.com/afkT/DevComponent/blob/main/interesting/DevReplace/src/main/java/afkt_replace/replace/ReplaceMain.kt
[DevReplace.Code]: https://github.com/afkT/DevComponent/blob/main/interesting/DevReplace/src/main/java/afkt_replace/replace/Code.kt
[DevComponent.Core]: https://github.com/afkT/DevComponent/blob/main/component/core
[DevComponent.Application]: https://github.com/afkT/DevComponent/blob/main/application
[modular.gradle]: https://github.com/afkT/DevComponent/blob/main/file/gradle/modular.gradle