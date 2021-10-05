package VatinaCharo.Commands;

import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import net.mamoe.mirai.message.data.QuoteReply;

import java.util.List;

public abstract class SimpleCommand implements Command {
    private final String name;
    private final String usage;

    public SimpleCommand(String name, String usage) {
        this.name = name;
        this.usage = usage;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String info() {
        return getName() + " -> " + usage;
    }

    @Override
    public void onCommand(GroupMessageEvent event) {
        if (getGroups().contains(event.getGroup().getId())) {
            handle(event);
        } else {
            MessageChain msg = new MessageChainBuilder()
                    .append(new QuoteReply(event.getSource()))
                    .append(new At(event.getSender().getId()))
                    .append("\n当前群未启用此指令 >_<")
                    .build();
            event.getGroup().sendMessage(msg);
        }
    }

    abstract List<Long> getGroups();

    abstract void handle(GroupMessageEvent event);
}
