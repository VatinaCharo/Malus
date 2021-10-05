package VatinaCharo.Commands;

import VatinaCharo.Utils.Config;
import VatinaCharo.Utils.Resources;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import net.mamoe.mirai.message.data.QuoteReply;

import java.util.List;

public class Ping extends SimpleCommand {
    public Ping() {
        super("ping", Resources.NAME_CN + "会@你并回复'pong'");
    }

    @Override
    List<Long> getGroups() {
        return Config.INSTANCE.getPing();
    }

    @Override
    void handle(GroupMessageEvent event) {
        MessageChain msg = new MessageChainBuilder()
                .append(new QuoteReply(event.getSource()))
                .append(new At(event.getSender().getId()))
                .append("\npong")
                .build();
        event.getGroup().sendMessage(msg);
    }
}
