package afkt_replace.standard.function

object Code {

    /**
     * 是否隐藏文件、文件夹判断
     * @param path 待判断文件路径
     * @return `true` yes, `false` no
     */
    fun isHidden(path: String?): Boolean {
        return path?.let {
            val temp: String = it.replace("/../..".toRegex(), "")
                .replace("\\...\\...".toRegex(), "")
                .replace("\\..\\..".toRegex(), "")
            // 判断是否存在特殊情况
            if (temp.contains("..")) {
                println("path replace 未彻底 : $it")
            }
            return temp.contains("\\.") || temp.contains("/.")
        } ?: false
    }

    /**
     * 是否 Build 文件、文件夹
     * @param path 待判断文件路径
     * @return `true` yes, `false` no
     */
    fun isBuild(path: String?): Boolean {
        return path?.let {
            if (it.contains("\\build\\")) return true
            if (it.contains("/build/")) return true
            return false
        } ?: false
    }
}