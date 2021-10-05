package VatinaCharo.Utils

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.value

object Config : AutoSavePluginConfig("${Resources.NAME}Config") {
    var commandPrefix: String by value(Resources.DEFAULT_COMMAND_PREFIX)
    var imageAPI: String by value(Resources.DEFAULT_IMG_API_URL)
    var imageStorage: String by value(Resources.DEFAULT_IMG_STORAGE_PATH)
    var hitokotoAPI: String by value(Resources.DEFAULT_HITOKOTO_API_URL)

    // Command Setting
    var ping: List<Long> by value(arrayListOf(123456789, 987654321))
    var gkd: List<Long> by value(arrayListOf(123456789, 987654321))
    var gkdCD: Int by value(5000)
    var yy: List<Long> by value(arrayListOf(123456789, 987654321))
    var tq: List<Long> by value(arrayListOf(123456789, 987654321))
}