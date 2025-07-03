package org.brassbrewery.fragaliciousCore.afk;

import java.util.UUID;

public class AfkPlayer {
    private final UUID uuid;
    private int afkTime;
    private boolean isAfk;
    public AfkPlayer(UUID uuid){
        this.uuid=uuid;
        this.isAfk=false;
        this.afkTime=0;
    }

    public UUID getUuid() {
        return uuid;
    }

    public int getAfkTime() {
        return afkTime;
    }

    public boolean isAfk() {
        return isAfk;
    }
    public void addTime(){
        this.afkTime++;
    }

    public void setAfkTime(int afkTime) {
        this.afkTime = afkTime;
    }

    public void setAfk(boolean afk) {
        isAfk = afk;
    }
}
