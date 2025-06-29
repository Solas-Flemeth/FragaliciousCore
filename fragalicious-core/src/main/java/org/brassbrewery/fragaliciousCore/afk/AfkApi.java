package org.brassbrewery.fragaliciousCore.afk;

import org.brassbrewery.fragaliciousCore.structure.FragaliciousAPI;

import java.util.UUID;

public class AfkApi extends FragaliciousAPI<AfkModule> {
    public AfkApi(AfkModule fragaliciouModule) {
        super(fragaliciouModule);
    }
    boolean isPlayerAfk(UUID uuid){
        return false; //TODO: Implement this method.
    }
}
