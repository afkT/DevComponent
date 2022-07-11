

## 摘要

* [第三方多渠道](#第三方多渠道)
* [Android 官方多渠道](#Android-官方多渠道)
* [总结与扩展](#总结与扩展)


## 第三方多渠道

目前市面上的多渠道打包工具，大多基于 V1、V2 签名的多渠道打包方案实现。

比较火的有 [Walle 瓦力多渠道打包][Walle 瓦力多渠道打包]、[VasDolly 多渠道打包][VasDolly 多渠道打包] 都是基于该方案实现

具体实现原理可以点击上方链接查看对应 **README**、**WIKI**。

**优点：**不需要重新构建新渠道包，不需要重新签名，打包方式速度非常快，每个渠道包仅需几秒的耗时，非常适合渠道较多的 APK。

**缺点：**

1. 不支持不同渠道编译不同代码、logo、布局、资源文件等各种差异化实现。
2. 依赖作者同步更新实现方案，如 v3 签名、gradle 更新等。


## Android 官方多渠道

另外一种就是 [Android 官方多渠道 productFlavors][Android 官方多渠道 productFlavors] 实现方案。






[Walle 瓦力多渠道打包]: https://github.com/Meituan-Dianping/walle
[VasDolly 多渠道打包]: https://github.com/Tencent/VasDolly
[Android 官方多渠道 productFlavors]: https://developer.android.com/studio/build/build-variants