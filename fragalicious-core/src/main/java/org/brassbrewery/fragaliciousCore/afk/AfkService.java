package org.brassbrewery.fragaliciousCore.afk;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class AfkService implements Listener {
    private AfkModule module;
    private AfkPlayerList afkPlayerList;
    public AfkService(AfkModule module) {
        this.module = module;
        this.afkPlayerList = new AfkPlayerList(module.getAfkConfig());
    }
    public void playerJoinServerEvent(PlayerJoinEvent playerJoinEvent){
        
    }
}
