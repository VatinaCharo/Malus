package VatinaCharo.Commands;

import VatinaCharo.Utils.Resources;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.utils.MiraiLogger;

import java.util.ArrayList;

public class CommandsManager {
    public final String commandPrefix;
    private final MiraiLogger logger;
    private final Help help = new Help(this);
    public static final ArrayList<Command> COMMANDS = new ArrayList<>();
    public static final ArrayList<SimpleCommand> SIMPLE_COMMANDS = new ArrayList<>();

    public CommandsManager(String commandPrefix, MiraiLogger logger) {
        this.commandPrefix = commandPrefix;
        this.logger = logger;
        COMMANDS.add(help);
    }

    public void registerCommand(SimpleCommand sc) {
        COMMANDS.add(sc);
        SIMPLE_COMMANDS.add(sc);
        logger.info(Resources.NAME_CN + "：注册简单指令：" + sc.getName());
        logger.info(Resources.NAME + ": register simple command：" + sc.getName());
    }

    /**
     * 解析群消息，提取合法的指令消息，并分配给对应的指令去处理事件
     *
     * @param event 机器人传入的群消息事件
     */
    public void handle(GroupMessageEvent event) {
        String[] rawText = event.getMessage().contentToString().split(" ");
        if (rawText[0].startsWith(commandPrefix)) {
            String name = rawText[0].substring(2);
            //分派解析后的指令到对应的command处理
            if (name.equals(help.getName())) {
                help.onCommand(event);
            } else {
                SIMPLE_COMMANDS.stream()
                        .filter(sc -> name.equals(sc.getName()))
                        .forEach(sc -> sc.onCommand(event));
            }
        }
    }
}
