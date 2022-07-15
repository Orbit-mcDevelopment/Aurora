package net.orbitdev.aurorabungee.commands.admin;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.orbitdev.aurorabungee.AuroraBungee;
import net.orbitdev.aurorabungee.listener.Country;
import net.orbitdev.aurorabungee.utils.CC;

public class UserInfoCommand extends Command {

    public UserInfoCommand() {
        super("userinfo", "aurora.admin");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage((CC.translate("&cUsage: /userinfo <player>")));
            return;
        }

        ProxiedPlayer target = AuroraBungee.getInstance().getProxy().getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage((CC.translate("&cPlayer not found.")));
            return;
        }
        String IP = target.getAddress().getAddress().getHostAddress();

        sender.sendMessage(CC.translate(""));
        sender.sendMessage(CC.translate("&d&lUser Information&f:"));
        sender.sendMessage(CC.translate(""));
        sender.sendMessage(CC.translate("&5 • &fName: &d" + target.getName()));
        sender.sendMessage(CC.translate("&5 • &fUUID: &d" + target.getUniqueId()));
        sender.sendMessage(CC.translate("&5 • &fPing: &d" + target.getPing()));
        sender.sendMessage(CC.translate("&5 • &fIP: &d" + target.getAddress().getAddress().getHostAddress()));
        sender.sendMessage(CC.translate("&5 • &fCountry: &d" + Country.getCountry(IP)));
        sender.sendMessage(CC.translate("&5 • &fCurrent Server: &d" + target.getServer().getInfo().getName()));
        sender.sendMessage(CC.translate(""));
    }
}