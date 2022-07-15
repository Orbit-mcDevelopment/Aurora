package net.orbitdev.aurorabungee;

import lombok.Getter;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.orbitdev.aurorabungee.commands.admin.*;
import net.orbitdev.aurorabungee.commands.staff.StaffListCommand;
import net.orbitdev.aurorabungee.commands.user.HubCommand;
import net.orbitdev.aurorabungee.commands.user.ReportCommand;
import net.orbitdev.aurorabungee.listener.StaffListener;

@Getter
public final class AuroraBungee extends Plugin {

    public Configuration config;

    @Getter
    private static AuroraBungee instance;

    private boolean maintenance;
    private boolean chatlock;

    @Override
    public void onEnable() {
        instance = this;

        // Admin
        getProxy().getPluginManager().registerCommand(this, new UserInfoCommand());
        getProxy().getPluginManager().registerCommand(this, new MaintenanceCommand());
        getProxy().getPluginManager().registerCommand(this, new FindCommand());
        getProxy().getPluginManager().registerCommand(this, new AlertCommand());
        getProxy().getPluginManager().registerCommand(this, new StaffAlertCommand());

        // Staff
        getProxy().getPluginManager().registerCommand(this, new StaffListCommand());

        // User
        getProxy().getPluginManager().registerCommand(this, new HubCommand());
        getProxy().getPluginManager().registerCommand(this, new ReportCommand());
        getProxy().getPluginManager().registerCommand(this, new ReportCommand());
        // Listeners

        getProxy().getPluginManager().registerListener(this, new StaffListener());
    }

    @Override
    public void onDisable() {
        getProxy().getPluginManager().unregisterCommands(this);
        getProxy().getPluginManager().unregisterListeners(this);

        instance = null;
    }

    public boolean toggleMaintenance() {
        return maintenance = !maintenance;
    }
    public void toggleChatLock() {
        if (getChatStatus()) {
            this.chatlock = false;
            return;
        }
        this.chatlock = true;
    }
    public boolean getChatStatus() {
        return this.chatlock;
    }
}
