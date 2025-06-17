package org.brassbrewery.fragaliciousCombat.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.brassbrewery.fragaliciousCombat.FragaliciousCombat;
import org.brassbrewery.fragaliciousCore.exceptions.ModuleNotLoadedException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TogglePvpCommand implements TabExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args)  {
        if(!(sender instanceof Player player)){
            sender.sendMessage(Component.text("You must be a player to execute this command").color(TextColor.color(0xFF0000)));
            return true;
        }try{
            boolean isForced = args.length > 0 && "force".equalsIgnoreCase(args[0]);
            FragaliciousCombat.getInstance().getProtectionAPI().toggleProtection(player, isForced);
        }catch (ModuleNotLoadedException e){
            sender.sendMessage(Component.text("This command cannot be used without the protection module enabled").color(TextColor.color(0xFF0000)));
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1 && sender.hasPermission("fragalicious.combat.protection")) {
            return List.of("force");
        }
        return List.of();
    }
}
