package net.orbitdev.aurorabungee.commands.admin;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.orbitdev.aurorabungee.AuroraBungee;
import net.orbitdev.aurorabungee.utils.CC;

import java.util.stream.Collectors;

public class StaffAlertCommand extends Command {

    public StaffAlertCommand() {
        super("staffalert", "aurora.admin", "staffannounce");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage((CC.translate("&cUsage: /staffalert <message>")));
            return;
        }

        TextComponent message = new TextComponent(CC.translate("&d[Alert] &r" + CC.message(args, 0) ));

        AuroraBungee.getInstance().getProxy().getPlayers()
                .stream()
                .filter(staff -> staff.hasPermission("aurora.staff"))
                .collect(Collectors.toList())
                .forEach(players -> players.sendMessage(message));
    }
}
