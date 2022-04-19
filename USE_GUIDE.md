# 如何使用该项目进行开发?

### 直接使用该项目代码会遇到的问题

* 最直接的就是 `包名` 每个应用包名都是唯一且有意义的

### 解决方案

针对 `包名` 替换问题，已编写 [DevReplace][DevReplace] 项目进行快捷替换，
便于直接使用该模板进行组件化开发, 减少使用者包名替换、重新搭建组件化流程。

> 核心实现代码 [DevReplace.Code][DevReplace.Code]，代码执行入口 [ReplaceMain][ReplaceMain]

#### DevReplace 使用步骤

1. Clone `https://github.com/afkT/DevComponent.git` 项目到本地
2. 打开项目根目录，进入 `/interesting/DevReplace` 文件目录，定位并打开 `Code.kt` 文件
3. 搜索 `const val REPLACE_PACKNAME = ""` 并修改字符串值为待变更包名，例：com.afkt.shop
4. 同目录打开 `ReplaceMain.kt` 文件，执行 `main` 方法，完成替换包名第一步
5. 进行全局搜索 **afkt_replace** 并替换为刚才 `REPLACE_PACKNAME` 字符串设置的值，并结束替换流程

**至此整个流程结束，成功将该项目包名替换为指定包名**

* 最后只需 clean project 并进行 rebuild 即可使用该组件化模板进行个人、公司项目开发
* 项目文件名 ( DevComponent ) 直接手动修改即可
* 执行该操作结束后记得把 .git 文件夹删除，再 push 到 git 服务器上





[DevReplace]: https://github.com/afkT/DevComponent/tree/main/interesting
[ReplaceMain]: https://github.com/afkT/DevComponent/blob/main/interesting/DevReplace/src/main/java/afkt_replace/replace/ReplaceMain.kt
[DevReplace.Code]: https://github.com/afkT/DevComponent/blob/main/interesting/DevReplace/src/main/java/afkt_replace/replace/Code.kt