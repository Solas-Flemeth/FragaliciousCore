package org.brassbrewery.fragaliciousLib.exceptions;

import java.util.UUID;

public class UnknownPlayerException extends Exception{
    public UnknownPlayerException(UUID uuid){
        super("Could not find player with ID" + uuid);
    }
    public UnknownPlayerException(String playerName){
        super("Could not find player with name " + playerName);
    }
    public UnknownPlayerException(){
        super("Could not find player (unknown player)");
    }
}
