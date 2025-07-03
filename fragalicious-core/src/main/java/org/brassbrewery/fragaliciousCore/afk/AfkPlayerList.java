package org.brassbrewery.fragaliciousCore.afk;

import org.brassbrewery.fragaliciousCore.FragaliciousCore;
import org.brassbrewery.fragaliciousCore.exceptions.UnknownPlayerException;
import org.brassbrewery.fragaliciousCore.util.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class AfkPlayerList {
    private ConcurrentHashMap<UUID, AfkPlayer> afkPlayerMap;
    private AfkConfig afkConfig;
    public AfkPlayerList(AfkConfig afkConfig){
        this.afkPlayerMap = new ConcurrentHashMap<>();
        this.afkConfig = afkConfig;
        Bukkit.getScheduler().runTaskTimerAsynchronously(FragaliciousCore.getInstance(), this::tick,0, 20);
    }
    public void addPlayer(UUID uuid){
        this.afkPlayerMap.put(uuid, new AfkPlayer(uuid));
    }
    public void setAfk(UUID uuid){
        System.out.println("Manaul Setafk:Started");
                afkPlayerMap.get(uuid).setAfk(false);
                afkPlayerMap.get(uuid).setAfkTime(afkConfig.getAfkTimeInSeconds());
        System.out.println("Manaul setAfk: Complete");
    }
    public boolean containsPlayer(UUID uuid){return this.afkPlayerMap.containsKey(uuid);}
    public void removePlayer(UUID uuid){
        this.afkPlayerMap.remove(uuid);
    }
    public void tick(){
        int afkLimit = afkConfig.getAfkTimeInSeconds();
        int kickLimit = afkLimit + afkConfig.getAfkKickTime();
        for(UUID uuid : this.afkPlayerMap.keySet()){
            AfkPlayer afkPlayer = afkPlayerMap.get(uuid);
            Player player = Bukkit.getPlayer(uuid);
            afkPlayer.addTime();
            if (afkPlayer.getAfkTime() >= afkLimit  && !afkPlayer.isAfk()){
                afkPlayer.setAfk(true);
                new PlayerAfkStateChangeEvent(player, true).callEvent();

            }
            //kick player
            if(afkPlayer.getAfkTime() >=  kickLimit && afkConfig.shouldKickPlayersForBeingAfkTooLong()) {
                kickAfkPlayer(player);
            }else{
                afkPlayerMap.put(afkPlayer.getUuid(), afkPlayer);
            }
        }
    }
    public void resetPlayerAfk(UUID uuid){
        if(this.afkPlayerMap.containsKey(uuid)){
            AfkPlayer afkPlayer = afkPlayerMap.get(uuid);
            if(afkPlayer.isAfk()){
                afkPlayer.setAfkTime(0);
                afkPlayer.setAfk(false);
                Bukkit.getScheduler().runTaskAsynchronously(FragaliciousCore.getInstance(), () -> new PlayerAfkStateChangeEvent(Objects.requireNonNull(Bukkit.getPlayer(uuid)), false).callEvent());
            }
            afkPlayerMap.put(afkPlayer.getUuid(), afkPlayer);
        }
    }

    private void kickAfkPlayer(Player player){
        Bukkit.getScheduler().runTask(FragaliciousCore.getInstance(), () ->player.kick(MessageUtil.convertStringToComponenet(afkConfig.getAfkKickReason())));
    }

    public boolean isAfk(UUID uuid) throws UnknownPlayerException{
        if(this.afkPlayerMap.containsKey(uuid)){
            return afkPlayerMap.get(uuid).isAfk();
        }
        throw new UnknownPlayerException(uuid);
    }

    public AfkConfig getAfkConfig() {
        return afkConfig;
    }
}
