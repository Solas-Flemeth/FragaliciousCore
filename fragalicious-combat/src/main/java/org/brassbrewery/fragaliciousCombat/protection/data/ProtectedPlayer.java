package org.brassbrewery.fragaliciousCombat.protection.data;

import java.util.UUID;

public class ProtectedPlayer {
    private UUID player;
    private ProtectionType protectionType;
    private int duration; //this is in seconds - set to 0 if UPKEEP or NONE.
    private boolean statusNotificationNeedsSent = false;
    private long lastLogin = System.currentTimeMillis();

    /**
     * Used for loading from database
     * @param player
     * @param protectionType
     * @param duration
     * @param statusNotificationNeedsSent
     * @param lastLogin
     */
    public ProtectedPlayer(UUID player, String protectionType, int duration, boolean statusNotificationNeedsSent, long lastLogin){
        this.player = player;
        this.protectionType = ProtectionType.fromString(protectionType);
        this.duration = duration;
        this.statusNotificationNeedsSent = statusNotificationNeedsSent;
        this.lastLogin = lastLogin;
    }

    /**
     * Used when a new player joins without protection
     * @param player
     * @param protectionType
     */
    public  ProtectedPlayer(UUID player, ProtectionType protectionType) {
        this.player = player;
        this.protectionType = protectionType;
        duration = 0;
        statusNotificationNeedsSent = false;
    }

    /**
     * Used when a new player joins with protection
     * @param player
     * @param protectionType
     * @param duration
     */
    public ProtectedPlayer (UUID player, ProtectionType protectionType, int duration){
        this.player = player;
        this.protectionType = protectionType;
        this.duration = duration;
        statusNotificationNeedsSent = false;

    }


    /**
     * This will decrease the duration. If the player changes protection type due to the tick, it will return the old protection type
     * @return the protection type if changed, if it did not change, returns null;
     */
    public ProtectionType tick(int secondsTicked){
        if(protectionType == ProtectionType.NEW_PLAYER || protectionType == ProtectionType.RESPAWN){
            duration= duration - secondsTicked;
            if(duration <= 0){
                duration = 0;
                ProtectionType oldType = getProtectionType();
                protectionType = ProtectionType.NONE;
                return oldType;
            }
        }
        return null;
    }
    public void updateLastLogin(){
        lastLogin = System.currentTimeMillis();
    }

    public UUID getPlayer() {
        return player;
    }

    public void setPlayer(UUID player) {
        this.player = player;
    }

    public ProtectionType getProtectionType() {
        return protectionType;
    }

    public void setProtectionType(ProtectionType protectionType) {
        this.protectionType = protectionType;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        if(duration < 0 ){
            duration = 0;
        }
        this.duration = duration;
    }
    public boolean getStatusNotificationNeedsSent() {
        return statusNotificationNeedsSent;
    }
    public void setStatusNotificationNeedsSent(boolean statusNotificationNeedsSent) {
        this.statusNotificationNeedsSent = statusNotificationNeedsSent;
    }

    public long getLastLogin() {
        return lastLogin;
    }
    public void setLastLogin(long lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Override
    public String toString() {
        return "ProtectedPlayer{" +
                "player=" + player +
                ", protectionType=" + protectionType +
                ", duration=" + duration +
                ", statusNotificationNeedsSent=" + statusNotificationNeedsSent +
                ", lastLogin=" + lastLogin +
                '}';
    }
}
