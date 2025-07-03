package org.brassbrewery.fragaliciousCore.afk;

import org.brassbrewery.fragaliciousCore.FragaliciousCore;
import org.brassbrewery.fragaliciousCore.exceptions.ModuleNotLoadedException;
import org.brassbrewery.fragaliciousCore.util.MessageUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AfkCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (sender instanceof Player player) {
            try {
                if(!FragaliciousCore.getInstance().getAfkApi().isPlayerAfk(player)){
                    FragaliciousCore.getInstance().getAfkApi().setAfk(player);
                    System.out.println("SETTING USER TO AFK");
                }else{
                    MessageUtil.sendMessage(player, "&cCannot set afk, you are already afk!");
                }
            } catch (ModuleNotLoadedException e) {
                MessageUtil.sendMessage(player, "&cThe AFK Module is not loaded!");
            }
        } else {
            sender.sendMessage(MessageUtil.convertStringToComponenet("&cYou cannot use this command via console"));
        }
        return true;
    }
}
