package afkt_replace.standard

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

/**
 * detail: 获取 Gradle 文件夹下随机名字
 * @author Ttt
 * Gradle 缓存目录文件命名规则
 * https://www.cnblogs.com/rainboy2010/p/7062279.html
 * https://services.gradle.org/distributions
 * Android Gradle 插件版本说明
 * https://developer.android.com/studio/releases/gradle-plugin
 * 快捷搜索、下载地址:
 * distributionUrl=https\://services.gradle.org/distributions/gradle-6.5-all.zip
 * C:/Users/Administrator/.gradle/wrapper/dists
 */
internal object GradleMain {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = getGradleFileName(
            "https://services.gradle.org/distributions/gradle-6.5-all.zip"
        )
        println(data) // 2oz4ud9k3tuxjg84bbf55q0tn
    }

    private fun getGradleFileName(distributionUrl: String): String? {
        try {
            val messageDigest = MessageDigest.getInstance("MD5")
            messageDigest.update(distributionUrl.toByteArray())
            return BigInteger(1, messageDigest.digest()).toString(36)
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return null
    }
}