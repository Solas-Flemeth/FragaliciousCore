package org.brassbrewery.fragaliciousCore.afk;

import org.brassbrewery.fragaliciousCore.FragaliciousCore;
import org.brassbrewery.fragaliciousCore.exceptions.UnknownPlayerException;
import org.bukkit.Bukkit;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class AfkPlayerList {
    private ConcurrentHashMap<UUID, Integer> afkPlayerMap;
    private AfkConfig afkConfig;
    public AfkPlayerList(AfkConfig afkConfig){
        this.afkPlayerMap = new ConcurrentHashMap<>();
        this.afkConfig = afkConfig;
        Bukkit.getScheduler().runTaskTimerAsynchronously(FragaliciousCore.getInstance(), this::tick,0, 20);

    }
    public void addPlayer(UUID uuid){
        this.afkPlayerMap.put(uuid, 0);
    }
    public void removePlayer(UUID uuid){
        this.afkPlayerMap.remove(uuid);
    }
    public void tick(){
        for(UUID uuid : this.afkPlayerMap.keySet()){
            int afkTime = this.afkPlayerMap.get(uuid)+1;
            this.afkPlayerMap.put(uuid, afkTime);
            if(afkTime >= afkConfig.getAfkTimeInSeconds()){
                new PlayerAfkStateChangeEvent(Objects.requireNonNull(Bukkit.getPlayer(uuid)), true).callEvent();
            }
        }
    }
    public void resetPlayerAfk(UUID uuid){
        if(this.afkPlayerMap.containsKey(uuid)){
            int afkTime = this.afkPlayerMap.get(uuid);
            if(afkTime >= afkConfig.getAfkTimeInSeconds()){
                Bukkit.getScheduler().runTaskAsynchronously(FragaliciousCore.getInstance(), () -> new PlayerAfkStateChangeEvent(Objects.requireNonNull(Bukkit.getPlayer(uuid)), false).callEvent());
            }
            this.afkPlayerMap.put(uuid, 0);
        }
    }

    public boolean isAfk(UUID uuid) throws UnknownPlayerException{
        if(this.afkPlayerMap.containsKey(uuid)){
            return afkPlayerMap.get(uuid) > afkConfig.getAfkTimeInSeconds();
        }
        throw new UnknownPlayerException(uuid);
    }

    public AfkConfig getAfkConfig() {
        return afkConfig;
    }
}
