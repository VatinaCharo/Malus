package VatinaCharo.Commands;

import VatinaCharo.Utils.Config;
import VatinaCharo.Utils.HitokotoHelper;
import VatinaCharo.Utils.Resources;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import net.mamoe.mirai.message.data.QuoteReply;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Hitokoto extends SimpleCommand {
    public Hitokoto() {
        super("yy", Resources.NAME_CN + "会@你并发送一言");
    }

    @Override
    List<Long> getGroups() {
        return Config.INSTANCE.getYy();
    }

    @Override
    void handle(GroupMessageEvent event) {
        CompletableFuture<String> getText = CompletableFuture.supplyAsync(() -> {
            try {
                URL url = new URL(Config.INSTANCE.getHitokotoAPI());
                BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
                return in.readLine();
            } catch (IOException e) {
                return "err";
            }
        });
        MessageChainBuilder mcb = new MessageChainBuilder()
                .append(new QuoteReply(event.getSource()))
                .append(new At(event.getSender().getId()));
        getText.thenAccept(result -> {
            if (result.equalsIgnoreCase("err")) {
                event.getGroup().sendMessage(mcb.append("获取失败 >_<").build());
            } else {
                event.getGroup().sendMessage(mcb.append(HitokotoHelper.parseJSON(result)).build());
            }
        });
    }
}
