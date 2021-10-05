# Malus

小棠(Malus)，一个基于[mirai](https://github.com/mamoe/mirai)和[mirai-console](https://github.com/mamoe/mirai-console)的机器人。

## 使用说明

**安装**

去`release`界面下载**jar**包，然后放置到`plugins`文件下即可

**说明**

出于避免打扰群友的考虑，小棠默认不对任何群启用功能(~~除非你的群正好命中了配置文件里默认生成的示例群号~~), 启用插件需向指令对应的配置属性下添加想应的群号

**当前支持的群功能**

* `!!help` 小棠会回复帮助信息并@你
* `!!ping` 小棠会回复`pong`并@你
* `!!gkd` 小棠会发一张图片并@你
* `!!yy` 小棠会发送一言并@你

**演示**
![demo](https://raw.githubusercontent.com/VatinaCharo/PicgoPicAssets/main/pic/malus_demo.png)

**配置文件示例**
```yaml
# ===============
# 全局配置属性
# ===============
# 默认的指令前缀
commandPrefix: !!
# 默认的图片API，可依据需求自行更改，但务必保证返回的结果是一张图片，最好是jpg，其他不做可用性保证
imageAPI: 'https://imgapi.cn/cos.php?return=img'
# 下载的图片储存位置
imageStorage: './data/image/Malus/'
#  默认的图片API，暂不支持兼容其他站点的一言接口，可用此站的参数进行装饰
hitokotoAPI: 'https://v1.hitokoto.cn'
# ===============
# 指令具体配置属性
# ===============
# ping指令的群白名单
ping: 
  - 123456789
  - 987654321
# gkd指令的群白名单
gkd: 
  - 123456789
  - 987654321
# gkd指令的冷却时间，单位ms
gkdCD: 5000
# 一言指令的群白名单
yy: 
  - 123456789
  - 987654321
# 天气指令的群白名单
# Coming Soooooon
tq: 
  - 123456789
  - 987654321
```

## 项目结构
```text
Main Class : VatinaCharo.Malus

main/
├── java/
│   └── VatinaCharo/
│       ├── Commands/
│       │   ├── Command.java
│       │   ├── CommandsManager.java
│       │   ├── GetRandImage.java
│       │   ├── Help.java
│       │   ├── Hitokoto.java
│       │   ├── Ping.java
│       │   └── SimpleCommand.java
│       ├── Malus.java
│       └── Utils/
│           ├── Config.kt
│           ├── HitokotoHelper.java
│           ├── ImgDownloader.java
│           └── Resources.java
└── resources/
    └── META-INF/
        └── services/
            └── net.mamoe.mirai.console.plugin.jvm.JvmPlugin
```
## LICENSE

```
Malus, a mirai bot based on mirai and mirai-console.
Copyright (C) 2021  Vatina Charo
 
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as published
by the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.
 
You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.
```
