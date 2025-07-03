package org.brassbrewery.fragaliciousCore.afk;

import net.kyori.adventure.text.Component;
import org.brassbrewery.fragaliciousCore.exceptions.UnknownPlayerException;
import org.brassbrewery.fragaliciousCore.util.MessageUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class AfkService implements Listener {
    private AfkModule module;
    private AfkPlayerList afkPlayerList;
    public AfkService(AfkModule module) {
        this.module = module;
        this.afkPlayerList = new AfkPlayerList(module.getAfkConfig());
    }

    public boolean isAfk(UUID uuid) throws UnknownPlayerException {
        return afkPlayerList.isAfk(uuid);
    }

    @EventHandler
    public void playerJoinServerEvent(PlayerJoinEvent playerJoinEvent){
        afkPlayerList.addPlayer(playerJoinEvent.getPlayer().getUniqueId());
    }
    @EventHandler
    public void playerQuit(PlayerQuitEvent event){
        afkPlayerList.removePlayer(event.getPlayer().getUniqueId());
    }
    @EventHandler
    public void playerMove(PlayerMoveEvent event){
        Location from = event.getFrom();
        Location to = event.getTo();
        if(from.getYaw() != to.getYaw() || from.getPitch() != to.getPitch()){
            afkPlayerList.resetPlayerAfk(event.getPlayer().getUniqueId());
        }
    }
    @EventHandler
    public void playerInteract(PlayerInteractEvent event){
        afkPlayerList.resetPlayerAfk(event.getPlayer().getUniqueId());
    }
    @EventHandler
    public void onAfkStateChange(PlayerAfkStateChangeEvent stateChangeEvent){
        System.out.println("DETECTED PLAYER AFK STATUS CHANGE EVENT");
        Player player = stateChangeEvent.getPlayer();
        boolean sendAfkMessage = module.getAfkConfig().shouldSendAfkMessage();
        boolean sendAfkBroadcast = module.getAfkConfig().shouldBroadcastAfkStatusChanges();
        if(stateChangeEvent.isAfk()){
            module.fine("Player" + player.getName() + " is now afk");
            if(sendAfkMessage){
                MessageUtil.sendMessage(player, replaceVariables(module.getAfkConfig().getAfkMessageStart(), player));
            }
            if(sendAfkBroadcast){
                MessageUtil.broadcastMessage(replaceVariables(module.getAfkConfig().getAfkBroadcastStart(), player));
            }
        }else{
            module.fine("Player" + player.getName() + " is no longer afk");
            if(sendAfkMessage){
                MessageUtil.sendMessage(player, replaceVariables(module.getAfkConfig().getAfkMessageEnd(), player));
            }
            if(sendAfkBroadcast){
                MessageUtil.broadcastMessage(replaceVariables(module.getAfkConfig().getAfkBroadcastEnd(), player));
            }
        }
    }


    public void setAfk(Player player) {
        afkPlayerList.setAfk(player.getUniqueId());
    }

    public AfkModule getModule() {
        return module;
    }
    private String replaceVariables(String message, Player player){
        return message.replace("{name}", player.getName());
    }
}
