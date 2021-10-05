package VatinaCharo.Commands;

import VatinaCharo.Utils.Config;
import VatinaCharo.Utils.ImgDownloader;
import VatinaCharo.Utils.Resources;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import net.mamoe.mirai.message.data.QuoteReply;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GetRandImage extends SimpleCommand {
    public static Long time = 0L;

    public GetRandImage() {
        super("gkd", Resources.NAME_CN + "会@你并发一张图到群里");
    }

    @Override
    List<Long> getGroups() {
        return Config.INSTANCE.getGkd();
    }

    @Override
    void handle(GroupMessageEvent event) {
        long newTime = System.currentTimeMillis();
        if (newTime - time > Config.INSTANCE.getGkdCD()) {
            time = newTime;
            // 开辟额外的线程去下载图片
            CompletableFuture<String> getImage = CompletableFuture.supplyAsync(() -> {
                try {
                    return ImgDownloader.download(Config.INSTANCE.getImageAPI(), Config.INSTANCE.getImageStorage());
                } catch (IOException e) {
                    return "err";
                }
            });
            MessageChainBuilder mcb = new MessageChainBuilder()
                    .append(new QuoteReply(event.getSource()))
                    .append(new At(event.getSender().getId()));
            // 下载图片的线程，在完成下载之后自动回调发送信息的方法
            getImage.thenAccept((result) -> {
                if (result.equalsIgnoreCase("err")) {
                    event.getGroup().sendMessage(mcb.append("图片获取失败 >_<").build());
                } else {
                    mcb.append(Contact.uploadImage(event.getGroup(), new File(Config.INSTANCE.getImageStorage() + result)));
                    event.getGroup().sendMessage(mcb.build());
                }
            });
        } else {
            // 发图冷却提醒
            event.getGroup().sendMessage(
                    new MessageChainBuilder()
                            .append(new QuoteReply(event.getSource()))
                            .append(new At(event.getSender().getId()))
                            .append("\ngkd太频繁了，年轻人要节制哦，请冷静一会儿吧\n")
                            .append("Left: ")
                            .append(String.valueOf((Config.INSTANCE.getGkdCD() - newTime + time) / 1000))
                            .append(" s")
                            .build()
            );
        }
    }
}
