package org.brassbrewery.fragaliciousLib.combat.events;

/**
 * The reason the pvp ended.
 *
 * DEATH - The player died in combat.
 * DISCONNECT - The player disconnected from the server while in combat.
 * TIME - The player had been out of combat for longer than the configured time limit. (See {@link org.brassbrewery.fragaliciousLib.combat.config.CombatMainConfig#getCombatTimer()} )
 * UNKNOWN - Something has forced combat to end without a standard reason.
 */
public enum PvpEndReason{
    DEATH,
    DISCONNECT,
    TIME,
    UNKNOWN;
}
