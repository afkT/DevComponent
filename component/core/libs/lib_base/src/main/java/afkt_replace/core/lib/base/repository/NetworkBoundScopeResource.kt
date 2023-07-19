package afkt_replace.core.lib.base.repository

import afkt_replace.core.lib.base.core.AppExecutors
import afkt_replace.core.lib.base.split.inter.FunctionFlowCall
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.retrofit.scopeExecuteRequest
import kotlinx.coroutines.CoroutineScope

abstract class NetworkBoundScopeResource<ResultType>(
    private val scope: CoroutineScope,
    appExecutors: AppExecutors = AppExecutors.instance(),
    private val flowCall: FunctionFlowCall? = null
) : NetworkBoundResource<ResultType, ResultType>(appExecutors) {

    override fun fetchService(): LiveData<ApiResponse<ResultType>> {
        val liveData = MutableLiveData<ApiResponse<ResultType>>()
        val uuid = fetchRandomUUID()
        scope.scopeExecuteRequest(
            block = {
                flowCall?.block()
                fetchRequest()
            },
            start = {
                if (shouldFetchByUUID(uuid)) {
                    flowCall?.start()
                }
            },
            error = {
                if (shouldFetchByUUID(uuid)) {
                    flowCall?.error()
                    liveData.value = ApiResponse.error(
                        it, null
                    )
                }
            },
            success = {
                if (shouldFetchByUUID(uuid)) {
                    flowCall?.success()
                    if (it != null) {
                        liveData.value = ApiResponse.success(it)
                    } else {
                        liveData.value = ApiResponse.empty()
                    }
                }
            },
            finish = {
                if (shouldFetchByUUID(uuid)) {
                    flowCall?.finish()
                }
            }
        )
        return liveData
    }

    // ==========
    // = 二次封装 =
    // ==========

    abstract suspend fun fetchRequest(): ResultType?

    abstract fun fetchRandomUUID(): Long

    abstract fun shouldFetchByUUID(uuid: Long): Boolean
}