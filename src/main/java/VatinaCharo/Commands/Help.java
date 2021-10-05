package VatinaCharo.Commands;

import VatinaCharo.Utils.Resources;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import net.mamoe.mirai.message.data.QuoteReply;

public class Help implements Command {
    private final CommandsManager commandsManager;

    public Help(CommandsManager commandsManager) {
        this.commandsManager = commandsManager;
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String info() {
        return getName() + " -> " + Resources.NAME_CN + "会@你并回复帮助信息";
    }

    @Override
    public void onCommand(GroupMessageEvent event) {
        MessageChainBuilder mb = new MessageChainBuilder()
                .append(new QuoteReply(event.getSource()))
                .append(new At(event.getSender().getId()))
                .append("\n")
                .append("Help Info Manuel\n")
                .append("--------------------\n");
        for (Command c : CommandsManager.COMMANDS) {
            mb.append(commandsManager.commandPrefix).append(c.info()).append("\n");
        }
        event.getSubject().sendMessage(mb.build());
    }
}
