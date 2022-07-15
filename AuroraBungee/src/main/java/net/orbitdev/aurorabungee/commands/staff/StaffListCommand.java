package net.orbitdev.aurorabungee.commands.staff;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.orbitdev.aurorabungee.AuroraBungee;
import net.orbitdev.aurorabungee.utils.CC;

import java.util.ArrayList;
import java.util.List;

public class StaffListCommand extends Command {

    public StaffListCommand() {
        super("stafflist", "aurora.stafflist", "staffs");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage((CC.translate("&cThis command cannot execute from console.")));
            return;
        }
        List<String> staffs = new ArrayList<>();
        AuroraBungee.getInstance().getProxy().getPlayers()
                .stream()
                .filter(ProxiedPlayer::isConnected).filter(staffM -> staffM.hasPermission("aurora.staff"))
                .forEach(player -> staffs.add(player.getName())
                );
        List<String> players = new ArrayList<>();
        AuroraBungee.getInstance().getProxy().getPlayers()
                .stream()
                .forEach(player -> players.add(player.getName())
                );
        sender.sendMessage(CC.translate("&5&lOrbit &fNetwork &fPlayers Online: &f" + players.size()));
        sender.sendMessage(CC.translate("&5&lOrbit &fNetwork &dStaff Online: &f" + staffs.size()));
        sender.sendMessage(CC.translate(""));
        sender.sendMessage(CC.translate("Staffs: " + String.join("&d, ", staffs)));
    }
}

