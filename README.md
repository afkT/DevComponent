```
    该分支基于 ARouter 框架实现组件化路由导航，
    单 core lib 实现基础核心开发库，减少 lib module 数量及维护开发难度
```


<h1 align="center">DevComponent</h1>


<p align="center">
	<a href="https://github.com/afkT">
		<img alt="Profile" src="https://img.shields.io/badge/GitHub-afkT-orange.svg" />
	</a>
	<a href="https://github.com/afkT/DevComponent/blob/master/LICENSE">
		<img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg" />
	</a>
	<a href="https://search.maven.org/search?q=io.github.afkt">
		<img alt="Version" src="https://img.shields.io/badge/Maven-Dev-5776E0.svg" />
	</a>
	<a href="https://android-arsenal.com/api?level=14">
		<img alt="API" src="https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=flat" />
	</a>
</p>


<p align="center">
	🍋 这是一个 Android 组件化模板项目，通过在此基础上开发项目，使其拥有组件化能力。
</p>


<p align="center">
	<b>
		<a href="https://github.com/afkT/DevUtils/blob/master/README/android_standard.md">Android 规范</a>
	</b>、
	<b>
		<a href="https://github.com/afkT/DevUtils/blob/master/README/java_standard.md">Java 规范</a>
	</b>、
	<b>
		<a href="https://github.com/afkT/DevUtils/blob/master/README/git_standard.md">Git 规范</a>
	</b>
</p>


<p align="center">
	<b>
		<a href="https://github.com/afkT/DevComponent/blob/arouter_single_core/USE_GUIDE.md">使用说明</a>
	</b>、
	<b>
		<a href="https://github.com/afkT/DevComponent/blob/arouter_single_core/USE_CHANNEL.md">多渠道打包</a>
	</b>
</p>


<p align="center">
	<b>
		<a href="https://github.com/afkT/DevComponent/blob/arouter_single_core/USE_RUN.md">运行说明</a>
	</b>
</p>


# About

> Android 组件化就是利用多个 Module 来表示应用的多个模块实现代码和资源的隔离，并且每个 Module 都有单独运行和组合的能力。

该 Android 项目组件化示例代码 [100% Kotlin][100% Kotlin] 实现，使用 [ARouter][ARouter] 方案实现组件化，
整个项目基于 [Android JetPack][Android JetPack] 组件库 + [Kotlin][Kotlin] 等最新技术栈进行开发，
使用 MVVM 架构 ( [DataBinding][DataBinding] + [ViewModel][ViewModel] + [Lifecycle][Lifecycle] )

## 组件化架构

| 名词 | 含义 |
| --- | --- |
| 集成模式 | 所有的业务组件被 APP 壳工程依赖，组成一个完整的 APP |
| APP 壳工程 | 基本上是一个空壳，用于集成所有的功能组件，统一编译，用于输出组中的 APK |
| lib_XXX | 抽离的一个库模块，功能具有通用性，属于公共基础组件 |
| module_XXX | 根据业务分出来的能独立运行的业务组件，即可以单独运行，又可以库的形式集成到 APP 壳中，比如 Main 组件、购物车组件、聊天组件、订单组件等。 |
| core | core 是项目的基础，提供最基础的、与具体业务无关的功能，比如网络请求、通用 UI 组件、core 还负责集成功能的 Library 库 ( 当中也有一些业务相关的、各模块共享的功能，这是不可避免的 ) |

## 组件目录展示

### [目录](https://github.com/afkT/DevComponent/blob/arouter_single_core/component)

```
- component          | 根目录
   - core            | 核心基础整合库
   - libs            | 通用 Library、第三方库 clone 差异化修改
```

### [core](https://github.com/afkT/DevComponent/blob/arouter_single_core/component/core)

该目录属于核心基础库代码，整个组件化项目基于该基础上进行开发

> 该 [Core Module](https://github.com/afkT/DevComponent/blob/arouter_single_core/component/core/core) 基于 [Dev 系列开发库](https://github.com/afkT/DevUtils) 搭建，
( 全部开发基于该 module ) 用于统一维护基础核心开发库、第三方库依赖，对外只需要依赖该 module 便可使用整个核心模块 ( core 文件以及内部所有 libs )

```
- core                              | 根目录
   - core                           | 核心基础整合库 ( 内部集成 core libs, 对外依赖该 module 即可 )
   - core_desc                      | 核心基础库依赖描述
   - core_project                   | 项目相关代码库
   - libs                           | 具体功能拆分, 封装 lib
      - lib_debug_assist            | Debug 编译辅助开发库 ( 提供切换环境、抓包数据可视化、调试按钮开关等辅助功能 )
      - lib_environment             | 通用环境配置切换库
```

### [libs](https://github.com/afkT/DevComponent/blob/arouter_single_core/component/libs)

该目录属于 项目模块快捷工具封装复用、第三方库 clone 对源码进行差异化修改使用等存储目录

```
- libs                         | 根目录
   - lib_splash_ads            | 启动页广告 lib
   - lib_tmdb_ui               | TheMovieDB 通用 UI lib
```

## 应用程序目录展示

### [目录](https://github.com/afkT/DevComponent/blob/arouter_single_core/application)

```
- application       | 根目录
   - app            | 主体应用 ( APP 壳工程 )
   - module         | 具体功能模块 ( 可单独运行 )，被主体应用 ( 壳 ) 所依赖使用
```

### [module](https://github.com/afkT/DevComponent/blob/arouter_single_core/application/module)

该目录下的 Module 在 `isModular=true` 的情况下，都属于独立的应用可单独运行，为 `false` 则都属于功能模块，被主体应用 ( 壳 ) 所依赖使用

```
- module                    | 根目录
   - module_main            | 首页 Module
   - module_movie           | 电影 Module
   - module_person          | 人物 Module
   - module_splash          | 启动页 ( 广告页、首次启动引导页 ) Module
   - module_template        | 模板 Module ( 便于 copy )
   - module_tv              | 剧集 Module
```

### 基础架构组件

基础组件中的 **core** 模块是基础架构库，提供可扩展性强低侵入性的通用功能：

- Activity 基类：公共行为封装
  - 初始化流程
  - onBackPress 与 Fragment 联动
  - 生命周期状态监控
- Fragment 基类：公共行为封装
  - 初始化流程、生命周期绑定
  - 显示通用的 LoadingDialog 和 Message
  - 状态布局的切换 ( Content、Error、Loading、Empty )，同时支持个性化配置
  - List 界面的行为封装：Refresh 和 LoadMore
- Adapter 封装
  - RecyclerView 列表适配器封装
  - ViewPager 列表适配器封装
- 通用的工具类集合
- 动态权限申请适配
- 日志库
- 图片加载框架
- 网络状态的监控
- App 生命周期的监控

> 其中 **lib_engine** 组件支持通用 Engine ( 图片加载、日志、JSON、权限、资源选择、缓存等 ) 进行自行实现，无缝全局替换第三方库实现方案

### 客户端模块化组件化架构

整体上，架构设计采用的是模块化组件化架构。

- **模块化**：模块化注重的是 **功能划分，边界清晰，模块间低耦合，模块内高内聚**。展开来讲，这也是对单一功能设计原则和迪米特法则的应用。不管你是什么类型，只要你们用于实现同一业务功能，你们之间应该是内聚的，然后对于模块之外，应该做到尽量少的暴露公共方法，模块间通过低耦合的协议进行交互，这就是低耦合的体现。
- **组件化**：相对于模块化，业务组件中的组件是可独立的，模块依附于主工程，而组件可以依附在主工程也可以独立存在。它更加强调独立。
  - 组件化拆分的更彻底，在编码阶段，可以完全脱离主工程进行独立开发，这在团队协作上也有一定的效率提升，而且组件之间的代码可以做的完全隔离。
  - 开发调试时，组件可以单独运行调试，从而减少了编译运行的时间消耗。
  - 对于一个团队内部，如果存在多个并行开发的项目，组件化还可以进一步扩展，按照基于可重用的目的，将同一业务的多个软件系统拆分成多个独立的组件，某些业务组件在不同的项目中都是通用的，这样就达到了复用的目的。

#### 模块化架构实施

- 在分包上按照模块划分进行分包，而不是按照类类型进行分包。
- 在项目代码规范上严格要求项目成员按照模块化组织所负责的功能。

#### 组件化架构实施

在 Android 中如何实施组件化？

1. 构建一个封装完善的基础库，各个组件都依赖于它，它应该为组件化提供强大的支持，比如路由架构、各种基础功能的集成、一些共享数据模型等。
2. 在模块化的基础上，进一步将项目划分为各个独立的组件。
3. 消除各个组件之间的直接依赖。

Android Studio 使用 gradle 进行项目构建，gradle 使得我们可以像编程一样控制构建过程，当一个模块应用 application 插件时，它将被构建为 apk，当一个模块应用 library 插件时，
它将被构建为 aar，这正是我们进行组件化的利器，通过各种配置达到一键切换整体编译集成还是各个组件独立编译允许的目的。从而大大的提高开发效率。当然既然是可以编程的，
我们可以自定义一个 Gradle 插件，实现灵活的项目构建配置。

##### 开关配置

主要技术点为在 [modular.gradle](https://github.com/afkT/DevComponent/blob/arouter_single_core/file/gradle/modular.gradle) 中定义标识

```groovy
// 模块化编译开关 ( true = 每个模块都是独立应用, false = 整合运行 )
isModular = false
```

根据标识，应用不同的[构建文件](https://github.com/afkT/DevComponent/blob/arouter_single_core/file/gradle/build/build_module.gradle)。

```groovy
def isModular = isModular.toBoolean()

if (isModular) { // 每个模块都是独立应用
    apply from: rootProject.file(files.build_application_gradle)
} else { // 整合运行, 每个 module 都是 library
    apply from: rootProject.file(files.build_library_module_gradle)
}
```

##### 组件间通信

经过组件化后，组件之间在物理上被隔离，组件间彼此不感知对方的存在，但是在业务上组件间又有通信的需求，
业界主流采用的方案是采用路由通信。项目将会采用阿里巴巴开源的 [ARouter][ARouter] 通信通信方案。

##### 配置文件

* [config.gradle](https://github.com/afkT/DevComponent/blob/arouter_single_core/file/gradle/config.gradle) ：主要存储第三方库版本信息等

* [versions.gradle](https://github.com/afkT/DevComponent/blob/arouter_single_core/file/gradle/versions.gradle) ：构建信息以及 core 库版本配置

* [modular.gradle](https://github.com/afkT/DevComponent/blob/arouter_single_core/file/gradle/modular.gradle) ：组件化配置以及各个 Module 版本信息

> 关于 core 库依赖可以参考 [MavenCentral Links - 搭建自己的 Maven 库](https://github.com/afkT/Resources/blob/main/link/MavenCentral.md) 搭建自己的 Maven 库，通过 aar 直接进行依赖

### 项目依赖库

- [AndroidX](https://developer.android.com/jetpack/androidx) Android 官方推出的一系列架构组件与向下兼容库。
- [Android Jetpack](https://developer.android.com/jetpack) Android Jetpack 是一个由多个库组成的套件。
- [Navigation](https://developer.android.com/guide/navigation) Android Fragment 路由导航。
- [Kotlin](http://www.kotlincn.net/docs/reference) Kotlin 语言支持。
- [Kotlin Coroutines](http://www.kotlincn.net/docs/reference/coroutines/coroutines-guide.html) Kotlin 语言协程支持。
- [RxJava](https://github.com/ReactiveX/RxJava) 一个基于事件组合异步操作的响应式编程框架。
- [RxKotlin](https://github.com/ReactiveX/RxKotlin) RxJava 在 Kotlin 上的扩展。
- [RxAndroid](https://github.com/ReactiveX/RxAndroid) RxJava 在 Android 平台上的扩展。
- [AutoDispose](https://github.com/uber/AutoDispose) 用于将 RxJava 事件流绑定到 Android 组件生命周期，以避免内存泄漏。
- [OkHttp3](https://github.com/square/okhttp) Http 协议实现。
- [Retrofit2](https://github.com/square/retrofit) Type-safe HTTP client for Android and Java.
- [Gson](https://github.com/google/gson) Json 序列化工具。
- [Glide](https://github.com/bumptech/glide) 图片加载框架。
- [MMKV](https://github.com/Tencent/MMKV) 高性能 key-value 存储库。
- [ARouter](https://github.com/alibaba/ARouter) 路由导航框架。
- [LiveEventBus](https://github.com/JeremyLiao/LiveEventBus) 是一款 Android 消息总线，基于 LiveData，具有生命周期感知能力。
- [AndroidAutoSize](https://github.com/JessYanCoding/AndroidAutoSize/blob/master/README-zh.md) 今日头条屏幕适配方案终极版。
- [SmartRefreshLayout](https://github.com/scwang90/SmartRefreshLayout) 下拉刷新框架。
- [Dev 系列库](https://github.com/afkT/DevUtils) Android 工具类库、辅助类、基类、UI 库、环境配置切换库。
- [多渠道打包](https://github.com/afkT/DevComponent/blob/arouter_single_core/USE_CHANNEL.md)

调试工具：

- [Bugly](https://bugly.qq.com/docs) 异常上报和运营统计。
- [LeakCanary](https://github.com/square/leakcanary) 内存泄漏检测工具。
- [BlockCanary](https://github.com/markzhai/AndroidPerformanceMonitor/blob/master/README_CN.md) 性能监控组件。
- [UeTool](https://github.com/eleme/UETool) 饿了么 UI 调试工具。

### 问题及解决方案

* 如何处理组件化后多 Application 问题

1. 使用 [Jetpack App Startup](https://blog.csdn.net/qq_40909351/article/details/106726204) 实现多 Module 初始化
2. 使用 ARouter IProvider ( 且也可以使用 IProvider 进行组件间通讯 )

* 组件间如何通讯

1. 使用 [LiveEventBus](https://github.com/JeremyLiao/LiveEventBus) / [EventBus](https://github.com/greenrobot/EventBus)
2. 使用 ARouter IProvider、自定义接口实现、BroadcastReceive 等

### Link

* [Component Links](https://github.com/afkT/Resources/blob/main/link/Component.md)

* [MavenCentral Links - 搭建自己的 Maven 库](https://github.com/afkT/Resources/blob/main/link/MavenCentral.md)

* [什么是 Android 组件化](https://blog.csdn.net/u011692041/article/details/92572758)

* [阿里的ARouter，问几个你 “不会” 的问题！](https://mp.weixin.qq.com/s/vYsVJI1SoT4gaiMGoEkx-Q)

* [关于 Android 架构，你是否还在生搬硬套？](https://juejin.cn/post/6942464122273398820)

* [引入 Jetpack 架构后，你的 App 会发生哪些变化？](https://juejin.cn/post/6955491901265051661)

### 参考项目

* [AndroidModulePattern](https://github.com/guiying712/AndroidModulePattern)

* [Component](https://github.com/xiaojinzi123/Component)


## License

    Copyright 2022 afkT

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.





[100% Kotlin]: http://www.kotlincn.net/docs/reference
[ARouter]: https://github.com/alibaba/ARouter
[Android JetPack]: https://developer.android.com/jetpack
[Kotlin]: http://www.kotlincn.net/docs/reference
[DataBinding]: https://developer.android.com/topic/libraries/data-binding
[ViewModel]: https://developer.android.com/topic/libraries/architecture/viewmodel
[Lifecycle]: https://developer.android.com/topic/libraries/architecture/lifecycle
