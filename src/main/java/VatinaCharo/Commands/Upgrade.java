package VatinaCharo.Commands;

import VatinaCharo.Utils.Resources;
import VatinaCharo.Utils.UpgradeHelper;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import net.mamoe.mirai.message.data.QuoteReply;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

public class Upgrade implements Command {

    @Override
    public String getName() {
        return "upgrade";
    }

    @Override
    public String info() {
        return getName() + " -> " + Resources.NAME_CN + "会@你并回复更新信息";
    }

    @Override
    public void onCommand(GroupMessageEvent event) {
        CompletableFuture<String> getText = CompletableFuture.supplyAsync(() -> {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(new URL(Resources.GITHUB_URL).openStream(), StandardCharsets.UTF_8));
                return in.readLine();
            } catch (IOException e) {
                return "err";
            }
        });
        MessageChainBuilder mcb = new MessageChainBuilder()
                .append(new QuoteReply(event.getSource()))
                .append(new At(event.getSender().getId()))
                .append("\n");
        getText.thenAccept(result -> {
            if (result.equalsIgnoreCase("err")) {
                event.getGroup().sendMessage(mcb.append("获取失败 >_<").build());
            } else {
                // 解析github api传回的json数据
                String[] text = UpgradeHelper.parseJSON(result);
                // 检查更新
                if (text[0].isEmpty()) {
                    event.getGroup().sendMessage(mcb.append("获取失败 >_<").build());
                } else if (text[0].equals(Resources.VERSION)) {
                    event.getGroup().sendMessage(mcb.append("暂无更新哦~ >_<").build());
                } else {
                    event.getGroup().sendMessage(mcb.append(text[1]).build());
                }
//                event.getGroup().sendMessage(mcb.append(text[1]).build());
            }
        });
    }
}
