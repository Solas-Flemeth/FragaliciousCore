package org.brassbrewery.fragaliciousCombat.protection.data;

public enum ProtectionType {
    NONE,
    PAID,
    RESPAWN,
    NEW_PLAYER;

    /**
     * Returns the name of the enum as lowercase. This makes it easier for users to use commands with protection types.
     */
    public String toString() {
        return this.name().toLowerCase();
    }

    /**
     * Converts a string into its corresponding enum type.
     * @param string
     * @return
     */
    public static ProtectionType fromString(String string) {
        return valueOf(string.toUpperCase());
    }
}
