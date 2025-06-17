package org.brassbrewery.fragaliciousCombat.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.brassbrewery.fragaliciousCombat.FragaliciousCombat;
import org.brassbrewery.fragaliciousCombat.protection.data.ProtectedPlayer;
import org.brassbrewery.fragaliciousCore.exceptions.ModuleNotLoadedException;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PvpStatusCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if(args.length > 0){
            Player targetPlayer = Bukkit.getPlayer(args[0]);
            if(targetPlayer != null && targetPlayer.isOnline()){
                try {
                    ProtectedPlayer protectedPlayer = FragaliciousCombat.getInstance().getProtectionAPI().getPlayer(targetPlayer.getUniqueId());
                    sender.sendMessage("Sending " +  args[0] + " PVP data:");
                    sender.sendMessage(protectedPlayer.toString());
                } catch (ModuleNotLoadedException e) {
                    sender.sendMessage(Component.text("Unknown or invalid player").color(TextColor.color(0xFF0000)));
                }//end of catch
            }//end of if
        }//end of if
        else if(sender instanceof Player player){
            try {
                ProtectedPlayer protectedPlayer = FragaliciousCombat.getInstance().getProtectionAPI().getPlayer(player.getUniqueId());
                player.sendMessage("Sending your PVP data:");
                player.sendMessage(protectedPlayer.toString());
            } catch (ModuleNotLoadedException e) {
                sender.sendMessage(Component.text("Unknown or invalid player").color(TextColor.color(0xFF0000)));
            }
        }else{
            sender.sendMessage("You cannot run this command on yourself as a non player");
        }
        return true;
    }
}
