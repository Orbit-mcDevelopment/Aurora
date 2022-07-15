package net.orbitdev.aurorabungee.commands.user;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.orbitdev.aurorabungee.AuroraBungee;
import net.orbitdev.aurorabungee.listener.Cooldown;
import net.orbitdev.aurorabungee.utils.CC;

import java.util.stream.Collectors;

public class ReportCommand extends Command {

    public ReportCommand() {
        super("report", "aurora.report", "reportar");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage((CC.translate("&cThis command cannot execute from console.")));
            return;
        }
        ProxiedPlayer player = (ProxiedPlayer) sender;
        if (args.length == 0) {
            player.sendMessage((CC.translate("&cUsage: /report <player> <reason>")));
            return;
        }

        ProxiedPlayer target = AuroraBungee.getInstance().getProxy().getPlayer(args[0]);
        if (target == null) {
            player.sendMessage((CC.translate("&cPlayer not found.")));
            return;
        }
        if (target == player) {
            player.sendMessage((CC.translate("&cYou cannot report you!")));
            return;
        }

        if (args.length == 1) {
            player.sendMessage((CC.translate("&cSpecify the reason of the report.")));
            return;
        }

        if (!Cooldown.checkCooldown(player, "reportCooldown", 60000L)) {
            player.sendMessage((CC.translate("&cYou can't use this command at the moment, please try again in a while.")));
            return;
        }

        TextComponent message = new TextComponent(CC.translate("&5[Report] &7(&o" + player.getServer().getInfo().getName() + "&r&7) &d" + player.getName() + " &7has reported &c" + target.getName() + "&5Reason: &d" + CC.message(args, 1)));
        message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(CC.translate("&aClick to go" + player.getServer().getInfo().getName()))));
        message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/server " + player.getServer().getInfo().getName()));

        AuroraBungee.getInstance().getProxy().getPlayers()
                .stream()
                .filter(staff -> staff.hasPermission("aurora.staff.report"))
                .collect(Collectors.toList())
                .forEach(staff -> staff.sendMessage(message));

        player.sendMessage((CC.translate("&aYour report has been sent successfully!")));
        Cooldown.setCooldown(player, "reportCooldown", 60000L);
    }
}