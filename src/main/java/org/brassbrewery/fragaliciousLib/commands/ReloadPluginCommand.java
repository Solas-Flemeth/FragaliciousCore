package org.brassbrewery.fragaliciousLib.commands;

import org.brassbrewery.fragaliciousLib.FragaliciousLib;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ReloadPluginCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        FragaliciousLib.getInstance().reload();
        commandSender.sendMessage("Reloading Origins Plugin");
        return true;
    }

}
