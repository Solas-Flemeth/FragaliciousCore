package org.brassbrewery.fragaliciousCombat.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.brassbrewery.fragaliciousCombat.FragaliciousCombat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CombatReloadCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        sender.sendMessage(Component.text("Reloading Fragalicious Combat. There may be lag for a short duration").color(TextColor.color(0xFF3F)));
        FragaliciousCombat.getInstance().reload();
        sender.sendMessage(Component.text("Reload Complete").color(TextColor.color(0xFF3F)));
        return true;
    }
}
