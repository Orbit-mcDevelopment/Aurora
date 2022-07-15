package net.orbitdev.aurorabungee.commands.admin;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import net.orbitdev.aurorabungee.AuroraBungee;
import net.orbitdev.aurorabungee.utils.CC;

public class MaintenanceCommand extends Command {

    public MaintenanceCommand() {
        super("maintenance", "aurora.admin", "mantenimiento");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage(CC.translate((AuroraBungee.getInstance().toggleMaintenance() ? "&d[Maintenance] &atoggled on." : "&d[Maintenance] &ctoggled off.")));
    }
}