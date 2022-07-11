

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

> 当前主线分支 [master][master] 使用的就是该方案，[feature_20220707_walle][feature_20220707_walle] 分支使用的是 [Walle 瓦力多渠道打包][Walle 瓦力多渠道打包] 方案

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









[Walle 瓦力多渠道打包]: https://github.com/Meituan-Dianping/walle
[VasDolly 多渠道打包]: https://github.com/Tencent/VasDolly
[Android 官方多渠道 productFlavors]: https://developer.android.com/studio/build/build-variants
[master]: https://github.com/afkT/DevComponent
[feature_20220707_walle]: https://github.com/afkT/DevComponent/tree/feature_20220707_walle
[apk_channel.gradle]: https://github.com/afkT/DevComponent/blob/main/file/gradle/channel/apk_channel.gradle
[app/src]: https://github.com/afkT/DevComponent/blob/main/application/app/src