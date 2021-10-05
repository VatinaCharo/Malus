package VatinaCharo.Commands;

import net.mamoe.mirai.event.events.GroupMessageEvent;

public interface Command {
    String getName();

    String info();

    void onCommand(GroupMessageEvent event);
}
