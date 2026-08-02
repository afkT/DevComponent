

```
    Esta rama implementa la navegación por rutas de componentes basada en el framework TheRouter, y utiliza múltiples bibliotecas core lib para implementar la biblioteca de desarrollo central básica.
```


<h1 align="center">DevComponent</h1>


<div align="center">

[![GitHub Profile](https://img.shields.io/badge/GitHub-afkT-orange.svg?style=for-the-badge)](https://github.com/afkT)
[![GitHub License](https://img.shields.io/badge/License-Apache%202.0-blue.svg?style=for-the-badge)](https://github.com/afkT/DevComponent/blob/master/LICENSE)
[![Maven](https://img.shields.io/badge/Maven-Dev-5776E0.svg?style=for-the-badge)](https://search.maven.org/search?q=io.github.afkt)
[![Android API](https://img.shields.io/badge/API-23%2B-brightgreen.svg?style=for-the-badge)](https://developer.android.com/about/versions)

</div>


<p align="center">
	🍋 Este es un proyecto plantilla de Android para componentización. Desarrollar proyectos sobre esta base les otorga capacidades de componentización.
</p>


<p align="center">
	<b>
		<a href="https://github.com/afkT/DevUtils/blob/master/README/android_standard.md">Normas de Android</a>
	</b>、
	<b>
		<a href="https://github.com/afkT/DevUtils/blob/master/README/java_standard.md">Normas de Java</a>
	</b>、
	<b>
		<a href="https://github.com/afkT/DevUtils/blob/master/README/git_standard.md">Normas de Git</a>
	</b>
</p>


<p align="center">
	<b>
		<a href="https://github.com/afkT/DevComponent/blob/therouter/USE_GUIDE.md">Guía de Uso</a>
	</b>、
	<b>
		<a href="https://github.com/afkT/DevComponent/blob/therouter/USE_CHANNEL.md">Empaquetado Multicanal</a>
	</b>
</p>


<p align="center">
	<b>
		<a href="https://github.com/afkT/DevComponent/blob/therouter/USE_RUN.md">Guía de Ejecución</a>
	</b>
</p>


# Acerca de

> La componentización en Android consiste en utilizar múltiples Módulos para representar los distintos módulos de la aplicación, logrando el aislamiento del código y los recursos, y dotando a cada Módulo de la capacidad de ejecutarse por separado y de combinarse.

El código de ejemplo de componentización de este proyecto Android está implementado en [100% Kotlin][100% Kotlin], y utiliza el enfoque [ARouter][ARouter] para lograr la componentización. Todo el proyecto se desarrolla sobre la última pila tecnológica, incluyendo el conjunto de componentes [Android JetPack][Android JetPack] y [Kotlin][Kotlin], y sigue una arquitectura MVVM ( [DataBinding][DataBinding] + [ViewModel][ViewModel] + [Lifecycle][Lifecycle] ).

## Arquitectura de Componentes

| Término | Significado |
| --- | --- |
| Modo integrado | Todos los componentes de negocio son dependencias del proyecto contenedor APP, formando una aplicación completa |
| Proyecto contenedor APP | Básicamente un contenedor vacío, utilizado para integrar todos los componentes funcionales, compilarlos de manera unificada y generar el APK |
| lib_XXX | Módulo de biblioteca extraído con funcionalidades genéricas, que pertenece a los componentes base públicos |
| module_XXX | Componentes de negocio derivados según las funciones, capaces de ejecutarse de forma independiente; pueden funcionar por sí solos o integrarse como biblioteca en el contenedor APP (ej. componente principal, carrito de compras, chat, pedidos, etc.) |
| core | core es la base del proyecto, proporcionando funcionalidades fundamentales independientes del negocio específico, como solicitudes de red y componentes UI genéricos. Además, core es responsable de integrar bibliotecas de funciones (incluyendo algunas compartidas entre módulos relacionadas con el negocio, lo cual es inevitable) |

## Estructura de Directorios de Componentes

### [Directorio](https://github.com/afkT/DevComponent/blob/therouter/component)

```
- component          | Directorio raíz
   - core            | Biblioteca integradora base central
   - libs            | Bibliotecas genéricas y clones de bibliotecas de terceros con modificaciones diferenciales
```

### [core](https://github.com/afkT/DevComponent/blob/therouter/component/core)

Este directorio contiene el código de la biblioteca base central, sobre la cual se desarrolla todo el proyecto componentizado.

> Este [Core Module](https://github.com/afkT/DevComponent/blob/therouter/component/core/core) se construye sobre la [Biblioteca de Desarrollo Serie Dev](https://github.com/afkT/DevUtils),
( todo el desarrollo se basa en este módulo ) para mantener de forma unificada la biblioteca de desarrollo central básica y las dependencias de terceros. Externamente, solo es necesario depender de este módulo para utilizar todo el módulo central ( archivos core y todas las libs internas ).

```
- core                                 | Directorio raíz
   - core                              | Biblioteca integradora base central ( integra internamente las libs de core, externamente solo depende de este módulo )
   - core_base_lib                     | Dependencias de la biblioteca de desarrollo base central ( mantenimiento unificado de dependencias convenientes de libs )
   - libs                              | División de funciones específicas, encapsulamiento en lib
      - lib_base                       | Clases base relacionadas ( Activity, Application, etc. )
      - lib_base_split                 | Desempaquetado de lib básica ( clases base, etc. )
      - lib_bean                       | Clases de entidad genéricas ( entidades de módulo migradas hacia abajo )
      - lib_channel_flavors            | Multicanal de App
      - lib_config                     | Configuración genérica e información de constantes
      - lib_debug_assist               | Biblioteca auxiliar de compilación Debug ( proporciona funciones auxiliares como cambio de entorno, visualización de datos de capturas de paquetes, interruptores de botones de depuración, etc. )
      - lib_engine                     | Lib de Motor genérico ( carga de imágenes, registros, JSON, permisos, selección de recursos, caché )
      - lib_environment                | Biblioteca de cambio de configuración de entorno genérica
      - lib_language                   | Lib de multilenguaje genérica
      - lib_mvvm                       | Encapsulamiento de código genérico MVVM ( los módulos que usen MVVM deben depender por api; si usan compileOnly no encontrarán BindingAdapter, etc. )
      - lib_network                    | Lib relacionada con red ( solicitudes de red, subida y descarga )
      - lib_property                   | Lib de optimización y detección de rendimiento
      - lib_receiver                   | Relacionado con la escucha de broadcasts ( ej. estado de red, batería, desbloqueo de pantalla )
      - lib_router                     | Relacionado con rutas
      - lib_ui                         | Componentes relacionados con style, widget y ui unificados
      - lib_ui_skin                    | Control relacionado con temas y cambio de piel de APP
      - lib_upload                     | Biblioteca de subida genérica
      - lib_utils                      | Biblioteca de herramientas genéricas
      - lib_web                        | Relacionado con WebView
```

### [libs](https://github.com/afkT/DevComponent/blob/therouter/component/libs)

Este directorio pertenece al almacenamiento de encapsulamiento y reutilización de herramientas rápidas de módulos del proyecto, clones de bibliotecas de terceros con modificaciones diferenciales en el código fuente, etc.

```
- libs                         | Directorio raíz
   - lib_splash_ads            | Lib de anuncios en pantalla de inicio
   - lib_tmdb_ui               | Lib de UI genérica de TheMovieDB
```

## Estructura de Directorios de la Aplicación

### [Directorio](https://github.com/afkT/DevComponent/blob/therouter/application)

```
- application       | Directorio raíz
   - app            | Aplicación principal ( proyecto contenedor APP )
   - module         | Módulo de funcionalidad específica ( puede ejecutarse de forma independiente ), utilizado como dependencia por la aplicación principal ( contenedor )
```

### [module](https://github.com/afkT/DevComponent/blob/therouter/application/module)

Los Módulos en este directorio, cuando `isModular=true`, son aplicaciones independientes que pueden ejecutarse por sí solas; si es `false`, son módulos funcionales que son dependencias de la aplicación principal ( contenedor ).

```
- module                    | Directorio raíz
   - module_main            | Módulo de inicio
   - module_movie           | Módulo de películas
   - module_person          | Módulo de personajes
   - module_splash          | Módulo de pantalla de inicio ( página de anuncios, página de introducción al primer lanzamiento )
   - module_template        | Módulo plantilla ( fácil de copiar )
   - module_tv              | Módulo de episodios
```

### Componentes de Arquitectura Base

El módulo **core** dentro de los componentes base es la biblioteca de arquitectura base, que proporciona funciones genéricas altamente extensibles y de baja intrusividad:

- Clase base de Activity: encapsulamiento de comportamientos comunes
  - Flujo de inicialización
  - Interacción de onBackPress con Fragment
  - Monitoreo del estado del ciclo de vida
- Clase base de Fragment: encapsulamiento de comportamientos comunes
  - Flujo de inicialización, vinculación del ciclo de vida
  - Muestra LoadingDialog y Message genéricos
  - Cambio de layouts de estado ( Content, Error, Loading, Empty ), con soporte para configuración personalizada
  - Encapsulamiento de comportamientos para interfaces de lista: Refresh y LoadMore
- Encapsulamiento de Adapter
  - Encapsulamiento de adaptadores de lista RecyclerView
  - Encapsulamiento de adaptadores de lista ViewPager
- Conjunto de clases de herramientas genéricas
- Adaptación de solicitud de permisos dinámicos
- Biblioteca de registros
- Framework de carga de imágenes
- Monitoreo del estado de la red
- Monitoreo del ciclo de vida de la App

> Entre ellos, el componente **lib_engine** admite la implementación personalizada de Motores genéricos ( carga de imágenes, registros, JSON, permisos, selección de recursos, caché, etc. ), permitiendo reemplazar sin problemas las soluciones de bibliotecas de terceros a nivel global.

### Arquitectura Cliente Modular y Componentizada

En general, el diseño de la arquitectura adopta un enfoque modular y componentizado.

- **Modularización**: se centra en la **división funcional, límites claros, bajo acoplamiento entre módulos y alta cohesión dentro de los módulos**. En detalle, esto es una aplicación del principio de responsabilidad única y la ley de Deméter. Independientemente del tipo, siempre que se utilicen para implementar la misma función de negocio, deben estar cohesionados; hacia el exterior del módulo, se debe exponer el mínimo número de métodos públicos, y la interacción entre módulos se realiza a través de protocolos de bajo acoplamiento.
- **Componentización**: en comparación con la modularización, los componentes dentro de los componentes de negocio son independientes; los módulos dependen del proyecto principal, mientras que los componentes pueden depender del proyecto principal o existir de forma independiente. Enfatiza aún más la independencia.
  - La componentización divide de manera más exhaustiva; en la fase de codificación, se puede desarrollar completamente de forma independiente sin el proyecto principal, lo que mejora la eficiencia en la colaboración del equipo y permite un aislamiento total del código entre componentes.
  - Durante el desarrollo y la depuración, los componentes pueden ejecutarse y depurarse por separado, reduciendo el tiempo de compilación y ejecución.
  - Para equipos internos con múltiples proyectos en desarrollo paralelo, la componentización puede extenderse aún más: con el objetivo de reutilización, se pueden dividir múltiples sistemas de software del mismo negocio en varios componentes independientes. Algunos componentes de negocio son genéricos en diferentes proyectos, logrando así el objetivo de reutilización.

#### Implementación de la Arquitectura Modular

- La división de paquetes debe basarse en la separación por módulos, no por tipos de clases.
- Las normas de código del proyecto exigen estrictamente que los miembros organicen las funciones responsables según la modularización.

#### Implementación de la Arquitectura Componentizada

¿Cómo implementar la componentización en Android?

1. Construir una biblioteca base bien encapsulada de la cual dependan todos los componentes; debe ofrecer un potente soporte para la componentización, como arquitectura de rutas, integración de diversas funciones base, algunos modelos de datos compartidos, etc.
2. Sobre la base de la modularización, dividir el proyecto aún más en componentes independientes.
3. Eliminar las dependencias directas entre los distintos componentes.

Android Studio utiliza gradle para la construcción del proyecto. gradle nos permite controlar el proceso de construcción como si fuera programación: cuando un módulo aplica el plugin application, se construirá como un apk; cuando un módulo aplica el plugin library, se construirá como un aar. Esta es precisamente nuestra herramienta clave para la componentización. Mediante diversas configuraciones, podemos lograr con un solo clic cambiar entre la compilación integrada completa o la compilación independiente de cada componente, mejorando enormemente la eficiencia del desarrollo. Dado que es programable, podemos personalizar un plugin de Gradle para implementar configuraciones de construcción flexibles.

##### Configuración de Interruptores

El punto técnico principal es definir el identificador en [modular.gradle](https://github.com/afkT/DevComponent/blob/therouter/file/gradle/modular.gradle)

```groovy
// 模块化编译开关 ( true = 每个模块都是独立应用, false = 整合运行 )
isModular = false
```

Según el identificador, se aplican diferentes [archivos de construcción](https://github.com/afkT/DevComponent/blob/therouter/file/gradle/build/build_module.gradle).

```groovy
def isModular = isModular.toBoolean()

if (isModular) { // 每个模块都是独立应用
    apply from: rootProject.file(files.build_application_gradle)
} else { // 整合运行, 每个 module 都是 library
    apply from: rootProject.file(files.build_library_module_gradle)
}
```

##### Comunicación entre Componentes

Tras la componentización, los componentes están físicamente aislados y no son conscientes de la existencia mutua, pero existe la necesidad de comunicación entre ellos a nivel de negocio. La solución predominante en la industria es la comunicación por rutas. El proyecto adoptará la solución de comunicación de código abierto de Alibaba, [ARouter][ARouter].

##### Archivos de Configuración

* [config.gradle](https://github.com/afkT/DevComponent/blob/therouter/file/gradle/config.gradle) : almacena principalmente información de versiones de bibliotecas de terceros, etc.

* [versions.gradle](https://github.com/afkT/DevComponent/blob/therouter/file/gradle/versions.gradle) : información de compilación y configuración de versiones de la biblioteca core

* [modular.gradle](https://github.com/afkT/DevComponent/blob/therouter/file/gradle/modular.gradle) : configuración de componentización e información de versiones de cada Módulo

> Para las dependencias de la biblioteca core, puede consultar [MavenCentral Links - Crear su propio repositorio Maven](https://github.com/afkT/Resources/blob/main/link/MavenCentral.md) para configurar su propio repositorio Maven y depender directamente mediante aar.

### Bibliotecas Dependidas del Proyecto

- [AndroidX](https://developer.android.com/jetpack/androidx) Serie de componentes de arquitectura y bibliotecas de compatibilidad hacia atrás lanzadas oficialmente por Android.
- [Android Jetpack](https://developer.android.com/jetpack) Android Jetpack es un conjunto compuesto por múltiples bibliotecas.
- [Navigation](https://developer.android.com/guide/navigation) Navegación por rutas de Fragmentos de Android.
- [Kotlin](http://www.kotlincn.net/docs/reference) Soporte del lenguaje Kotlin.
- [Kotlin Coroutines](http://www.kotlincn.net/docs/reference/coroutines/coroutines-guide.html) Soporte de corutinas del lenguaje Kotlin.
- [RxJava](https://github.com/ReactiveX/RxJava) Un framework de programación reactiva basado en la composición de operaciones asíncronas mediante eventos.
- [RxKotlin](https://github.com/ReactiveX/RxKotlin) Extensión de RxJava para Kotlin.
- [RxAndroid](https://github.com/ReactiveX/RxAndroid) Extensión de RxJava para la plataforma Android.
- [AutoDispose](https://github.com/uber/AutoDispose) Utilizado para vincular flujos de eventos de RxJava al ciclo de vida de componentes de Android, evitando fugas de memoria.
- [OkHttp3](https://github.com/square/okhttp) Implementación del protocolo Http.
- [Retrofit2](https://github.com/square/retrofit) Cliente HTTP seguro en tipos para Android y Java.
- [Gson](https://github.com/google/gson) Herramienta de serialización Json.
- [Glide](https://github.com/bumptech/glide) Framework de carga de imágenes.
- [MMKV](https://github.com/Tencent/MMKV) Biblioteca de almacenamiento key-value de alto rendimiento.
- [ARouter](https://github.com/alibaba/ARouter) Framework de navegación por rutas.
- [LiveEventBus](https://github.com/JeremyLiao/LiveEventBus) Es un bus de mensajes para Android, basado en LiveData, con capacidad de percepción del ciclo de vida.
- [AndroidAutoSize](https://github.com/JessYanCoding/AndroidAutoSize/blob/master/README-zh.md) Versión definitiva de la solución de adaptación de pantalla de Toutiao.
- [SmartRefreshLayout](https://github.com/scwang90/SmartRefreshLayout) Framework de actualización por deslizamiento.
- [Biblioteca Serie Dev](https://github.com/afkT/DevUtils) Biblioteca de herramientas de Android, clases auxiliares, clases base, biblioteca UI, biblioteca de cambio de configuración de entorno.
- [Empaquetado Multicanal](https://github.com/afkT/DevComponent/blob/therouter/USE_CHANNEL.md)

Herramientas de Depuración:

- [Bugly](https://bugly.qq.com/docs) Reporte de excepciones y estadísticas operativas.
- [LeakCanary](https://github.com/square/leakcanary) Herramienta de detección de fugas de memoria.
- [BlockCanary](https://github.com/markzhai/AndroidPerformanceMonitor/blob/master/README_CN.md) Componente de monitoreo de rendimiento.
- [UeTool](https://github.com/eleme/UETool) Herramienta de depuración UI de Eleme.

### Problemas y Soluciones

* ¿Cómo manejar el problema de múltiples Applications tras la componentización?

1. Utilizar [Jetpack App Startup](https://blog.csdn.net/qq_40909351/article/details/106726204) para implementar la inicialización de múltiples Módulos
2. Utilizar ARouter IProvider ( también se puede usar IProvider para comunicación entre componentes )

* ¿Cómo comunicarse entre componentes?

1. Utilizar [LiveEventBus](https://github.com/JeremyLiao/LiveEventBus) / [EventBus](https://github.com/greenrobot/EventBus)
2. Utilizar ARouter IProvider, implementación de interfaces personalizadas, BroadcastReceive, etc.

### Enlaces

* [Component Links](https://github.com/afkT/Resources/blob/main/link/Component.md)

* [MavenCentral Links - Crear su propio repositorio Maven](https://github.com/afkT/Resources/blob/main/link/MavenCentral.md)

* [¿Qué es la Componentización en Android?](https://blog.csdn.net/u011692041/article/details/92572758)

* [ARouter de Alibaba, ¡unas pocas preguntas que "no sabes"!](https://mp.weixin.qq.com/s/vYsVJI1SoT4gaiMGoEkx-Q)

* [Sobre la arquitectura de Android, ¿sigue copiando ciegamente?](https://juejin.cn/post/6942464122273398820)

* [Después de introducir la arquitectura Jetpack, ¿qué cambios ocurrirán en tu App?](https://juejin.cn/post/6955491901265051661)

### Proyectos de Referencia

* [AndroidModulePattern](https://github.com/guiying712/AndroidModulePattern)

* [Component](https://github.com/xiaojinzi123/Component)


## Licencia

    Derechos de autor 2022 afkT

    Licenciado bajo la Licencia Apache, Versión 2.0 (la "Licencia");
    no podrá utilizar este archivo excepto en cumplimiento con la Licencia.
    Puede obtener una copia de la Licencia en

       http://www.apache.org/licenses/LICENSE-2.0

    Salvo lo exijable por ley aplicable o acordado por escrito, el software
    distribuido bajo la Licencia se distribuye en una BASE "TALES CUALES",
    SIN GARANTÍAS O CONDICIONES DE NINGÚN TIPO, ya sean expressas o implícitas.
    Consulte la Licencia para conocer el lenguaje específico que rige los permisos y
    las limitaciones conforme a la Licencia.





[100% Kotlin]: http://www.kotlincn.net/docs/reference
[ARouter]: https://github.com/alibaba/ARouter
[Android JetPack]: https://developer.android.com/jetpack
[Kotlin]: http://www.kotlincn.net/docs/reference
[DataBinding]: https://developer.android.com/topic/libraries/data-binding
[ViewModel]: https://developer.android.com/topic/libraries/architecture/viewmodel
[Lifecycle]: https://developer.android.com/topic/libraries/architecture/lifecycle
```
