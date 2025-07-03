package org.brassbrewery.fragaliciousCore.afk;

import org.brassbrewery.fragaliciousCore.FragaliciousCore;
import org.brassbrewery.fragaliciousCore.configs.FragaliciousConfig;
import org.brassbrewery.fragaliciousCore.configs.objects.BooleanConfigObject;
import org.brassbrewery.fragaliciousCore.configs.objects.IntegerConfigObject;
import org.brassbrewery.fragaliciousCore.configs.objects.StringConfigObject;

public class AfkConfig extends FragaliciousConfig {
    //generic
    private IntegerConfigObject afkTimeInSeconds;
    //messages
    private BooleanConfigObject enableAfkMessage;
    private StringConfigObject afkMessageStart;
    private StringConfigObject afkMessageEnd;
    private BooleanConfigObject enableAfkBroadcast;
    private StringConfigObject afkBroadcastStart;
    private StringConfigObject afkBroadcastEnd;
    //kicking
    private BooleanConfigObject enableAfkKick;
    private IntegerConfigObject afkKickTime;
    private StringConfigObject afkKickReason;

    public AfkConfig(FragaliciousCore plugin ) {
        super(plugin, "AfkModule");
    }

    @Override
    public void registerAllConfigObjects() {
        // Generic
        afkTimeInSeconds = new IntegerConfigObject("afk.time", 300, "How long it takes to go afk in seconds (Default 300)");
        // messages
        enableAfkMessage = new BooleanConfigObject("afk.messages.self.enable", false, "Should we send a message to the player when they go afk?");
        afkMessageStart = new StringConfigObject("afk.messages.self.start",
                "&r&8[&cAFK&8] &7You are now &o&cAFK&r", "Sent to player when they go AFK");
        afkMessageEnd = new StringConfigObject("afk.messages.self.end",
                "&r&8[&aBACK&8] &7You are &a&oNO LONGER AFK&r", "Sent to player when they return from AFK");
        enableAfkBroadcast = new BooleanConfigObject("afk.messages.broadcast.enable", false, "Should we broadcast AFK status changes to others?");
        afkBroadcastStart = new StringConfigObject("afk.messages.broadcast.start",
                "&7* &f{name} &7is now &oAFK", "Broadcast when a player goes AFK");
        afkBroadcastEnd = new StringConfigObject("afk.messages.broadcast.end",
                "&7* &f{name} &7is no longer AFK", "Broadcast when a player comes back from AFK");
        afkKickTime = new IntegerConfigObject("afk.kick.time", 3300, "How many seconds after going afk until a player gets kicked? (Default 3600)"); //1 hour default
        afkKickReason = new StringConfigObject("afk.kick.reason", "&4You have been kicked for being AFK too long!", "The reason given to players who get kicked for being AFK.");
        enableAfkKick = new BooleanConfigObject("afk.kick.enabled", false, "Should we kick players after they've been AFK for too long?");

        //generic
        super.registerConfigObject(afkTimeInSeconds);
        //messages
        super.registerConfigObject(enableAfkMessage);
        super.registerConfigObject(afkMessageStart);
        super.registerConfigObject(afkMessageEnd);
        super.registerConfigObject(enableAfkBroadcast);
        super.registerConfigObject(afkBroadcastStart);
        super.registerConfigObject(afkBroadcastEnd);
        //kick
        super.registerConfigObject(afkKickTime);
        super.registerConfigObject(afkKickReason);
        super.registerConfigObject(enableAfkKick);
    }

    public Integer getAfkTimeInSeconds() {
        return afkTimeInSeconds.getValue();
    }
    public boolean shouldSendAfkMessage() {return enableAfkMessage.getValue();}
    public String getAfkMessageStart() {return afkMessageStart.getValue();}
    public String getAfkMessageEnd() {return afkMessageEnd.getValue();}
    public boolean shouldBroadcastAfkStatusChanges() {return enableAfkBroadcast.getValue();}
    public String getAfkBroadcastStart() {return afkBroadcastStart.getValue();}
    public String getAfkBroadcastEnd() {return afkBroadcastEnd.getValue();}
    public int getAfkKickTime() {return afkKickTime.getValue();}
    public String getAfkKickReason() {return afkKickReason.getValue();}
    public boolean shouldKickPlayersForBeingAfkTooLong() {return enableAfkKick.getValue();}

}
