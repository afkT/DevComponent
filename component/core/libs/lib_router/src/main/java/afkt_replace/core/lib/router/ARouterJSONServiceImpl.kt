package afkt_replace.core.lib.router

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.service.SerializationService
import dev.kotlin.engine.json.fromJson
import dev.kotlin.engine.json.toJson
import java.lang.reflect.Type

/**
 * detail: ARouter JSON 序列号转换服务
 * @author Ttt
 */
@Route(path = "/service/json_serialization")
class ARouterJSONServiceImpl : SerializationService {

    override fun init(context: Context?) {
    }

    override fun <T : Any> json2Object(
        input: String?,
        clazz: Class<T>?
    ): T? {
        return parseObject(input, clazz)
    }

    override fun object2Json(instance: Any?): String? {
        return instance?.toJson()
    }

    override fun <T : Any> parseObject(
        input: String?,
        clazz: Type?
    ): T? {
        return input?.fromJson(typeOfT = clazz)
    }
}