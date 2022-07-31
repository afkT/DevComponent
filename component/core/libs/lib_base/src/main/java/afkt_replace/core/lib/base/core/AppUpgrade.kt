package afkt_replace.core.lib.base.core

import android.Manifest
import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
import dev.engine.permission.IPermissionEngine
import dev.kotlin.engine.permission.permission_request
import dev.utils.app.AppUtils
import dev.utils.app.IntentUtils
import dev.utils.app.permission.PermissionUtils
import dev.utils.common.FileUtils
import java.io.File

/**
 * detail: APP 升级
 * @author Ttt
 * <!-- 安装权限 -->
 * <uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES" />
 */
object AppUpgrade {

    // =========
    // = 内部类 =
    // =========

    /**
     * detail: App 版本信息
     * @author Ttt
     */
    open class VersionInfo(
        val id: Long = 0L, // 版本升级递增 id
        val content: String? = "", // 版本更新内容
        val isForce: Boolean? = false, // 是否强制升级
        val publishMillis: Long = 0L, // 版本发布时间 ( 毫秒 )
        val channel: String? = "", // 渠道信息
        val versionCode: Long = 0, // APP 版本号
        val versionName: String? = "", // APP 版本名
        val fileMD5: String? = null, // APK MD5
        val fileSHA256: String? = null, // APK SHA256
        val fileUrl: String? = null, // APK 下载链接
        val upgradeCode: Long = 0L, // 升级版本号
        val type: Int = 0, // 自定义字段 ( 扩展区分类型 )
        val extras: String? = "", // 额外携带信息 ( 如 json 自行进行映射 )
        val moduleId: Int = 0, // 模块 Id
        val moduleName: String? = "", // 模块名
        val featureVersion: String? = "" // 模块功能版本标识 ( 需求、RP 版本 )
    )

    // =============
    // = 对外公开方法 =
    // =============

    /**
     * 版本比较是否存在新版本
     * @param versionCode APP 当前版本号
     * @param info APP 版本信息
     * @return `true` yes, `false` no
     */
    fun versionCompare(
        versionCode: Long,
        info: VersionInfo?
    ): Boolean {
        return info?.let {
            it.versionCode > versionCode
        } ?: false
    }

    /**
     * 判断是否低版本 ( 后台版本低于当前版本 )
     * @param versionCode APP 当前版本号
     * @param info APP 版本信息
     * @return `true` yes, `false` no
     */
    fun isLowVersion(
        versionCode: Long,
        info: VersionInfo?
    ): Boolean {
        return info?.let {
            it.versionCode < versionCode
        } ?: false
    }

    // =======
    // = 安装 =
    // =======

    /**
     * detail: 安装方法状态事件 ( 非结果属于方法执行回调 )
     * @author Ttt
     */
    interface InstallListener {

        /**
         * 安装文件未找到
         */
        fun onFileNotFound()

        /**
         * 安装方法调用触发
         * @param result 安装方法执行结果 ( 非安装结果 )
         */
        fun onInstall(result: Boolean)

        /**
         * 安装权限申请
         * @param file 待安装文件
         * 内部可调用 [installPermission]
         */
        fun onPermission(file: File?)
    }

    /**
     * 安装 APP ( 支持 8.0 ) 的意图
     * @param filePath 文件路径
     * @param listener 安装方法状态事件
     */
    fun installApp(
        filePath: String?,
        listener: InstallListener
    ) {
        installApp(FileUtils.getFileByPath(filePath), listener)
    }

    /**
     * 安装 APP ( 支持 8.0 ) 的意图
     * @param file 文件
     * @param listener 安装方法状态事件
     */
    fun installApp(
        file: File?,
        listener: InstallListener
    ) {
        if (FileUtils.isFileExists(file)) {
            // Android 8.0 以上
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (PermissionUtils.canRequestPackageInstalls()) {
                    val result = AppUtils.installApp(file)
                    listener.onInstall(result)
                } else {
                    // isFileExists 已判 null, 这里肯定不为 null
                    listener.onPermission(file)
                }
            } else {
                val result = AppUtils.installApp(file)
                listener.onInstall(result)
            }
        } else {
            listener.onFileNotFound()
        }
    }

    /**
     * 安装权限申请封装方法
     * @param activity Activity
     * @param callback 权限请求回调
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun installPermission(
        activity: Activity,
        callback: IPermissionEngine.Callback
    ) {
        activity.permission_request(
            permissions = arrayOf(
                Manifest.permission.REQUEST_INSTALL_PACKAGES
            ),
            callback = object : IPermissionEngine.Callback {
                override fun onGranted() {
                    callback.onGranted()
                }

                override fun onDenied(
                    grantedList: List<String>,
                    deniedList: List<String>,
                    notFoundList: List<String>
                ) {
                    if (deniedList.isNotEmpty()) {
                        // 跳转设置页面, 开启安装未知应用权限
                        AppUtils.startActivity(IntentUtils.getLaunchAppInstallPermissionSettingsIntent())
                        // 触发回调
                        callback.onDenied(
                            grantedList, deniedList, notFoundList
                        )
                    } else {
                        callback.onGranted()
                    }
                }
            }
        )
    }
}