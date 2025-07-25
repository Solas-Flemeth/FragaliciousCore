package org.brassbrewery.fragaliciousEconomics.mcmmo.data;

import org.bukkit.entity.Player;

public class CooldownPlayer {
    private final Player player;
    private int cooldown;
    public CooldownPlayer(Player player, int cooldown) {
        this.player = player;
        this.cooldown = cooldown;
    }

    public Player getPlayer() {
        return player;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }
    public void addCooldown(int cooldown){
        this.cooldown += cooldown;
    }

    /**
     * Represents a passage of time. Lowers players cooldown
     */
    public void tick(){
        cooldown--;
    }
}
