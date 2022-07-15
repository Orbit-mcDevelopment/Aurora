package net.orbitdev.aurorabungee.commands.admin;


import net.orbitdev.aurorabungee.AuroraBungee;
import net.orbitdev.aurorabungee.utils.CC;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.awt.*;

public class FindCommand extends Command {

    public FindCommand() {
        super("find", "aurora.admin", "locate");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage((CC.translate("&cUsage: /find <player>")));
            return;
        }
        ProxiedPlayer target = AuroraBungee.getInstance().getProxy().getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage((CC.translate("&cPlayer not found.")));
            return;
        }
        TextComponent message = new TextComponent(CC.translate("&5[Find] &r" + target.getName() + " &dis connected on &r" + target.getServer().getInfo().getName()));
        message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(CC.translate("&aClick to go " + target.getServer().getInfo().getName()))));
        message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/server " + target.getServer().getInfo().getName()));
        sender.sendMessage(message);
        }
    }
