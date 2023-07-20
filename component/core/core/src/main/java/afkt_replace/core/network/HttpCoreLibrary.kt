package afkt_replace.core.network

import afkt_replace.core.network.common.OkHttpBuilderGlobal
import afkt_replace.core.network.common.RetrofitResetListenerGlobal
import android.content.Context
import dev.DevHttpManager

/**
 * detail: Http Core Lib
 * @author Ttt
 * 执行循序为
 * [Global.OnRetrofitResetListener] onResetBefore
 * [RetrofitBuilder] onResetBefore
 * [Global.OkHttpBuilder] createOkHttpBuilder
 * [RetrofitBuilder] createRetrofitBuilder
 * [RetrofitBuilder] onReset
 * [Global.OnRetrofitResetListener] onReset
 * 使用全局监听事件、构建操作是为了提供统一管理方法, 方便统一做处理
 * 并且自身也存在回调方法, 也能够单独处理
 * <p></p>
 * 使用方法
 * [DevHttpManager.RM.putRetrofitBuilder]
 * 通过 Key 绑定存储 RetrofitBuilder 并返回 Operation 操作对象
 */
object HttpCoreLibrary {

    // 全局通用 OkHttp Builder
    private val mOkHttpBuilderGlobal = OkHttpBuilderGlobal()

    // 全局 Retrofit 重新构建监听事件
    private val mRetrofitResetListenerGlobal = RetrofitResetListenerGlobal()

    // =============
    // = 对外公开方法 =
    // =============

    /**
     * 初始化 OkHttp 管理库 ( Retrofit 多 BaseUrl 管理、Progress 监听 )
     * @param context Context
     */
    fun initialize(context: Context) {
        // 设置全局 OkHttp Builder 接口对象
        DevHttpManager.RM.setOkHttpBuilder(
            mOkHttpBuilderGlobal
        )
        // 设置全局 Retrofit 重新构建监听事件
        DevHttpManager.RM.setRetrofitResetListener(
            mRetrofitResetListenerGlobal
        )
    }
}