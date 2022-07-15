package net.orbitdev.aurorabungee.listener;

import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.orbitdev.aurorabungee.AuroraBungee;
import net.orbitdev.aurorabungee.utils.CC;


public class StaffListener implements Listener {

    @EventHandler
    public void onProxyPing(ProxyPingEvent event) {
        if (AuroraBungee.getInstance().isMaintenance()) {
            event.getResponse().setVersion(new ServerPing.Protocol(CC.translate("&4Maintenance ON"), 9999));
            event.getResponse().setDescription(CC.translate("&4The server is currently on maintenance."));
        }
        event.setResponse(event.getResponse());
    }

    @EventHandler
    public void onServerConnect(ServerConnectEvent event) {
        if (AuroraBungee.getInstance().isMaintenance()) {
            if (event.getPlayer().hasPermission("aurora.maintanance.bypass")) {
                return;
            }
            event.setCancelled(true);
            event.getPlayer().disconnect(CC.translate("&4&lMAINTENANCE\n&7\n&cThe server is currently in maintenance.\n&cJoin our discord to stay updated\n&ddiscord.orbitdev.net"));
        }
    }

    @EventHandler
    public void onServerJoin(ServerConnectEvent event) {
        if (event.getPlayer().hasPermission("aurora.staff.join")) {
            for (ProxiedPlayer staff : AuroraBungee.getInstance().getProxy().getPlayers()) {
                if (staff.hasPermission("aurora.staff.join")) {
                    staff.sendMessage(CC.translate("&5[Aurora]" + event.getPlayer() +"&ajoined &7(&o" + event.getPlayer().getServer().getInfo().getName() + "&r&7)" ));
                }
            }
        }
    }

    @EventHandler
    public void onPlayerDisconnect(PlayerDisconnectEvent event) {
        if (event.getPlayer().hasPermission("aurora.staff.leave")) {
            for (ProxiedPlayer staff : AuroraBungee.getInstance().getProxy().getPlayers()) {
                if (staff.hasPermission("aurora.staff.leave")) {
                    staff.sendMessage(CC.translate("&5[Aurora] " + event.getPlayer() + " &cleft from &7(&o" + event.getPlayer().getServer().getInfo().getName() + "&r&7)"));
                }
            }
        }
    }
}