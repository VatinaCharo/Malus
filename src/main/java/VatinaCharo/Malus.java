package VatinaCharo;

import VatinaCharo.Commands.*;
import VatinaCharo.Utils.Config;
import VatinaCharo.Utils.Resources;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.GroupMessageEvent;

import java.io.File;

public final class Malus extends JavaPlugin {
    public static final Malus INSTANCE = new Malus();
    public CommandsManager commandsManager;

    private Malus() {
        super(new JvmPluginDescriptionBuilder(Resources.ID, Resources.VERSION)
                .name(Resources.NAME)
                .info(Resources.DESCRIPTION)
                .author(Resources.AUTHOR)
                .build());
    }

    @Override
    public void onEnable() {
        getLogger().info(Resources.NAME_CN + "：正在加载...");
        getLogger().info(Resources.NAME + ": Loading...");
        // 加载配置文件
        reloadPluginConfig(Config.INSTANCE);
        // 初始化图片文件夹
        initImageStorage();

        if (Resources.isInitImageStorage) {
            //创建自建指令管理系统
            commandsManager = new CommandsManager(Config.INSTANCE.getCommandPrefix(), getLogger());
            getLogger().info(Resources.NAME_CN + "：注册指令中...");
            getLogger().info(Resources.NAME + ": registering commands...");
            //注册自建指令
            // TODO: 2021/10/6 tq(天气)
            commandsManager.registerCommand(new Ping());
            commandsManager.registerCommand(new GetRandImage());
            commandsManager.registerCommand(new Hitokoto());
            //注入群事件到自建的指令系统之中
            GlobalEventChannel.INSTANCE.subscribeAlways(GroupMessageEvent.class, commandsManager::handle);

            getLogger().info(Resources.NAME_CN + "：加载成功");
            getLogger().info(Resources.NAME + ": Succeed to Load");
        } else {
            getLogger().info(Resources.NAME_CN + "：加载失败");
            getLogger().info(Resources.NAME + ": Failed to Load");
        }
    }

    public void initImageStorage() {
        File imageStorage = new File(Config.INSTANCE.getImageStorage());
        if (!imageStorage.exists()) {
            if (!imageStorage.mkdirs()) {
                Resources.isInitImageStorage = false;
                getLogger().error(Resources.NAME_CN + "：创建图片文件夹失败");
                getLogger().error(Resources.NAME + ": Failed to create image storage folder");
            }
        }

    }
}