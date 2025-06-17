package org.brassbrewery.fragaliciousCombat.protection.data;

public enum ProtectionUpkeepType {
    NO_UPKEEP(0),
    TOWNY(1),
    INGAME_DAY(2),
    DAILY(3),
    MANAUL(-1);

    private int value;
    private ProtectionUpkeepType(int value) { this.value = value; }
    private int getValue() { return value; }
    public static ProtectionUpkeepType getProtectionUpkeepType(int value) {
        for (ProtectionUpkeepType type : values()) {
            if(type.getValue() == value)
                return type;
        }
        return NO_UPKEEP;
    }
}
