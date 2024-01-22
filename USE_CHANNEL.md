

## 摘要

* [第三方多渠道](#第三方多渠道)
* [Android 官方多渠道](#Android-官方多渠道)
* [总结与扩展](#总结与扩展)


## 第三方多渠道

`目前市面上的多渠道打包工具，大多基于 V1、V2 签名的多渠道打包方案实现。`

比较火的有 [Walle 瓦力多渠道打包][Walle 瓦力多渠道打包]、[VasDolly 多渠道打包][VasDolly 多渠道打包] 都是基于该方案实现

> 具体实现原理可以点击上方链接查看对应 **README**、**WIKI**。

**优点：**

1. 不需要重新构建新渠道包，不需要重新签名，打包方式速度非常快，每个渠道包仅需几秒的耗时，非常适合渠道较多的 APK。

**缺点：**

1. 不支持不同渠道编译不同代码、logo、布局、资源文件等各种差异化实现。
2. 依赖作者同步更新实现方案，如 v3 签名、gradle 更新等。


## Android 官方多渠道

另外一种就是 [Android 官方多渠道 productFlavors][Android 官方多渠道 productFlavors] 实现方案。

**优点：**

1. 支持不同渠道编译不同代码、logo、布局、资源文件等各种差异化实现。
2. 官方提供实现方案，天然完美支持无需升级等待适配

**缺点：**

1. 每生成一个渠道包，都要重新执行一遍构建流程，效率太低，只适用于渠道较少的场景。
2. 部分热修复方案需要对每个渠道包单独生成差分补丁或进行适配处理。

> 当前主线分支 [main][main] 使用的就是该方案。

**如何使用该方案：**

以 `官方渠道`、`华为渠道`、`小米渠道` 为例，参照 [apk_channel.gradle][apk_channel.gradle]，首先创建 `productFlavors`

```gradle
// 多渠道信息定义
productFlavors {
    // 默认 APP ( 官方 ) 渠道
    app_default {
    }

    // 华为渠道
    hua_wei {
    }

    // 小米渠道
    xiao_mi {
    }
}
```

可以参照官方文档进行配置，如 `包名统一加后缀`、`修改包名`、`替换清单文件中的标签`

```gradle
// 默认 APP ( 官方 ) 渠道
app_default {
    // 替换清单文件中的标签
    manifestPlaceholders = [

    ]
    // 甚至可以设置不同包名、包名自动加后缀等
    applicationId "afkt_replace.component"
    // 当前包名加后缀
    applicationIdSuffix ".debug"
}
```

接着通过 `sourceSets` 进行设置资源合并配置，**支持不同渠道编译不同代码、logo、布局、资源文件等各种差异化实现。**

```gradle
sourceSets {
    app_default {
        // 有特殊配置可单独设置, 如某个渠道合并 AndroidManifest.xml
        manifest.srcFile = ['src/app_default/AndroidManifest.xml']

        // 合并 java 目录代码
        java.srcDirs = ['src/app_default/java']

        // 合并 res 资源目录
        res.srcDirs = ['src/app_default/res']
    }
}
```

最后通过在 [app/src][app/src] 目录下，创建与 `productFlavors` 渠道名一样的文件夹 `app_default`、`hua_wei`、`xiao_mi` 即可

**如何切换渠道进行运行安装 APK**

1. 点击菜单栏中的 "Build" -> "Select Build Variant" ( 一般在 Project 侧边栏横条最下方 )
2. 通过点击 ":app" 切换不同渠道

**如何进行打包 APK**

1. 可通过菜单栏中的 "Build" -> "Generate Signed Bundle or APK" 进行选择渠道打包
2. 也可以通过 "Gradle" -> "app" -> "Tasks" -> "build" 点击 `assemble渠道名` 进行构建

**以上为官方多渠道实现方案及流程**，针对该组件化项目如何获取不同渠道信息实现方案如下：

首先创建 `多渠道接口` 用于统一不同渠道使用相同方法获取渠道、数据信息。

参照 `core` 模块 [core/AbstractChannelFlavors][core/AbstractChannelFlavors] 类

```kotlin
/**
 * detail: 多渠道接口
 * @author Ttt
 * 用于统一不同渠道使用相同方法获取渠道、数据信息
 * 通过官方 productFlavors 实现
 * @see https://developer.android.com/studio/build/build-variants
 * 支持不同渠道编译不同代码、logo、布局、资源文件等各种差异化实现
 */
interface AbstractChannelFlavors {

    /**
     * 获取渠道名
     * @return 渠道名
     */
    fun getChannel(): String

    // ==========
    // = 渠道信息 =
    // ==========

    /**
     * 获取指定 Key 渠道信息 ( 只读 )
     * @param key 指定 Key
     * @return 指定渠道信息
     */
    fun getChannelInfo(key: String): String?

    // ==============
    // = 额外携带信息 =
    // ==============

    /**
     * 获取指定 Key 渠道额外携带信息 ( 只读 )
     * @param key 指定 Key
     * @return 指定渠道额外携带信息
     * 专门用于区分渠道信息, 便于后续扩展及区分
     */
    fun getExtraInfo(key: String): String?

    // ============
    // = 可读写数据 =
    // ============

    /**
     * 获取渠道变量操作基类
     * @return DevVariable<String, Any>
     */
    fun getVariable(): DevVariable<String, Any>

    /**
     * 操作渠道变量
     * @param operate 操作类型
     * @return `true` success, `false` fail
     * 可自行决定是否根据 [operate] 存储到本地、删除数据等
     * 防止开发人员传入无法 json 映射数据
     * 尽量建议该 [DevVariable] 用于内存数据读写不操作本地
     * 只是提供一种方案用于不用自行决定统一规范
     */
    fun opVariable(operate: String): Boolean
}
```

为减少各个多渠道 `AbstractChannelFlavors` 实现类，需手动调用初始化，避免对外曝光通过 `class.newInstance()` 进行创建初始化。

参照 [AppChannel][AppChannel] 具体代码

```kotlin
/**
 * detail: APP 渠道信息
 * @author Ttt
 */
object AppChannel : AbstractChannelFlavors {

    // 渠道信息实现
    private val IMPL: AbstractChannelFlavors by lazy {
        newChannelFlavorsIMPL()
    }

    // ==========
    // = 内部方法 =
    // ==========

    // 多渠道实现类名 ( 类名必须一致且包名位置相同 )
    private val IMPL_CLASS_NAME = "afkt_replace.core.lib.channel.ChannelFlavorsIMPL"

    /**
     * 通过 class 创建多渠道实例
     * @return AbstractChannelFlavors IMPL
     */
    private fun newChannelFlavorsIMPL(): AbstractChannelFlavors {
        return try {
            val clazz = Class.forName(IMPL_CLASS_NAME)
            val channelIMPL = clazz.newInstance()
            channelIMPL as AbstractChannelFlavors
        } catch (e: Exception) {
            NotFoundChannelFlavors(e.toString())
        }
    }

    /**
     * detail: 未找到渠道实现
     * @author Ttt
     */
    private class NotFoundChannelFlavors(
        private val errorMessage: String
    ) : AbstractChannelFlavors {

        private val mVariable = DevVariable<String, Any>()

        override fun getChannel(): String {
            return DevFinal.STR.NOT_FOUND.uppercase()
        }

        override fun getChannelInfo(key: String): String {
            return errorMessage
        }

        override fun getExtraInfo(key: String): String {
            return errorMessage
        }

        override fun getVariable(): DevVariable<String, Any> {
            return mVariable
        }

        override fun opVariable(operate: String): Boolean {
            return false
        }
    }
}
```

注意以上代码，常量 `IMPL_CLASS_NAME` 注释

```kotlin
// 多渠道实现类名 ( 类名必须一致且包名位置相同 )
private val IMPL_CLASS_NAME = "afkt_replace.core.lib.channel.ChannelFlavorsIMPL"
```

通过 `Class.forName(IMPL_CLASS_NAME).newInstance()` 方式创建 [AbstractChannelFlavors][AbstractChannelFlavors] 实现类

以 `小米渠道` 为例，参照 [xiao_mi/ChannelFlavorsIMPL][xiao_mi/ChannelFlavorsIMPL]，如上要求实现类，必须类名、包名都一致。

最后通过 `AppChannel.xxxx` 进行获取即可，至此该组件化项目实现多渠道打包完整实现如上。


## 总结与扩展

扩展思路 ( 例 )：

1. 针对官方多渠道方案，可自行编写 gradle task 进行一个渠道构建完自动构建下个渠道，以及自动移动构建完成的 release APK
2. 针对第三方多渠道，可以考虑封装多渠道资源 aar 统一依赖，通过渠道名获取不同信息，或如上定义接口，创建不同实现类，以达到不同渠道使用不同代码、资源等。

两种方案各有优缺点，没有哪个是最完美的方案，如何扩展以及取舍都是需要自己权衡利弊。

不管怎么在各自方案上面进行一定程度上的扩展都还是有缺点，**只有符合自己项目需求的实现方案才是最合适的。**





[Walle 瓦力多渠道打包]: https://github.com/Meituan-Dianping/walle
[VasDolly 多渠道打包]: https://github.com/Tencent/VasDolly
[Android 官方多渠道 productFlavors]: https://developer.android.com/studio/build/build-variants
[main]: https://github.com/afkT/DevComponent
[apk_channel.gradle]: https://github.com/afkT/DevComponent/blob/therouter_single_core/file/gradle/channel/apk_channel.gradle
[app/src]: https://github.com/afkT/DevComponent/blob/therouter_single_core/application/app/src
[AbstractChannelFlavors]: https://github.com/afkT/DevComponent/blob/therouter_single_core/component/core/core/src/main/java/afkt_replace/core/channel/AbstractChannelFlavors.kt
[core/AbstractChannelFlavors]: https://github.com/afkT/DevComponent/blob/therouter_single_core/component/core/core/src/main/java/afkt_replace/core/channel/AbstractChannelFlavors.kt
[AppChannel]: https://github.com/afkT/DevComponent/blob/therouter_single_core/component/core/core/src/main/java/afkt_replace/core/base/core/AppChannel.kt
[xiao_mi/ChannelFlavorsIMPL]: https://github.com/afkT/DevComponent/blob/therouter_single_core/application/app/src/xiao_mi/java/afkt_replace/core/lib/channel/ChannelFlavorsIMPL.kt